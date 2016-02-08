
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tallesventura
 */
public class Connection extends Thread {

    private GopherServer server;
    private Socket clientSocket;

    public Connection(GopherServer server, Socket socket) {
        this.server = server;
        this.clientSocket = socket;
    }

    @Override
    public void run() {

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String input, output;
            GopherProtocol protocol = new GopherProtocol(server);
            
            if(protocol != null){
                out.println("Welcome to the OLS gopher server version 1.0\r\n"
                + "Creator: Talles Oliveira de Souza Alves\r\n"
                + "Instructions:\r\n"
                + "Files ending with \"...\" are directories\r\n"
                + "To access a directory you don`t need to type the \"...\"\r\n"
                + "To open a file you must type the whole name of the file, including its extension\r\n"
                + "type the name of the file and press Enter to access it\r\n"
                + "Press Enter with an empty line to show the files in the current directory\r\n"
                + "Type \"..\" to go back to the parent directory\r\n"
                + "Type \"!q\" to end the connection\r\n");
            }

            while ((input = in.readLine()) != null) {
                output = protocol.processInput(input);         
                if (output.equals("bye")){
                    out.println("---------Closing the connection---------\r\n");
                    break;
                }else{
                    out.println("\r\n");
                    out.println(output);
                }
            }
            this.interrupt();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
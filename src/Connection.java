
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
                out.println("Welcome to the OLS gopher server version 1.0\n"
                + "Creator: Talles Oliveira de Souza Alves\n"
                + "Instructions:\n"
                + "Files ending with \"...\" are directories\n"
                + "To access a directory you don`t need to type the \"...\"\n"
                + "type the name of the file and press Enter to access it\n"
                + "Press Enter with an empty line to show the files in the current directory\n"
                + "Type \"..\" to go back to the parent directory\n"
                + "Type \"!q\" to end the connection\n");
            }

            while ((input = in.readLine()) != null) {
                output = protocol.processInput(input);         
                if (output.equals("bye")){
                    out.println("---------Closing the connection---------");
                    break;
                }else{
                    out.println(output);
                }
            }
            this.interrupt();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
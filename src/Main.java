
import java.io.IOException;
import java.net.ServerSocket;
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
public class Main {
    
    public static void main(String[] args) {

        GopherServer server = new GopherServer();
                       
        try {
            
            server.createServerSocket();
                     
            while(true){
                server.acceptConnection();                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GopherServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
}

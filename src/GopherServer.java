
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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

public class GopherServer {
    
    private int port;
    private String host;
    private String root;
    private ServerSocket serverSocket;
    private Set<Connection> connections;

    public GopherServer() {
        try {
            
            ConfigReader cr = new ConfigReader();
            HashMap<String,String> propMap = (HashMap) cr.readConfig();
            this.port = Integer.parseInt(propMap.get("port"));
            this.host = propMap.get("host");
            this.root = propMap.get("root");
            this.connections = new HashSet<Connection>();
            
        } catch (IOException ex) {
            Logger.getLogger(GopherServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createServerSocket() throws IOException{
        this.serverSocket = new ServerSocket(this.port);
    }
    
    public void acceptConnection() throws IOException{
        Socket socket = serverSocket.accept();
        Connection con = new Connection(this,socket);
        connections.add(con);
        con.start();
    }
    
    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getRoot() {
        return root;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setRoot(String root) {
        this.root = root;
    }

}

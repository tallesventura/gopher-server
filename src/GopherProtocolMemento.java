
import java.io.File;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tallesventura
 */
public class GopherProtocolMemento {
    
    private Set<Item> itens;
    private GopherServer server;
    private File currentPath;

    public GopherProtocolMemento(Set<Item> itens, GopherServer server, File currentPath) {
        this.itens = itens;
        this.server = server;
        this.currentPath = currentPath;
    }
    
    public GopherProtocolMemento(){}

    public Set<Item> getItens() {
        return itens;
    }

    public GopherServer getServer() {
        return server;
    }

    public File getCurrentPath() {
        return currentPath;
    }
    
}

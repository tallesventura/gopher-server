
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class GopherProtocol {

    private Set<Item> itens;
    private GopherServer server;
    private File currentPath;
    private GopherProtocolCaretaker caretaker = new GopherProtocolCaretaker();

    public GopherProtocol(GopherServer server) {
        
        itens = new HashSet();
        this.server = server;
        this.currentPath = new File(server.getRoot());
        updateItenSet();
        caretaker.addState(buildMemento());
    }
    
    public void updateItenSet() {

        File[] files = currentPath.listFiles();
        itens.clear();

        for (File f : files) {
            if (f.isFile()) {
                Item i = new Item(0, f.getName(), f.getPath(), server.getHost(), server.getPort());
                itens.add(i);
            } else if (f.isDirectory()) {
                Item i = new Item(1, f.getName(), f.getPath(), server.getHost(), server.getPort());
                itens.add(i);
            }
        }
    }

    public String getDirectoryFiles() {

        StringBuilder builder = new StringBuilder();

        for (Item e : itens) {
            if (e.getType() == 0) {
                builder.append(e.getItemName() + "\r\n");
            } else {
                builder.append(e.getItemName() + "...\r\n");
            }
        }
        return builder.toString();
    }

    public String processInput(String input) {

        StringBuilder builder = new StringBuilder();
        String result = "";

        if (input.equals("")) {
            return getDirectoryFiles();
        } else if (input.equals("..")){
            restoreMemento(caretaker.getLastState());
            return getDirectoryFiles();
        }else if(input.equals("!q")){
            return "bye";
        } else {
            for (Item e : itens) {
                if (e.getItemName().equals(input)) {
                    
                    caretaker.addState(buildMemento());
                    currentPath = new File(e.getSelector());

                    if (e.getType() == 1) {
                        updateItenSet();
                        return getDirectoryFiles();
                    } else if (currentPath.canRead()) {
                        FileReader fr = null;
                        try {

                            String line = null;
                            fr = new FileReader(currentPath);
                            BufferedReader bf = new BufferedReader(fr);

                            while ((line = bf.readLine()) != null) {
                                builder.append(line + "\r\n");
                            }
                            return builder.toString();

                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(GopherProtocol.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                        } catch (IOException ex) {
                            Logger.getLogger(GopherProtocol.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                fr.close();
                            } catch (IOException ex) {
                                Logger.getLogger(GopherProtocol.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    } else {
                        return "The file cannot be read";
                    }
                    break;
                }                
            }
            return "File not found. Check the spelling.";
        }
    }
    
    public void restoreMemento(GopherProtocolMemento memento){
        
        this.currentPath = memento.getCurrentPath();
        this.itens = memento.getItens();
        this.server = memento.getServer();
    }
    
    public GopherProtocolMemento buildMemento(){
        
        GopherProtocolMemento memento;
        Set<Item> itens2 = new HashSet<Item>();
        itens2.addAll(itens);
        File file2 = new File(currentPath.getPath());
        
        return new GopherProtocolMemento(itens2,this.server,file2);
    }

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

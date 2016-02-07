
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tallesventura
 */
public class GopherProtocolCaretaker {
    
    private ArrayList<GopherProtocolMemento> states;

    public GopherProtocolCaretaker() {
        this.states = new ArrayList();
    }
    
    public void addState(GopherProtocolMemento memento){
        this.states.add(memento);
    }
    
    public GopherProtocolMemento getLastState(){
        
        if(states.size() <= 0){
            throw new RuntimeException("There are no saved states");
        }
        
        GopherProtocolMemento memento = states.get(states.size() - 1);
        GopherProtocolMemento result = new GopherProtocolMemento(memento.getItens(),memento.getServer(),memento.getCurrentPath());
        states.remove(states.size() - 1);
        return result;
    }
}

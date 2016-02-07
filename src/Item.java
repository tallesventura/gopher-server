/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tallesventura
 */
public class Item {
    
    private int type;
    private String itemName;
    private String selector;
    private String hostName;
    private int portNumber;

    public Item(int type, String itemName, String selector, String hostName, int portNumber) {
        this.type = type;
        this.itemName = itemName;
        this.selector = selector;
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public int getType() {
        return type;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSelector() {
        return selector;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }
    
    
    @Override
public String toString(){
    return this.type + "\t" + this.itemName + "\t" + this.selector + "\t" + this.hostName + "\t" + this.portNumber;
}

}
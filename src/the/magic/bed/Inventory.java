/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed;

import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author 343079463
 */
public class Inventory {
    
    private String item;
    private boolean hasMagicBed;
    private String destination;
    private String requestedItem;
    private String[] items = new String[10]; 
    public int itemCount = 0; 
    
    public Inventory(String item){
        if (itemCount < items.length) {
            items[itemCount] = item;
            itemCount++;
        } else {
            System.out.println("Inventory is full!");
        }
    }
    
    public String summon (String item){
        for (int i = 0; i < items.length; i++){
            if (items[i].equals(item)){
                return items[i];
            } 
        } 
        return null;
    }
    
    public void useMagicBed (String destination){
        this.destination = destination;
    }
    
    public void acquireItem(String requestedItem){
        if (requestedItem != null && !requestedItem.isEmpty()) {
            itemCount++;
            System.out.println("Successfully acquired: " + requestedItem);
        }
    }
    
}

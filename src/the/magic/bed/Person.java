/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed;

import processing.core.PApplet;

public class Person extends Character { 

    private Item currentItem;
    private int itemCount;
    private String status;
    
    // Primary Constructor
    public Person (PApplet p, int x, int y, String name, String imagePath, Item currentItem){
        super(p, x, y, name, imagePath);
        this.currentItem = currentItem;
        this.status = "happy"; // Automatically set to happy
    }
    
    // Overloaded Constructor
    public Person(PApplet p, int x, int y, String name, String imagePath, int totalitemcount){ 
        super(p, x, y, name, imagePath); 
        this.itemCount = totalitemcount;
        this.currentItem = null; 
        this.status = "happy"; // Automatically set to happy
    }
    
    @Override
    public void displayInfo() {
        // Render base character visuals (draws the image/name using parent logic)
        super.displayInfo(); 
        
        // Text styling using the parent's 'app' variable
        app.fill(255);
        app.textAlign(PApplet.CENTER);
        app.text("Status: " + this.status, this.x + this.width / 2, this.y - 30);
        app.text("Total Items: " + this.itemCount, this.x + this.width / 2, this.y - 15);
        app.textAlign(PApplet.LEFT);
    }
    
    // --- Getters and Setters ---
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    
    public void addItem() {
        itemCount++;
    }

    public void removeItem() {
        if (itemCount > 0) {
            itemCount--;
        }
    }
    
    public Item getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed; // the package is the.magic.bed

import processing.core.PApplet; // import the PApplet
import processing.core.PImage; // import the PImage
/**
 *
 * @author 343079463
 */
public class Person extends Character { // create the class Person which extends Character (inheritance)
    
    private int health; 
    private Inventory inventory;
    private String item;
    
    public Person (PApplet p, int x, int y, String name, String imagePath, int health, Inventory inventory, String item){
        super(p, x, y, name, imagePath);
        this.health = health;
        this.inventory = inventory;
        this.item = item;
    }
    
    public Person(PApplet p, int x, int y, String name, String imagePath){ // create overloaded constructor of person 
        super(p, x, y, name, imagePath); // call on the variables to set them from the parent class
        this.health = 0; 
        this.inventory = inventory;
        this.item = "nothing";
    }
    
    public void talk (PApplet p){
        p.textAlign(PApplet.CENTER, PApplet.CENTER);

        // Set font size and color
        p.textSize(16);
        p.fill(255);

        // Draw background bubble for the text
        p.fill(255); 
        p.stroke(0);
        p.rectMode(PApplet.CENTER);
        p.rect(this.x, this.y - 50, 150, 40, 5);
    }
    
}

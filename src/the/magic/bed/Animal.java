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
public class Animal extends Character {
    
    private String species;
    private int health;
    private String behavior;
    private int facePerspective;

    public Animal (PApplet p, int x, int y, String name, String imagePath, String species, int health, String behaviour, int facePerspective){
        super(p, x, y, name, imagePath);
        this.species = species;
        this.health = health;
        this.behavior = behavior;
        this.facePerspective = facePerspective;
    }
            
    public void makeSound(PApplet p){
        if (species.equals("tiger")){
            p.text("ROARRR", 20, 20);
        } else if (species.equals("ant")){
            p.text("chirp?", 20, 20);
        }
    }
    
    public void changeBehavior (String behavior){
        this.behavior = behavior;
    }
    
    public void dialogue (PApplet p){
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
    
    public void changePerspective (int facePerspective){
        this.facePerspective = facePerspective;
    }

}

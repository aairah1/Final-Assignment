/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed;

import processing.core.PApplet;

public class Animal extends Character {

    private String species;
    public float health; 
    private float maxHealth;

    public Animal(PApplet p, int x, int y, String name, String imagePath, String species, int health) {
        super(p, x, y, name, imagePath);
        this.species = species;
        this.maxHealth = 100; 
        this.health = health;    // Default health is now 50
    }

    @Override
    public void displayInfo() {
        // Render base character visuals (draws the image/name using parent logic)
        super.displayInfo();

        // Text styling using the parent's 'app' variable
        app.fill(255);
        app.textAlign(PApplet.CENTER, PApplet.TOP);
        app.textSize(12);

        // Displays health text directly underneath using the parent's 'app'
        app.text("Health: " + this.health, this.x + this.width / 2, this.y - 30);
    }

    // --- Getters and Setters ---
    public String getSpecies() {
        return species;
    }

    public float getHealth() {
        return health;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
}

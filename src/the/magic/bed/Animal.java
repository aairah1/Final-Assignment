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
import processing.core.PApplet;

public class Animal extends Character {

    private String species;
    private boolean available;

    public Animal(PApplet p,
                  int x,
                  int y,
                  String name,
                  String imagePath,
                  String species) {

        super(p, x, y, name, imagePath);

        this.species = species;
        available = false;
    }

    public void makeSound(PApplet p) {

        if (species.equalsIgnoreCase("Tiger")) {

            p.text("ROARRR!", x, y - 20);

        } else if (species.equalsIgnoreCase("Ant")) {

            p.text("Chirp!", x, y - 20);
        }
    }

    public void talk(PApplet p, String message) {

        p.fill(255);
        p.stroke(0);

        p.rectMode(PApplet.CENTER);
        p.rect(x, y - 50, 200, 50, 5);

        p.fill(0);
        p.textAlign(PApplet.CENTER, PApplet.CENTER);
        p.text(message, x, y - 50);
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getSpecies() {
        return species;
    }
}

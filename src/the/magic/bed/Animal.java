/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed; // add this class to the package

import processing.core.PApplet; // import PApplet

/**
 * Inheritance class of Character, specifies Animals 
 * @author Aairah Ahmeed
 */
public class Animal extends Character { // start the animal class extending character
    // instance variables
    private String species; // string variable for the species of animal
    public float health;  // float variable for the health of the animal
    private float maxHealth; // float variable of the animals maximum health

    /**
     * The constructor for the Animal class. Sets all basic information
     * @param p the PApplet from parent class
     * @param x the x value from parent class
     * @param y the y value from parent class
     * @param name the name of the animal form parent class
     * @param imagePath  the path of the image
     * @param species  the species of the animal
     * @param health the health of the animal
     */
    public Animal(PApplet p, int x, int y, String name, String imagePath, String species, int health) { // start constructor
        super(p, x, y, name, imagePath); // call on parent class method to set the information as not available here
        this.species = species; // set the species to the entered one
        this.maxHealth = 100;  // make the max health automatically 100
        this.health = health;  // Default health is the entered amount per animal
    } // end constructor

    @Override // show this instead if character is an animal insteda of parent class one
    /**
     * Display the info of each animal
     */
    public void displayInfo() { // start displaying the information
        super.displayInfo(); // display the infromation from the parent class method
        app.fill(255); // make the color white
        app.textAlign(PApplet.CENTER, PApplet.TOP); // make the text align top center
        app.textSize(12); // make the font size 12
        app.text("Health: " + this.health, this.x + this.width / 2, this.y - 30); // make the text int eh certain location
    } // end the method

    // --------------Getters and Setters---------------
    /**
     * getter method for the species
     * @return species the species of the animal
     */
    public String getSpecies() { // start the getter method for the species
        return species; // return the species
    } // close method

    /**
     * Getter method for the health of the animal
     * @return health the health fo the animal
     */
    public float getHealth() { // start the getter method for the health
        return health; // return the health
    } // end method
    
    /**
     * Setter method for the animals health
     * @param health the health of the animal
     */
    public void setHealth(int health){ // start the setter method for the health of the animal
        this.health = health; // set the animals health to the entered one
    } // end the method
} // end class

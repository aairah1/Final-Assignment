/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed; // add this class to the package

import processing.core.PApplet; // import PApplet

/**
 * Inheritance class of Character, specifies characters into persons
 *
 * @author Aairah Ahmeed
 */
public class Person extends Character { // start the person class extending character
    // instance variables
    private Item currentItem; // item currently held by the player
    private int itemCount; // total number of items collected by player
    private String status; // current status of the player (happy, etc)

    /**
     * Primary Constructor for Person
     * @param p the PApplet from parent class
     * @param x the x position from parent class
     * @param y the y position from parent class
     * @param name the name of the character
     * @param imagePath the image file path
     * @param currentItem the item currently held by the player
     */
    public Person(PApplet p, int x, int y, String name, String imagePath, Item currentItem) { // start person constructor
        super(p, x, y, name, imagePath); // call parent constructor
        this.currentItem = currentItem; // set current item
        this.status = "happy"; // default status is happy
    } // end constructor

    /**
     * Overloaded Constructor for Person without current item
     * @param p the PApplet from parent class
     * @param x the x position from parent class
     * @param y the y position from parent class
     * @param name the name of the character
     * @param imagePath the image file path
     * @param totalitemcount starting item count
     */
    public Person(PApplet p, int x, int y, String name, String imagePath, int totalitemcount) { // start overloaded constructor
        super(p, x, y, name, imagePath); // call parent constructor
        this.itemCount = totalitemcount; // set initial item count
        this.currentItem = null; // no item equipped at start
        this.status = "happy"; // default status is happy
    } // end constructor

    @Override // override parent displayInfo method
    /**
     * Display the info of the player character
     */
    public void displayInfo() { // start displayInfo method
        super.displayInfo(); // display base character info from parent class
        // text styling using Processing app reference from Character class
        app.fill(255); // set text colour to white
        app.textAlign(PApplet.CENTER); // center text above character
        app.text("Status: " + this.status, this.x + this.width / 2, this.y - 30); // show player status
        app.text("Total Items: " + this.itemCount, this.x + this.width / 2, this.y - 15); // show item count
        app.textAlign(PApplet.LEFT); // reset alignment to the left
    } // end displayInfo method

    // --------------------Getters and Setters---------------
    /**
     * Getter method for the amount of items
     * @return itemCount the amount of items
     */
    public int getItemCount() { // start getter for itemCount
        return itemCount; // return item count
    } // end getter

    /**
     * Setter method for the amount of items
     * @param itemCount the amount of items
     */
    public void setItemCount(int itemCount) { // start setter
        this.itemCount = itemCount; // set item count
    } // end setter

    /**
     * Method to add items to the item count
     */
    public void addItem() { // start addItem method
        itemCount++; // increase item count by 1
    } // end method

    /**
     * Method to remove items from the item count
     */
    public void removeItem() { // start removeItem method
        if (itemCount > 0) { // check if items exist (person has any)
            itemCount--; // decrease item count by 1
        } // end if
    } // end method

    /**
     * Getter method for the status of the person
     * @return status the status they are in
     */
    public String getStatus() { // start getter for the persons status
        return this.status; // return status
    } // end getter

    /**
     * Setter method for the status
     * @param status the status of the person
     */
    public void setStatus(String status) { // start setter for the persons status
        this.status = status; // set status
    } // end setter
} // end class

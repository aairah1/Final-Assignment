/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed; // package declaration

import processing.core.PApplet; // import Processing core class
import processing.core.PImage; // import Processing image class

/**
 * Association class for the items
 *
 * @author Aairah Ahmeed
 */
public class Item { // start Item class

    // Static counter to keep track of total items created/added
    public static int totalItemCount = 0;  // tracks total item count

    // Instance variables
    public int rx, ry; // item x and y position
    private PApplet app; // reference to Processing sketch
    private String name; // item name
    private PImage itemImage; // item image
    public int width, height; // image width and height

    /**
     * Constructor for the Item. Sets all the basic information about the items
     * @param app the app
     * @param rx the x value
     * @param ry the y value
     * @param name the name
     * @param imagePath the image
     */
    public Item(PApplet app, int rx, int ry, String name, String imagePath) { // constructor with parameters
        this.app = app; // set Processing reference
        this.rx = rx; // set x position
        this.ry = ry; // set y position
        this.name = name; // set item name
        this.itemImage = app.loadImage(imagePath); // load image
        this.width = itemImage.width; // set width from image
        this.height = itemImage.height; // set height from image
    } // end constructor

    /**
     * Move item to a certain location
     */
    public void move(int dx, int dy) { // start move method with two parameters
        rx += dx; // move x by the amount stated
        ry += dy; // move y by the amount stated
    } // end move method

    /**
     * Use item and follow a special command if it is the magic bed
     */
    public void accessItem() { // start accessItem method
        if (this.name != null && this.name.equalsIgnoreCase("Magic Bed")) { // check if magic bed and not null. If it is
            MySketch.stage += 1; // advance stage by incrementing the static int 
        } // end if
    } // end method

    /**
     * Draw item to be use din the sketch class
     */
    public void draw() { // start draw method
        app.image(itemImage, rx, ry); // draw image with location
    } // end draw method

    //-----------------Getters and Setters---------------
    public String getName() { // start getName
        return this.name; // return name
    } // end getName

    /**
     * Setter method for the items name
     * @param name 
     */
    public void setName(String name) { // start setName method for the item
        this.name = name; // set name to new name
    } // end setName

    /**
     * Getter method for the item image
     * @return the image
     */
    public PImage getItemImage() { // start getItemImage method with no param
        return this.itemImage; // return image of the item
    } // end getItemImage

    /**
     * Sets the image of the item
     * @param itemImage  PImage of the item
     */
    public void setItemImage(PImage itemImage) { // start setItemImage method with one parameter
        this.itemImage = itemImage; // set image to new image
    } // end setItemImage

    /** 
     * Static getter/setter for total item count
     * @return totalItemCount the total count of the items
     */
    public static int getTotalItemCount() { // start getter for the total item count
        return totalItemCount; // return total item count 
    } // end getter

    /**
     * The setter method for the total item count
     * @param count 
     */
    public static void setTotalItemCount(int count) { // start setter for the total item count
        totalItemCount = count; // set count to new count
    } // end setter

    /**
     * Collision detection between items
     * @param other Item that the other item is colliding with
     * @return the x and y information
     */
    public boolean isCollidingWith(Item other) { // start collision check
        boolean isLeftOfOtherRight = rx < other.rx + other.width; // left check
        boolean isRightOfOtherLeft = rx + width > other.rx; // right check
        boolean isAboveOtherBottom = ry < other.ry + other.height; // top check
        boolean isBelowOtherTop = ry + height > other.ry; // bottom check
        return isLeftOfOtherRight && isRightOfOtherLeft // x overlap check
                && isAboveOtherBottom && isBelowOtherTop; // y overlap check
    } // end collision method
} // end Item class

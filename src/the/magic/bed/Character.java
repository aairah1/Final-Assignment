/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed; // add this class to the package

import processing.core.PApplet; // import Processing core class
import processing.core.PImage; // import Processing image class

/**
 * Base Character class used for all game entities (player, NPCs, enemies,
 * animals) Handles movement, drawing, collision detection, and basic
 * interaction logic
 *
 * This is the parent class that other classes like Person and Animal inherit
 * from
 *
 * @author 343079463
 */
public class Character { // start Character class

    // instance variables
    public int x, y; // x and y position of character on screen
    String name; // name of character (used for display and identification)
    protected PApplet app; // reference to Processing sketch (used for drawing and images)
    private PImage image; // current image used to represent the character
    protected int width, height; // dimensions of the character image (used for collision + positioning)
    int health; // basic health variable (not heavily used in this class but available for extension)

    /**
     * Constructor for Character Initializes position, name, and loads the
     * character image
     * @param p Processing PApplet reference (main sketch)
     * @param x starting x position
     * @param y starting y position
     * @param name name of the character
     * @param imagePath file path for the character image
     */
    public Character(PApplet p, int x, int y, String name, String imagePath) { // start constructor
        this.app = p; // store Processing reference
        this.x = x; // set x position
        this.y = y; // set y position
        this.name = name; // set character name
        this.image = app.loadImage(imagePath); // load image from file path
        this.width = image.width; // store image width for collision detection
        this.height = image.height; // store image height for collision detection
    } // end constructor

    /**
     * Moves the character by a given amount (relative movement)
     */
    public void move(int dx, int dy) { // start move method
        x += dx; // add dx to x position
        y += dy; // add dy to y position
    } // end move method

    /**
     * Moves the character directly to a specific position (absolute movement)
     */
    public void moveTo(int dx, int dy) { // start moveTo method
        x = dx; // set x position directly
        y = dy; // set y position directly
    } // end moveTo method

    /**
     * Draws the character image at its current position
     */
    public void draw() { // start draw method
        app.image(image, x, y); // render character image at (x, y)
    } // end draw method

    /**
     * Changes the character's appearance by loading a new image
     */
    public void changePerspective(String imagePath) { // start changePerspective method
        this.image = app.loadImage(imagePath); // update character image
    } // end changePerspective method

    /**
     * Displays basic character information (name label above character)
     */
    public void displayInfo() { // start displayInfo method
        app.fill(255); // set text colour to white
        app.textSize(12); // set text size
        app.textAlign(PApplet.CENTER); // center text alignment
        app.text(name, this.x + this.width / 2, this.y - 45); // draw name above character
    } // end displayInfo method

    /**
     * Collision detection between two characters using bounding boxes
     */
    public boolean isCollidingWith(Character other) { // start character collision check

        boolean isLeftOfOtherRight = x < other.x + other.width; // left edge check
        boolean isRightOfOtherLeft = x + width > other.x; // right edge check
        boolean isAboveOtherBottom = y < other.y + other.height; // top edge check
        boolean isBelowOtherTop = y + height > other.y; // bottom edge check

        return isLeftOfOtherRight && isRightOfOtherLeft // check horizontal overlap
                && isAboveOtherBottom && isBelowOtherTop; // check vertical overlap
    } // end character collision method

    /**
     * Collision detection between Character and Item
     */
    public boolean isCollidingWith(Item other) { // start item collision check

        boolean isLeftOfOtherRight = x < other.rx + other.width; // left edge check
        boolean isRightOfOtherLeft = x + width > other.rx; // right edge check
        boolean isAboveOtherBottom = y < other.ry + other.height; // top edge check
        boolean isBelowOtherTop = y + height > other.ry; // bottom edge check

        return isLeftOfOtherRight && isRightOfOtherLeft // horizontal overlap check
                && isAboveOtherBottom && isBelowOtherTop; // vertical overlap check
    } // end item collision method

    /**
     * Checks if mouse is hovering over this character
     */
    public boolean isHovered(int mouseX, int mouseY) { // start hover check
        return mouseX >= x // mouse inside left boundary
                && mouseX <= x + width // mouse inside right boundary
                && mouseY >= y // mouse inside top boundary
                && mouseY <= y + height; // mouse inside bottom boundary
    } // end isHovered method
} // end Character class

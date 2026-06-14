/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed;

import processing.core.PApplet;
import processing.core.PImage;

public class Item {
    
    // Static counter to keep track of total items created/added
    public static int totalItemCount = 0; 
    
    // Instance variables
    public int rx, ry;
    private PApplet app;
    private String name;
    private PImage itemImage;
    public int width, height;
    
    /**
     * Constructor for the Item class
     * @param app The PApplet instance (needed to load images in Processing)
     * @param name The name of the item
     * @param imagePath The file path to the image (e.g., "bed.png")
     */
    public Item(PApplet app, int rx, int ry, String name, String imagePath) {
        this.app = app;
        this.rx = rx;
        this.ry = ry;      
        this.name = name;
        this.itemImage = app.loadImage(imagePath);
        this.width = itemImage.width;
        this.height = itemImage.height;
    }
    
    public void move(int dx, int dy){
        rx += dx;
        ry += dy;
    }
    
    
    /**
     * Simulates accessing or using the item.
     * If the item is the "Magic Bed", it advances the game stage.
     */
    public void accessItem() {
        if (this.name != null && this.name.equalsIgnoreCase("Magic Bed")) {
            MySketch.stage += 1; 
        }
    }
    
    public void draw(){
        app.image(itemImage, rx, ry);
    }
    
    // --- Getters and Setters ---

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PImage getItemImage() {
        return this.itemImage;
    }

    public void setItemImage(PImage itemImage) {
        this.itemImage = itemImage;
    }

    // Static getter/setter for the global item count
    public static int getTotalItemCount() {
        return totalItemCount;
    }

    public static void setTotalItemCount(int count) {
        totalItemCount = count;
    }
    
    public boolean isCollidingWith(Item other) {
        // Check if the bounding boxes of the two items intersect
        boolean isLeftOfOtherRight = rx < other.rx + other.width;
        boolean isRightOfOtherLeft = rx + width > other.rx;
        boolean isAboveOtherBottom = ry < other.ry + other.height;
        boolean isBelowOtherTop = ry + height > other.ry;

        return isLeftOfOtherRight && isRightOfOtherLeft
                && isAboveOtherBottom && isBelowOtherTop;
    }
}
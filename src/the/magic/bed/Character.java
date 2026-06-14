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
public class Character {
    
    public int x, y;
    String name;
    protected PApplet app;
    private PImage image;
    protected int width, height;
    
    public Character (PApplet p, int x, int y, String name, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    
    public void moveTo (int dx, int dy){
        x = dx;
        y = dy;
    }
    
    public void draw(){
        app.image(image, x, y);
    }
    
    public void changePerspective (String imagePath){
        this.image = app.loadImage(imagePath);
    }

    public void displayInfo(){
        app.fill(255); // White text
        app.textSize(12);
        app.textAlign(PApplet.CENTER);
        app.text(name, this.x + this.width / 2, this.y - 45);
    }
    
    public boolean isCollidingWith(Character other) {
        // Check if the bounding boxes of the two persons intersect
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;

        return isLeftOfOtherRight && isRightOfOtherLeft 
          && isAboveOtherBottom && isBelowOtherTop;
    }
    
    public boolean isCollidingWith(Item other) {
        // Check if the bounding boxes of the two items intersect
        boolean isLeftOfOtherRight = x < other.rx + other.width;
        boolean isRightOfOtherLeft = x + width > other.rx;
        boolean isAboveOtherBottom = y < other.ry + other.height;
        boolean isBelowOtherTop = y + height > other.ry;

        return isLeftOfOtherRight && isRightOfOtherLeft
        && isAboveOtherBottom && isBelowOtherTop;
    }

    void addItem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void removeItem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x
                && mouseX <= x + width
                && mouseY >= y
                && mouseY <= y + height;
    }

    void setItemCount(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}

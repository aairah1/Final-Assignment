/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed;

/**
 *
 * @author 343079463
 */
import processing.core.PApplet;
import processing.core.PImage;

public class MySketch extends PApplet {
    private Person person1;
    String userInput = "";
    int stage = 0;
    PImage bg1Image, bg2Image, bg3Image; // background images
    PImage dialog1; // dialogues
    
    // the main characters facial perspectives
    String pfp1 = "images/1.png";
    String pfp2 = "images/2.png";
    String pfp3 = "images/3.png";
    String pfp4 = "images/4.png";
    
    // object's
    private Person mango1;
    private Character antKing;
    
    public void settings(){
	   //sets the size of the window
        size (1000,700);
    }
    
    public void setup(){
	   //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
           // sets the text information, resizes the background images and loads them in
        background(255, 255, 255);
        textSize(20);
        
        //backgrounds
        bg1Image = loadImage("images/intobg.png");
        bg1Image.resize(1000, 700);
        bg2Image = loadImage("images/bgimage1.png");
        bg2Image.resize(1000, 700);
        bg3Image = loadImage("images/bgimage2.png");
        bg3Image.resize(1000, 700);
        
        // characters
        person1 = new Person (this, 450, 500, "Prince", pfp1);
        antKing = new Character (this, 600, 300, "Ant King", "images/antking.png");
        
        //objects
        mango1 = new Person (this, 650, 330, "Mango", "images/mango.png");
        
        //dialogues
        dialog1 = loadImage("images/dialog1.png");
    }
    
    
    public void draw() {
        background(255, 255, 255); 

        if (stage == 0) {
            background(bg1Image); 
            textSize(20);
            text("Welcome!", 450, 350);
            textSize(15);
            text("Enter name: ", 450, 365);
            text(userInput, 450, 380);
        } else if (stage == 1) {
            background(bg2Image); 
            mango1.draw();
            person1.draw();
        } else if (stage == 2){
            background(bg3Image);
            fill(255, 255, 255);
            this.text("AAAHH PLEASE DONT EAT ME", antKing.x, antKing.y - 45);
            antKing.draw();
        } else if (stage == 3){
            background(bg2Image); 
            mango1.draw();
            person1.draw();
            antKing.draw();
        }
        
        if (keyPressed) {
            if (keyCode == LEFT) {
                person1.changePerspective(pfp2);
                person1.move(-5, 0);
            } else if (keyCode == RIGHT) {
                person1.changePerspective(pfp3);
                person1.move(5, 0);
            } else if (keyCode == UP) {
                person1.changePerspective(pfp4);
                person1.move(0, -5);
            } else if (keyCode == DOWN) {
                person1.changePerspective(pfp1);
                person1.move(0, 5);
            }
        }


        if (stage == 1 && person1.isCollidingWith(mango1)) {
            stage = 2;
        }
        
        if (person1.isCollidingWith(antKing) && stage == 3){
            image(dialog1, 200, 500);
        }
    }
    
    public void keyPressed(){
        if (stage == 0){
            if (keyCode == ENTER) {
                stage = 1;
            } else if (key != CODED){
            userInput += key;
            }
        }
    }
    
    public void mousePressed(){
        if (stage == 2 && mouseX >= antKing.x && mouseX <= antKing.x + 100 && 
            mouseY >= antKing.y && mouseY <= antKing.y + 100) {
            stage = 3;
            
            person1.x = 750; 
            person1.y = 330;
            
            antKing.x = 570;
            antKing.y = 330;
        }
    }
}

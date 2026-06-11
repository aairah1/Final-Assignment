/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed;

/**
 *
 * @author 343079463
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import processing.core.PApplet;
import processing.core.PImage;

public class MySketch extends PApplet {
    private Person person1;
    String userInput = "";
    double stage = 0;
    PImage bg1Image, bg2Image, bg3Image, bg4Image, bg5Image; // background images
    PImage dialog1, dialog2, dialog3, dialog4, dialog5; // dialogues
    
    // the main characters facial perspectives
    String pfp1 = "images/1.png";
    String pfp2 = "images/2.png";
    String pfp3 = "images/3.png";
    String pfp4 = "images/4.png";
    
    // the tigers facial 
    String tfp = "images/1t.png";
    
    // object's
    private Person mango1;
    private Character antKing, tiger;
    
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
        bg4Image = loadImage("images/bgImage3.png");
        bg4Image.resize(1000, 700);
        bg5Image = loadImage("images/bgimage4.png");
        bg5Image.resize(1000, 700);
        
        // characters
        person1 = new Person (this, 450, 500, "Prince", pfp1);
        antKing = new Character (this, 600, 300, "Ant King", "images/antking.png");
        tiger = new Character (this, 580, 400, "Tiger", "images/tiger1.png");
        
        //objects
        mango1 = new Person (this, 650, 330, "Mango", "images/mango.png");
        
        //dialogues
        dialog1 = loadImage("images/dialog1.png");
        dialog2 = loadImage("images/dialog2.png");
        dialog3 = loadImage("images/dialog3.png");
        dialog4 = loadImage("images/dialog4.png");
        dialog5 = loadImage("images/dialog5.png");
    }
    
    
    public void draw() {
        background(255, 255, 255); 

        if (stage == 0) { // if we are on stage 0 (intro)
            background(bg1Image); // set the background
            textSize(20); // make the text size 20
            text("Welcome!", 450, 350); // create a welcome message
            textSize(15); // set the text size as 15
            text("Enter name: ", 450, 365); // create a space for the user to enter their name
            text(userInput, 450, 380); // gett eh user input
        } else if (stage == 1) { // if we are on stage 1
            background(bg2Image);  // set the background
            mango1.draw(); // draw the mango
            person1.draw(); // draw the price
        } else if (stage == 2){ // if we are on stage 2
            background(bg3Image); // set the background
            fill(255, 255, 255); // set the text color to white
            this.text("AAAHH PLEASE DONT EAT ME", antKing.x, antKing.y - 45); // set the text for the ant king above him
            antKing.draw(); // draw the ant king
        } else if (stage == 3){ // if we are on stage 3
            background(bg2Image);  // set the background 
            mango1.draw(); // draw the mango
            antKing.draw(); // draw the ant king
            person1.draw(); // draw the prince
        } else if (stage == 3.1 || stage == 3.2 || stage == 3.4 || stage == 3.5){ // if we are on stage 3.1, 3.2,3.4, or 3.5
            background(bg2Image); // set the background
            mango1.draw(); // draw the mango
            antKing.draw(); // draw the ant king
            person1.draw(); // draw the prince
        } else if (stage == 3.3){
            background(bg4Image);
        } else if (stage == 4){
            background(bg5Image);
            person1.draw();
        } else if (stage == 5){
            background(bg5Image);
            tiger.draw();
            person1.draw();
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

        //Collision detection and Stage changes
        // dialogue changes as well
        if (stage == 1 && person1.isCollidingWith(mango1)) {
            stage = 2;
        }
        
        if (person1.isCollidingWith(antKing) && stage == 3){
            image(dialog1, 200, 500);
        }
        
        if (stage == 3.1 && person1.isCollidingWith(antKing)){
            image(dialog2, 200, 500);
        }
        
        if (stage == 3.2 && person1.isCollidingWith(antKing)){
            image(dialog3, 200, 500);
        }
        
        if (stage == 3.4 && person1.isCollidingWith(antKing)){
            image(dialog4, 200, 500);
        }
        
        if (stage == 3.5 && person1.isCollidingWith(antKing)){
            image(dialog5, 200, 500);
        }
        
        // location check
        if (person1.y >= height - 50) { // 'height' is 700. 'height - 50' gives a small buffer zone
            stage = 4;
            person1.y = 500;
            person1.x = 100;
        }
        
        if (stage == 4){
            person1.changePerspective(pfp3);
            person1.move(3, 0);
            if (person1.x >= width - 5){
                stage = 5;
                person1.x = 0;
                person1.y = 450;
                person1.move(3, 0);
                if (person1.x >= 100){
                    person1.y = 450;
                    person1.x = 100; 
                }
            }
        }
        
        if (stage == 5 && person1.isCollidingWith(tiger)){
            tiger.changePerspective(tfp);
        } else {
            tiger.changePerspective("images/tiger1.png");
        }
    }
    
    public void keyPressed() {
        if (stage == 0) {
            if (key == ENTER || key == RETURN) {
                // 1. Grab the username immediately before clearing or changing stages
                String username = userInput.trim(); 

                // Only save if the user actually typed something
                if (username.length() > 0) {
                    try {
                        // 2. Write to the file (appends cleanly with a .txt extension)
                        FileWriter w = new FileWriter("usernames.txt", true);
                        PrintWriter fileOutput = new PrintWriter(w);
                        fileOutput.println(username);
                        fileOutput.close();
                        System.out.println("Saved username: " + username);

                    } catch (IOException e) {
                        System.out.println("Error saving file: " + e.getMessage());
                    }
                }

                // 3. Move to the next stage safely AFTER saving is completed
                stage = 1;

            } else if (key == BACKSPACE) {
                // Fixes the backspace glitch so users can fix typos
                if (userInput.length() > 0) {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
            } else if (key != CODED) {
                // Adds the typed letter, ignoring system keys like Shift/Ctrl/Enter
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

        if (stage == 3 && mouseX >= 200 && mouseX <= 200 + dialog1.width && mouseY >= 500 && mouseY <= 500 + dialog1.height){
            stage = 3.1;
        }        
        else if (stage == 3.1 && mouseX >= 200 && mouseX <= 200 + dialog2.width && mouseY >= 500 && mouseY <= 500 + dialog2.height){
            stage = 3.2;
        }        
        else if (stage == 3.2 && mouseX >= 200 && mouseX <= 200 + dialog3.width && mouseY >= 500 && mouseY <= 500 + dialog3.height){ // Fixed typo here (dialog3)
            stage = 3.3;
        }        
        else if (stage == 3.3 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height){ // Fixed background click area here
            stage = 3.4;
        }
        else if (stage == 3.4 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height){ // Fixed background click area here
            stage = 3.5;
        }
    }
}

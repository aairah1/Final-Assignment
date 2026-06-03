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

public class MySketch extends PApplet {
    private Person person1;
    String userInput = "";
    int stage = 0;
    
    public void settings(){
	   //sets the size of the window
        size (400,400);
    }
    
    public void setup(){
	   //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
        background(255, 255, 255);
        textSize(20);
        person1 = new Person (this, 100, 100, "Aairah", "images/princetryout.png");
    }
    
    public void draw(){
        background (255, 255, 255);
        if (stage == 0){
            fill(0,0,0);
            text("Welcome!", 20, 150);
            text("Enter text: ", 20, 50);
            text(userInput, 20, 100);
        } else if (stage == 1){
            person1.draw();
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
}

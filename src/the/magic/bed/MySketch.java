/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed;

/**
 *
 * @author 343079463
 */
//-----------------------------------IMPORTS---------------------------------
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.*;

public class MySketch extends PApplet {
    //-----------------------------------VARIABLES---------------------------------
    SoundFile backgroundMusic;
    private Character person1, faqir, rajah, princess;
    
    String userInput = "";
    private String currentUsername = "unnamed user";
    private int finalTime = 0;
    private String[][] leaderboard = new String[100][2];
    private int leaderboardCount = 0;
    
    public static double stage = 14;
    double nextStage = 0;
    int playerhealth = 100;
    PImage bg1Image, bg2Image, bg3Image, bg4Image, bg5Image, bg6Image, bg7Image, bg8Image, bg9Image, bg10Image, bg11Image, bg12Image, bg13Image, bg14Image; // background images
    PImage dialog1, dialog2, dialog3, dialog4, dialog5, dialog6, dialog7, dialog8, dialog9, dialog10, dialog11, dialog12, dialog13; // dialogues
    PImage steamy, pow;
    
    // the main characters facial perspectives
    String pfp1 = "images/1.png";
    String pfp2 = "images/2.png";
    String pfp3 = "images/3.png";
    String pfp4 = "images/4.png";
    
    // the tigers facial 
    String tfp = "images/1t.png";
    
    // object's
    private Item mango1, splinter, magicbed, wheat, oil, evilmagic, lightball;
    
    // Animals
    private Animal antKing, tiger, antarmy, demon;
    
    // Fade variables
    private boolean isFadingOut = false;
    private boolean isFadingIn = false;
    private float fadeAlpha = 0;
    private Button startButton, Task1, helpButton, nextButton, returnBut, finButton;
    
    // Stage specific variables
    private boolean showEndingLoading = false;
    private boolean showUsernameScreen = false;
    private boolean isLoading = true;
    private float loadingProgress = 0;
    private boolean showPickupPrompt = false;
    private boolean showExitPrompt = false;
    private boolean showNightOverlay = false;

    private float wheatHealth = 1.0f; // 100%
    private boolean summonAnts = false;
    private boolean wheatTurnedToOil = false;
    boolean attackActive = false;
    
    private int startTime;
    
    // physics variables
    float velocityY = 0;
    float gravity = 0.7f;
    int jumpCount = 0;
    
    //-----------------------------------SETTINGS (UNCHANGED) ---------------------------------
    public void settings(){
	   //sets the size of the window
        size (1000,700);
    }
    
    //-----------------------------------SETUP---------------------------------
    public void setup(){
	   //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
           // sets the text information, resizes the background images and loads them in
        background(255, 255, 255);
        textSize(20);
        backgroundMusic = new SoundFile(this, "audio/bgmusic.wav");
        backgroundMusic.loop();
        backgroundMusic.amp(0.5f);
        
        startTime = millis();
        
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
        bg6Image = loadImage("images/bgimage5.png");
        bg6Image.resize(1000, 700);
        bg7Image = loadImage("images/bgimage6.png");
        bg7Image.resize(1000, 700);
        bg8Image = loadImage("images/bgimage7.png");
        bg8Image.resize(1000, 700);
        bg9Image = loadImage("images/bgimage8.png");
        bg9Image.resize(1000, 700);
        bg10Image = loadImage("images/bgimage9.png");
        bg10Image.resize(1000, 700);
        bg11Image = loadImage("images/bgimage10.png");
        bg11Image.resize(1000, 700);
        bg12Image = loadImage("images/bgimage11.png");
        bg12Image.resize(1000, 700);
        bg13Image = loadImage("images/bgimage12.png");
        bg13Image.resize(1000, 700);
        bg14Image = loadImage("images/bgimage13.png");
        bg14Image.resize(1000, 700);
        
        // characters
        person1 = new Person (this, 450, 500, "Prince", pfp1, 0);
        faqir = new Person (this, 100, 350, "Faqir", "images/faqir.png", 1);
        rajah = new Person (this, 415, 300, "Rajah", "images/rajah.png", 10000);
        princess = new Person (this, 545, 300, "Princess Lalun", "images/princess.png", 10000);
        
        // animals
        antKing = new Animal (this, 600, 300, "Ant King", "images/antking.png", "Ant", 100);
        tiger = new Animal (this, 580, 400, "Tiger", "images/tiger1.png", "Tiger", 50);
        antarmy = new Animal (this, -100, 300, "Ant Army","images/armyants.png","Ants", 100);
        demon = new Animal (this, 100, 300, "Demon", "images/demon.png", "Demon", 100);
        
        //objects
        mango1 = new Item (this, 650, 330, "Mango", "images/mango.png");
        splinter = new Item (this, 325, 350, "Splinter", "images/splinter.png");
        magicbed = new Item (this, 650, 330, "Magic Bed", "images/magicbed.png");
        wheat = new Item (this, 200, 300, "Wheat", "images/wheat.png");
        oil = new Item (this, 200, 300, "Oil", "images/oil.png");
        evilmagic = new Item (this, 200, 300, "Evil Magic", "images/evilmagic.png");
        lightball = new Item (this, 300, 300, "Good Magic", "images/lightball.png");
        steamy = loadImage("images/steamvapor.png");
        pow = loadImage("images/pow.png");
        
        //dialogues
        dialog1 = loadImage("images/dialog1.png");
        dialog2 = loadImage("images/dialog2.png");
        dialog3 = loadImage("images/dialog3.png");
        dialog4 = loadImage("images/dialog4.png");
        dialog5 = loadImage("images/dialog5.png");
        dialog6 = loadImage("images/dialog6.png");
        dialog7 = loadImage("images/dialog7.png");
        dialog8 = loadImage("images/dialog8.png");
        dialog9 = loadImage("images/dialog9.png");
        dialog10 = loadImage("images/dialog10.png");
        dialog11 = loadImage("images/dialog11.png");
        dialog12 = loadImage("images/dialog12.png");
        dialog13 = loadImage("images/dialog13.png");
        
        // Initialize start button
        startButton = new Button(this, 460, 350, 80, 35, "Start");
        isLoading = true;
        showUsernameScreen = false;
        Task1 = new Button(this, 450, 650, 80, 38, "Start Tasks");
        helpButton = new Button(this, 450, 100, 100, 40, "Get Help");
        nextButton = new Button (this, 450, 600, 100, 40, "NEXT TASK");
        returnBut = new Button (this, 450, 600, 100, 40, "RETURN");
        finButton = new Button (this, 450, 150, 100, 40, "FINISH");
    }
    
    //-----------------------------------DRAW---------------------------------
    public void draw() {
        background(255, 255, 255);
    
        //-----------------------------------LOADING SCREEN---------------------------------
        // Loading screen
        if (stage == 0) {
            background(bg1Image);
            loadingProgress += 0.01;
            drawLoadingScreen();
            if (loadingProgress >= 1) {
                showUsernameScreen = true;
                stage = -1;
            }
            return;
        }
        
        //-----------------------------------USERNAME INTRO SCREEN---------------------------------
        // Username screen
        if (showUsernameScreen) {
            background(bg1Image);
            drawUsernameScreen();
            return;
        }
        
        //-----------------------------------FADING TRANSITION---------------------------------
        // Handle fade transitions
        handleFadeTransition();

        //-----------------------------------STORY START---------------------------------
        if (stage == 1) { // if we are on stage 1
            background(bg2Image);  // set the background
            mango1.draw(); // draw the mango
            person1.draw(); // draw the prince
            checkHover(person1);
            if (showPickupPrompt) {
                fill(0, 0, 0, 150);
                rect(0, 0, width, height);
                fill(255);
                textAlign(CENTER);
                textSize(24);
                text("Pick up the mango", 500, 100);
                textAlign(LEFT);
            }
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
            checkHover(person1);
            checkHover(antKing);
        } else if (stage == 3.1 || stage == 3.2 || stage == 3.4){ // if we are on stage 3.1, 3.2, or 3.4
            background(bg2Image); // set the background
            mango1.draw(); // draw the mango
            antKing.draw(); // draw the ant king
            person1.draw(); // draw the prince
            checkHover(person1);
            checkHover(antKing);
        } else if (stage == 3.5){ // if we are on stage 3.5
            background(bg2Image); // set the background
            mango1.draw(); // draw the mango
            antKing.draw(); // draw the ant king
            person1.draw(); // draw the prince
            checkHover(person1);
            checkHover(antKing);
            if (showExitPrompt) {
                fill(0, 0, 0, 150);
                rect(0, 0, width, height);
                fill(255);
                textAlign(CENTER);
                textSize(24);
                text("Exit the garden", 500, 100);
                textAlign(LEFT);
            }
        } else if (stage == 3.3){
            background(bg4Image);
        } else if (stage == 4){
            background(bg5Image);
            person1.draw();
            showNightOverlay = true;
        } else if (stage == 5){
            background(bg5Image);
            tiger.draw();
            person1.draw();
            showNightOverlay = false;
            checkHover(person1);
            checkHover(tiger);
        } else if (stage == 5.1 || stage == 5.3 || stage == 5.4){
            background(bg5Image);
            tiger.draw();
            person1.draw();
            checkHover(person1);
            checkHover(tiger);
        } else if (stage == 5.2){
            background(bg6Image);
            splinter.draw();
            fill(255);
            textAlign(CENTER);
            textSize(24);
            text("Press on the DOWN key to pull out the splinter", 500, 100);
        } else if (stage == 5.5){
            background(bg5Image);
            tiger.draw();
            person1.draw();
            fill(255);
            textAlign(CENTER);
            textSize(24);
            text("Press on the LEFT key to exit the jungle", 500, 100);
            checkHover(person1);
            checkHover(tiger);
        } else if (stage == 6 || stage == 6.1){
            background(bg7Image);
            faqir.draw();
            person1.draw();
            checkHover(person1);
            checkHover(faqir);
        } else if (stage == 6.2){
            background(bg7Image);
            faqir.draw();
            magicbed.draw();
            person1.draw();
            fill(255);
            textAlign(CENTER);
            textSize(24);
            text("Tap the bed to store to inventory", 500, 100);
            checkHover(person1);
            checkHover(faqir);
        } else if (stage == 7) {
            background(bg8Image);
            loadingProgress += 0.01;
            drawLoadingScreen();
            if (loadingProgress >= 1) {
                loadingProgress = 1;
                if (!isFadingOut && !isFadingIn) {
                    startFade(8);
                    return;
                }
            }
        } else if (stage == 8) {
            background(bg9Image);
        } else if (stage == 9) {
            background(bg10Image);
            rajah.draw();
            person1.draw();
            Task1.display();
            checkHover(person1);
            checkHover(rajah);
        } else if (stage == 10){
            background(bg11Image);
            fill(255);
            textAlign(CENTER);
            textSize(24);
            text("TASK 1: TURN THE WHEAT INTO OIL", 500, 80);
            // draw wheat if not finished
            if (!wheatTurnedToOil) {
                wheat.draw();
            }
            // progress bar
            fill(255, 140, 0);
            rect(350, 150, 300, 25);
            fill(0, 255, 0);
            rect(350, 150, 300 * wheatHealth, 25);
            helpButton.display();         
            person1.draw();
            checkHover(person1);
            checkHover(person1);
            // ant movement
            if (summonAnts) {
                antarmy.draw();
                antarmy.move(3, 0);
                // collision with wheat
                if (antarmy.x >= wheat.rx - 20) {
                    wheatHealth = 0;
                    wheatTurnedToOil = true;
                }
            }
            // oil appears
            if (wheatTurnedToOil) {
                fill(255);
                textSize(20);
                text("Oil Created!", 450, 200);
                // replace with your oil object later
                oil.draw();
                nextButton.display();
            }
        } else if (stage == 11){
            background(bg11Image);
            fill(255);
            textAlign(CENTER);
            textSize(24);
            text("TASK 2: DEFEAT THE DEMON, click the shift bar", 500, 80);
            person1.draw();
            demon.draw();
            checkHover(person1);
            checkHover(demon);
            helpButton.display();
            // --------------------
            // DEMON HEALTH BAR
            // --------------------
            fill(255, 0, 0);
            rect(demon.x+50, demon.y - 25, 100, 10);
            fill(0, 255, 0);
            rect(demon.x+50, demon.y - 25, demon.health, 10);

            // --------------------
            // PLAYER HEALTH BAR
            // --------------------
            fill(255, 0, 0);
            rect(person1.x+20, person1.y - 25, 100, 10);
            fill(0, 255, 0);
            rect(person1.x+20, person1.y - 25, playerhealth, 10);
            
            if (demon.health > 0 && playerhealth > 0) {
                attackActive = true;
            }
            if (attackActive) {
                int steps = 1; // how fast it moves per frame
                while (steps > 0) {
                    if (evilmagic.rx < person1.x) {
                        evilmagic.rx += 1;
                    } else if (evilmagic.rx > person1.x) {
                        evilmagic.rx -= 1;
                    }
                    if (evilmagic.ry < person1.y) {
                        evilmagic.ry += 1;
                    } else if (evilmagic.ry > person1.y) {
                        evilmagic.ry -= 1;
                    }
                    steps--;
                }
                evilmagic.draw();
                if (person1.isCollidingWith(evilmagic)) {
                    playerhealth = max(playerhealth - 20, 0);
                    evilmagic.rx = 400;
                    evilmagic.ry = 300;
                } 
                if (playerhealth <= 0) {
                    stage = 11.1;
                    attackActive = false;
                }
            }
        } else if (stage == 11.1){
            background(0);
            fill(255);
            textSize(50);
            text("K.O. You died", 350, 200);
            returnBut.display();
        } else if (stage == 11.2){
            background(bg11Image);
            fill(255);
            textAlign(CENTER);
            textSize(24);
            text("Click the shift bar to help the tiger", 500, 80);
            tiger.changePerspective(tfp);
            tiger.draw();
            checkHover(tiger);
            tiger.y = 300;
            demon.draw(); 
            checkHover(demon);
            // --------------------
            // DEMON HEALTH BAR
            // --------------------
            fill(255, 0, 0);
            rect(demon.x + 50, demon.y - 25, 100, 10);
            fill(0, 255, 0);
            rect(demon.x + 50, demon.y - 25, demon.health, 10);

            // --------------------
            // TIGER HEALTH BAR
            // --------------------
            fill(255, 0, 0);
            rect(tiger.x + 20, tiger.y - 25, 100, 10);
            fill(0, 255, 0);
            rect(tiger.x + 20, tiger.y - 25, tiger.health, 10);

            if (demon.health > 0 && tiger.health > 0) {
                attackActive = true;
            }
            if (attackActive) {
                int steps = 1; // how fast it moves per frame
                while (steps > 0) {
                    if (evilmagic.rx < tiger.x) {
                        evilmagic.rx += 1;
                    } else if (evilmagic.rx > tiger.x) {
                        evilmagic.rx -= 1;
                    }
                    if (evilmagic.ry < tiger.y) {
                        evilmagic.ry += 1;
                    } else if (evilmagic.ry > tiger.y) {
                        evilmagic.ry -= 1;
                    }
                    steps--;
                }
                evilmagic.draw();
                if (tiger.isCollidingWith(evilmagic)) {
                    tiger.health = max(tiger.health - 15, 0);
                    evilmagic.rx = 400;
                    evilmagic.ry = 300;
                }
                if (demon.health <= 0) {
                    attackActive = false;
                }
            }
            if (attackActive == false){
                fill(255);
                textSize(20);
                text("Congrats! You defeated the demon!", 500, 120);
                nextButton.display();
            }
        } else if (stage == 12){
            background(bg11Image);
            person1.draw();
            checkHover(person1);
            fill(255);
            textAlign(CENTER);
            textSize(24);
            text("TASK 3: FINAL! HIT A DRUM IN THE SKY", 500, 80);
            helpButton.display();
            velocityY += gravity;     // Gravity constantly pulls down
            person1.y += velocityY;   // Move person1's Y coordinate

            // Floor collision (keeps him at your starting height of 300)
            if (person1.y >= 300) {
                person1.y = 300;
                velocityY = 0;
                jumpCount = 0; // Reset jumps when touching ground
            }
        } else if (stage == 13){
            background(bg11Image);
            magicbed.draw();
            person1.draw();
            checkHover(person1);
            fill(255);
            textSize(20);
            text("You have activated your magic bed! Climb on it to get to the sky", 200, 120);    
        } else if (stage == 14){
            background(bg12Image);
            fill(255);
            textSize(20);
            text("HIT THE DRUM", 430, 120);
        } else if (stage == 14.1){
            background(bg13Image);
            fill(255);
            textSize(20);
            text("YOU DID IT! WOOHOO", 430, 120);
        } else if (stage == 15){
            background(bg14Image);
            person1.setItemCount(10000);
            person1.draw();
            person1.changePerspective(pfp1);
            person1.x = 400;
            person1.y = 280;
            princess.draw();
            tiger.changePerspective("images/tiger1.png");
            tiger.draw();
            rajah.x = 200;
            rajah.draw();
            antKing.x = 400;
            antKing.y = 500;
            antKing.draw();
            faqir.x = 30;
            faqir.draw();
            fill(255);
            textSize(24);
            text("THE END", 450, 120);
            finButton.display();
        } else if (stage == 16){
            background(0);
            fill(255);
            textSize(30);
            textAlign(CENTER);
            text("LEADERBOARD", width / 2, 100);
            textSize(20);
            for (int i = 0; i < leaderboardCount; i++) {
                text(leaderboard[i][0] + " - " + leaderboard[i][1] + " sec", width / 2, 160 + i * 30);
            }
        }

        //-----------------------------------KEY PRESSED---------------------------------
        if (keyPressed) {
            if (keyCode == LEFT) {
                person1.changePerspective(pfp2);
                person1.move(-5, 0);
            } else if (keyCode == RIGHT) {
                person1.changePerspective(pfp3);
                person1.move(5, 0);
            } else if (keyCode == UP && stage!=12) {
                person1.changePerspective(pfp4);
                person1.move(0, -5);
            } else if (keyCode == DOWN && stage!=12){
                person1.changePerspective(pfp1);
                person1.move(0, 5);
            }
        }
        
        if (keyPressed){
            if (stage == 5.2 && keyCode == DOWN) {
            splinter.move(0, 8);
                if (splinter.ry >= height - 5){
                    startFade(5.3);
                    person1.x = 570;
                    person1.y = 430; 
                    tiger.setHealth(100);
                }
            }
        }
        
        if (keyPressed){
            if (stage == 11 && keyCode == SHIFT){
                lightball.draw();
                lightball.move(-5, 0);
                if (demon.isCollidingWith(lightball)){
                    lightball.ry = 300;
                    lightball.rx = 600;
                    demon.health -= 5;
                }
                if (evilmagic.isCollidingWith(lightball)){
                    image(steamy, lightball.rx, lightball.ry);
                }
            } if (stage == 11.2 && keyCode == SHIFT){
                image(pow, 300, 100);
                demon.health = max(demon.health - 15, 0);
            }
        }
        
        
        //-----------------------------------COLLISION DETECTION---------------------------------
        //Collision detection and Stage changes
        // dialogue changes as well
        if (stage == 1 && person1.isCollidingWith(mango1) && !showPickupPrompt) {
            showPickupPrompt = true;
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
            showExitPrompt = true;
        }
        
        if (stage == 5 && person1.isCollidingWith(tiger)){
            tiger.changePerspective(tfp);
            image(dialog6, 200, 500);
        } else if (stage == 5) {
            tiger.changePerspective("images/tiger1.png");
        }

        if (stage == 5.1 && person1.isCollidingWith(tiger)){
            image(dialog7, 200, 500);
        }
        
        if (stage == 5.3 && person1.isCollidingWith(tiger)){
            tiger.changePerspective("images/tiger1.png");
            image(dialog8, 200, 500);
        }
        
        if (stage == 5.4 && person1.isCollidingWith(tiger)){
            image(dialog9, 200, 500);
        }
        
        if (stage == 5.5 && person1.isCollidingWith(tiger)){
            image(dialog10, 200, 500);
        }
        
        if (stage == 6 && person1.isCollidingWith(faqir)){
            image(dialog11, 200, 500);
        }
        
        if (stage == 6.1 && person1.isCollidingWith(faqir)){
            image(dialog12, 180, 500);
        }
        
        if (stage == 9 && person1.isCollidingWith(rajah)){
            image(dialog13, 180, 80);
        }
        
        if (stage == 13 && person1.isCollidingWith(magicbed)){
            person1.move(0, -5);
            magicbed.move(0, -5);
        }
        
        //-----------------------------------LOCATION CHECK---------------------------------
        
        if (stage == 3.5 && person1.y >= height - 50) { // 'height' is 700. 'height - 50' gives a small buffer zone
            person1.y = 500;
            person1.x = 100;
            startFade(4);
        }
        
        if (stage == 4){
            person1.changePerspective(pfp3);
            person1.move(3, 0);
            if (stage == 4 && person1.x >= width - 5){
                person1.x = 0;
                person1.y = 450;
                startFade(5);
            }
        }
        
        if (stage == 5.5 && person1.x <= 0){
            startFade(6);
            person1.x = 750;
            person1.y = 400;
        }
        
        if (stage == 13 && person1.y <= 0 && magicbed.ry <= 0 && !isFadingOut && !isFadingIn){
            startFade(14);
        }
        
        //-----------------------------------FADING---------------------------------
        if (isFadingOut || isFadingIn) {
            fill(0, fadeAlpha);
            rect(0, 0, width, height);
        }
        
        // Draw night overlay if active (only on stage 4)
        if (showNightOverlay && (stage == 4 || stage == 5.5)) {
            fill(0, 100);
            rect(0, 0, width, height);
        }
        
        if (stage > 0 && stage < 16) {
            int elapsedSeconds = (millis() - startTime) / 1000;

            fill(255);
            textSize(20);
            textAlign(LEFT);
            text("Time: " + elapsedSeconds, 20, 30);
        }
    }
    
    
    private void handleFadeTransition() {
        if (isFadingOut) {
            fadeAlpha += 5;
            if (fadeAlpha >= 255) {
                fadeAlpha = 255;
                isFadingOut = false;
                isFadingIn = true;
                stage = nextStage;
            }
        } else if (isFadingIn) {
            fadeAlpha -= 5;
            if (fadeAlpha <= 0) {
                fadeAlpha = 0;
                isFadingIn = false;
            }
        }
    }
    
    private void startFade(double targetStage) {
        nextStage = targetStage;
        isFadingOut = true;
        fadeAlpha = 0;
    }
    
    private void drawLoadingScreen() {
        fill(150, 100, 200);
        rect(375, 320, 250, 15, 5);
        fill(200, 150, 255);
        rect(375, 320, 250 * loadingProgress, 15, 5);
        fill(255);
        textAlign(CENTER);
        textSize(14);
        text("Loading...", 500, 305);
        textAlign(LEFT);
    }
    
    private void drawUsernameScreen() {
        fill(255);
        textAlign(CENTER);
        textSize(28);
        text("Welcome, Traveler", 500, 250);
        
        textSize(16);
        text("Enter your name:", 500, 290);
        
        stroke(150, 100, 200);
        strokeWeight(2);
        fill(50, 50, 50);
        rect(350, 305, 300, 35, 3);
        
        fill(255);
        textSize(16);
        textAlign(LEFT);
        text(userInput, 365, 328);
        textAlign(CENTER);
        
        startButton.display();
        textAlign(LEFT);
    }
    
    public void keyPressed() {
        if (showUsernameScreen) {
            if (key == BACKSPACE) {
                if (userInput.length() > 0) {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
            } else if (key != CODED) {
                userInput += key;
            }
        }
        
        if (stage == 12 && key == ' ') {
            if (jumpCount < 2) {       // Tracks single jump and double jump
                velocityY = -12;       // Launches him UP
                person1.changePerspective(pfp4); // Look upward
                jumpCount++;           // Count this jump
            }
        }
    }
  
    //-----------------------------------MOUSE PRESSED---------------------------------
    public void mousePressed(){ 
        // Start button on username screen
        if (showUsernameScreen && startButton.isClicked(mouseX, mouseY)) {
            String username = userInput.trim();
            if (username.length() > 0) {
                saveUsername(username);
            }
            showUsernameScreen = false;
            isFadingOut = true;
            fadeAlpha = 0;
            nextStage = 1;
            return;
        }
        
        //-----------------------------------STAGE CHANGES (CLICKING) ---------------------------------
        // Stage 1: Mango pickup
        if (stage == 1 && showPickupPrompt && mouseX >= mango1.rx && mouseX <= mango1.rx + 100 && 
            mouseY >= mango1.ry && mouseY <= mango1.ry + 100) {
            startFade(2);
            showPickupPrompt = false;
            return;
        }
        
        // Stage 2: Click ant king to start dialog
        if (stage == 2 && mouseX >= antKing.x && mouseX <= antKing.x + 100 && 
            mouseY >= antKing.y && mouseY <= antKing.y + 100) {
            stage = 3;
            person1.x = 750; 
            person1.y = 330;
            antKing.x = 570;
            antKing.y = 330;
            return;
        }
        

        if (stage == 3 && mouseX >= 200 && mouseX <= 200 + dialog1.width && mouseY >= 500 && mouseY <= 500 + dialog1.height){
            stage = 3.1;
        }        
        else if (stage == 3.1 && mouseX >= 200 && mouseX <= 200 + dialog2.width && mouseY >= 500 && mouseY <= 500 + dialog2.height){
            stage = 3.2;
        }        
        else if (stage == 3.2 && mouseX >= 200 && mouseX <= 200 + dialog3.width && mouseY >= 500 && mouseY <= 500 + dialog3.height){
            stage = 3.3;
        }        
        else if (stage == 3.3 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height){
            stage = 3.4;
        }
        else if (stage == 3.4 && mouseX >= 200 && mouseX <= 200 + dialog4.width && mouseY >= 500 && mouseY <= 500 + dialog4.width){
            stage = 3.5;
        }
        else if (stage == 5 && mouseX >= 200 && mouseX <= 200 + dialog6.width && mouseY >= 500 && mouseY <= 500 + dialog6.height){
            stage = 5.1;
        }
        else if (stage == 5.1 && mouseX >= 200 && mouseX <= 200 + dialog7.width && mouseY >= 500 && mouseY <= 500 + dialog7.height){
            startFade(5.2);
            return;
        }
        else if (stage == 5.3 && mouseX >= 200 && mouseX <= 200 + dialog8.width && mouseY >= 500 && mouseY <= 500 + dialog8.height){
            stage = 5.4;
        }
        else if (stage == 5.4 && mouseX >= 200 && mouseX <= 200 + dialog9.width && mouseY >= 500 && mouseY <= 500 + dialog9.height){
            stage = 5.5;
        }
        else if (stage == 6 && mouseX >= 200 && mouseX <= 200 + dialog11.width && mouseY >= 500 && mouseY <= 500 + dialog11.height){
            stage = 6.1;
        }
        else if (stage == 6.1 && mouseX >= 200 && mouseX <= 200 + dialog12.width && mouseY >= 500 && mouseY <= 500 + dialog12.height){
            stage = 6.2;
        }        
        else if (stage == 6.2 && mouseX >= 650 && mouseX <= 650 + magicbed.width && mouseY >= 330 && mouseY <= 330 + magicbed.height){
            loadingProgress = 0;
            startFade(7);
            person1.addItem();
            faqir.removeItem();
        }
        else if (stage == 8 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height){
            startFade(9);
            person1.changePerspective(pfp4);
            person1.x = 450;
            person1.y = 500;
        } 
        else if (stage == 9 && Task1.isClicked(mouseX, mouseY)) {
            person1.changePerspective(pfp2);
            person1.y = 300;
            person1.x = 600;
            startFade(10);
        } 
        else if (stage == 10 && !wheatTurnedToOil && mouseX >= wheat.rx && mouseX <= wheat.rx + wheat.width
                && mouseY >= wheat.ry && mouseY <= wheat.ry + wheat.height) {
            wheatHealth -= 0.01f;
            if (wheatHealth < 0) {
                wheatHealth = 0;
                wheatTurnedToOil = true;
            }
        } 
        else if (stage == 10 && helpButton.isClicked(mouseX, mouseY)) {
            summonAnts = true;
        } 
        else if (stage == 10 && nextButton.isClicked(mouseX, mouseY)) {
            person1.y = 300;
            person1.x = 600;
            startFade(11);
        } 
        else if (stage == 11 && helpButton.isClicked(mouseX, mouseY)) {
            stage = 11.2;
        } 
        else if (stage == 11.1 && returnBut.isClicked(mouseX, mouseY)) {
            attackActive = true;
            playerhealth = 100;
            demon.setHealth(100);
            stage = 11;
        } 
        else if (stage == 11.2 && nextButton.isClicked(mouseX, mouseY)) {
            person1.changePerspective(pfp1);
            person1.y = 300;
            person1.x = 450;
            startFade(12);
        } 
        else if (stage == 12 && helpButton.isClicked(mouseX, mouseY)) {
            magicbed.accessItem();
        } 
        else if (stage == 14 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height){
            stage = 14.1;
        }
        else if (stage == 14.1 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height){
            startFade(15);
        } 
        else if (stage == 15 && finButton.isClicked(mouseX, mouseY)) {
            finalTime = (millis() - startTime) / 1000;
            leaderboard[leaderboardCount][0] = currentUsername;
            leaderboard[leaderboardCount][1] = String.valueOf(finalTime);
            leaderboardCount++;
            saveScore();
            loadLeaderboard();
            startFade(16);
        }
    }
    
    //-----------------------------------MORE INTRO CODE---------------------------------
    private void saveUsername(String username) {
        currentUsername = username;
    }
    
    private void saveScore() {
        try {
            FileWriter w = new FileWriter("leaderboard.txt", true);
            PrintWriter fileOutput = new PrintWriter(w);
            fileOutput.println(currentUsername + "," + finalTime);
            fileOutput.close();
            System.out.println("Saved: " + currentUsername + " " + finalTime);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    
    private void loadLeaderboard() {
        String[] lines = loadStrings("leaderboard.txt");
        if (lines == null) {
            return;
        }
        leaderboardCount = 0;
        for (int i = 0; i < lines.length; i++) {
            String[] parts = split(lines[i], ',');
            if (parts.length == 2) {
                leaderboard[leaderboardCount][0] = parts[0];
                leaderboard[leaderboardCount][1] = parts[1];
                leaderboardCount++;
            }
        }
    }
    
    private class Button {
        private float x, y, w, h;
        private String label;
        private PApplet parent;
        
        Button(PApplet p, float x, float y, float w, float h, String label) {
            this.parent = p;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.label = label;
        }
        
        void display() {
            fill(150, 100, 200);
            stroke(120, 70, 170);
            strokeWeight(2);
            rect(x, y, w, h, 3);
            fill(255);
            textSize(12);
            textAlign(CENTER, CENTER);
            text(label, x + w/2, y + h/2);
            textAlign(LEFT);
        }
        
        boolean isClicked(float mx, float my) {
            return mx >= x && mx <= x + w && my >= y && my <= y + h;
        }
    }
    
    public void checkHover(Character c) {
        if (c.isHovered(mouseX, mouseY)) {
            c.displayInfo();
        }
    }

}
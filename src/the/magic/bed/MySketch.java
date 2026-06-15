/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.magic.bed; // add this class to the package

/**
 * The imports for the Program
 * @author 343079463
 */
//-----------------------------------IMPORTS---------------------------------
import java.io.FileWriter; // import fileWriter
import java.io.IOException; // import IOException
import java.io.PrintWriter; // import PrintWriter
import processing.core.PApplet; // import PApplet
import processing.core.PImage; // import PImage
import processing.sound.*; // import sound for bg music

/**
 * Main code for this class
 * @author Aairah Ahmeed
 */
//================================BEGINNING=======================================
public class MySketch extends PApplet { // start MySketch class which extends (inheritance) PApplet
    //-----------------------------------VARIABLES---------------------------------
    SoundFile backgroundMusic; // create soundfile variable called backgroundMusic
    
    //username and leaderboard variables
    private String userInput = ""; // create a string to store the user's input and make it blank
    private String currentUsername = "unnamed user"; // create a string to store the current players username and make it unnamed if not entered
    private int finalTime = 0; // create a final time integer and set it to 0
    private String[][] leaderboard = new String[100][2]; // create a 2D array to store all players and their times
    private int leaderboardCount = 0; // integer to count amount of people on leaderboard 
    
    //stage variables and PImages
    public static double stage = 0;  // static variable of the stage, set to 0 to start from the top
    private double nextStage = 0; // double variable to help change stages throughout the program
    private int playerhealth = 100; // initialize player health to 100 so they can start with full health
    private PImage bg1Image, bg2Image, bg3Image, bg4Image, bg5Image, bg6Image, bg7Image, bg8Image, bg9Image, bg10Image, bg11Image, bg12Image, bg13Image, bg14Image; // create background images using PImages
    private PImage dialog1, dialog2, dialog3, dialog4, dialog5, dialog6, dialog7, dialog8, dialog9, dialog10, dialog11, dialog12, dialog13; // create dialogue images using PImages
    private PImage steamy, pow; // create some side items images using PImage (there are not sued as objects int he story which is why they are images)
    
    // ALL MAIN CHARACTER (PRINCE/USER) FACIAL EXPRESSIONS
    private String pfp1 = "images/1.png"; // string variable to store one of the princes facial expressions
    private String pfp2 = "images/2.png"; // string variable to store one of the princes facial expressions
    private String pfp3 = "images/3.png"; // string variable to store one of the princes facial expressions
    private String pfp4 = "images/4.png"; // string variable to store one of the princes facial expressions
    
    // ALL TIGER FACIAL EXPRESSIONS 
    private String tfp = "images/1t.png"; // string variable to store one of the tigers facial expressions
    
    // ALL CHARACTERS
    private Person person1, faqir, rajah, princess;  // create all the characters (person)
    private Animal antKing, tiger, antarmy, demon; // create all the characters (animal)
    
    // ALL ITEMS
    private Item mango1, splinter, magicbed, wheat, oil, evilmagic, lightball; // create all Item objects to be used later
    
    // Fade variables (only needed for fade transition between scenes)
    private boolean isFadingOut = false;  // make the isFadingOut false to show that the stage is not fading out
    private boolean isFadingIn = false; // make the isFadingIn false to show that the stage is not fading in
    private float fadeAlpha = 0; // measures the transperancy of the screen for the fade
    
    //Buttons  
    private Button startButton, Task1, helpButton, nextButton, returnBut, finButton; // create buttons to be used throughout the program
    
    // UI state flags (control which screens / overlays are currently visible)
    private boolean showEndingLoading = false; // keeps track of whether the ending loading screen should be displayed (false = not showing it yet)
    private boolean showUsernameScreen = false; // controls if the username input screen is active (false = user is not in username entry state)
    private boolean isLoading = true; // indicates whether the game is currently in a loading state (true = still loading assets or scenes)
    private float loadingProgress = 0; // represents how far loading has progressed (0.0 = nothing loaded, 1.0 = fully loaded)

    // Prompt / overlay states (used for temporary UI prompts and visual overlays)
    private boolean showPickupPrompt = false; // determines whether the item pickup prompt is shown on screen (false = no prompt visible)
    private boolean showExitPrompt = false; // determines whether the exit confirmation prompt is shown (false = player is not being asked to exit)
    private boolean showNightOverlay = false; // toggles a dark night overlay effect over the game world (false = normal lighting)

    // Game state variables (track world events and gameplay changes)
    private float wheatHealth = 1.0f; // represents the health of the wheat object (1.0 = 100% healthy, 0.0 = destroyed)
    private boolean summonAnts = false; // triggers whether ants should spawn in the world (false = no ant event happening)
    private boolean wheatTurnedToOil = false; // tracks if the wheat has transformed into oil (false = normal wheat state, true = transformed state)
    boolean attackActive = false; // indicates whether an attack event is currently active in the game (false = no attack happening)

    // Timing (used for measuring how long the game/session has been running)
    private int startTime; // stores the time (usually in milliseconds) when the game started

    // Physics variables (used for movement and jumping mechanics)
    private float velocityY = 0; // vertical velocity of the player or object (positive/negative controls upward or downward movement)
    private float gravity = 0.7f; // constant gravity force applied every frame to pull objects downward
    private int jumpCount = 0; // tracks how many jumps the player has performed without touching the ground (used for double jump / limits)
    
    //================================SETTINGS=======================================
    /**
     * The setup of the window and game, needed for program to run
     */
    public void settings(){ // begin the method for the setting to set the game
        size (1000,700); //sets the size of the window
    } // close the method
    
    //================================SETUP=======================================
    /**
     * Setup the characters, backgrounds, music, timing, buttons, and dialogues
     * Basically everything you need is originally setup here and initialized
     */
    public void setup(){ // begin the setup process of the stages and scenes 
	   //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
           // sets the text information, resizes the background images and loads them in
        background(255, 255, 255); // make the background white
        textSize(20); // make the text size 20
        backgroundMusic = new SoundFile(this, "audio/bgmusic.wav"); // add an audio file to the backgroudnMusic
        backgroundMusic.loop(); // loop the music so it starts again once ended if the game is still going on
        backgroundMusic.amp(0.5f); // lower the volume of the audio
        
        // variable to start the time and count milliseconds
        startTime = millis(); // make the start time variable = millis()
        
        //backgrounds (load and resize all the background images needed througout the program)
        bg1Image = loadImage("images/intobg.png"); // load the intro background image
        bg1Image.resize(1000, 700); // resize it
        bg2Image = loadImage("images/bgimage1.png"); // load the background image
        bg2Image.resize(1000, 700); // resize it
        bg3Image = loadImage("images/bgimage2.png"); // load the background image
        bg3Image.resize(1000, 700); // resize it
        bg4Image = loadImage("images/bgImage3.png"); // load the background image
        bg4Image.resize(1000, 700); // resize it
        bg5Image = loadImage("images/bgimage4.png"); // load the background image
        bg5Image.resize(1000, 700); // resize it
        bg6Image = loadImage("images/bgimage5.png"); // load the background image
        bg6Image.resize(1000, 700); // resize it
        bg7Image = loadImage("images/bgimage6.png"); // load the background image
        bg7Image.resize(1000, 700); // resize it
        bg8Image = loadImage("images/bgimage7.png"); // load the background image
        bg8Image.resize(1000, 700); // resize it
        bg9Image = loadImage("images/bgimage8.png"); // load the background image
        bg9Image.resize(1000, 700); // resize it
        bg10Image = loadImage("images/bgimage9.png"); // load the background image
        bg10Image.resize(1000, 700); // resize it
        bg11Image = loadImage("images/bgimage10.png"); // load the background image
        bg11Image.resize(1000, 700); // resize it
        bg12Image = loadImage("images/bgimage11.png"); // load the background image
        bg12Image.resize(1000, 700); // resize it
        bg13Image = loadImage("images/bgimage12.png"); // load the background image
        bg13Image.resize(1000, 700); // resize it
        bg14Image = loadImage("images/bgimage13.png"); // load the background image
        bg14Image.resize(1000, 700); // resize it
        
        // CHARACTERS
        // persons (initialize all the characters needed for the story to Persons) with their name, location, image, and items
        person1 = new Person (this, 450, 500, "Prince", pfp1, 0); // create the prince person
        faqir = new Person (this, 100, 350, "Faqir", "images/faqir.png", 1); // create the faqir person
        rajah = new Person (this, 415, 300, "Rajah", "images/rajah.png", 10000); // create the rajah person
        princess = new Person (this, 545, 300, "Princess Lalun", "images/princess.png", 10000); // create the princess person
        
        // animals (initialize all the characters needed for the story to Animals) with their health, names, locations, and images
        antKing = new Animal (this, 600, 300, "Ant King", "images/antking.png", "Ant", 100); // create the ant king animal
        tiger = new Animal (this, 580, 400, "Tiger", "images/tiger1.png", "Tiger", 50); // create the tiger animal
        antarmy = new Animal (this, -100, 300, "Ant Army","images/armyants.png","Ants", 100); // create the army of ants animals
        demon = new Animal (this, 100, 300, "Demon", "images/demon.png", "Demons", 100); // create the demon animal (made most sense)
        
        //objects (initialize all the items present throughout the story)
        mango1 = new Item (this, 650, 330, "Mango", "images/mango.png"); // create mango object with image and location
        splinter = new Item (this, 325, 350, "Splinter", "images/splinter.png"); // create splinter object with image and location
        magicbed = new Item (this, 650, 330, "Magic Bed", "images/magicbed.png"); // create magic bed object with image and location
        wheat = new Item (this, 200, 300, "Wheat", "images/wheat.png"); // create wheat object with image and location
        oil = new Item (this, 200, 300, "Oil", "images/oil.png"); // create oil object with image and location
        evilmagic = new Item (this, 200, 300, "Evil Magic", "images/evilmagic.png"); // create magic ball (evil) object with image and location
        lightball = new Item (this, 300, 300, "Good Magic", "images/lightball.png"); // create magic ball (good) object with image and location
        steamy = loadImage("images/steamvapor.png"); // create steam image (not an actual item)
        pow = loadImage("images/pow.png"); // create pow effect image (not an actual item)
        
        //dialogues (all dialogue images used for speaking)
        dialog1 = loadImage("images/dialog1.png"); // load image of dialogue
        dialog2 = loadImage("images/dialog2.png"); // load image of dialogue
        dialog3 = loadImage("images/dialog3.png"); // load image of dialogue
        dialog4 = loadImage("images/dialog4.png"); // load image of dialogue
        dialog5 = loadImage("images/dialog5.png"); // load image of dialogue
        dialog6 = loadImage("images/dialog6.png"); // load image of dialogue
        dialog7 = loadImage("images/dialog7.png"); // load image of dialogue
        dialog8 = loadImage("images/dialog8.png"); // load image of dialogue
        dialog9 = loadImage("images/dialog9.png"); // load image of dialogue
        dialog10 = loadImage("images/dialog10.png"); // load image of dialogue
        dialog11 = loadImage("images/dialog11.png"); // load image of dialogue
        dialog12 = loadImage("images/dialog12.png"); // load image of dialogue
        dialog13 = loadImage("images/dialog13.png"); // load image of dialogue
        
        // Initialize BUTTONS & start screen variables
        startButton = new Button(this, 460, 350, 80, 35, "Start"); // create the start button for the story
        isLoading = true; // make the isLoading boolean true 
        showUsernameScreen = false; // make the username screen false to see
        Task1 = new Button(this, 450, 650, 80, 38, "Start Tasks"); // create the task1 button
        helpButton = new Button(this, 450, 100, 100, 40, "Get Help"); // create the help button
        nextButton = new Button (this, 450, 600, 100, 40, "NEXT TASK"); // create the next task button
        returnBut = new Button (this, 450, 600, 100, 40, "RETURN"); // create the return button
        finButton = new Button (this, 450, 150, 100, 40, "FINISH"); // create the finishing button
    } // close setup method
    
    //====================================DRAW=======================================
    /**
     * Method to draw each stage and scene of the code. Included drawing text, objects, characters, and more.
     */
    public void draw() { // begin draw method
        background(255, 255, 255); // create white background
    
        //-----------------------------------LOADING SCREEN---------------------------------
        // Loading screen (occurs before the start)
        if (stage == 0) { // if the stage is 0
            background(bg1Image); // make the background image the first one
            loadingProgress += 0.01; // increase the loading progress by 0.01
            drawLoadingScreen(); // draw the actual loading screen from the method
            if (loadingProgress >= 1) { // if the progress equals or is greater than one
                showUsernameScreen = true; // go to the username screen
                stage = -1; // change the stage to -1
            } // end if
            return; // return to method
        } // end if
        
        //-----------------------------------USERNAME INTRO SCREEN---------------------------------
        // Username screen (gets username, occurs before story)
        if (showUsernameScreen) { // if the username screen is showing
            background(bg1Image); // make the background image the first one
            drawUsernameScreen(); // draw the username screen from the method
            return; // return to the method
        } // end if
        
        //-----------------------------------FADING TRANSITION---------------------------------
        // Handle fade transitions
        handleFadeTransition(); // method to ahndle all the fade transitions and run them

        //-----------------------------------STORY START---------------------------------
        // GARDEN SCENE
        if (stage == 1) { // if we are on stage 1
            background(bg2Image);  // set the background
            mango1.draw(); // draw the mango
            person1.draw(); // draw the prince
            checkHover(person1); // display information if mouse hovers over the prince
            if (showPickupPrompt) { // if the pickup prompt is true, show the code below
                fill(0, 0, 0, 150); // choose the colors for the box
                rect(0, 0, width, height); // create the box
                fill(255); // fill the text
                textAlign(CENTER); // center the text
                textSize(24); // change text size to 24
                text("Pick up the mango", 500, 100); // text instructions
                textAlign(LEFT); // align text left
            } // end if
        // ZOOM IN MANGO SCENE
        } else if (stage == 2){ // if we are on stage 2
            background(bg3Image); // set the background
            fill(255, 255, 255); // set the text color to white
            this.text("AAAHH PLEASE DONT EAT ME", antKing.x, antKing.y - 45); // set the text for the ant king above him
            antKing.draw(); // draw the ant king
        // GARDEN & CONVO SCENES
        } else if (stage == 3){ // if we are on stage 3
            background(bg2Image);  // set the background 
            mango1.draw(); // draw the mango
            antKing.draw(); // draw the ant king
            person1.draw(); // draw the prince
            checkHover(person1); // if the mouse is hovering over the person, display the info
            checkHover(antKing); // if the mouse is hovering over the animal, display the info
        } else if (stage == 3.1 || stage == 3.2 || stage == 3.4){ // if we are on stage 3.1, 3.2, or 3.4
            background(bg2Image); // set the background
            mango1.draw(); // draw the mango
            antKing.draw(); // draw the ant king
            person1.draw(); // draw the prince
            checkHover(person1); // if the mouse is hovering over the person, display the info
            checkHover(antKing); // if the mouse is hovering over the ant king, display the info
        // GARDEN EXIT SCENE
        } else if (stage == 3.5){ // if we are on stage 3.5
            background(bg2Image); // set the background
            mango1.draw(); // draw the mango
            antKing.draw(); // draw the ant king
            person1.draw(); // draw the prince
            checkHover(person1); // if the mouse is hovering over the person, display their information
            checkHover(antKing); // if the mouse is hovering over the ant king, display their information
            if (showExitPrompt) { // if the showExitPrompt is true
                fill(0, 0, 0, 150); // choose the colors for the box
                rect(0, 0, width, height); // create the box
                fill(255); // fill the text white
                textAlign(CENTER); // align the text to the center
                textSize(24); // change the size to 24
                text("Exit the garden", 500, 100); // exit the garden text message
                textAlign(LEFT); // align it left
            } // end if
        //JUNGLE SCENES
        } else if (stage == 3.3) { // if the stage is 3.3
            background(bg4Image); // show the fourth background image for this stage
        } else if (stage == 4) {
            background(bg5Image); // show the fifth background image
            person1.draw(); // draw the player on the screen
            showNightOverlay = true; // make the night overlay true to darken the scene
        } else if (stage == 5) {
            background(bg5Image); // keep showing the fifth background image
            tiger.draw(); // draw the tiger character
            person1.draw(); // draw the player character
            showNightOverlay = false; // make the night overlay false to return to normal lighting
            checkHover(person1); // check if the mouse is hovering over the player
            checkHover(tiger); // check if the mouse is hovering over the tiger
        } else if (stage == 5.1 || stage == 5.3 || stage == 5.4){ // if the stage is 5.1, 5.3, or 5.4
            background(bg5Image); // keep showing the fifth background image
            tiger.draw(); // draw the tiger
            person1.draw(); // draw the person (prince)
            checkHover(person1); // check if the mouse is hovering over the person and display their info
            checkHover(tiger); // check if the mouse is hovering over the tiger and display their info
        // SPLINTER PULL GAME
        } else if (stage == 5.2){ // if the stage is 5.2
            background(bg6Image);  // mak the background the sixth one
            splinter.draw(); // draw the splinter item
            fill(255); // make the text white
            textAlign(CENTER); // make the text centered
            textSize(24); // make the size 24
            text("Press on the DOWN key to pull out the splinter", 500, 100); // make the text and set its location
        // BACK TO JUNGLE SCENES
        } else if (stage == 5.5) {
            background(bg5Image); // keep showing the jungle background
            tiger.draw(); // draw the tiger character
            person1.draw(); // draw the player character
            fill(255); // set the text color to white
            textAlign(CENTER); // center-align the text
            textSize(24); // set the text size
            text("Press on the LEFT key to exit the jungle", 500, 100); // display instructions for leaving the jungle
            checkHover(person1); // check if the mouse is hovering over the player
            checkHover(tiger); // check if the mouse is hovering over the tiger
        // FAQIR VILLAGE SCENE
        } else if (stage == 6 || stage == 6.1) { // if the stage is 6 or 6.1
            background(bg7Image); // show the village/desert background
            faqir.draw(); // draw the faqir character
            person1.draw(); // draw the player character
            checkHover(person1); // check if the mouse is hovering over the player
            checkHover(faqir); // check if the mouse is hovering over the faqir
        } else if (stage == 6.2) { // if the stage is 6.2
            background(bg7Image); // keep showing the same background
            faqir.draw(); // draw the faqir character
            magicbed.draw(); // draw the magic bed item
            person1.draw(); // draw the player character
            fill(255); // set the text color to white
            textAlign(CENTER); // center-align the text
            textSize(24); // set the text size
            text("Tap the bed to store to inventory", 500, 100); // tell the player to collect the bed
            checkHover(person1); // check if the mouse is hovering over the player
            checkHover(faqir); // check if the mouse is hovering over the faqir
        // NEW LOADING SEQUENCE
        } else if (stage == 7) { // if stage equals 7
            background(bg8Image); // show the loading screen background
            loadingProgress += 0.01; // gradually increase the loading progress
            drawLoadingScreen(); // draw the loading screen and progress bar
            if (loadingProgress >= 1) { // check if loading has finished
                loadingProgress = 1; // keep the loading progress at 100%
                if (!isFadingOut && !isFadingIn) { // make sure a fade transition is not already happening
                    startFade(8); // start fading to the next stage
                    return; // stop running the rest of the draw loop for this frame
                } // end if
            } // end if
        // BACKSTORY SCENE
        } else if (stage == 8) { // if stage equals 8
            background(bg9Image); // show the backstory background
        // TALKING TO RAJAH IN PALACE SCENE
        } else if (stage == 9) { // is stage equals 9
            background(bg10Image); // show the palace background
            rajah.draw(); // draw the rajah character
            person1.draw(); // draw the player character
            Task1.display(); // display the first task
            checkHover(person1); // check if the mouse is hovering over the player
            checkHover(rajah); // check if the mouse is hovering over the rajah
            // TASK 1 GAME SCENE
        } else if (stage == 10) { // if the stage equals 10
            background(bg11Image); // show the task 1 background
            fill(255); // set the text color to white
            textAlign(CENTER); // center-align the text
            textSize(24); // set the text size
            text("TASK 1: TURN THE WHEAT INTO OIL", 500, 80); // display the task objective
            // draw wheat if not finished
            if (!wheatTurnedToOil) { // check if the wheat has not been turned into oil yet
                wheat.draw(); // draw the wheat object
            } // end if
            // progress bar
            fill(255, 140, 0); // set the progress bar background color
            rect(350, 150, 300, 25); // draw the progress bar background
            fill(0, 255, 0); // set the progress bar fill color
            rect(350, 150, 300 * wheatHealth, 25); // draw the wheat health progress
            helpButton.display(); // display the help button
            person1.draw(); // draw the player character
            // hover checking for display info
            checkHover(person1); // check if the mouse is hovering over the player
            // ant movement
            if (summonAnts) { // check if the ants should be spawned
                antarmy.draw(); // draw the ant army
                antarmy.move(3, 0); // move the ants to the right
                // collision with wheat
                if (antarmy.x >= wheat.rx - 20) { // check if the ants have reached the wheat
                    wheatHealth = 0; // set the wheat health to zero
                    wheatTurnedToOil = true; // turn the wheat into oil
                } // end if
            } // end if
            // oil appearance
            if (wheatTurnedToOil) { // check if the wheat has been converted into oil
                fill(255); // set the text color to white
                textSize(20); // set the text size
                text("Oil Created!", 450, 200); // display a success message
                oil.draw(); // draw the oil object
                nextButton.display(); // display the next button to go to next stage
            } // end if
            // TASK 2 GAME SCENE
        } else if (stage == 11) { // if the stage is 11, start task 2 where the player must defeat the demon
            background(bg11Image); // show the task 2 background
            // display the task instructions at the top of the screen
            fill(255); // set the text color to white
            textAlign(CENTER); // center the text
            textSize(24); // make the text larger
            text("TASK 2: DEFEAT THE DEMON, click the shift bar", 500, 80); // tell the player what to do
            // draw the characters in the scene
            person1.draw(); // draw the player character
            demon.draw(); // draw the demon character
            // check if the mouse is hovering over any characters
            checkHover(person1); // check if the mouse is hovering over the player
            checkHover(demon); // check if the mouse is hovering over the demon
            // show the help button for the user to use
            helpButton.display(); // display the help button
            // draw the demon's health bar above the demon
            fill(255, 0, 0); // set the health bar background to red
            rect(demon.x + 50, demon.y - 25, 100, 10); // draw the demon health bar background
            fill(0, 255, 0); // set the remaining health color to green
            rect(demon.x + 50, demon.y - 25, demon.health, 10); // draw the demon's current health
            // draw the player's health bar above the player
            fill(255, 0, 0); // set the health bar background to red
            rect(person1.x + 20, person1.y - 25, 100, 10); // draw the player health bar background
            fill(0, 255, 0); // set the remaining health color to green
            rect(person1.x + 20, person1.y - 25, playerhealth, 10); // draw the player's current health
            // start attacks only while both characters are still alive
            if (demon.health > 0 && playerhealth > 0) { // check if both the demon and player still have health remaining
                attackActive = true; // allow the demon's attack to continue
            } // end if
            // control the evilmagic ball attack
            if (attackActive) { // check if the attack sequence is active
                int steps = 1; // controls how many pixels the evilmagic ball moves each frame
                while (steps > 0) { // keep moving the evilmagic ball until all movement steps are used
                    // move the evilmagic ball horizontally toward the player
                    if (evilmagic.rx < person1.x) { // check if the evilmagic ball is left of the player
                        evilmagic.rx += 1; // move the evilmagic ball right
                    } else if (evilmagic.rx > person1.x) { // check if the evilmagic ball is right of the player
                        evilmagic.rx -= 1; // move the evilmagic ball left
                    } // end if
                    // move the evilmagic ball vertically toward the player
                    if (evilmagic.ry < person1.y) { // check if the evilmagic ball is above the player
                        evilmagic.ry += 1; // move the evilmagic ball downward
                    } else if (evilmagic.ry > person1.y) { // check if the evilmagic ball is below the player
                        evilmagic.ry -= 1; // move the evilmagic ball upward
                    } // end if
                    steps--; // decrease the number of movement steps remaining
                } // end if
                evilmagic.draw(); // draw the evilmagic ball on the screen
                // check if the evilmagic ball has hit the player
                if (person1.isCollidingWith(evilmagic)) { // check for a collision between the player and the evilmagic ball
                    playerhealth = max(playerhealth - 20, 0); // remove 20 health from the player
                    evilmagic.rx = 400; // reset the evilmagic ball's x position
                    evilmagic.ry = 300; // reset the evilmagic ball's y position
                } // end if
                // check if the player has been defeated
                if (playerhealth <= 0) { // check if the player's health has reached zero
                    stage = 11.1; // switch to the game over stage
                    attackActive = false; // stop the demon's attacks
                } // end if
            } // end if
            // DEAD SCENE
        } else if (stage == 11.1) { // if the stage is 11.1, show the game over screen
            background(0); // set the background to black
            fill(255); // set the text color to white
            textSize(50); // make the text large
            text("K.O. You died", 350, 200); // display the game over message
            returnBut.display(); // display the return button

        // TIGER FIGHT SCENE
        } else if (stage == 11.2) { // if the stage is 11.2, start the tiger vs demon battle
            background(bg11Image); // show the battle background
            // display instructions for the tiger fight
            fill(255); // set the text color to white
            textAlign(CENTER); // center the text
            textSize(24); // make the text larger
            text("Click the shift bar to help the tiger", 500, 80); // tell the player how to help the tiger
            // draw the tiger and demon characters
            tiger.changePerspective(tfp); // change the tiger to its fighting perspective
            tiger.draw(); // draw the tiger character
            checkHover(tiger); // check if the mouse is hovering over the tiger
            tiger.y = 300; // keep the tiger at the correct y position
            demon.draw(); // draw the demon character
            checkHover(demon); // check if the mouse is hovering over the demon
            // draw the demon's health bar
            fill(255, 0, 0); // set the health bar background to red
            rect(demon.x + 50, demon.y - 25, 100, 10); // draw the demon health bar background
            fill(0, 255, 0); // set the remaining health color to green
            rect(demon.x + 50, demon.y - 25, demon.health, 10); // draw the demon's current health
            // draw the tiger's health bar
            fill(255, 0, 0); // set the health bar background to red
            rect(tiger.x + 20, tiger.y - 25, 100, 10); // draw the tiger health bar background
            fill(0, 255, 0); // set the remaining health color to green
            rect(tiger.x + 20, tiger.y - 25, tiger.health, 10); // draw the tiger's current health
            // start attacks only while both the tiger and demon are still alive
            if (demon.health > 0 && tiger.health > 0) { // check if both characters still have health remaining
                attackActive = true; // keep the attack sequence active
            } // end if 
            // control the evilmagic ball attack
            if (attackActive) { // check if the attack sequence is active
                int steps = 1; // controls how many pixels the evilmagic ball moves each frame
                while (steps > 0) { // move the evilmagic ball toward the tiger
                    // move the evilmagic ball horizontally toward the tiger
                    if (evilmagic.rx < tiger.x) { // check if the evilmagic ball is left of the tiger
                        evilmagic.rx += 1; // move the evilmagic ball right
                    } else if (evilmagic.rx > tiger.x) { // check if the evilmagic ball is right of the tiger
                        evilmagic.rx -= 1; // move the evilmagic ball left
                    } // end if
                    // move the evilmagic ball vertically toward the tiger
                    if (evilmagic.ry < tiger.y) { // check if the evilmagic ball is above the tiger
                        evilmagic.ry += 1; // move the evilmagic ball down
                    } else if (evilmagic.ry > tiger.y) { // check if the evilmagic ball is below the tiger
                        evilmagic.ry -= 1; // move the evilmagic ball up
                    } // end if
                    steps--; // reduce the remaining movement steps
                } // end if
                evilmagic.draw(); // draw the evilmagic ball
                // check if the evilmagic ball has hit the tiger
                if (tiger.isCollidingWith(evilmagic)) { // check for a collision between the tiger and the evilmagic ball
                    tiger.health = max(tiger.health - 15, 0); // remove 15 health from the tiger
                    evilmagic.rx = 400; // reset the evilmagic ball's x position
                    evilmagic.ry = 300; // reset the evilmagic ball's y position
                } // end if
                // stop attacks when the demon is defeated
                if (demon.health <= 0) { // check if the demon has no health remaining
                    attackActive = false; // stop the attack sequence
                } // end if
            } // end if
            // show the victory message when the battle is over
            if (attackActive == false) { // check if the fight has ended
                fill(255); // set the text color to white
                textSize(20); // make the text smaller than the title
                text("Congrats! You defeated the demon!", 500, 120); // display the victory message
                nextButton.display(); // display the next button
            } // end if
        } else if (stage == 12) { // if the stage is 12, start the final task where the player must reach the drum in the sky
            background(bg11Image); // show the task 3 background
            person1.draw(); // draw the player character
            checkHover(person1); // check if the mouse is hovering over the player
            // display the final task instructions
            fill(255); // set the text color to white
            textAlign(CENTER); // center the text
            textSize(24); // make the text larger
            text("TASK 3: FINAL! HIT A DRUM IN THE SKY", 500, 80); // tell the player the final objective
            helpButton.display(); // display the help button
            // apply gravity to the player character
            velocityY += gravity; // continuously increase the player's downward speed
            person1.y += velocityY; // move the player vertically based on the current velocity
            // keep the player from falling through the ground
            if (person1.y >= 300) { // check if the player has reached the floor
                person1.y = 300; // place the player back on the floor
                velocityY = 0; // stop downward movement
                jumpCount = 0; // allow the player to jump again
            } // end if
        } else if (stage == 13) { // if the stage is 13, show the magic bed scene
            background(bg11Image); // show the same background
            magicbed.draw(); // draw the magic bed
            person1.draw(); // draw the player character
            checkHover(person1); // check if the mouse is hovering over the player
            // display instructions for using the magic bed
            fill(255); // set the text color to white
            textSize(20); // set the text size
            text("You have activated your magic bed! Climb on it to get to the sky", 200, 120); // explain what the player should do next
        } else if (stage == 14) { // if the stage is 14, show the sky scene with the drum
            background(bg12Image); // show the sky background
            // display the objective
            fill(255); // set the text color to white
            textSize(20); // set the text size
            text("HIT THE DRUM", 430, 120); // tell the player to hit the drum
        } else if (stage == 14.1) { // if the stage is 14.1, show the success screen after hitting the drum
            background(bg13Image); // show the success background
            // display the success message
            fill(255); // set the text color to white
            textSize(20); // set the text size
            text("YOU DID IT! WOOHOO", 430, 120); // congratulate the player
        } else if (stage == 15) { // if the stage is 15, show the ending celebration scene
            background(bg14Image); // show the ending background
            // prepare the player character for the final scene
            person1.setItemCount(10000); // give the player a large item count for the ending
            person1.draw(); // draw the player character
            person1.changePerspective(pfp1); // switch the player to the default perspective
            person1.x = 380; // position the player in the scene
            person1.y = 280; // position the player in the scene
            // draw all characters who helped throughout the story
            princess.draw(); // draw the princess
            tiger.changePerspective("images/tiger1.png"); // switch the tiger back to its normal appearance
            tiger.draw(); // draw the tiger
            rajah.x = 200; // position the rajah
            rajah.draw(); // draw the rajah
            antKing.x = 400; // position the ant king
            antKing.y = 500; // position the ant king
            antKing.draw(); // draw the ant king
            faqir.x = 30; // position the faqir
            faqir.draw(); // draw the faqir
            // display the ending message
            fill(255); // set the text color to white
            textSize(24); // set the text size
            text("THE END", 450, 120); // show the ending title
            finButton.display(); // display the finish button
        } else if (stage == 16) { // if the stage is 16, show the leaderboard screen
            background(0); // set the background to black
            // display the leaderboard title
            fill(255); // set the text color to white
            textSize(30); // make the title larger
            textAlign(CENTER); // center the text
            text("LEADERBOARD", width / 2, 100); // display the leaderboard heading
            // display all saved leaderboard entries
            textSize(20); // use a smaller text size for scores
            for (int i = 0; i < leaderboardCount; i++) { // loop through each leaderboard entry
                text(leaderboard[i][0] + " - " + leaderboard[i][1] + " sec", width / 2, 160 + i * 30); // display the player's name and completion time
            } // close for loop
        } // close if else sequence for stages

        //-----------------------------------KEY PRESSED---------------------------------
        // handle player movement using the arrow keys all throughout the program
        if (keyPressed) { // check if any key is currently being pressed
            if (keyCode == LEFT) { // check if the left arrow key is pressed
                person1.changePerspective(pfp2); // change the player to the left-facing perspective
                person1.move(-5, 0); // move the player left
            } else if (keyCode == RIGHT) { // check if the right arrow key is pressed
                person1.changePerspective(pfp3); // change the player to the right-facing perspective
                person1.move(5, 0); // move the player right
            } else if (keyCode == UP && stage != 12) { // check if the up arrow key is pressed and the player is not in the jumping stage
                person1.changePerspective(pfp4); // change the player to the upward-facing perspective
                person1.move(0, -5); // move the player up
            } else if (keyCode == DOWN && stage != 12) { // check if the down arrow key is pressed and the player is not in the jumping stage
                person1.changePerspective(pfp1); // change the player to the downward-facing perspective
                person1.move(0, 5); // move the player down
            } // end if
        } // end if
        // control the splinter during the jungle escape sequence
        if (keyPressed) { // check if any key is currently being pressed
            if (stage == 5.2 && keyCode == DOWN) { // if the stage is 5.2 and the down arrow key is pressed
                splinter.move(0, 8); // move the splinter downward
                // check if the splinter has reached the bottom of the screen
                if (splinter.ry >= height - 5) {
                    startFade(5.3); // fade to the next stage
                    person1.x = 570; // move the player to the new starting position
                    person1.y = 430; // move the player to the new starting position
                    tiger.setHealth(100); // reset the tiger's health
                } // end if
            } // end if
        } // end if
        // control combat actions during the demon battles
        if (keyPressed) { // check if any key is currently being pressed
            // player attacks the demon using the lightball
            if (stage == 11 && keyCode == SHIFT) { // if the stage is 11 and the shift key is pressed
                lightball.draw(); // draw the lightball attack
                lightball.move(-5, 0); // move the lightball toward the demon
                // check if the lightball hits the demon
                if (demon.isCollidingWith(lightball)) {
                    lightball.ry = 300; // reset the lightball's y position
                    lightball.rx = 600; // reset the lightball's x position
                    demon.health -= 5; // remove health from the demon
                } // end if
                // check if the lightball collides with the evilmagic ball
                if (evilmagic.isCollidingWith(lightball)) {
                    image(steamy, lightball.rx, lightball.ry); // display a collision effect
                } // end if
            } // end if
            // tiger attacks the demon during the tiger battle
            if (stage == 11.2 && keyCode == SHIFT) { // if the stage is 11.2 and the shift key is pressed
                image(pow, 300, 100); // display the attack effect
                demon.health = max(demon.health - 15, 0); // remove health from the demon without going below zero
            } // end if
        } // end if
        
        //-----------------------------------COLLISION DETECTION---------------------------------
        // Collision detection and stage changes
        // dialogue changes as well based on interaction with characters and objects
        if (stage == 1 && person1.isCollidingWith(mango1) && !showPickupPrompt) { // if the stage is 1 and player collides with mango1
            showPickupPrompt = true; // show the pickup prompt for the mango
        } // end if
        
        if (person1.isCollidingWith(antKing) && stage == 3) { // if the stage is 3 and player collides with the antKing
            image(dialog1, 200, 500); // show antKing dialogue 1
        } // end if

        if (stage == 3.1 && person1.isCollidingWith(antKing)) { // if the stage is 3.1 and player collides with the antKing
            image(dialog2, 200, 500); // show antKing dialogue 2
        } // end if

        if (stage == 3.2 && person1.isCollidingWith(antKing)) { // if the stage is 3.2 and player collides with the antKing
            image(dialog3, 200, 500); // show antKing dialogue 3
        } // end if

        if (stage == 3.4 && person1.isCollidingWith(antKing)) { // if the stage is 3.4 and player collides with the antKing
            image(dialog4, 200, 500); // show antKing dialogue 4
        } // end if

        if (stage == 3.5 && person1.isCollidingWith(antKing)) { // if the stage is 3.5 and player collides with the antKing
            image(dialog5, 200, 500); // show antKing final dialogue
            showExitPrompt = true; // allow the player to exit the antKing area
        } // end if

        if (stage == 5 && person1.isCollidingWith(tiger)) { // if the stage is 5 and player collides with the tiger
            tiger.changePerspective(tfp); // switch tiger to interaction/facing perspective
            image(dialog6, 200, 500); // show tiger dialogue 1
        } else if (stage == 5) { // if the stage is 5 but player is not colliding with the tiger
            tiger.changePerspective("images/tiger1.png"); // reset tiger to default appearance
        } // end if

        if (stage == 5.1 && person1.isCollidingWith(tiger)) { // if the stage is 5.1 and player collides with the tiger
            image(dialog7, 200, 500); // show tiger dialogue 2
        } // end if

        if (stage == 5.3 && person1.isCollidingWith(tiger)) { // if the stage is 5.3 and player collides with the tiger
            tiger.changePerspective("images/tiger1.png"); // reset tiger appearance
            image(dialog8, 200, 500); // show tiger dialogue 3
        } // end if

        if (stage == 5.4 && person1.isCollidingWith(tiger)) { // if the stage is 5.4 and player collides with the tiger
            image(dialog9, 200, 500); // show tiger dialogue 4
        } // end if

        if (stage == 5.5 && person1.isCollidingWith(tiger)) { // if the stage is 5.5 and player collides with the tiger
            image(dialog10, 200, 500); // show tiger dialogue 5
        } // end if

        if (stage == 6 && person1.isCollidingWith(faqir)) { // if the stage is 6 and player collides with the faqir
            image(dialog11, 200, 500); // show faqir dialogue 1
        } // end if

        if (stage == 6.1 && person1.isCollidingWith(faqir)) { // if the stage is 6.1 and player collides with the faqir
            image(dialog12, 180, 500); // show faqir dialogue 2
        } // end if

        if (stage == 9 && person1.isCollidingWith(rajah)) { // if the stage is 9 and player collides with the rajah
            image(dialog13, 180, 80); // show rajah dialogue
        } // end if

        if (stage == 13 && person1.isCollidingWith(magicbed)) { // if the stage is 13 and player collides with the magicbed
            person1.move(0, -5); // move the player upward with the magic bed
            magicbed.move(0, -5); // move the magic bed upward with the player
        } // end if
        
        //-----------------------------------LOCATION CHECK---------------------------------
        // stage transition logic based on player position and scene boundaries
        if (stage == 3.5 && person1.y >= height - 50) { // if the stage is 3.5 and the player reaches the bottom of the screen (height is 700, so height - 50 adds a small buffer zone)
            person1.y = 500; // reset player y position before transitioning
            person1.x = 100; // reset player x position for the next scene
            startFade(4); // fade transition to stage 4
        } // end if

        if (stage == 4) { // if the stage is 4 (jungle movement scene)
            person1.changePerspective(pfp3); // set player to right-facing movement animation
            person1.move(3, 0); // automatically move the player to the right
            if (stage == 4 && person1.x >= width - 5) { // if the player reaches the right edge of the screen
                person1.x = 0; // wrap player to the left side
                person1.y = 450; // set new y position for continuity in the next area
                startFade(5); // transition to stage 5
            } // end if
        } // end if

        if (stage == 5.5 && person1.x <= 0) { // if the stage is 5.5 and player reaches the left edge of the screen
            startFade(6); // fade into stage 6
            person1.x = 750; // reposition player on the right side of the next scene
            person1.y = 400; // set player height in the new scene
        } // end if

        if (stage == 13 && person1.y <= 0 && magicbed.ry <= 0 && !isFadingOut && !isFadingIn) { // if the stage is 13, the player and magicbed have reached the top of the screen, and no fade is happening
            startFade(14); // transition to stage 14 (sky/drum scene)
        } // end if 
        
        //-----------------------------------FADING---------------------------------
        // handle screen fade transitions between stages
        if (isFadingOut || isFadingIn) { // if a fade transition is currently active
            fill(0, fadeAlpha); // set a transparent black overlay based on fadeAlpha
            rect(0, 0, width, height); // draw full-screen fade overlay
        } // end if

        // draw night overlay if active (only on specific stages where lighting is darker)
        if (showNightOverlay && (stage == 4 || stage == 5.5)) { // if night mode is enabled and the stage is 4 or 5.5
            fill(0, 100); // set semi-transparent black overlay for night effect
            rect(0, 0, width, height); // draw night overlay across the screen
        } // end if

        //-----------------------------------TIMER---------------------------------
        // display the in-game timer during gameplay stages
        if (stage > 0 && stage < 16) { // if the game is in an active playable stage (not menu or leaderboard)
            int elapsedSeconds = (millis() - startTime) / 1000; // calculate how many seconds have passed since the game started

            fill(255); // set text color to white
            textSize(20); // set timer text size
            textAlign(LEFT); // align timer to the top-left corner
            text("Time: " + elapsedSeconds, 20, 30); // display the elapsed time on screen
        } // end if
    } // end if
    
    //==============================MORE FADING================================
    /**
     * handles fade transition animation between stages (fade out then fade in)
     */
    private void handleFadeTransition() {
        if (isFadingOut) { // if the screen is currently fading out
            fadeAlpha += 5; // increase transparency of black overlay
            if (fadeAlpha >= 255) { // if screen is fully black (fade out complete)
                fadeAlpha = 255; // clamp alpha to max darkness
                isFadingOut = false; // stop fade-out phase
                isFadingIn = true; // start fade-in phase
                stage = nextStage; // switch to the next stage after fade out completes
            } // end if
        } else if (isFadingIn) { // if the screen is currently fading back in
            fadeAlpha -= 5; // decrease black overlay transparency
            if (fadeAlpha <= 0) { // if fade-in is complete (fully visible again)
                fadeAlpha = 0; // clamp alpha to fully visible
                isFadingIn = false; // stop fade-in phase
            } // end if
        } // end if
    } // end if

    /**
     * starts a fade transition to a target stage
     * @param targetStage the stage it must go to
     */
    private void startFade(double targetStage) {
        nextStage = targetStage; // store the stage we want to switch to after fading
        isFadingOut = true; // begin fade-out process
        fadeAlpha = 0; // reset fade value so it starts from transparent
    } // end method

    //==========================LOADING SCREEN==========================
    /**
     * draws the loading screen UI with progress bar and text
     */
    private void drawLoadingScreen() { // start the method
        fill(150, 100, 200); // background color of progress bar
        rect(375, 320, 250, 15, 5); // draw progress bar outline/background
        fill(200, 150, 255); // fill color for loading progress
        rect(375, 320, 250 * loadingProgress, 15, 5); // draw current loading progress
        fill(255); // set text color to white
        textAlign(CENTER); // center loading text
        textSize(14); // set small text size
        text("Loading...", 500, 305); // display loading label
        textAlign(LEFT); // reset text alignment for rest of UI
    } // end method 

    //==========================USERNAME SCREEN==========================
    /**
     * draws the username input screen where player enters their name
     */
    private void drawUsernameScreen() { // start the method
        fill(255); // set text color to white
        textAlign(CENTER); // center title text
        textSize(28); // large welcome text
        text("Welcome, Traveler", 500, 250); // show greeting message

        textSize(16); // smaller instruction text
        text("Enter your name:", 500, 290); // prompt user input

        stroke(150, 100, 200); // set border color for input box
        strokeWeight(2); // make border slightly thicker
        fill(50, 50, 50); // dark background for input box
        rect(350, 305, 300, 35, 3); // draw input field

        fill(255); // set text color for input
        textSize(16); // input text size
        textAlign(LEFT); // align typed text to left inside box
        text(userInput, 365, 328); // display what the user has typed so far
        textAlign(CENTER); // reset alignment for UI consistency

        startButton.display(); // show the start button
        textAlign(LEFT); // reset alignment for other UI elements
    } // end method
    
    //==========================MORE KEY PRESSED SCREEN==========================
    /**
     * All actions that will happen when the key is pressed (for more specific keys)
     */
    public void keyPressed() { // start the method
        // handle typing input when the username screen is active
        if (showUsernameScreen) { // if the player is currently on the username screen
            if (key == BACKSPACE) { // if the backspace key is pressed
                if (userInput.length() > 0) { // make sure there is text to delete
                    userInput = userInput.substring(0, userInput.length() - 1); // remove the last character typed
                } // end if
            } else if (key != CODED) { // if the key is a normal character (not a special coded key)
                userInput += key; // add the typed character to the username input
            } // end if
        } // end if

        // handle jumping mechanics in stage 12 (final sky task)
        if (stage == 12 && key == ' ') { // if the stage is 12 and the space bar is pressed
            if (jumpCount < 2) { // allow for single jump and double jump (max 2 jumps before landing)
                velocityY = -12; // apply upward force to make the player jump
                person1.changePerspective(pfp4); // switch player to upward-facing animation
                jumpCount++; // increase jump counter
            } // end if
        } // end if
    } // close the method
  
    //===============================MOUSE PRESSED==================================
    /**
     * If the mouse is pressed, perform the actions below
     * @return 
     */
    public void mousePressed() { // start the method
        // handle clicks on the username screen start button
        if (showUsernameScreen && startButton.isClicked(mouseX, mouseY)) { // if the username screen is active and the start button is clicked
            String username = userInput.trim(); // remove extra spaces from the username input
            if (username.length() > 0) { // check that the username is not empty
                saveUsername(username); // save the entered username
            } // end if
            showUsernameScreen = false; // hide the username screen and start the game
            isFadingOut = true; // begin fade-out transition
            fadeAlpha = 0; // reset fade value for transition effect
            nextStage = 1; // set the first gameplay stage as the next scene
            return; // stop further mouse processing since we already handled the click
        } // end if
        
        //-----------------------------------STAGE CHANGES (CLICKING) ---------------------------------
        // handle all mouse interactions for the stage changes
        // Stage 1: Mango pickup
        if (stage == 1 && showPickupPrompt && mouseX >= mango1.rx && mouseX <= mango1.rx + 100
                && mouseY >= mango1.ry && mouseY <= mango1.ry + 100) { // if the player clicks on the mango during stage 1
            startFade(2); // move to stage 2 after picking up the mango
            showPickupPrompt = false; // hide the pickup prompt
            return; // stop further click processing
        } // end if
        // Stage 2: Click ant king to start dialog
        if (stage == 2 && mouseX >= antKing.x && mouseX <= antKing.x + 100
                && mouseY >= antKing.y && mouseY <= antKing.y + 100) { // if the player clicks on the antKing
            stage = 3; // start antKing dialogue sequence
            person1.x = 750; // reposition player x for dialogue scene
            person1.y = 330;  // reposition player y for dialogue scene
            antKing.x = 570; // reposition antKing x for dialogue scene
            antKing.y = 330; // reposition antking y for dialogue scene
            return; // stop further click processing
        }
        // Stage 3: antKing dialogue progression
        if (stage == 3 && mouseX >= 200 && mouseX <= 200 + dialog1.width && mouseY >= 500 && mouseY <= 500 + dialog1.height) {
            stage = 3.1; // move to next antKing dialogue
        } else if (stage == 3.1 && mouseX >= 200 && mouseX <= 200 + dialog2.width && mouseY >= 500 && mouseY <= 500 + dialog2.height) {
            stage = 3.2; // continue antKing dialogue progression
        } else if (stage == 3.2 && mouseX >= 200 && mouseX <= 200 + dialog3.width && mouseY >= 500 && mouseY <= 500 + dialog3.height) {
            stage = 3.3; // continue dialogue sequence
        } else if (stage == 3.3 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height) {
            stage = 3.4; // advance to next dialogue trigger
        } else if (stage == 3.4 && mouseX >= 200 && mouseX <= 200 + dialog4.width && mouseY >= 500 && mouseY <= 500 + dialog4.width) {
            stage = 3.5; // finish antKing dialogue sequence
        } // Stage 5: tiger dialogue progression
        else if (stage == 5 && mouseX >= 200 && mouseX <= 200 + dialog6.width && mouseY >= 500 && mouseY <= 500 + dialog6.height) {
            stage = 5.1; // continue tiger dialogue
        } else if (stage == 5.1 && mouseX >= 200 && mouseX <= 200 + dialog7.width && mouseY >= 500 && mouseY <= 500 + dialog7.height) {
            startFade(5.2); // transition to jungle escape sequence
            return; // return
        } else if (stage == 5.3 && mouseX >= 200 && mouseX <= 200 + dialog8.width && mouseY >= 500 && mouseY <= 500 + dialog8.height) {
            stage = 5.4; // continue tiger dialogue
        } else if (stage == 5.4 && mouseX >= 200 && mouseX <= 200 + dialog9.width && mouseY >= 500 && mouseY <= 500 + dialog9.height) {
            stage = 5.5; // continue tiger dialogue
        } // Stage 6: faqir dialogue progression
        else if (stage == 6 && mouseX >= 200 && mouseX <= 200 + dialog11.width && mouseY >= 500 && mouseY <= 500 + dialog11.height) {
            stage = 6.1; // continue faqir dialogue
        } else if (stage == 6.1 && mouseX >= 200 && mouseX <= 200 + dialog12.width && mouseY >= 500 && mouseY <= 500 + dialog12.height) {
            stage = 6.2; // finish faqir dialogue
        } // Stage 6.2: magicbed pickup
        else if (stage == 6.2 && mouseX >= 650 && mouseX <= 650 + magicbed.width && mouseY >= 330 && mouseY <= 330 + magicbed.height) {
            loadingProgress = 0; // reset loading bar for next transition
            startFade(7); // move to loading scene
            person1.addItem(); // add magicbed to player inventory
            faqir.removeItem(); // remove item from faqir
        } // Stage 8: backstory skip/continue
        else if (stage == 8 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height) {
            startFade(9); // move to palace scene
            person1.changePerspective(pfp4); // set player facing direction
            person1.x = 450; // position player in palace scene
            person1.y = 500; // position person y
        } // Stage 9: start Task 1
        else if (stage == 9 && Task1.isClicked(mouseX, mouseY)) {
            person1.changePerspective(pfp2); // set player direction for task start
            person1.y = 300; // position person y
            person1.x = 600; // position person x
            startFade(10); // begin Task 1 scene
        } // Stage 10: wheat interaction (damage over time system)
        else if (stage == 10 && !wheatTurnedToOil && mouseX >= wheat.rx && mouseX <= wheat.rx + wheat.width
                && mouseY >= wheat.ry && mouseY <= wheat.ry + wheat.height) {
            wheatHealth -= 0.01f; // reduce wheat health when clicked
            if (wheatHealth < 0) { // check if wheat is fully destroyed
                wheatHealth = 0; // make the wheat health equal 0
                wheatTurnedToOil = true; // convert wheat into oil state
            } // end if
        } // Stage 10: summon ants help button
        else if (stage == 10 && helpButton.isClicked(mouseX, mouseY)) { // if the help button is clicked on stage 10
            summonAnts = true; // trigger ant army event
        } // Stage 10: proceed to next task
        else if (stage == 10 && nextButton.isClicked(mouseX, mouseY)) { // if the next button is clicked on stage 10
            person1.y = 300; // reposition person y
            person1.x = 600; // reposition person x
            startFade(11); // move to Task 2 scene
        } // Stage 11: trigger help mode (tiger battle option)
        else if (stage == 11 && helpButton.isClicked(mouseX, mouseY)) { // if the help button is clicked on stage 11
            stage = 11.2; // switch to tiger battle mode
        } // Stage 11.1: retry fight
        else if (stage == 11.1 && returnBut.isClicked(mouseX, mouseY)) { // if the return button is clicked on stage 11.1
            attackActive = true; // restart combat system
            playerhealth = 100; // reset player health
            demon.setHealth(100); // reset demon health
            stage = 11; // return to fight scene
        } // Stage 11.2: finish tiger battle
        else if (stage == 11.2 && nextButton.isClicked(mouseX, mouseY)) { // if the next button is clicked on stage 11.2
            person1.changePerspective(pfp1); // reset player animation
            person1.y = 300; // position person y
            person1.x = 450; // position person x
            startFade(12); // move to final task
        } // Stage 12: activate magicbed ability
        else if (stage == 12 && helpButton.isClicked(mouseX, mouseY)) { // if help button is clicked on stage 12
            magicbed.accessItem(); // use magicbed to help climb sky
        } // Stage 14: continue drum scene
        else if (stage == 14 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height) { // if screen is clicked on stage 14
            stage = 14.1; // move to success screen
        } // Stage 14.1: finish game transition
        else if (stage == 14.1 && mouseX >= 0 && mouseX <= width && mouseY >= 0 && mouseY <= height) { // if screen is clicke don stage 14.1
            startFade(15); // go to ending scene
        } // Stage 15: final leaderboard entry
        else if (stage == 15 && finButton.isClicked(mouseX, mouseY)) { // if the finish button is clicked on stage 15
            finalTime = (millis() - startTime) / 1000; // calculate completion time
            leaderboard[leaderboardCount][0] = currentUsername; // store player name
            leaderboard[leaderboardCount][1] = String.valueOf(finalTime); // store completion time
            leaderboardCount++; // increase leaderboard size
            saveScore(); // save score to file/storage
            loadLeaderboard(); // reload leaderboard data
            sortLeaderboard(); // sort the leaderboard
            startFade(16); // move to leaderboard screen
        } // end if
    } // end method
    
    //=============================FILE CODE============================
    /**
     * saves the username entered by the player into a global variable
     * @param username the username entered by the user
     */
    private void saveUsername(String username) { // start method with one parameter
        currentUsername = username; // store the player's username for later use (leaderboard + save file)
    } // end method

    /**
     * saves the player's final score (name + time) into a text file
     */
    private void saveScore() { // start method with no parameter
        try { // try the commands below
            FileWriter w = new FileWriter("leaderboard.txt", true); // open leaderboard file in append mode (do not overwrite existing scores)
            PrintWriter fileOutput = new PrintWriter(w); // create writer to write formatted text into file
            fileOutput.println(currentUsername + "," + finalTime); // save username and completion time in CSV format
            fileOutput.close(); // close file to ensure data is properly written
            System.out.println("Saved: " + currentUsername + " " + finalTime); // debug message to confirm save worked
        } catch (IOException e) { // handle errors that may occur while writing to file
            System.out.println("Error saving file: " + e.getMessage()); // print error message for debugging
        } // end try-catch
    } // end method

    /**
     * loads leaderboard data from text file and rebuilds in-game leaderboard array
     */
    private void loadLeaderboard() { // start method with no parameters
        String[] lines = loadStrings("leaderboard.txt"); // read all lines from leaderboard file
        if (lines == null) { // if file does not exist or is empty
            return; // exit loading function safely
        } // end if
        leaderboardCount = 0; // reset leaderboard counter before loading new data
        for (int i = 0; i < lines.length; i++) { // loop through each saved score entry
            String[] parts = split(lines[i], ','); // split each line into name and time
            if (parts.length == 2) { // ensure valid format (name,time)
                leaderboard[leaderboardCount][0] = parts[0]; // store player name
                leaderboard[leaderboardCount][1] = parts[1]; // store completion time
                leaderboardCount++; // move to next leaderboard slot
            } // end if
        } // end for loop
    } // end method

    /**
     * Method to sort the times of the leaderboard and create a ranking of the users
     */
    private void sortLeaderboard() { // start method to sort leaderboard from fastest to slowest time
        for (int i = 0; i < leaderboardCount - 1; i++) { // loop through each entry (outer loop picks current position)
            for (int j = i + 1; j < leaderboardCount; j++) { // compare current entry with all following entries
                int timeI = Integer.parseInt(leaderboard[i][1]); // convert stored time (string) into integer for comparison
                int timeJ = Integer.parseInt(leaderboard[j][1]); // convert second time into integer for comparison
                if (timeJ < timeI) { // if the later entry has a better (lower) time, swap them
                    // swap names
                    String tempName = leaderboard[i][0]; // temporarily store name at position i
                    leaderboard[i][0] = leaderboard[j][0]; // move better name into position i
                    leaderboard[j][0] = tempName; // move old name into position j
                    // swap times
                    String tempTime = leaderboard[i][1]; // temporarily store time at position i
                    leaderboard[i][1] = leaderboard[j][1]; // move better time into position i
                    leaderboard[j][1] = tempTime; // move old time into position j
                } // end if swap condition
            } // end inner loop
        } // end outer loop
    } // end method
    
    //=============================BUTTON CODE============================
    /**
     * button UI class used for clickable UI elements (start, next, help, etc.)
     */
    private class Button { // start button class
        private float x, y, w, h; // position and size of button variables
        private final String label; // text displayed on the button variables
        private PApplet parent; // reference to Processing sketch  variable

        /**
         * The main constructor to set information for the button
         * @param p PApplet p
         * @param x float x for location
         * @param y float y for location
         * @param w float w for width
         * @param h float h for height
         * @param label String label for written message
         */
        Button(PApplet p, float x, float y, float w, float h, String label) { // start button contructor
            this.parent = p; // store reference to main sketch
            this.x = x; // set button x position
            this.y = y; // set button y position
            this.w = w; // set button width
            this.h = h; // set button height
            this.label = label; // set button text
        } // end constructor

        /**
         * The way the button is displayed
         */
        void display() { // create display method
            fill(150, 100, 200); // button background color
            stroke(120, 70, 170); // button border color
            strokeWeight(2); // border thickness
            rect(x, y, w, h, 3); // draw rounded rectangle button

            fill(255); // set text color to white
            textSize(12); // set button text size
            textAlign(CENTER, CENTER); // center text inside button
            text(label, x + w / 2, y + h / 2); // draw button label

            textAlign(LEFT); // reset alignment for rest of UI
        } // end method
        
        /**
         * A certain action is performed if the button is clicked
         * @param mx
         * @param my
         * @return 
         */
        boolean isClicked(float mx, float my) { // create is clicked method
            return mx >= x && mx <= x + w && my >= y && my <= y + h; // check if mouse is inside button bounds
        } // end method
    } // end class

    /**
     * checks if the mouse is hovering over a character and shows info if true
     * @param c the character we are checking
     */
    public void checkHover(Character c) {
        if (c.isHovered(mouseX, mouseY)) { // if mouse is over the character
            c.displayInfo(); // show character information popup/text
        } // end if
    } // end method
} // end entire class
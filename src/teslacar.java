import java.awt.*;

public class teslacar {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Image pic;
    public Rectangle rec;
    public SoundFile song;
    public SoundFile music;
    public boolean left, right, up, down;   //declared multiple variables on one line


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public teslacar(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 5;
        dy = 5;
        width = 125;
        height = 60;
        isAlive = true;
        down = false;
        up = false;
        left = false;
        right = false;
        rec = new Rectangle(xpos, ypos, width, height);
        song = new SoundFile("Steelbar.wav");


    } // constructor


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {


            if (up == true) {
                ypos = ypos - dy; //move up
            }
            if (down == true) {
                ypos = ypos + dy; // move down
            }
            if (left == true) {
                xpos = xpos - dy;
            }
            if (right == true) {
                xpos = xpos + dx;
            }
            if (ypos < 0) {
                ypos = 0;
            }
            if (ypos > 740){
                ypos = 740;
            }
            if (xpos < 0){
                xpos = 0;
            }
            if (xpos > 910){
                xpos = 910;
            }


            //always rebuild or update the rectangle after you've changed an object's position, height or width
            rec = new Rectangle(xpos, ypos, width-10, height-10);    //construct a rectangle


        }
    }










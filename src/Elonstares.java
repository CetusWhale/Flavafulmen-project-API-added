import java.awt.*;

public class Elonstares {
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


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Elonstares(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 2;
        dy = 2;
        width = 400;
        height = 400;
        isAlive = true;

    } // constructor


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        int randomN = (int) (Math.random()*10);

        if (xpos > 1200) {
            dx = -randomN;
            dy = -randomN;
        }

        if (xpos < -800) {
            dx = randomN;
            dy = randomN;
        }

        if (ypos > 1200) {
            dy = -randomN;
        }

        if (ypos < -800) {
            dy = randomN;
        }


        //always rebuild or update the rectangle after you've changed an object's position, height or width
        rec = new Rectangle(xpos, ypos, width-100, height-100);    //construct a rectangle


    }

}


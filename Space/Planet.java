import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Planet {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public int xpos;				//the x position
    public int ypos;				//the y position
    public boolean isAlive;			//a boolean to denote if the object is alive or dead.
    public int width;
    public int height;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.
    public Planet(int xParameter, int yParameter)
    {
        xpos = xParameter;
        ypos = yParameter;
        isAlive=true;
        width = 80;
        height= 80;

    } // constructor


}

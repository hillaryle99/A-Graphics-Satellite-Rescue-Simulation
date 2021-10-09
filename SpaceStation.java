import java.util.Random;
/**
 * The space station moves diagonally from upper-left to lower right on the
 * canvas. It is green on the first orbit (the first time it moves on the diagonal), 
 * yellow on the second orbit, and red thereafter, until a new refueling operation
 * begins.
 * 
 * @author Thu Le & Jiamu Chen
 * @version October 2020
 */

public class SpaceStation
{
    private Diamond spaceStation;
    private Canvas canvas;
    private int initXPosition; // where to start the shuttle (a random x-coordinate
                               // in the canvas boundary
    private int initYPosition; // the initial y-coordinate is always sero
    private int xDistance;     // the distance to move in the x direction
    private int yDistance;     // the distance to move in the y direction
    private int orbitCount;    // the number of orbits completed, used for color change control
    private Random r;          // a random number generator
  
    /**
     * Creates a space shuttle as a Diamond object at a random position at the top of
     * the simulation window. Once the space station is initialized, it should begin
     * to orbit.
     * 
     * @param canvas -- the canvas on which to render the space station
     */
    public SpaceStation(Canvas simCanvas)
    {
        canvas = simCanvas;
        spaceStation = new Diamond(simCanvas);
        spaceStation.changeSize(50,80);
        r = new Random();
        /** To Do: Complete the initialization of the spaceStation:
 *      set its initXPosition to a random integer between 0 and the width of the canvas-100
 *      set its initYPosition so that its upper vertex is at the canvas upper border
 *      move it to its initXPosition, initYPosition
 *      set its color to "green"
 *      make it visible
 */
        initXPosition = r.nextInt(canvas.getWidth()); 
        initYPosition = 50; 
        spaceStation.moveTo(initXPosition, initYPosition);
        spaceStation.changeColor("green"); 
        spaceStation.makeVisible();
    }

    /**      
     * Handle orbiting details:
     * (1) move the space station, if it is within canvas boundaries
     * (2) begin another orbit at a lower starting point, if space station reaches the 
     * right side of the canvas. Do this by computing new initXPosition and initYPosition
     * and then by moving there
     * (2a) update orbitCount
     * (2b) if space station completed 1 orbit, change its color to "yellow"
     * (2c) if space station completed 2 orbits, change its color to "red"
     * 

     */
    public void moveSmallDistance()
    {
        
        if(orbitCount == 1){  
            spaceStation.changeColor("yellow"); }

        else if(orbitCount >= 2) 
        {
            spaceStation.changeColor("red"); 
        }

        if(spaceStation.getXPosition() < canvas.getWidth() && spaceStation.getYPosition() < canvas.getHeight() ) {
            spaceStation.moveDirection(xDistance,yDistance);

            //spaceStation.setSpeed()
            //spaceStation.moveTo(spaceStation.getXPosition() + 15, spaceStation.getYPosition() + 15);
         canvas.wait(60);  // Slows down the animation a bit so you can see what's happening

        }
        else 
        {
            orbitCount++; 
            if(initXPosition - 70 > 0) {  
                spaceStation.moveTo((initXPosition - 150) ,0);
                initXPosition = spaceStation.getXPosition(); 

            }
            else 
            { 
                spaceStation.moveTo(10,0);
            }
        }
    }
    
   /**
  * To Do: reFuel the refueling process. The fuel cell has docked, so:
  * (1) change color back to "green"
  * (2) wait 3 seconds
  */
    public void reFuel()
    {
        spaceStation.changeColor("green");
        canvas.wait(3000); 
    }
  
    /**
  * setSpeed set the orbiting speed according to 'B', 'I', or 'A'.
  *    You can experiment with xDistance and yDistance for this.
  *    To Do: Write the body of the method
  */
    public void setSpeed(char newSpeed)
    {
        
        if(newSpeed== 'B') 
        { xDistance = 2; 
            yDistance= 1; 
        }
        else if( newSpeed== 'I') 
        {xDistance = 5; 
            yDistance = 2;  
        }
        else if( newSpeed== 'A') 
        { xDistance = 8; 
            yDistance = 3; 
        }  
        
    }

    /**
  * getXposition a simple acccessor used for the docking proceses
  * 
  * @return xPosition the current x-coordinate of the space station
  * To Do: Write the body of this method
  */
    public int getXPosition()
    {
        return spaceStation.getXPosition();
    }

    /**
  * getYposition a simple acccessor used for the docking proceses
  * 
  * @return yPosition the current y-coordinate of the space station
  *  * To Do: Write the body of this method
  */
    public int getYPosition()
    {
       return spaceStation.getYPosition();
    }
}

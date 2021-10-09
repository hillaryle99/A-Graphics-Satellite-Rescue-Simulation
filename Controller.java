import java.awt.event.*;

/**
 * This class plays the role of controller of the simulation.
 * It creates the shuttle and space station objects as well as
 * the supporting canvas and label objects.
 * 
 * @author Thu Le & Jiamu Chen
 * @version October 2020
 */
public class Controller
    implements KeyListener
{
    private SpaceStation spaceStation;
    private FuelCell fuelCell;
    private Circle[] star;
    private Shuttle shuttle;
    private Canvas canvas;
    private boolean sim;             // Used to determine whether (true) or 
                                     //      not (false)to continue the simulation 
    private boolean speedSet;        // Used to sequence the label at the top of the simulation window
    private boolean moveShuttle;     // true to move the shuttle up or down once
    private boolean launchFuelCell;  // true to launch a fuel cell
    private boolean fuelCellInMotion;
    private boolean inOrbit;         // true if the space station is orbiting
    private Label label;             // The label at the top of the simulation window
    
   /**
     * Class constructor. Doesn't do much.
     */
    public Controller()
    {
        canvas = new Canvas("Space Station Fueling Simulator",800,600);
        canvas.setListener(this);
        spaceStation = new SpaceStation(canvas);
        shuttle = new Shuttle(canvas,spaceStation);
        // Create 100 stars in the sky
        star= new Circle[100];
        int appletHeight = canvas.getHeight();
        int appletWidth = canvas.getWidth();
        for(int i=0;i<100;i++){
            star[i]=new Circle(canvas);
            star[i].changeSize(5);
            star[i].moveTo((int)(Math.random()*appletWidth),(int)(Math.random()*appletHeight));
            star[i].changeColor("white");
            star[i].makeVisible();
        }
    }

    /**
     * startSimulation creates the simulation environment and runs the simulation
     * using an indefinite while loop that looks at the state of the simulation
     * and acts accordingly each time through the loop.
     */
    public void simulate()
    {
        speedSet = false;           // speed not set yet
        label = new Label("Choose speed: B, I, or A", 350, 15,canvas);
        label.makeVisible();
        canvas.setVisible(true);

        moveShuttle = false;        // don't move the shuttle until the 'J' or 'K'  key is pressed
        launchFuelCell = false;    
        fuelCellInMotion = false;

        sim = true;
        while (sim) // wait 3 seconds for the shuttle to travel back
        {   if (inOrbit)
            {
                spaceStation.moveSmallDistance(); 
            }
            if (moveShuttle)
            {
                shuttle.moveSmallDistance();
                moveShuttle = false;
            }
            if (launchFuelCell && shuttle.getNumberFuelCells()>0)
            {
                fuelCell = shuttle.launchCell();
                launchFuelCell = false;
                fuelCellInMotion = true;
            }          
            if (fuelCellInMotion) // The cell has been launched
            {
                fuelCell.moveSmallDistance();
                if (fuelCell.status().equals("refueled"))
                {
                    shuttle.dock();
                    canvas.wait(3000);
                    sim = false;
                }
                if (!fuelCell.status().equals("moving"))
                {
                    fuelCellInMotion = false;
                }
            }      
            if (spaceStation.getYPosition()>=canvas.getHeight())
            {
                canvas.wait(3000);
                sim = false;
            }
            canvas.wait(30);
        }   
        canvas.setVisible(false);        
    }
   
   
    public void keyTyped(KeyEvent ke)
    {
        char myChar = Character.toUpperCase(ke.getKeyChar());
        if (myChar == 'J')
        {
            moveShuttle = true;
            shuttle.setDirection("up");
        }    
         if (myChar == 'K')
        {
            moveShuttle = true;
            shuttle.setDirection("down");
        } 
        /**
         * To Do: add logic here to move the shuttle "up"
         */
        
        else if (myChar == ' ' && !fuelCellInMotion)
        {   
            launchFuelCell = true;
        }    
        else if (myChar == 'S' && speedSet)
        {
            label.setText("Up: J, Down: K, Launch: Space");
            inOrbit = true;
        }    
        else if (myChar == 'B' || myChar == 'I' || myChar == 'A')
        {
            spaceStation.setSpeed(myChar);
            speedSet = true;
            label.setText("To start: S, To Stop: X");
        }    
        else if (myChar == 'X')
        {
            sim = false;
        }    
    }

    /**
     * Ignore methods below this point
     */
    public void keyReleased(KeyEvent ke)
    {
    }

    public void keyPressed(KeyEvent ke)
    {
    }
}  
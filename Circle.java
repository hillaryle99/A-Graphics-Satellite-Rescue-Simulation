import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @Author: Thu Le & Jiamu Chen
 * October 2020
 */

public class Circle
{
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private Canvas canvas;
    
    /**
     * Create a new circle with a default positionm default color and default size
     */
    public Circle(Canvas canvas)
    {
        diameter = 30;
        xPosition = 50;
        yPosition = 50;
        color = "blue";
        isVisible = false;
        this.canvas = canvas;
      }

    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this circle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
     /**
     * Move the center of the Circle object to the location defined by xDistance and yDistance
     * @param  distanceX -- how far to move right or left in the horizontal direction
     * @param  distanceY -- how far to move right or left in the vertical direction
     */
    public void moveDirection(int xDistance, int yDistance)
    {
       erase();
       yPosition += yDistance;
       xPosition += xDistance;
       draw();
   }
        
     /**
     * Move the center of the Circle object the distance defined by xDistance and yDistance
     * @param  distanceX -- how far to move left or right in the horizontal direction
     * @param  distanceY -- how far to move up or down in the vertical direction
     */
    public void moveTo(int newXPosition, int newYPosition)
    {
       erase();
       yPosition = newYPosition;
       xPosition = newXPosition;
       draw();
   }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newDiameter)
    {
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }

    /**
     * Draw the circle with current specifications (color, size, center) on the canvas
     */
    private void draw()
    {
        if(isVisible) {
            canvas.draw(this, color, new Ellipse2D.Double(xPosition-diameter/2, yPosition-diameter/2, 
                                                          diameter, diameter));
        }
    }

    /**
     * Remove all graphics from the canvas
     */
    public void erase()
    {
        if(isVisible) {
            canvas.erase(this);
        }
    }

    /**
     * @return xPosition -- the x coordinate of the center of the circle.
     */
    public int getXPosition()
    {
        return xPosition;
    }
    
    /**
     * @return yPosition -- the y coordinate of the center of the circle.
     */
    public int getYPosition()
    {
        return yPosition;
    }

    /**
     * @return diameter -- the "size" of the circle
     */
    public int getDiameter()
    {
        return diameter;
    }
    
    
}
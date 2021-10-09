import java.awt.*;

/**
 * A diamond that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling & David J. Barnes
 * @version 1.0  (15 July 2000)
 * 
 * Revised by Thu Le & Jiamu Chen
 * @version Oct 2020
 */

public class Diamond
{
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private Canvas canvas;

    /**
     * Create a new circle with a default position, default color and default size
     */
    public Diamond(Canvas canvas)
    {
        height = 30;
        width = 40;
        xPosition = 50;
        yPosition = 50;
        color = "green";
        isVisible = false;
        this.canvas = canvas;
    }

    /**
     * Make this diamond visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this diamond invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
     /**
     * Move the lowest vertex of the Diamond object to the location defined by xDistance and yDistance
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
     * Move the lowest vertex of the Diamond object the distance defined by xDistance and yDistance
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
    public void changeSize(int newHeight, int newWidth)
    {
        erase();
        height = newHeight;
        width = newWidth;
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
     * Draw the diamond with current specifications (color, size, center) on the canvas
     */
    private void draw()
    {
        if(isVisible) 
        {
            Polygon myPoly = new Polygon();                                //Points in order drawn:
            myPoly.addPoint(xPosition,yPosition);                          //First
            myPoly.addPoint(xPosition -(width/2),yPosition - (height/2));  //Second
            myPoly.addPoint(xPosition,yPosition - height);                 //Third
            myPoly.addPoint(xPosition + (width/2),yPosition - (height/2)); //Fourth
            canvas.draw(this, color, myPoly);
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
     * @return xPosition -- the x coordinate of the lowest vertex of the diamond
     */
    public int getXPosition()
    {
        return xPosition;
    }
    
    /**
     * @return yPosition -- the y coordinate of the lowest vertex of the diamond
     */
    public int getYPosition()
    {
        return yPosition;
    }

    /**
     *@return width -- measure of the horizontal axis of the diamond
     */
    public int getWidth()
    {
        return width;
    }

    /**
     *@return height -- measure of the vertical axis of the diamond
     */
    public int getHeight()
    {
        return height;
    }
}

//assignment8_00802106
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *Implementation of the Shape class in which Oval and Rectangle extend from
 */

public abstract class Shape {
    /** stroke colour of shape**/
    private Color strokeColour;
    /** x-coordinate **/
    private double x;
    /** y-coordinate **/
    private double y;
    /** width of shape**/
    private double width;
    /**height of shape**/
    private double height;
    /** fill colour of shape**/
    private Color fillColour;
    /** stroke width of shape**/
    private int strokeWidth;

    //constructor for Shape
    public Shape (double x, double y, double width,double height, int strokeWidth, Color strokeColour, Color fillColour) {
        this.x = x;
        this.y = y;
        this. width = width;
        this.height = height;
        this.strokeColour = strokeColour;
        this.strokeWidth = strokeWidth;
        this.fillColour = fillColour;

    }

    /**
     * method returns fill colour of the shape
     * @return fillColour
     */
    public Color getFillColour(){
        return fillColour;
    }

    /**
     * method returns stroke colour of the shape
     * @return strokeColour
     */
    public Color getStrokeColour(){
        return strokeColour;
    }

    /**
     * method returns x-coordinate of where the shape will be drawn
     * @return x
     */
    public double getX (){
        return x;
    }

    /**
     * method returns stroke width of the shape
     * @return strokeWidth
     */
    public int getStrokeWidth(){return strokeWidth;}

    /**
     * method returns y-coordinate of where the shape will be drawn
     * @return y
     */
    public double getY () {
        return y;
    }

    /**
     * method returns width of the shape
     * @return width
     */
    public double getWidth(){
        return width;
    }

    /**
     * method returns height of the shape
     * @return height
     */
    public double getHeight(){
        return height;
    }

    /**
     * abstract method that is used in child class Oval and Rectangle
     * @param gc
     */
    public abstract void draw (GraphicsContext gc);



}

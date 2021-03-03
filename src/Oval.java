//assignment8_00802106
/**
 * Draws an oval based on the user's input
 * @author Rhagavy Rakulan
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Oval extends Shape {

    //constructor for Oval
    public Oval (double x, double y, double width, double height, int strokeWidth, Color strokeColour, Color fillColour){
        super(x,y,width,height,strokeWidth,strokeColour,fillColour );
    }


    /**
     * method will draw the oval based on user's selection of colours and input for stroke width
     * @param gc
     */
    public void draw (GraphicsContext gc){
        gc.setStroke(getStrokeColour());
        gc.setLineWidth(getStrokeWidth());
        gc.strokeOval(getX(),getY(),getWidth(),getHeight());
        gc.setFill(getFillColour());
        gc.fillOval(getX(),getY(),getWidth(),getHeight());

    }
}

//assignment8_00802106
/**
 * Draws an rectangle based on the user's input
 * @author Rhagavy Rakulan
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//constructor for Rectangle
public class Rectangle extends Shape {

    public Rectangle (double x, double y, double width, double height, int strokeWidth, Color strokeColour, Color fillColour){
        super(x,y,width,height,strokeWidth,strokeColour,fillColour );

    }

    /**
     * method will draw the oval based on user's selection of colours and input for stroke width
     * @param gc
     */
    public void draw (GraphicsContext gc){
        gc.setStroke(getStrokeColour());
        gc.setLineWidth(getStrokeWidth());
        gc.strokeRect(getX(),getY(),getWidth(),getHeight());
        gc.setFill(getFillColour());
        gc.fillRect(getX(),getY(),getWidth(),getHeight());
    }


}

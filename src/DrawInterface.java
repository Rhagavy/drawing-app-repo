//assignment8_00802106
/**
 * Program is a drawing application that allows user to draw two types of shapes: oval and rectangle.
 * The user can pick the outline and fill colour for the shape and can adjust the stroke width as well.
 * User may undo the last shape drawn and clear the canvas if wanted.
 * @author Rhagavy Rakulan
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;



public class DrawInterface extends Application {
    /**text field of line width**/
    TextField lineWidth;
    /**label for draw**/
    Label draw;
    /**label for Outline**/
    Label outline;
    /**label for Filler**/
    Label filler;
    /**label for Stroke Width **/
    Label sw;
    /**label for Instruction Header**/
    Label instructionsHeader;
    /**label for Instructions**/
    Label instructions;

    /**user's selected shae**/
    private String selectedShape;
    /**array list for shapes drawn**/
    private ArrayList<Shape> shapes= new ArrayList<>();
    /**x-coordinate from first mouse click**/
    private double startX;
    /**y-coordinate from first mouse click**/
    private double startY;
    /**gc**/
    private GraphicsContext gc;
    /**colour picker for outline**/
    private ColorPicker cp1;
    /**colour picker for filler**/
    private ColorPicker cp2;
    /**stroke width**/
    private int strokeWidth;


    /**
     * method saves user's selected shape
     * @param e
     */
    private void selectShape(ActionEvent e){
        selectedShape = ((Button)e.getSource()).getText();

    }

    /**
     * method saves x-coordinate and y-coordinate from first mouse click
     * @param e
     */
    private void clickMouseDown (MouseEvent e){
        startX= e.getX();
        startY= e.getY();
    }

    /**
     * method creates shape based calculating the size and user's colour
     * and stoke width choice.Alerts user if criteria to create a shape hasn't
     * been met.
     * @param e
     */
    private void clickMouseUp(MouseEvent e){
        
        if(e.getX()>startX) {
            double deltaX = e.getX() - startX;
            double deltaY = e.getY() - startY;
            if(selectedShape==null){
                new Alert(Alert.AlertType.WARNING, "Select a shape before you start!").showAndWait();

            }

            else if (selectedShape.equals("Oval")){
                shapes.add(new Oval(startX,startY,deltaX,deltaY,strokeWidth,cp1.getValue(),cp2.getValue()));

            }

            else if (selectedShape.equals("Rectangle")){
                shapes.add(new Rectangle(startX,startY,deltaX,deltaY,strokeWidth,cp1.getValue(),cp2.getValue()));
            }
        }
        else{
            double deltaX = startX - e.getX();
            double deltaY = startY - e.getY();
            if(selectedShape==null){
                new Alert(Alert.AlertType.WARNING, "Select a shape before you start!").showAndWait();

            }

            else if (selectedShape.equals("Oval")){
                shapes.add(new Oval(startX,startY,deltaX,deltaY,strokeWidth,cp1.getValue(),cp2.getValue()));

            }

            else if (selectedShape.equals("Rectangle")){
                shapes.add(new Rectangle(startX,startY,deltaX,deltaY,strokeWidth,cp1.getValue(),cp2.getValue()));
            }
        }

        draw(gc);

    }

    /**
     * method sets stroke width based on user's input after enter key is pressed
     * @param e
     */
    private void setStrokeWidth(KeyEvent e){
        if (e.getCode()== KeyCode.ENTER) {

            try {
                int tempStrokeWidth = Integer.parseInt(lineWidth.getText());
                if(tempStrokeWidth<0){
                    throw new Exception("No negative numbers!");
                }
                strokeWidth=tempStrokeWidth;

            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.WARNING, "Only enter positive numbers!").showAndWait();
                lineWidth.setText("");

            }
            catch (Exception ex){
                new Alert(Alert.AlertType.WARNING, ex.getMessage()).showAndWait();
                lineWidth.setText("");
            }

        }
    }

    /**
     * method draws canvas with shape(s) and border
     * @param gc
     */
    private void draw (GraphicsContext gc){
        gc.clearRect(0,0,1100,850);
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,1100,850);
        border(gc);
        for(Shape shape: shapes){
            shape.draw(gc);
        }
    }

    /**
     * method draws a border
     * @param gc
     */
    private void border (GraphicsContext gc){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeLine(1100,0,1100,850);


    }

    /**
     * method will remove the last shape drawn
     * @param e
     */
    private void undoDraw(ActionEvent e){
        if(shapes.size()>0) {
            shapes.remove(shapes.size() - 1);
        }
        else {
            new Alert(Alert.AlertType.WARNING, "No shapes drawn!").showAndWait();
        }
        draw(gc);
    }

    /**
     * method clears the canvas by resetting the shapes
     * array list
     * @param e
     */
    private void clearCanvas(ActionEvent e){
        gc.clearRect(0,0,1100,850);
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,1100,850);
        border(gc);
        shapes = new ArrayList<>();
    }

    /**
     *
     * @param stage
     * @throws Exception
     */
    public void start(Stage stage) throws Exception{
        //setting canvas and stage
        Group root = new Group();
        Scene scene = new Scene(root, 1500,850,Color.LIGHTGREY);
        Canvas canvas = new Canvas(1100, 850);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        gc = canvas.getGraphicsContext2D();

        stage.setTitle("Drawing App (Rhagavy Rakulan)");
        draw(gc);

        //GUI components
        lineWidth = new TextField("");

        //create buttons and labels
        Button makeOval = new Button("Oval");
        Button makeRectangle = new Button ("Rectangle");
        Button undo = new Button("Undo");
        Button clear = new Button("Clear");
        cp1 = new ColorPicker();
        cp2 = new ColorPicker();
        draw = new Label("Draw: ");
        outline = new Label ("Outline Colour: ");
        filler = new Label ("Fill Colour: ");
        sw = new Label("Stroke Width:");
        instructionsHeader = new Label("Instructions:");
        instructions = new Label ("1. Click the shape and colour. \n"+"2. Type in a number for the stroke width\n"+ "    and press the Enter key.\n"
                +"3. Press the mouse down and drag.\n" +"4. Release mouse to draw the shape.\n"+"Note: If a stroke width error appears\n"+"press the OK button and enter a valid\n"+ "number.");
        //dashes are used to separate the css and fx means we're using java fx
        //draw.setStyle("-fx-font-weight:bold");


        //setting font for label
        draw.setFont(new Font("Comic Sans MS",22));
        draw.setStyle("-fx-font-weight:bold");
        outline.setFont(new Font("Comic Sans MS",22));
        outline.setStyle("-fx-font-weight:bold");
        filler.setFont(new Font("Comic Sans MS",22));
        filler.setStyle("-fx-font-weight:bold");
        sw.setFont(new Font("Comic Sans MS",22));
        sw.setStyle("-fx-font-weight:bold");
        instructionsHeader.setFont(new Font("Comic Sans MS",22));
        instructionsHeader.setStyle("-fx-font-weight:bold;-fx-underline: true;-fx-text-fill:purple");
        instructions.setFont(new Font("Comic Sans MS",18));
        instructions.setStyle("-fx-font-weight:bold;-fx-text-fill:purple");

        //size of buttons
        makeOval.setPrefSize(150,50);
        makeRectangle.setPrefSize(150,50);
        undo.setPrefSize(150,50);
        clear.setPrefSize(150,50);
        cp1.setPrefSize(150,50);
        cp2.setPrefSize(150,50);

        root.getChildren().addAll(makeOval,makeRectangle,cp1,cp2,draw,outline,filler,sw,lineWidth,instructionsHeader,instructions,undo,clear);

        //button location
        draw.relocate(1150,30);
        makeOval.relocate(1150,65);
        makeRectangle.relocate(1150,125);
        outline.relocate(1150,200);
        cp1.relocate(1150,230);
        filler.relocate(1150,300);
        cp2.relocate(1150,330);
        sw.relocate(1150,400);
        lineWidth.relocate(1320,405);
        lineWidth.setPrefSize(65,20);
        undo.relocate(1150,475);
        clear.relocate(1325,475);
        instructionsHeader.relocate(1115,600);
        instructions.relocate(1115,650);

        //button style
        makeOval.setFont(Font.font("Comic Sans MS",18));
        makeOval.setStyle("-fx-border-color:black;-fx-background-color:plum;-fx-font-weight:bold;-fx-border-width:1.5");
        makeRectangle.setFont(Font.font("Comic Sans MS",18));
        makeRectangle.setStyle("-fx-border-color:black;-fx-background-color:plum;-fx-font-weight:bold;-fx-border-width:1.5");
        cp1.setStyle("-fx-border-color:black;-fx-background-color:plum;-fx-font-weight:bold;-fx-border-width:1.5");
        cp2.setStyle("-fx-border-color:black;-fx-background-color:plum;-fx-font-weight:bold;-fx-border-width:1.5");
        undo.setFont(Font.font("Comic Sans MS",18));
        undo.setStyle("-fx-border-color:black;-fx-background-color:plum;-fx-font-weight:bold;-fx-border-width:1.5");
        clear.setFont(Font.font("Comic Sans MS",18));
        clear.setStyle("-fx-border-color:black;-fx-background-color:plum;-fx-font-weight:bold;-fx-border-width:1.5");
        //clear.setTextFill(Color.DARKRED);


        //listeners for buttons,mouse and keyboard
        makeOval.setOnAction(this::selectShape);
        makeRectangle.setOnAction(this::selectShape);
        clear.setOnAction(this::clearCanvas);
        undo.setOnAction(this::undoDraw);
        lineWidth.setOnKeyReleased(this::setStrokeWidth);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::clickMouseDown);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::clickMouseUp);



        //show the application
        stage.show();

    }

    //launch app
    public static void main(String[] args) {
        launch(args);
    }
}

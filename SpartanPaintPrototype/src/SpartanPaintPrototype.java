
import java.awt.MouseInfo;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

// An alternative implementation of Example 3,
//    using the Timeline, KeyFrame, and Duration classes.
// Animation of Earth rotating around the sun. (Hello, world!)
public class SpartanPaintPrototype extends Application {

    private int x = 100;
    private int y = 100;

    private double window_x = 0;
    private double window_y = 0;
    private double scene_x = 0;
    private double scene_y = 0;
    private int mouse_x = 0;
    private int mouse_y = 0;
    private int points = 0;
    int MAX_POINTS = 1000000;
    private int point_x[] = new int[MAX_POINTS];
    private int point_y[] = new int[MAX_POINTS];
    private int point_width[] = new int[MAX_POINTS];
    private int point_connected[] = new int[MAX_POINTS];
    private Color point_color[] = new Color[MAX_POINTS];
    private int point_undo[] = new int[MAX_POINTS];
    private Color drawColor = Color.BLUE;
    private int drawWidth = 2;
    private int undo = 0;
    private int max_undo = 0;
    private int undo_points = 0;
    private int undo_position[] = new int[MAX_POINTS];
    private int mouseDown = 0;
    
    private int canvas_width = 1024;
    private int canvas_height = 768;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Spartan Paint v.0.1.01");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        window_x = theScene.getWindow().getX();
        window_y = theScene.getWindow().getY();
        scene_x = theScene.getX();
        scene_y = theScene.getY();

        //Canvas
        Canvas canvas = new Canvas(canvas_width, canvas_height);
        root.getChildren().add(canvas);
        Button button_undo = new Button();
        Button button_redo = new Button();
        HBox hbox = new HBox(2); // spacing = 8
        //hbox.getChildren().addAll(new Label("Name:"), new TextBox());
        root.getChildren().add(hbox);

        MenuBar topBar = new MenuBar();
        Menu fileMenu = new Menu();
        fileMenu.setText("File");
        Menu editMenu = new Menu();
        editMenu.setText("Edit");
        Menu helpMenu = new Menu();
        helpMenu.setText("Help");
        MenuItem loadButton = new MenuItem();
        
        MenuItem add = new MenuItem("New Image");        
        fileMenu.getItems().addAll(add);
        add = new MenuItem("Load Image");        
        fileMenu.getItems().addAll(add);
        add = new MenuItem("Save Image");        
        fileMenu.getItems().addAll(add);
        
        add = new MenuItem("New Image");        
        editMenu.getItems().addAll(add);
        add = new MenuItem("Load Image");        
        editMenu.getItems().addAll(add);
        add = new MenuItem("Save Image");        
        editMenu.getItems().addAll(add);
        
        
        topBar.getMenus().addAll(fileMenu,editMenu,helpMenu);
        hbox.getChildren().add(topBar);
        
        //Clear Canvas Button
        Button button_clear_canvas = new Button();
        button_clear_canvas.setText("Clear Canvas");
        button_clear_canvas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                points = 0;
                undo_points = 0;
                button_redo.setDisable(true);
                button_undo.setDisable(true);
                //System.out.println("Hello World!");
            }
        });
        hbox.getChildren().add(button_clear_canvas);

        //Undo Button
        button_undo.setText("Undo");
        button_undo.setDisable(true);
        button_undo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (undo_points > 0)
                {
                button_redo.setDisable(false);
                points = undo_position[undo_points-1];
                undo_points--;
                if (undo_points == 0)
                    button_undo.setDisable(true);
                }
            }
        });
        hbox.getChildren().add(button_undo);
        
        //Redo Button
        button_redo.setText("Redo");
        button_redo.setDisable(true);
        button_redo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (undo_points < max_undo)
                {
                points = undo_position[undo_points+1];
                undo_points++;
                }
            }
        });
        hbox.getChildren().add(button_redo);

        //Set color to red button
        Button button_red = new Button();
        button_red.setText("Red");
        button_red.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawColor = Color.RED;
            }
        });
        hbox.getChildren().add(button_red);

        //Set color to yellow button
        Button button_yellow = new Button();
        button_yellow.setText("Yellow");
        button_yellow.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawColor = Color.YELLOW;
            }
        });
        hbox.getChildren().add(button_yellow);

        //Set color to blue button
        Button button_blue = new Button();
        button_blue.setText("Blue");
        button_blue.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawColor = Color.BLUE;
            }
        });
        hbox.getChildren().add(button_blue);

        //Set width to 1 button
        Button button_width_1 = new Button();
        button_width_1.setText("Width: 1");
        button_width_1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawWidth = 1;
            }
        });
        hbox.getChildren().add(button_width_1);

        //Set width to 2 button
        Button button_width_2 = new Button();
        button_width_2.setText("Width: 2");
        button_width_2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawWidth = 2;
            }
        });
        hbox.getChildren().add(button_width_2);

        //Set width to 3 button
        Button button_width_3 = new Button();
        button_width_3.setText("Width: 3");
        button_width_3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawWidth = 3;
            }
        });
        hbox.getChildren().add(button_width_3);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        /*
         Image earth = new Image( "earth.png" );
         Image sun   = new Image( "sun.png" );
         Image space = new Image( "space.png" );
         */

        Timeline paintLoop = new Timeline();
        paintLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();

        root.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.PRIMARY) {
                    mouseDown = 0;
                    button_undo.setDisable(false);
                }
                if (t.getButton() == MouseButton.SECONDARY) {
                };
            }
        });
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.PRIMARY) {
                    undo = 0;
                    undo_position[undo_points] = points;
                    
                    if (undo_points == max_undo)
                        max_undo++;
                    undo_points++;
                    
                }
                if (t.getButton() == MouseButton.SECONDARY) {
                };
            }
        });
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.PRIMARY) {
                    point_x[points] = mouse_x;
                    point_y[points] = mouse_y;
                    point_width[points] = drawWidth;
                    point_connected[points] = mouseDown;
                    point_color[points] = drawColor;
                    point_undo[points] = undo;
                    points++;
                    undo++;
                    mouseDown = 1;
                }
                if (t.getButton() == MouseButton.SECONDARY) {
                };
            }
        });

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), // 60 FPS
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                    //double t = (System.currentTimeMillis() - timeStart) / 1000.0; 

                        //double x = 232 + 128 * Math.cos(t);
                        //double y = 232 + 128 * Math.sin(t);
                        window_x = theScene.getWindow().getX();
                        window_y = theScene.getWindow().getY();
                        scene_x = theScene.getX();
                        scene_y = theScene.getY();

                        mouse_x = MouseInfo.getPointerInfo().getLocation().x - ((int) window_x) - ((int) scene_x);
                        mouse_y = MouseInfo.getPointerInfo().getLocation().y - ((int) window_y) - ((int) scene_y);

                        
                        // Clear the canvas
                        gc.clearRect(0, 0, canvas_width, canvas_height);
                        //gc.fillRect(0,0,x + 50,y + 50);
                        gc.setFill(Color.BLUE);
                        x++;
                        gc.fillRect(mouse_x - 5, mouse_y - 5, 10, 10);

                        /*
                         if (MouseEvent.isPrimaryButtonDown())
                         {
                    
                         } 
                         else 
                         {
                    
                         }*/
                        for (int i = 0; i < points; i++) {
                            //gc.setFill(point_color[i]);
                            gc.setStroke(point_color[i]);
                            //gc.fillRect(point_x[i]-5,point_y[i]-5, 10, 10);
                            if (i > 0) {
                                //Line theLine = new Line(point_x[i-1], point_y[i-1], point_x[i], point_y[i]);
                                if (point_connected[i] == 1)
                                         {
                                    gc.setStroke(point_color[i-1]);
                                    gc.setLineWidth(point_width[i]);
                                    gc.strokeLine(point_x[i - 1], point_y[i - 1], point_x[i], point_y[i]);
                                }
                            }
                        }

                        String str1 = Double.toString(window_x);// + " , " + Double.toString(window_y);

                        //gc.fillText(str1, 200, 200); //draw screen position text
                        // background image clears canvas
                    /*
                         gc.drawImage( space, 0, 0 );
                         gc.drawImage( earth, x, y );
                         gc.drawImage( sun, 196, 196 );
                         */
                    }
                });

        paintLoop.getKeyFrames().add(kf);
        paintLoop.play();

        theStage.show();
    }

    public void button(String text) {

    }
}

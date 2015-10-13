
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

// An alternative implementation of Example 3,
//    using the Timeline, KeyFrame, and Duration classes.

// Animation of Earth rotating around the sun. (Hello, world!)
public class JavaFXGameExample extends Application 
{
    private int Jake = 1001;
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
    
    
    public static void main(String[] args) 
        {
        launch(args);
        }
    
    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Timeline Example" );
        
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        window_x = theScene.getWindow().getX();
        window_y = theScene.getWindow().getY();
        scene_x = theScene.getX();
        scene_y = theScene.getY();
        
        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );
        Button btn1 = new Button();
        btn1.setText("Clear Canvas");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                points = 0;
                //System.out.println("Hello World!");
            }
        });
        root.getChildren().add(btn1);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        /*
        Image earth = new Image( "earth.png" );
        Image sun   = new Image( "sun.png" );
        Image space = new Image( "space.png" );
        */
        
        Timeline paintLoop = new Timeline();
        paintLoop.setCycleCount( Timeline.INDEFINITE );
        
        final long timeStart = System.currentTimeMillis();
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
            if(t.getButton() == MouseButton.PRIMARY) 
                {
                    point_x[points] = mouse_x;
                    point_y[points] = mouse_y;
                    points++; 
                    
                };
            if(t.getButton() == MouseButton.SECONDARY) {};
            }
        });

        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.017),                // 60 FPS
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
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
                    gc.clearRect(0, 0, 512,512);
                    //gc.fillRect(0,0,x + 50,y + 50);
                    gc.setFill(Color.BLUE);
                    x++;
                    gc.fillRect(mouse_x-5,mouse_y-5, 10, 10);
                    
                    
                    /*
                    if (MouseEvent.isPrimaryButtonDown())
                    {
                    
                    } 
                    else 
                    {
                    
                    }*/
                 
                    for(int i = 0 ; i < points ; i++)
                    {
                    gc.fillRect(point_x[i]-5,point_y[i]-5, 10, 10);
                    
                    //Line theLine = new Line(point_x[i-1], point_y[i-1], point_x[i], point_y[i]);
                    
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
        
        paintLoop.getKeyFrames().add( kf );
        paintLoop.play();
        
        theStage.show();
    }

public void button(String text)
{

}
}



package spartanpaint;

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

/**
 * We haven't implemented the main class, UI, UICanvas, canvas class yet, so here's a temporary class to demo/debug/test our image-editing functionality.
 * @author Christian
 */
public class CanvasDemo extends Application 
{
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
    
    private Layer layer;
    private Brush brush;
    GraphicsContext gc;
    
    
    public static void main(String[] args) 
        {
        launch(args);
        }
    
    static void updateCanvas(GraphicsContext gc, Layer layer)
    {
         for(int i = 0; i < layer.length; i++)
         {
             for(int j = 0; j < layer.height; i++)
             {
                 gc.setFill(layer.getPixel(i, j));
                 gc.fillRect(i, j, 1, 1);
             }
         }
    }
    
    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "CanvasDemo" );
        
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        window_x = theScene.getWindow().getX();
        window_y = theScene.getWindow().getY();
        scene_x = theScene.getX();
        scene_y = theScene.getY();
        
        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );
        
        brush = new Brush(layer, new SquareBrush(), new PaintEffect(), "Brush" );
        layer = new Layer(512, 512, Color.WHITE, "Layer 1");
        
        Button btn1 = new Button();
        btn1.setText("Clear Canvas");
        
        Button btn2 = new Button();
        btn2.setText("Toggle Brush");
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        root.getChildren().add(btn1);
        root.getChildren().add(btn2);
        
        gc = canvas.getGraphicsContext2D();

        Timeline paintLoop = new Timeline();
        paintLoop.setCycleCount( Timeline.INDEFINITE );
        
        final long timeStart = System.currentTimeMillis();
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
            if(t.getButton() == MouseButton.PRIMARY) 
                {
                    
                    
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
                    //gc.clearRect(0, 0, 512,512);
                    //gc.fillRect(0,0,x + 50,y + 50);
                    
                    brush.primaryApply(new Coordinate(mouse_x, mouse_y));
                    updateCanvas(gc, layer);
                    
                    String str1 = Double.toString(window_x);

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Scenes;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import zombiedolphins.GraphicsView;
import zombiedolphins.World;

/**
 *
 * @author anton
 */
public class InGame extends BorderPane implements Controllable{
    GraphicsView graphicsView;
    Canvas gameCanvas;
    GraphicsContext gc;
    AnimationTimer timer;
    World world;
    
    double lastTick = 0;
    
    public InGame(GraphicsView gv){
        this.graphicsView = gv;
        super.setStyle("-fx-background-color: yellow;");
        
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                timerUpdate(now);
            }
        };

        //Return button
        Button btnReturn = new Button("Return");
        btnReturn.setMinWidth(100);
        btnReturn.setMinHeight(20);
        
        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        graphicsView.returnScene();
                }
        });
        
        //HUD
        HBox hBox = new HBox();
        super.setBottom(hBox); 
        hBox.getChildren().add(btnReturn);
        hBox.setStyle("-fx-background-color: gray;");
        hBox.setMinHeight(200);
        hBox.setAlignment(Pos.TOP_CENTER);
        
        //Canvas
        gameCanvas = new Canvas(1280,520);
        gc = gameCanvas.getGraphicsContext2D();
        super.setCenter(gameCanvas);
        gc.setStroke(Color.BLUE);
        gc.setFill(Color.WHITE);
        gc.setLineWidth(5);
        gc.strokeLine(10,10,20, 20);
        gc.fillRect(0, 0, 1280, 620);
    }
    
    public void initializeWorld(World.Level level){
        switch(level)
        {
            case ONE:
                world = new World();
                timer.start();
                break;
        }
    }
    
    public void timerUpdate(long now){

        double deltaMs = ((double)now - lastTick)/1000000000;
        lastTick = (double)now;
        world.update(deltaMs);
        //Clear
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        //Redraw world
        world.draw(deltaMs, gc);
    }

    @Override
    public void onKeyEvent(KeyEvent event) {
        world.handleInput(event);
    }
}

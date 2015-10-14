/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Scenes;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import zombiedolphins.GraphicsView;

/**
 *
 * @author anton
 */
public class InGame extends BorderPane implements Controllable{
    GraphicsView graphicsView;
    
    public InGame(GraphicsView gv){
        this.graphicsView = gv;
        super.setStyle("-fx-background-color: blue;");
        
        Button btnReturn = new Button("Return");
        btnReturn.setMinWidth(100);
        btnReturn.setMinHeight(20);
        
        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        graphicsView.returnScene();
                }
        });
        
        FlowPane flowPane = new FlowPane();
        super.setBottom(flowPane);
        
        flowPane.getChildren().add(btnReturn);
        
    }

    @Override
    public void onKeyEvent(KeyEvent event) {
        System.out.println("You are in InGame");
    }
}

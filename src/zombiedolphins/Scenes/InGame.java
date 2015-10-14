/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Scenes;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import zombiedolphins.GraphicsView;

/**
 *
 * @author anton
 */
public class InGame extends BorderPane implements Controllable{
    GraphicsView graphicsView;
    
    public InGame(GraphicsView gv){
        this.graphicsView = gv;
        
        super.setStyle("-fx-background-color: yellow;");
        
        Button btnReturn = new Button("Return");
        btnReturn.setMinWidth(100);
        btnReturn.setMinHeight(20);
        
        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        graphicsView.returnScene();
                }
        });
        
        HBox hBox = new HBox();
        super.setBottom(hBox);
        
        hBox.getChildren().add(btnReturn);
        
        hBox.setStyle("-fx-background-color: blue;");
        hBox.setMinHeight(100);
        hBox.setAlignment(Pos.CENTER);
        
    }

    @Override
    public void onKeyEvent(KeyEvent event) {
        System.out.println("You are in InGame");
    }
}

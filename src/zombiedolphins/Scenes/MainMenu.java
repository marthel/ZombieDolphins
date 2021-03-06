/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Scenes;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import zombiedolphins.GraphicsView;

/**
 *
 * @author anton
 */
public class MainMenu extends VBox implements Controllable{
    
    GraphicsView graphicsView;
    
    public MainMenu(GraphicsView gv){
        this.graphicsView = gv;
        
        super.setAlignment(Pos.TOP_CENTER);
        super.setSpacing(10);
        super.setPadding(new Insets(100,0,0,0));
        super.setStyle("-fx-background-color: green;");
        
        Button btnPlay = new Button("Play");
        btnPlay.setMinWidth(200);
        btnPlay.setMinHeight(50);
        Button btnOpt = new Button("Options");
        super.setFocusTraversable(false);
        
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        graphicsView.showLobby();
                }
        });
        btnOpt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        graphicsView.showOptions();
                }
        });
        
        super.getChildren().add(btnPlay);
        super.getChildren().add(btnOpt);
    }

    @Override
    public void onKeyEvent(KeyEvent event) {
        System.out.println("You are in MainMenu");
    }
}

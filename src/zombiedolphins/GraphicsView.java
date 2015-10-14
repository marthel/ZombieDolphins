/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zombiedolphins.Scenes.Controllable;
import zombiedolphins.Scenes.InGame;
import zombiedolphins.Scenes.MainMenu;

/**
 *
 * @author Marthin
 */
public class GraphicsView extends Application {
    private StackPane root = new StackPane();
    private MainMenu mainMenu = new MainMenu(this);
    private InGame inGame = new InGame(this);
    
    
    public GraphicsView(){
        
    }
    
    public void returnScene(){
        root.getChildren().remove(root.getChildren().size() - 1);
    }
    
    public void showLobby(){
        root.getChildren().add(inGame);
        System.out.println("Hellooo");
    }
    
    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(mainMenu);
        
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Zombie Dolphins!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        scene.setOnKeyPressed(new KeyHandler());
        
    }
    private class KeyHandler implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent event){
            Controllable c = (Controllable)(root.getChildren().get(root.getChildren().size() - 1));
            c.onKeyEvent(event);
        }
    }
}

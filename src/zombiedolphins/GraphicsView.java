/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import zombiedolphins.Misc.KeyMap;
import zombiedolphins.Scenes.Controllable;
import zombiedolphins.Scenes.InGame;
import zombiedolphins.Scenes.MainMenu;
import zombiedolphins.Scenes.Options;

/**
 *
 * @author Marthin
 */
public class GraphicsView extends Application {
    private final StackPane root = new StackPane();
    private final MainMenu mainMenu = new MainMenu(this);
    private final InGame inGame = new InGame(this);
    private final Options options = new Options(this);
    private KeyMap km;
    public GraphicsView(){
        
    }
    public void setKeyMap(KeyMap km){
        this.km = km;
    }
    public void returnScene(){
        root.getChildren().remove(root.getChildren().size() - 1);
    }
    
    public void showLobby(){
        root.getChildren().add(inGame);
        inGame.requestFocus();
        inGame.setKeyMap(km);
        inGame.initializeWorld(World.Level.ONE);
    }
    public void showOptions(){
        root.getChildren().add(options);
        options.requestFocus();
    }
    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(mainMenu);
        
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Zombie Dolphins!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        scene.setOnKeyPressed(new KeyHandler());
        scene.setOnKeyReleased(new KeyHandler());
        
    }
    private class KeyHandler implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent event){
            Controllable c = (Controllable)(root.getChildren().get(root.getChildren().size() - 1));
            c.onKeyEvent(event);
        }
    }
}

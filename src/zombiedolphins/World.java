/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins;

import zombiedolphins.Entities.Entity;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import zombiedolphins.Entities.Camera;
import zombiedolphins.Entities.Player;
import zombiedolphins.Misc.KeyMap;

/**
 *
 * @author Anton
 */
public class World {
    private ArrayList<Entity> entities;
    private Camera camera;
    
    public World(){
        entities = new ArrayList();
        camera = new Camera();
        KeyMap km = new KeyMap(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.SPACE, KeyCode.R);
        Player p = new Player(km, new Image("file:test.png", 100, 100, true, true));
        entities.add(p);
    }
    
    
    public void update(double delta){
        for(Entity e : entities){
            e.update(delta);
        }

    }
    
    public void draw(double delta, GraphicsContext gc){
        for(Entity e : entities){
            e.draw(gc, camera);
        }
    }
    
    public void handleInput(KeyEvent event){
        
        for(Entity e : entities){
            if(e instanceof Player){
                Player p = (Player) e;
                p.handleInput(event);
            }
        }
    }
    
    public enum Level{
        ONE
    }
}

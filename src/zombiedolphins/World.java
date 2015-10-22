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
import zombiedolphins.Entities.Bullet;
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
    Player p;
    Thread t1;
    public World() {
        entities = new ArrayList();
        camera = new Camera();
        t1 = new Thread(new Runnable() {
            public void run() {
                removeDeadBullets();
            }
        });t1.start();
        
        //Creates a testplayer and add it to the world.
        KeyMap km = new KeyMap(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.SPACE, KeyCode.R);
        Player p1 = new Player(250, 300, km, new Image("Textures/Knugen.png", 612, 32, true, true),this);
        KeyMap km2 = new KeyMap(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.O, KeyCode.P);
        Player p2 = new Player(30, 250, km2, new Image("Textures/Vickan.png", 612, 32, true, true),this);
        entities.add(p1);
        entities.add(p2);
    }

    public void update(double delta) {
        for (Entity e : entities) {
            e.update(delta);
        }
    }
    public void addBullet(Bullet b) {
        entities.add(b);
    }
    public void removeDeadBullets() {
        for (Entity e : entities) {
            if (e instanceof Bullet) {
                Bullet b = (Bullet) e;
                if(!b.getStatus()){
                    entities.remove(b);
                }
            }
        }
    }
    public void draw(double delta, GraphicsContext gc) {
        for (Entity e : entities) {
            e.draw(gc, camera);
        }
    }

    public int[] getAmmo() {
        int[] ammo = new int[2];
        int i=0;
        for (Entity e : entities) {
            if (e instanceof Player) {
                Player p = (Player) e;
                ammo[i]=p.getAmmo();
                i++;
            }
        }
        return ammo;
    }
    

    public void handleInput(KeyEvent event) {

        for (Entity e : entities) {
            if (e instanceof Player) {
                Player p = (Player) e;
                p.handleInput(event);
            }
        }
    }

    public enum Level {

        ONE
    }
}

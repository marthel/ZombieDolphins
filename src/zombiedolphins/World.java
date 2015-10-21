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

    public World() {
        entities = new ArrayList();

        camera = new Camera();

        ///creates bullets
        ArrayList<Bullet> p1Bullets = new ArrayList();
        ArrayList<Bullet> p2Bullets = new ArrayList();
        for (int i = 0; i < 30; i++) {
            p1Bullets.add(new Bullet(new Image("Textures/bullet.png", 3, 3, true, true)));
            p2Bullets.add(new Bullet(new Image("Textures/bullet.png", 3, 3, true, true)));
        }
        for (Bullet b : p1Bullets) {
            entities.add(b);
        }
        for (Bullet b : p2Bullets) {
            entities.add(b);
        }
        //Creates a testplayer and add it to the world.

        KeyMap km = new KeyMap(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.SPACE, KeyCode.R);
        Player p = new Player(250, 300, km, new Image("Textures/Knugen.png", 612, 32, true, true), p1Bullets);
        KeyMap km2 = new KeyMap(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.O, KeyCode.P);
        Player p2 = new Player(30, 250, km2, new Image("Textures/Vickan.png", 612, 32, true, true), p2Bullets);
        entities.add(p);
        entities.add(p2);
    }

    public void addBullets() {

    }

    public void update(double delta) {
        for (Entity e : entities) {
            e.update(delta);
        }

    }

    public void draw(double delta, GraphicsContext gc) {
        for (Entity e : entities) {
            e.draw(gc, camera);
        }
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

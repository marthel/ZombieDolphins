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
import zombiedolphins.Entities.AI;
import zombiedolphins.Entities.Bullet;
import zombiedolphins.Entities.Camera;
import zombiedolphins.Entities.Obstacle;
import zombiedolphins.Entities.Player;
import zombiedolphins.Misc.KeyMap;
import zombiedolphins.Misc.PathFinder;
import zombiedolphins.Misc.PathNode;

/**
 *
 * @author Anton
 */
public class World {

    private ArrayList<Entity> entities;
    private Camera camera;
    private PathFinder pathFinder;
    
    public World() {
        entities = new ArrayList();
        pathFinder = new PathFinder();
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
        
        //Obstacles
        
        Obstacle o = new Obstacle(100,100);
        Obstacle o1 = new Obstacle(132,100);
        Obstacle o2 = new Obstacle(164,100);
        Obstacle o3 = new Obstacle(196,100);
        Obstacle o4 = new Obstacle(100,132);
        Obstacle o5 = new Obstacle(100,164);
        Obstacle o6 = new Obstacle(100,196);
        entities.add(o);
        entities.add(o1);
        entities.add(o2);
        entities.add(o3);
        entities.add(o4);
        entities.add(o5);
        entities.add(o6);
        
        AI ai = new AI(this,p);
        ai.setX(50);
        ai.setY(100);
        entities.add(ai);
    }
    
    public ArrayList<PathNode> getPath(Entity start, Entity end){
        ArrayList<Obstacle> obstacles = new ArrayList();
        for(Entity e : entities){
            if(e instanceof Obstacle)
                obstacles.add((Obstacle)e);
        }
        
        return pathFinder.findPath(obstacles, start, end);
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

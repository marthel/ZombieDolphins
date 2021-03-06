
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins;

import zombiedolphins.Entities.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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

    private List<Entity> entities;
    private Camera camera;
    private PathFinder pathFinder;
    
    public World(KeyMap km) {
        entities = new CopyOnWriteArrayList();
        pathFinder = new PathFinder();
        camera = new Camera();
        
        //
        generateObstacles();
        //Creates a testplayer and add it to the world.
        //KeyMap km = new KeyMap(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.SPACE, KeyCode.R);
        Player p1 = new Player(250, 300, km, new Image("Textures/Knugen.png", 612, 32, true, true),this);
        KeyMap km2 = new KeyMap(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.O, KeyCode.P);
        Player p2 = new Player(300, 250, km2, new Image("Textures/Vickan.png", 612, 32, true, true),this);
        entities.add(p1);
        entities.add(p2);

        AI ai = new AI(this);
        ai.setX(50);
        ai.setY(100);
        entities.add(ai);
    }
    
    private void generateObstacles(){
        for(int i = 0; i < 40; i++){
            Obstacle o = new Obstacle(i * 32, 0);
            Obstacle o1 = new Obstacle(i * 32, 480);
            entities.add(o);
            entities.add(o1);
        }
        for(int i = 0; i < 15; i++){
            Obstacle o = new Obstacle(0, i*32);
            Obstacle o1 = new Obstacle(1248, i*32);
            entities.add(o);
            entities.add(o1);
        }
        
        for(int i = 0; i < 5;i++){
            Obstacle o = new Obstacle(560, 98 + i*32);
            Obstacle o1 = new Obstacle(300, 320 + i*32);
            entities.add(o);
            entities.add(o1);
            
            Obstacle o2 = new Obstacle(128 + i*32, 160);
            Obstacle o3 = new Obstacle(398 + i*32, 320);
            entities.add(o2);
            entities.add(o3);
            
            Obstacle o4 = new Obstacle(720 + i*32, 160);
            Obstacle o5 = new Obstacle(480 + i*32, 320);
            entities.add(o4);
            entities.add(o5);
            
            Obstacle o6 = new Obstacle(880 + i*32, 224);
            Obstacle o7 = new Obstacle(548 + i*32, 480);
            entities.add(o6);
            entities.add(o7);
            
            
        }
        for(int i = 0; i < 3;i++){
            Obstacle o8 = new Obstacle(880, 296+ i*32);
            Obstacle o9 = new Obstacle(480, 548 + i*32);
            entities.add(o8);
            entities.add(o9);
        }
        
    }
    
    public ArrayList<PathNode> getPath(Entity start, Entity end){
        ArrayList<Obstacle> obstacles = new ArrayList();
        for(Entity e : entities){
            if(e instanceof Obstacle)
                obstacles.add((Obstacle)e);
        }
        
        return pathFinder.findPath(obstacles, start, end);
    }
    
    public List<Entity> getEntities(){
        return entities;
    }

    public void update(double delta) {
        for (Entity e : entities) {
            e.update(delta);
        }
        removeDeadBullets();
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

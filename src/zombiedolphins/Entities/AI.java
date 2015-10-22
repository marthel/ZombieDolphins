/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import zombiedolphins.Misc.PathFinder;
import zombiedolphins.Misc.PathNode;
import zombiedolphins.World;

/**
 *
 * @author Anton
 */
public class AI extends Character{
    private static final Image dolphinTexture = new Image("Textures/dolphin2.png", 32, 32, true, true);
    private World world;
    ArrayList<PathNode> wayPoints;
    ArrayList<Player> players;
    private double health;
    
    Player p1;
    
    public AI(World world){
        super();
        this.world = world;
        super.texture = AI.dolphinTexture;
        players = getPlayers();
        moveSpeed = 50f;
        rect.setWidth(32);
        rect.setHeight(32);
        health = 100;
    }
    
    private Player findTarget(){
        Player target;
        if(players.size()>0){
            double distance = getDistance(this, players.get(0));
            target = players.get(0);
            for(int i = 1; i < players.size(); i++){
                if(getDistance(this, players.get(i)) < distance){
                    target = players.get(i);
                }
            }
            return target;
        }
        return null;
    }
    
    private ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList();
        for(Entity e : world.getEntities()){
            if(e instanceof Player){
                Player p = (Player)e;
                players.add(p);
            }
        }
        return players;
    }
    
    private double getDistance(Entity object1, Entity object2){
        return Math.sqrt(Math.pow(object1.getX() - object2.getX(), 2) + 
                Math.pow(object1.rect.getY() - object2.rect.getY(), 2));
    }

    @Override
    public void update(double deltaTime) {
        //TODO: Use a pathfinding system.
        wayPoints = world.getPath(this, findTarget());

        if(deltaTime > 1)
            return;
        
        if(wayPoints.size()> 0){
            if(rect.getX() + 16 < wayPoints.get(0).x){
                rect.setX(rect.getX() + moveSpeed * deltaTime);
            }else if(rect.getX() + 16 > wayPoints.get(0).x){
                rect.setX(rect.getX() - moveSpeed * deltaTime);
            }
            if(rect.getY() + 16< wayPoints.get(0).y){
                rect.setY(rect.getY() + moveSpeed * deltaTime);
            }else if(rect.getY() + 16 > wayPoints.get(0).y){
                rect.setY(rect.getY() - moveSpeed * deltaTime);
            }
        }
        
        
        for(Entity e : world.getEntities()){
            if(e instanceof Bullet){
                if(e.rect.intersects(this.rect.getBoundsInLocal())){
                    System.out.println("BULLET HIT!");
                    world.getEntities().remove(e);
                    health-=25;
                    if(health < 0){
                        world.getEntities().remove(this);
                        
                        for(int i = 0; i < 2; i++)
                        {
                            AI respawn;

                            int newX = (int)(Math.random() * 1280);
                            int newY = (int)(Math.random() * 520);
                            boolean collision = true;

                            respawn = new AI(world);
                            respawn.setX(newX);
                            respawn.setY(newY);


                            while(collision){
                                collision = false;
                                for(Entity o : world.getEntities()){
                                    if(o instanceof Obstacle){
                                        if(respawn.rect.intersects(o.rect.getBoundsInLocal())){
                                            respawn.setX((int)(Math.random() * 1280));
                                            respawn.setY((int)(Math.random() * 520));
                                            collision = true;
                                            System.out.println("collision");

                                        }
                                    }
                                }
                            }
                            System.out.println("HELLO");
                            world.getEntities().add(respawn);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        gc.setFill(Color.GREEN);
        //gc.fillRect(posX, posY, 32, 32);
        gc.drawImage(texture, rect.getX(), rect.getY(), 32, 32);
        
        if(wayPoints == null || wayPoints.size() < 1)
            return;
        
        gc.setFill(Color.BLACK);
        gc.fillOval(wayPoints.get(0).x - 2, wayPoints.get(0).y - 2, 5, 5);
        
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        int prevX = wayPoints.get(0).x;
        int prevY = wayPoints.get(0).y;
        for(int i = 1; i < wayPoints.size();i++){
            gc.strokeLine(prevX, prevY, wayPoints.get(i).x, wayPoints.get(i).y);
            gc.setFill(Color.BLACK);
            gc.fillOval(wayPoints.get(i).x - 2, wayPoints.get(i).y - 2, 5, 5);
            prevX = wayPoints.get(i).x;
            prevY = wayPoints.get(i).y;
        }
    }
}

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
    
    Player p1;
    
    public AI(World world){
        super();
        this.world = world;
        super.texture = AI.dolphinTexture;
        players = getPlayers();
        moveSpeed = 50f;
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
                Math.pow(object1.posY - object2.posY, 2));
    }

    @Override
    public void update(double deltaTime) {
        //TODO: Use a pathfinding system.
        wayPoints = world.getPath(this, findTarget());

        if(deltaTime > 1)
            return;
        
        if(wayPoints.size()> 0){
            if(posX + 16 < wayPoints.get(0).x){
                posX += moveSpeed * deltaTime;
            }else if(posX + 16 > wayPoints.get(0).x){
                posX -= moveSpeed * deltaTime;
            }
            if(posY + 16< wayPoints.get(0).y){
                posY += moveSpeed * deltaTime;
            }else if(posY + 16 > wayPoints.get(0).y){
                posY -= moveSpeed * deltaTime;
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        gc.setFill(Color.GREEN);
        //gc.fillRect(posX, posY, 32, 32);
        gc.drawImage(texture, posX, posY, 32, 32);
        
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

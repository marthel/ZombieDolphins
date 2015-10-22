/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import zombiedolphins.Misc.PathFinder;
import zombiedolphins.Misc.PathNode;
import zombiedolphins.World;

/**
 *
 * @author Anton
 */
public class AI extends Character{
    private World world;
    ArrayList<PathNode> wayPoints;
    
    Player p1;
    
    public AI(World world, Player p1){
        super();
        this.world = world;
        this.p1 = p1;
        wayPoints = world.getPath(this, p1);
    }
    
    private void findPlayer(){
        
    }

    @Override
    public void update(double deltaTime) {
        //TODO: Use a pathfinding system.
        wayPoints = world.getPath(this, p1);
    }

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        
        gc.setFill(Color.GREEN);
        gc.fillRect(posX, posY, 32, 32);
        
        if(wayPoints == null)
            return;
        
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        int prevX = wayPoints.get(0).x;
        int prevY = wayPoints.get(0).y;
        for(int i = 1; i < wayPoints.size();i++){
            gc.strokeLine(prevX, prevY, wayPoints.get(i).x, wayPoints.get(i).y);
            prevX = wayPoints.get(i).x;
            prevY = wayPoints.get(i).y;
        }
    }
}

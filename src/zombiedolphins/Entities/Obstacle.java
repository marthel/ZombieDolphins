/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Anton
 */
public class Obstacle extends Entity {
    
    private final int width = 32;
    private final int height = 32;
    
    public Obstacle(int x, int y){
        posX = x;
        posY = y;
    }
    
    public int getWidth(){return width;}
    public int getHeight(){return height;}

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        gc.setFill(Color.BLACK);
        gc.fillRect(posX, posY, width, height);
    }

    @Override
    public void update(double deltaTime) {
    }
    
}

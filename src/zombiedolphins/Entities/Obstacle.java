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
    public Obstacle(int x, int y){
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(32);
        rect.setHeight(32);
    }

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        gc.setFill(Color.BLACK);
        gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    @Override
    public void update(double deltaTime) {
    }
    
}

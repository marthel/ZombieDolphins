/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Anton
 */
public abstract class Entity {
    
    float posX = 0, posY = 0;
    Image texture;
    
    public Entity(){
        
    }
    
    public abstract void draw(GraphicsContext gc, Camera camera);
    public abstract void update(double deltaTime);
}

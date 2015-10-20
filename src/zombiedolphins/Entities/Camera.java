/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Anton
 */
public class Camera extends Entity {
    Entity followThis;
    
    @Override
    public void update(double deltaTime) {
        //Camera movement logic
    }

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.*;

/**
 *
 * @author Anton
 */
public abstract class Entity {

    Image texture;
    Rectangle rect = new Rectangle();

    public Entity() {

    }

    public Entity(float x, float y) {
        rect.setX(x);
        rect.setY(y);
    }

    public void setX(float x) {
        rect.setX(x);
    }

    public void setY(float y) {
        rect.setY(y);
    }

    public double getX() {
        return rect.getX();
    }

    public double getY() {
        return rect.getY();
    }
    
    public double getWidth() {
        return rect.getWidth();
    }
    public double getHeight(){
        return rect.getHeight();
    }

    public abstract void draw(GraphicsContext gc, Camera camera);

    public abstract void update(double deltaTime);
}

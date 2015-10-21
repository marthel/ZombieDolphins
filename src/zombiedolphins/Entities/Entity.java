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

    public Entity() {

    }

    public Entity(float x, float y) {
        posX = x;
        posY = y;
    }

    public void setX(float x) {
        posX = x;
    }

    public void setY(float y) {
        posY = y;
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public abstract void draw(GraphicsContext gc, Camera camera);

    public abstract void update(double deltaTime);
}

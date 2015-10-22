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
 * @author Marthin
 */
public class Bullet extends Entity {

    private static final Image bulletTexture = new Image("Textures/bullet.png", 3, 3, true, true);
    private boolean isActive;
    private float moveSpeed = 350f;
    private int direction;

    public Bullet(float x, float y, int dir) {
        super(x, y);
        super.texture = bulletTexture;
        isActive = true;
        direction = dir;
        rect.setWidth(3);
        rect.setHeight(3);
    }
    public void kill(){
        isActive = false;
    }
    public boolean getStatus(){
        return isActive;
    }
    private void checkBoundaries(){
        if(rect.getX()<0 || rect.getX() > 1280)
            kill();
        if(rect.getY()<0 || rect.getY() > 520)
            kill();
    }
    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        if (isActive) {
            gc.drawImage(texture, rect.getX(), rect.getY());
        }
    }

    @Override
    public void update(double deltaTime) {
        checkBoundaries();
        if (isActive) {
            if (direction == 1) {
                rect.setY(rect.getY() - moveSpeed * deltaTime);
            } else if (direction == -1) {
                rect.setY(rect.getY() + moveSpeed * deltaTime);
            } else if (direction == -2) {
                rect.setX(rect.getX() - moveSpeed * deltaTime);
            } else if (direction == 2) {
                rect.setX(rect.getX() + moveSpeed * deltaTime);
            }
        }
    }
}

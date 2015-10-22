/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import zombiedolphins.Misc.MoveDirection;

/**
 *
 * @author Marthin
 */
public class Bullet extends Entity {

    MoveDirection moveDir;
    private boolean isActive;
    private float moveSpeed = 350f;
    private int direction;

    public Bullet(Image texture) {
        super(500, 300);
        super.texture = texture;
        moveDir = new MoveDirection();
        isActive = false;
        direction = 0;

    }

    public void setState(boolean state) {
        isActive = state;
    }

    public void setDirection(int dir) {
        direction = dir;
    }

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        if (isActive) {
            gc.drawImage(texture, posX, posY);
        }
    }

    @Override
    public void update(double deltaTime) {
        if (isActive) {
            //System.out.println("borde skjuta nu");
            //System.out.println("UP  " + moveDir.getUp());
            if (direction == 1) {
                this.posY -= moveSpeed * deltaTime;

            } else if (direction == -1) {
                this.posY += moveSpeed * deltaTime;

            }

            if (direction == -2) {
                this.posX -= moveSpeed * deltaTime;

            } else if (direction == 2) {
                this.posX += moveSpeed * deltaTime;

            }
        }
    }
}

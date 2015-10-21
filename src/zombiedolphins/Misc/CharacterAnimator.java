/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Misc;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

/**
 *
 * @author Marthin
 */
public class CharacterAnimator extends AnimationTimer {

    private int currentFrame;
    private long prevNS;
    private MoveDirection moveDir;
    private Image texture;
    private Image[] movingUp;
    private Image[] movingDown;
    private Image[] movingLeft;
    private Image[] movingRight;

    public CharacterAnimator(Image[] up, Image[] down, Image[] left, Image[] right,Image texture) {
        currentFrame = 0;
        prevNS = 0;
        moveDir = new MoveDirection();
        movingUp = up;
        movingDown  = down;
        movingLeft = left;
        movingRight = right;
        this.texture = texture;
    }

    public void start(MoveDirection moveDir) {
        this.moveDir = moveDir;
        super.start();
    }

    @Override
    public void handle(long currentNS) {
        if (moveDir.getUp()) {
            if (currentFrame > movingUp.length) {
                currentFrame = 0;
            }
            if (currentNS - prevNS > 10000000) {
                currentFrame++;
                texture = movingUp[currentFrame];
            }

        } else if (moveDir.getDown()) {
            if (currentFrame > movingDown.length) {
                currentFrame = 0;
            }
            if (currentNS - prevNS > 10000000) {
                currentFrame++;
                texture = movingDown[currentFrame];
            }

        } else if (moveDir.getLeft()) {
            if (currentFrame > movingLeft.length) {
                currentFrame = 0;
            }
            if (currentNS - prevNS > 12000000) {
                currentFrame++;
                texture = movingLeft[currentFrame];
            }

        } else if (moveDir.getRight()) {
            if (currentFrame > movingRight.length) {
                currentFrame = 0;
            }
            if (currentNS - prevNS > 12000000) {
                currentFrame++;
                texture = movingRight[currentFrame];
            }

        }

        prevNS = currentNS;
    }
    public Image getTexture(){
        return texture;
    }
}

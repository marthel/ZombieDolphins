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

    private int currentFrame, nextFrame;
    private long prevNS;
    private MoveDirection moveDir;
    private Image texture;
    private int[] framesUp;
    private int[] framesDown;
    private int[] framesLeft;
    private int[] framesRight;
    private int[] framesIdle;

    public CharacterAnimator(int[] up, int[] down, int[] left, int[] right, int[] idle) {
        currentFrame = 0;
        nextFrame = 0;
        prevNS = 0;
        moveDir = new MoveDirection();
        framesUp = up;
        framesDown = down;
        framesLeft = left;
        framesRight = right;
        framesIdle = idle;
    }

    public void start(MoveDirection moveDir) {
        this.moveDir = moveDir;
        super.start();
    }

    @Override
    public void stop() {
        nextFrame = 0;
        if (moveDir.getUp()) {
            currentFrame = framesIdle[0];

        } else if (moveDir.getDown()) {
            currentFrame = framesIdle[2];

        } else if (moveDir.getLeft()) {
            currentFrame = framesIdle[1];

        } else if (moveDir.getRight()) {
            currentFrame = framesIdle[3];

        }
        super.stop();
    }

    @Override
    public void handle(long currentNS) {
        if (currentNS - prevNS > 12000000) {
            nextFrame++;
            System.out.println(nextFrame);
        }
        if (moveDir.getUp()) {
            if (nextFrame >= framesUp.length) {
                nextFrame = 0;
            }

            currentFrame = framesUp[nextFrame];

        } else if (moveDir.getDown()) {
            if (nextFrame >= framesDown.length) {
                nextFrame = 0;
            }
            currentFrame = framesDown[nextFrame];

        } else if (moveDir.getLeft()) {
            if (nextFrame >= framesLeft.length) {
                nextFrame = 0;
            }
            currentFrame = framesLeft[nextFrame];

        } else if (moveDir.getRight()) {
            if (nextFrame >= framesRight.length) {
                nextFrame = 0;
            }
            currentFrame = framesRight[nextFrame];

        }

        prevNS = currentNS;
    }

    public int getCurretFrame() {
        return currentFrame;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import zombiedolphins.Misc.CharacterAnimator;
import zombiedolphins.Misc.KeyMap;
import zombiedolphins.Misc.MoveDirection;

/**
 *
 * @author Anton
 */
public class Player extends Character {

    private KeyMap keyMap;
    private MoveDirection moveDir;
    private CharacterAnimator playerAnimator;
    private final int frameWidth = 17;
    private final int frameHeight = 32;
    private final int [] framesUp = {4,5,6,7,8,9,10,11};
    private final int [] framesDown = {20,21,22,23,24,25,26,27};
    private final int [] framesLeft = {12,13,14,15,16,17,18,19};
    private final int [] framesRight = {28,29,30,31,32,33,34,35};
    private final int [] framesIdle = {0,1,2,3};
    public Player(KeyMap keyMap, Image texture) {
        super();
        this.keyMap = keyMap;
        super.texture = texture;
        playerAnimator = new CharacterAnimator(framesUp,framesDown,framesLeft,framesRight,framesIdle);
        moveDir = new MoveDirection();
    }

    public KeyMap getKeyMap() {
        return keyMap;
    }

    private void shoot() {
        //TODO: Shoot logic
    }

    public void handleInput(KeyEvent event) {
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            System.out.print("Pressed: ");
            playerAnimator.start(moveDir);
            if (keyMap.moveUp == event.getCode()) {
                moveDir.setUp(true);
                System.out.println("Up");
            } else if (keyMap.moveDown == event.getCode()) {
                moveDir.setDown(true);
                System.out.println("Down");
            } else if (keyMap.moveRight == event.getCode()) {
                moveDir.setRight(true);
                System.out.println("Right");
            } else if (keyMap.moveLeft == event.getCode()) {
                moveDir.setLeft(true);
                System.out.println("Left");
            } else if (keyMap.shoot == event.getCode()) {
            }
        }
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            playerAnimator.stop();
            if (keyMap.moveUp == event.getCode()) {
                moveDir.setUp(false);
            } else if (keyMap.moveDown == event.getCode()) {
                moveDir.setDown(false);
            } else if (keyMap.moveRight == event.getCode()) {
                moveDir.setRight(false);
            } else if (keyMap.moveLeft == event.getCode()) {
                moveDir.setLeft(false);
            }
        }
    }

    @Override
    public void update(double deltaTime) {
        if (moveDir.getUp()) {
            super.posY -= super.moveSpeed * deltaTime;

        } else if (moveDir.getDown()) {
            super.posY += super.moveSpeed * deltaTime;

        }

        if (moveDir.getLeft()) {
            super.posX -= super.moveSpeed * deltaTime;

        } else if (moveDir.getRight()) {
            super.posX += super.moveSpeed * deltaTime;

        }
    }

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        //System.out.println("NIKI<3");
        //gc.drawImage(playerAnimator.getTexture(), posX, posY);
        gc.drawImage(texture,
                    playerAnimator.getCurretFrame()*frameWidth,0,frameWidth,frameHeight,
                    posX,posY,frameWidth*1.3,frameHeight*1.3);
        /* TODO:
         * - Draw relative to camera position.
         */

    }
}

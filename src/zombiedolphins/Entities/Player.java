/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import java.util.ArrayList;
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
    private long prevNS;
    AnimationTimer coolDownTimer;
    private MoveDirection moveDir;
    private CharacterAnimator playerAnimator;
    private ArrayList<Bullet> bullets;
    private int bulletCount;
    private int magazine;
    private boolean isShooting, coolDown;
    private int lastDir;
    private final int frameWidth = 17;
    private final int frameHeight = 32;
    private final int[] framesUp = {4, 5, 6, 7, 8, 9, 10, 11};
    private final int[] framesDown = {20, 21, 22, 23, 24, 25, 26, 27};
    private final int[] framesLeft = {12, 13, 14, 15, 16, 17, 18, 19};
    private final int[] framesRight = {28, 29, 30, 31, 32, 33, 34, 35};
    private final int[] framesIdle = {0, 1, 2, 3};

    public Player(float x, float y, KeyMap keyMap, Image texture, ArrayList<Bullet> bullets) {
        super(x, y);
        this.keyMap = keyMap;
        super.texture = texture;
        playerAnimator = new CharacterAnimator(framesUp, framesDown, framesLeft, framesRight, framesIdle);
        moveDir = new MoveDirection();
        lastDir = 1;
        this.bullets = bullets;
        bulletCount = 0;
        magazine = bullets.size();
        coolDown = false;
        coolDownTimer = new AnimationTimer() {
            @Override
            public void start() {
                coolDown = true;
                super.start();
            }

            @Override
            public void stop() {
                coolDown = false;
                super.stop();
            }

            @Override
            public void handle(long currentNS) {
                if (currentNS - prevNS > 12000000) {
                    coolDown = false;
                }
                prevNS = currentNS;
            }
        };

    }

    public KeyMap getKeyMap() {
        return keyMap;
    }

    private void shoot() {
        if (bulletCount < magazine) {
            bullets.get(bulletCount).setDirection(lastDir);
            bullets.get(bulletCount).setX(this.posX + frameWidth + 3);
            bullets.get(bulletCount).setY(this.posY + frameHeight);
            bullets.get(bulletCount).setState(true);
            bulletCount++;
        }
    }

    private void reload() {
        bulletCount = 0;
    }
    public int getAmmo(){
        //if(isShooting && !coolDown)
            return bulletCount;
        //return 0;
    }
    public void handleInput(KeyEvent event) {
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            playerAnimator.start(moveDir);
            if (keyMap.moveUp == event.getCode()) {
                moveDir.setUp(true);
            } else if (keyMap.moveDown == event.getCode()) {
                moveDir.setDown(true);
            } else if (keyMap.moveRight == event.getCode()) {
                moveDir.setRight(true);
            } else if (keyMap.moveLeft == event.getCode()) {
                moveDir.setLeft(true);
            } else if (keyMap.shoot == event.getCode()) {
                isShooting = true;
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
            } else if (keyMap.shoot == event.getCode()) {
                isShooting = false;
                coolDownTimer.stop();
            } else if (keyMap.reload == event.getCode()) {
                reload();
            }
        }
    }

    @Override
    public void update(double deltaTime
    ) {
        if (moveDir.getUp()) {
            super.posY -= super.moveSpeed * deltaTime;
            lastDir = 1;
        } else if (moveDir.getDown()) {
            super.posY += super.moveSpeed * deltaTime;
            lastDir = -1;

        }

        if (moveDir.getLeft()) {
            super.posX -= super.moveSpeed * deltaTime;
            lastDir = -2;
        } else if (moveDir.getRight()) {
            super.posX += super.moveSpeed * deltaTime;
            lastDir = 2;
        }
        if (isShooting && !coolDown) {
            shoot();
            coolDownTimer.start();
        }

    }

    @Override
    public void draw(GraphicsContext gc, Camera camera
    ) {
        gc.drawImage(texture,
                playerAnimator.getCurretFrame() * frameWidth, 0, frameWidth, frameHeight,
                posX, posY, frameWidth * 1.3, frameHeight * 1.3);
        /* TODO:
         * - Draw relative to camera position.
         */

    }
}

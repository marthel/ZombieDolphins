/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import zombiedolphins.Misc.CharacterAnimator;
import zombiedolphins.Misc.KeyMap;
import zombiedolphins.Misc.MoveDirection;
import zombiedolphins.World;

/**
 *
 * @author Anton
 */
public class Player extends Character {

    private KeyMap keyMap;
    private World world;
    private MoveDirection moveDir;
    private CharacterAnimator playerAnimator;
    private int bulletCount;
    private int magazine;
    private boolean isShooting;
    private int lastDir;
    private long prevTime;
    private final long coolDown = 200;
    private final int frameWidth = 17;
    private final int frameHeight = 32;
    private final int[] framesUp = {4, 5, 6, 7, 8, 9, 10, 11};
    private final int[] framesDown = {20, 21, 22, 23, 24, 25, 26, 27};
    private final int[] framesLeft = {12, 13, 14, 15, 16, 17, 18, 19};
    private final int[] framesRight = {28, 29, 30, 31, 32, 33, 34, 35};
    private final int[] framesIdle = {0, 1, 2, 3};

    public Player(float x, float y, KeyMap keyMap, Image texture, World world) {
        super(x, y);
        this.world = world;
        this.keyMap = keyMap;
        super.texture = texture;
        playerAnimator = new CharacterAnimator(framesUp, framesDown, framesLeft, framesRight, framesIdle);
        moveDir = new MoveDirection();
        lastDir = 1;
        bulletCount = 0;
        magazine = 30;
    }

    public KeyMap getKeyMap() {
        return keyMap;
    }

    private void shoot() {
        if (bulletCount < magazine) {
            Bullet b = new Bullet(this.posX + frameWidth + 3, this.posY + frameHeight, lastDir);
            world.addBullet(b);
            bulletCount++;
        }
    }

    private void reload() {
        bulletCount = 0;
    }

    public int getAmmo() {
        return bulletCount;
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
        if (isShooting) {
            if (System.currentTimeMillis() - prevTime < coolDown) {
                return;
            }
            prevTime = System.currentTimeMillis();
            shoot();
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

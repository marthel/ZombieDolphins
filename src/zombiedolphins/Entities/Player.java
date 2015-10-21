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
import zombiedolphins.Misc.KeyMap;
import zombiedolphins.Misc.MoveDirection;

/**
 *
 * @author Anton
 */
public class Player extends Character {

    private KeyMap keyMap;
    private MoveDirection moveDir;
    private AnimationTimer animation;
    private int currentFrame;
    private long prevNS;
    private final Image[] idleDirection = {
        new Image("Textures/Knugen_idle_up.png"),
        new Image("Textures/Knugen_idle_down.png"),
        new Image("Textures/Knugen_idle_right.png"),
        new Image("Textures/Knugen_idle_left.png")
    };
    private final Image[] movingUp = {
        new Image("Textures/Knugen_moving_up1.png"),
        new Image("Textures/Knugen_moving_up2.png"),
        new Image("Textures/Knugen_moving_up5.png"),
        new Image("Textures/Knugen_idle_up.png"),
        new Image("Textures/Knugen_moving_up6.png"),
        new Image("Textures/Knugen_moving_up4.png"),
        new Image("Textures/Knugen_moving_up3.png"),
        new Image("Textures/Knugen_idle_up.png")
    };
    private final Image[] movingDown = {
        new Image("Textures/Knugen_moving_down1.png"),
        new Image("Textures/Knugen_moving_down2.png"),
        new Image("Textures/Knugen_moving_down6.png"),
        new Image("Textures/Knugen_idle_down.png"),
        new Image("Textures/Knugen_moving_down4.png"),
        new Image("Textures/Knugen_moving_down5.png"),
        new Image("Textures/Knugen_moving_down3.png"),
        new Image("Textures/Knugen_idle_down.png")
    };
    private final Image[] movingRight = {
        new Image("Textures/Knugen_moving_right3.png"),
        new Image("Textures/Knugen_moving_right2.png"),
        new Image("Textures/Knugen_moving_right1.png"),
        new Image("Textures/Knugen_moving_right4.png"),
        new Image("Textures/Knugen_moving_right6.png"),
        new Image("Textures/Knugen_moving_right5.png"),
        new Image("Textures/Knugen_moving_right7.png"),
        new Image("Textures/Knugen_moving_right8.png")
    };
    private final Image[] movingLeft = {
        new Image("Textures/Knugen_moving_left3.png"),
        new Image("Textures/Knugen_moving_left2.png"),
        new Image("Textures/Knugen_moving_left1.png"),
        new Image("Textures/Knugen_moving_left4.png"),
        new Image("Textures/Knugen_moving_left5.png"),
        new Image("Textures/Knugen_moving_left6.png"),
        new Image("Textures/Knugen_moving_left7.png"),
        new Image("Textures/Knugen_moving_left8.png")
    };

    public Player(KeyMap keyMap, Image texture) {
        super();
        this.keyMap = keyMap;
        super.texture = texture;
        currentFrame = 0;
        prevNS = 0;
        moveDir = new MoveDirection();
        initAnimation();

    }

    private void initAnimation() {
        animation = new AnimationTimer() {
            @Override
            public void handle(long currentNS) {

                if (moveDir.getUp()) {
                    if (currentFrame > movingUp.length) {
                        currentFrame = 0;
                    }
                    if (currentNS - prevNS > 12000000) {
                        currentFrame++;
                        texture = movingUp[currentFrame];
                    }

                } else if (moveDir.getDown()) {
                    if (currentFrame > movingDown.length) {
                        currentFrame = 0;
                    }
                    if (currentNS - prevNS > 12000000) {
                        currentFrame++;
                        texture = movingDown[currentFrame];
                    }

                }

                if (moveDir.getLeft()) {
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
        };
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
            animation.start();
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
            animation.stop();
            if (keyMap.moveUp == event.getCode()) {
                moveDir.setUp(false);
                texture = idleDirection[0];
            } else if (keyMap.moveDown == event.getCode()) {
                moveDir.setDown(false);
                texture = idleDirection[1];
            } else if (keyMap.moveRight == event.getCode()) {
                moveDir.setRight(false);
                texture = idleDirection[2];
            } else if (keyMap.moveLeft == event.getCode()) {
                moveDir.setLeft(false);
                texture = idleDirection[3];
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
        gc.drawImage(texture, posX, posY, 36, 64);
        /* TODO:
         * - Animation
         * - Draw relative to camera position.
         */

    }
}

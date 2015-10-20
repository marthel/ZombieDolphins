/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import zombiedolphins.Misc.KeyMap;
import zombiedolphins.Misc.MoveDirection;

/**
 *
 * @author Anton
 */
public class Player extends Character {
    private KeyMap keyMap;
    private MoveDirection moveDir;
    
    public Player(KeyMap keyMap, Image texture){
        super();
        this.keyMap = keyMap;
        super.texture = texture;
        moveDir = new MoveDirection();
    }
    
    public KeyMap getKeyMap(){
        return keyMap;
    }
    
    private void shoot(){
        //TODO: Shoot logic
    }
    
    public void handleInput(KeyEvent event){
        if(KeyEvent.KEY_PRESSED.equals(event.getEventType())){
            System.out.print("Pressed: ");
            if(keyMap.moveUp == event.getCode()){    
                moveDir.setUp(true);
                System.out.println("Up");
            }
            else if(keyMap.moveDown == event.getCode()){    
                moveDir.setDown(true);
                System.out.println("Down");
            }
            else if(keyMap.moveRight == event.getCode()){    
                moveDir.setRight(true);
                System.out.println("Right");
            }
            else if(keyMap.moveLeft == event.getCode()){    
                moveDir.setLeft(true);
                System.out.println("Left");
            }
            else if(keyMap.shoot == event.getCode()){ 
            }
        }
        if(KeyEvent.KEY_RELEASED.equals(event.getEventType())){
            if(keyMap.moveUp == event.getCode()){    
                moveDir.setUp(false);
            }
            else if(keyMap.moveDown == event.getCode()){    
                moveDir.setDown(false);
            }
            else if(keyMap.moveRight == event.getCode()){    
                moveDir.setRight(false);
            }
            else if(keyMap.moveLeft == event.getCode()){    
                moveDir.setLeft(false);
            }
        }
    }
    
    @Override
    public void update(double deltaTime) {
        if(moveDir.getUp()){
            super.posY -= super.moveSpeed * deltaTime;
        }
        else if(moveDir.getDown()){
            super.posY += super.moveSpeed * deltaTime;
        }
        
        if(moveDir.getLeft()){
            super.posX -= super.moveSpeed * deltaTime;
        }
        else if(moveDir.getRight()){
            super.posX += super.moveSpeed * deltaTime;
        }
    }

    @Override
    public void draw(GraphicsContext gc, Camera camera) {
        gc.setFill(Color.BLACK);
        gc.fillRect(posX, posY, 50, 50);
        //TODO:FIX draw image.
        //gc.drawImage(texture, posX, posY, 100, 100);
    }
}

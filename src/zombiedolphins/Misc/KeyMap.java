/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Misc;

import java.io.Serializable;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Anton
 */
public class KeyMap implements Serializable{

    public KeyCode moveUp;
    public KeyCode moveDown;
    public KeyCode moveLeft;
    public KeyCode moveRight;
    public KeyCode shoot;
    public KeyCode reload;

    public KeyMap(KeyCode up, KeyCode down, KeyCode left, KeyCode right,
            KeyCode shoot, KeyCode reload) {
        this.moveUp = up;
        this.moveDown = down;
        this.moveLeft = left;
        this.moveRight = right;
        this.shoot = shoot;
        this.reload = reload;
    }
    public KeyMap() {
    }
    public void setUp(KeyCode up){
        this.moveUp = up;
    }
    public void setDown(KeyCode down){
        this.moveDown = down;
    }
    public void setLeft(KeyCode left){
        this.moveLeft = left;
    }
    public void setRight(KeyCode right){
        this.moveRight = right;
    }
    public void setShoot(KeyCode shoot){
        this.shoot = shoot;
    }
    public void setReload(KeyCode reload){
        this.reload = reload;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Entities;

/**
 *
 * @author Anton
 */
public abstract class Character extends Entity {

    float moveSpeed = 100f;

    public Character() {
        super();
    }

    public Character(float x, float y) {
        super(x, y);
    }
}

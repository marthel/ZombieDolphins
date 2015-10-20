/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Misc;

/**
 *
 * @author Anton
 */
public class MoveDirection {
    private boolean left = false,
            right = false, 
            up = false,
            down = false;
    
    public void setRight(boolean val){
        if(val == true){
            left = false;
            right = true;
        }
        else{
            right = false;
        }
    }
    
    public void setLeft(boolean val){
        if(val == true){
            right = false;
            left = true;
        }
        else{
            left = false;
        }
    }
    
    public void setUp(boolean val){
        if(val == true){
            down = false;
            up = true;
        }
        else{
            up = false;
        }
    }
    
    public void setDown(boolean val){
        if(val == true){
            up = false;
            down = true;
        }
        else{
            down = false;
        }
    }
    
    public boolean getUp(){return up;}
    public boolean getDown(){return down;}
    public boolean getLeft(){return left;}
    public boolean getRight(){return right;}
}

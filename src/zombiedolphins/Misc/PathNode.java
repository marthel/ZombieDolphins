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
public class PathNode{
        PathNode parent;
        boolean obstacle = false;
        boolean start = false;
        boolean end = false;
        int distanceToStart = 0;    //G-cost
        int distanceToEnd = 0;      //H-Cost
        public int x, y;
        
        public int getfCost(){
            return distanceToStart + distanceToEnd;
        }
        
        public PathNode(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public void setObstacle(){obstacle = true;}
        public void setStart(){start = true;}
        public void setEnd(){end = true;}
    }

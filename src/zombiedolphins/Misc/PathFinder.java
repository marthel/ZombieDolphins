/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Misc;

import java.util.ArrayList;
import java.util.Collections;
import zombiedolphins.Entities.Entity;
import zombiedolphins.Entities.Obstacle;

/**
 *
 * @author Anton
 */
public class PathFinder {
    public PathFinder(){
        
    }
    
    public ArrayList<PathNode> findPath(ArrayList<Obstacle> obstacles, Entity start, Entity end){
        System.out.println("Generating NavMesh");
        PathNode[][] navMesh = generateNavMesh(obstacles, start, end);
        System.out.println("NavMesh generated");
        PathNode startNode, endNode;
        ArrayList<PathNode> openNodes = new ArrayList();
        ArrayList<PathNode> closedNodes = new ArrayList();
        
        startNode = getStart(navMesh);
        endNode = getEnd(navMesh);
        
        openNodes.add(startNode);
        System.out.println("Finding path...");
        System.out.println("Startnode X:" + startNode.x + "Y:" + startNode.y);
        System.out.println("EndNode X:" + endNode.x + "Y:" + endNode.y);

        while(openNodes.size() > 0){
            PathNode current = openNodes.get(0);
            for(int i = 1; i < openNodes.size(); i++){
                if(openNodes.get(i).getfCost() < current.getfCost() || 
                        openNodes.get(i).getfCost() == current.getfCost() &&
                        openNodes.get(i).distanceToEnd < current.distanceToEnd){
                    current = openNodes.get(i);
                }
            }
            //System.out.println("Current node X = " + current.x + "Y:" + current.y);
            
            openNodes.remove(current);
            closedNodes.add(current);
            
            if(current.equals(endNode)){
                return translateToWorld(backtracePath(endNode, startNode));
            }
            
            ArrayList<PathNode> neighbours = getNeighbours(navMesh, current);
            
            for(PathNode n : neighbours){
                
                if(n.obstacle || closedNodes.contains(n))
                    continue;
                int newTravelCostToNeighbour = current.distanceToStart 
                        + calcDistance(current,n);
                
                if(newTravelCostToNeighbour < n.distanceToStart || !openNodes.contains(n)){
                    n.distanceToStart = newTravelCostToNeighbour;
                    n.distanceToEnd = calcDistance(n, endNode);
                    n.parent = current;
                    
                    if(!openNodes.contains(n)){
                        openNodes.add(n);
                    }
                }
            }
        }
        
        return null;
    }
    
    private ArrayList<PathNode> backtracePath(PathNode endNode, PathNode startNode){
        ArrayList<PathNode>path = new ArrayList();
        PathNode currentNode = endNode;
        while(currentNode != startNode){
            path.add(currentNode);
            currentNode = currentNode.parent;
        }
        Collections.reverse(path);
        return path;
    }
    
    private int calcDistance(PathNode node1, PathNode node2){
        return Math.abs(10*((node1.x - node2.x) + (node1.y - node2.y)));
    }
    
    private ArrayList<PathNode> getNeighbours(PathNode[][]navMesh, PathNode current){
        ArrayList<PathNode> neighbours = new ArrayList();
        
        if(current.x + 1 < 1024){
            neighbours.add(navMesh[current.x+1][current.y]);
        }
        if(current.x - 1 > -1){
            neighbours.add(navMesh[current.x-1][current.y]);
        }
        if(current.y + 1 < 1024){
            neighbours.add(navMesh[current.x][current.y+1]);
        }
        if(current.y - 1 > -1){
            neighbours.add(navMesh[current.x][current.y-1]);
        }
        
        
        return neighbours;
    }
    
    private PathNode getStart(PathNode[][]navMesh){
        for(int i = 0; i < 1024; i++){
            for(int j = 0; j < 1024; j++){
                if(navMesh[i][j].start){
                    return navMesh[i][j];
                }
            }
        }
        return null;
    }
    private PathNode getEnd(PathNode[][]navMesh){
        for(int i = 0; i < 1024; i++){
            for(int j = 0; j < 1024; j++){
                if(navMesh[i][j].end){
                    return navMesh[i][j];
                }
            }
        }
        return null;
    }
    
    private ArrayList<PathNode> translateToWorld(ArrayList<PathNode> path){
        for(PathNode p : path){
            p.x = ((p.x - 512) * 32) + 16;
            p.y = ((p.y - 512) * 32) + 16;
        }
        return path;
    }
    

    
    private PathNode[][] generateNavMesh(ArrayList<Obstacle> obstacles, Entity start, Entity end){
        PathNode[][] navMesh = new PathNode[1024][1024];
        
        //Generate nodes
        for(int i = 0; i < 1024; i++){
            for(int j = 0; j < 1024; j++){
                navMesh[i][j] = new PathNode(i,j);
            }
        }
        
        //Generate obstacles
        for(Obstacle o : obstacles){
            int x = 512 + (int)o.getX()/o.getWidth();
            int y = 512 + (int)o.getY()/o.getHeight();
            
            navMesh[x][y].setObstacle();
        }
        
        //Generate start
        int x = 512 + (int)(start.getX()+16)/32;
        int y = 512 + (int)(start.getY()+16)/32;
        navMesh[x][y].setStart();
        
        //Generate end
        x = 512 + (int)(end.getX() + 16)/32;
        y = 512 + (int)(end.getY() + 16)/32;
        navMesh[x][y].setEnd();
        return navMesh; 
    }
}

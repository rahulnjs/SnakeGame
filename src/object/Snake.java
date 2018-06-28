package object;

import config.Configuration;
import controller.Direction;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Hell
 */
public class Snake {
    
    int size = 1;
    Point headAt;
    Direction headDirection = Direction.LEFT;
    public List<Point> points = new LinkedList<Point>();
    
    
    public Snake() {
        headAt = new Point(0, 360);
        points.add(headAt);
    }
    
    public int getX() {
        return headAt.x;
    }
    
    public int getY() {
        return headAt.y;
    }
    
    
    public void changeCoordinate() {
        
        switch(headDirection) {
            case DOWN : 
                headAt.y += 18;
                if(headAt.y >= Configuration.FRAME_HEIGHT-10) {
                    headAt.y = 0;
                }
                break;
            case RIGHT : 
                headAt.x += 18;
                if(headAt.x >= Configuration.FRAME_WIDTH-5) {
                    headAt.x = 0;
                }
                break;                
            case LEFT : 
                headAt.x -= 18;
                if(headAt.x <= -10) {
                    headAt.x = Configuration.FRAME_WIDTH-10;
                }
                break;
            case UP: 
                headAt.y -= 18;
                if(headAt.y <= -10) {
                    headAt.y = Configuration.FRAME_HEIGHT-10;
                }
                break;
        }
    }
    
    public void setDirection(Direction dir) {
       headDirection = dir;
    }

    
    public int getSize() {
        return size;
    }
    
    public Direction getDirection() {
        return headDirection;
    }
    
    public void incrementSize() {
        size++;
    }
    
    public void addNewPoint() {
        
        Point newPoint = new Point(points.get(points.size()-1).x, points.get(points.size()-1).y);
        switch(headDirection) {
            case DOWN : 
                newPoint.y -= 18;
                break;
            case RIGHT : 
                newPoint.x -= 18;
                break;                
            case LEFT : 
                newPoint.x += 18;
                break;
            case UP: 
                newPoint.y += 18;
                break;
        }
        points.add(newPoint);
    } 
    
}

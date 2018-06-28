package graphics;

import config.Configuration;
import controller.Direction;
import controller.GameStarter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;
import object.Snake;

/**
 *
 * @author Hell
 */
public class Canvas extends JPanel{
    
    private Snake snake; 
    private boolean isFirstTym = true;
    private int baitX = 300;
    private int baitY = 300;
    
    public Canvas() {
        super();
        snake = new Snake();
    }
    
    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    
    
    @Override
    public void paint(Graphics g) {
       
        g.setColor(Configuration.gridColor);
        int x = 20;
        int y = 20;
        while(x < Configuration.FRAME_WIDTH) {
            g.drawLine(x, 0, x, Configuration.FRAME_HEIGHT);
            x += 20;
        }
        while(y < Configuration.FRAME_HEIGHT) {
            g.drawLine(0, y, Configuration.FRAME_WIDTH, y);
            y += 20;
        }
        if(isFirstTym) {
            drawBait(g);
            isFirstTym = false;
        }
        drawSnake(g);
    }
    
    private void drawSnake(Graphics g) {
        g.setColor(Configuration.snakeColor);
        if(isInsideTheBaitArea(snake.getX(), snake.getY()) ) {
            generateNewBait();
            snake.incrementSize();
            snake.addNewPoint();
        }
        drawBait(g);
        int lastX = snake.getX(), lastY = snake.getY();
        updateSankeHead(g);
        for(int i = 1; i < snake.getSize(); i++) {
            int currX = snake.points.get(i).x;
            int currY = snake.points.get(i).y;
            snake.points.get(i).x = lastX;
            snake.points.get(i).y = lastY;
            lastX = currX;
            lastY = currY;
            g.setColor(Configuration.snakeColor);
            g.fillRoundRect(snake.points.get(i).x, snake.points.get(i).y,
                Configuration.RECT_WIDTH, Configuration.RECT_HEIGHT,
                Configuration.ROUNDING_RADIUS, Configuration.ROUNDING_RADIUS);
        }
        updateScore(g);
        if(isHit()) {
            g.setColor(Color.red);
            g.fillRoundRect(snake.points.get(0).x, snake.points.get(0).y, 
                Configuration.RECT_WIDTH, Configuration.RECT_HEIGHT,
                Configuration.ROUNDING_RADIUS, Configuration.ROUNDING_RADIUS);
            GameStarter.stopGame();
            g.setColor(Color.red);
            g.setFont(new Font("Segoe UI", Font.BOLD, 26));
            g.drawString("Game Over", 280,320);
        }
        
        
    }
    
       
    
    private void updateSankeHead(Graphics g) {
        snake.changeCoordinate();
        g.setColor(Configuration.snakeHeadColor);
        g.fillRoundRect(snake.points.get(0).x, snake.points.get(0).y, 
                Configuration.RECT_WIDTH, Configuration.RECT_HEIGHT,
                Configuration.ROUNDING_RADIUS, Configuration.ROUNDING_RADIUS);
        
    }
    
    
    private boolean isHit() {
        boolean hit = false;
        int x = snake.getX();
        int y = snake.getY();
        for(int i = 3; i < snake.points.size(); i++) {
            Point p = snake.points.get(i);
            if(isOverlapped(x, y, p) || isOverlapped(x + 20, y, p) || isOverlapped(x, y + 20, p) || isOverlapped(x + 20, y +20, p)) {
                return true;
            }
        }
        
        return hit;
    }
    
    
    private boolean isOverlapped(int x, int y, Point point) {
        if((x >= point.x && x <= (point.x + 20)) && (y >= point.y && y <= (point.y + 20))) {
            return true;
        }
        return false;
    }
    
    private void updateScore(Graphics g) {
        g.setColor(Configuration.textColor);
        g.setFont(new Font("Segoe UI", Font.BOLD, 16));
        g.drawString("Score : " + ((snake.getSize()-1) * 10), 10, 655);
    }
    
    public void generateNewBait() {
        Random rand = new Random();
        baitX = rand.nextInt(Configuration.FRAME_WIDTH-100);
        baitY = rand.nextInt(Configuration.FRAME_WIDTH-100);
    } 
    
    private void drawBait(Graphics g) {
        g.setColor(Configuration.baitColor);
        g.fillRoundRect(baitX, baitY, 
                Configuration.BAIT_WIDTH, Configuration.BAIT_HEIGHT,
                Configuration.BAIT_ROUNDING_RADIUS, Configuration.BAIT_ROUNDING_RADIUS);
    }
    
    
    
    public boolean isInsideTheBaitArea(int x, int y) {
       
        Direction hD = snake.getDirection();
        switch(hD) {
            case DOWN : 
                if(!isInside(x, y + 20) && !isInside(x + 20, y + 20)) {
                    return false;
                }
                return true;
            case LEFT : 
                if(!isInside(x, y) && !isInside(x, y + 20)) {
                    return false;
                }
                return true;                
            case RIGHT : 
                if(!isInside(x + 20, y) && !isInside(x + 20, y + 20)) {
                    return false;
                }
                return true;
            case UP: 
                if(!isInside(x, y) && !isInside(x + 20, y)) {
                    return false;
                }
                return true;
            default:
                return false;
        }
            
    }
    
    public boolean isInside(int x, int y) {
        if((x >= baitX && x <= (baitX + 14)) && (y >= baitY && (y <= (baitY + 14)))) {
            return true;
        }else if(((x + 10) >= baitX && (x + 10) <= (baitX + 14)) && ((y + 10) >= baitY && ((y + 10) <= (baitY + 14)))){
            return true;
        }else {
             return false;            
        }
    }
    
    public Snake getSnake() {
        return snake;
    }
    
}

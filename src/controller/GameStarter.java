/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.Configuration;
import graphics.Canvas;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Hell
 */
public class GameStarter {

    Canvas canvas;
    static Timer timer;
    Component mainComp;
    
    public GameStarter(Canvas c, Component main) {
        this.canvas = c;
        mainComp = main;
        
        timer = new Timer(Configuration.DELAY, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                canvas.removeAll();
                mainComp.repaint();
                canvas.repaint();
                
            }
        });
        
    }
    
    public void setCanvas(Canvas c) {
        canvas = c;
    }
    
   
    public static boolean isGameRunning() {
        return timer.isRunning();
    }
    
    
    public void start() {
        timer.start();
    }
    
    public void stop(){
        timer.stop();
    }
    
    public static void stopGame() {
        timer.stop();
    }
    
    public static void setDelay(int delay) {
        timer.setDelay(delay);
    }
    
        
    
}

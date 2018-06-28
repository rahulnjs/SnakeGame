package config;

import controller.GameStarter;
import java.awt.Color;

/**
 *
 * @author Hell
 */
public abstract class Configuration {
    
    public static final int FRAME_WIDTH = 707;
    public static final int FRAME_HEIGHT = 710;
    public static Color snakeColor = new Color(0, 100, 0);
    public static Color hitHead = new Color(80, 05, 17);
    public static Color snakeHeadColor = new Color(25, 38, 60);
    public static final int RECT_WIDTH = 20;
    public static final int RECT_HEIGHT = 20;
    public static final int ROUNDING_RADIUS = 10;
    public static int DELAY = 30;
    public static final Color baitColor = new Color(46, 199, 199);
    public static final int BAIT_WIDTH = 14;
    public static final int BAIT_HEIGHT = 14;
    public static final int BAIT_ROUNDING_RADIUS = 1;
    public static Color gridColor = new Color(240, 240, 240);
    public static Color textColor = new Color(0, 33, 66);
    
    
    public static void setDelay() {
        GameStarter.setDelay(DELAY);
    }
}

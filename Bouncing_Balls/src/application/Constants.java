package application;

import java.util.HashSet;
import java.util.Set;
import javafx.geometry.Point2D;

public class Constants {	
	public static double SPEED_COEFFICIENT = 1.0;
    public static double RESTITUTION_COEFFICIENT = 0.8; // Coefficient of Restitution (0 = no bounce, 1 = perfect bounce)
    public static double TRAILCOLLISIONTHRESHOLD = 15;
    public static final Set<Point2D> TRAIL_POINTS = new HashSet<>();
    static {
    	// Letter P:
    	TRAIL_POINTS.add(new Point2D(300, 100));
    	TRAIL_POINTS.add(new Point2D(300, 155));
    	TRAIL_POINTS.add(new Point2D(300, 210));
    	TRAIL_POINTS.add(new Point2D(300, 265));
    	TRAIL_POINTS.add(new Point2D(300, 320));
    	
    	TRAIL_POINTS.add(new Point2D(355, 105));
    	TRAIL_POINTS.add(new Point2D(410, 135));
    	TRAIL_POINTS.add(new Point2D(410, 180));
    	TRAIL_POINTS.add(new Point2D(355, 205));
    	// Letter Q:
    	TRAIL_POINTS.add(new Point2D(710, 150));
    	TRAIL_POINTS.add(new Point2D(700, 210));
    	TRAIL_POINTS.add(new Point2D(710, 270));
    	
    	TRAIL_POINTS.add(new Point2D(855, 150));
    	TRAIL_POINTS.add(new Point2D(865, 210));
    	TRAIL_POINTS.add(new Point2D(855, 280));
    	TRAIL_POINTS.add(new Point2D(890, 315)); // tail
    	
    	TRAIL_POINTS.add(new Point2D(750, 105));
    	TRAIL_POINTS.add(new Point2D(750, 320));
    	TRAIL_POINTS.add(new Point2D(815, 105));
    	TRAIL_POINTS.add(new Point2D(815, 320));
    	// Letter Y:
    	TRAIL_POINTS.add(new Point2D(1105, 100));
    	TRAIL_POINTS.add(new Point2D(1155, 155));
    	TRAIL_POINTS.add(new Point2D(1245, 155));
    	TRAIL_POINTS.add(new Point2D(1295, 100));
    	
    	TRAIL_POINTS.add(new Point2D(1200, 210));
    	TRAIL_POINTS.add(new Point2D(1200, 265));
    	TRAIL_POINTS.add(new Point2D(1200, 320));   	
    	
    }
}

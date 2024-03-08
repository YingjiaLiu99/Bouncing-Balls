package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Ball {
	private Circle circle;
	private double velocityX;
	private double velocityY;
	private double speedCoefficient = 1.0;
	// Coefficient of Restitution (0 = no bounce, 1 = perfect bounce)
	private double restitutionCoefficient = 0.8;
	// Variables to store the difference between the ball's position and mouse position
    private double dragDeltaX, dragDeltaY;
    private long lastDragTime; // Time of the last drag event
    private double lastDragX, lastDragY; // Position of the last drag event
	private double releaseVelocityX, releaseVelocityY;
    
	public Ball(double centerX, double centerY, double radius, Color color) {
		// Define stops for the gradient
		Stop[] stops = new Stop[] {
				new Stop(0, color.brighter()),
			    new Stop(1, color.darker().darker())
		};		
		RadialGradient gradient = new RadialGradient(0, 0, 0.7, 0.3, 0.5, true, CycleMethod.NO_CYCLE, stops);
        // Create the circle with gradient
        circle = new Circle(centerX, centerY, radius, gradient);
        this.velocityX = -(10*Math.random())+5; // Initial horizontal velocity
        this.velocityY = 5*Math.random(); // Initial vertical velocity
        enableDrag();
    }
	
	private void enableDrag() {
		this.circle.setOnMousePressed((MouseEvent event) -> {
			// Calculate the difference between the ball's position and the mouse's position
            dragDeltaX = circle.getCenterX() - event.getSceneX();
            dragDeltaY = circle.getCenterY() - event.getSceneY();
            // record the position and time when the ball is selected
            lastDragX = event.getSceneX();
            lastDragY = event.getSceneY();
            lastDragTime = System.currentTimeMillis();
            // set the velocity to be 0 when the ball is dragged
            this.velocityX = 0;
            this.velocityY = 0;
            // can add code here to visually indicate that the ball is selected			
		});
		
		this.circle.setOnMouseDragged((MouseEvent event) -> {
			// Update the position of the ball as it is dragged
            double newX = event.getSceneX() + dragDeltaX;
            double newY = event.getSceneY() + dragDeltaY;
            setX(newX);
            setY(newY);			
            long currentTime = System.currentTimeMillis();
            long timeDiff = currentTime - lastDragTime;
            if (timeDiff > 0) { // Prevent division by zero
                double speedX = (newX - lastDragX) / timeDiff;
                double speedY = (newY - lastDragY) / timeDiff;
                // Store these for use on release
                releaseVelocityX = speedX * 5; // Adjust multiplier as needed for speed scaling
                releaseVelocityY = speedY * 5;
            }
            lastDragX = newX;
            lastDragY = newY;
            lastDragTime = currentTime;
		});
		// Add an onMouseReleased handler to perform any actions when the mouse is released
		circle.setOnMouseReleased((MouseEvent event) -> {
			velocityX = releaseVelocityX;
			velocityY = releaseVelocityY;
			releaseVelocityX = 0;
			releaseVelocityY = 0;            
        });
	}
		
	
	// Getter and Setter for position
    public double getX() {
        return circle.getCenterX();
    }

    public void setX(double x) {
        circle.setCenterX(x);
    }

    public double getY() {
        return circle.getCenterY();
    }

    public void setY(double y) {
        circle.setCenterY(y);
    }

    // Getter and Setter for velocity
    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    // Getter for the circle (for rendering in JavaFX)
    public Circle getCircle() {
        return circle;
    }

    // Getter and Setter for restitution coefficient
    public double getRestitutionCoefficient() {
        return restitutionCoefficient;
    }

    public void setRestitutionCoefficient(double restitutionCoefficient) {
        this.restitutionCoefficient = restitutionCoefficient;
    }
    
    public void setSpeedCoefficient(double newCoefficient) {
    	this.speedCoefficient = newCoefficient;
    }

    // Update the position of the ball based on its velocity
    public void updatePosition() {
        circle.setCenterX(circle.getCenterX() + velocityX * speedCoefficient);
        circle.setCenterY(circle.getCenterY() + velocityY * speedCoefficient);
    }
}

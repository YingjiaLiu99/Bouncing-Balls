package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	private Circle circle;
	private double velocityX;
	private double velocityY;
	// Coefficient of Restitution (0 = no bounce, 1 = perfect bounce)
	private double restitutionCoefficient = 0.8;

	public Ball(double centerX, double centerY, double radius, Color color) {
        this.circle = new Circle(centerX, centerY, radius);
        this.circle.setFill(color);
        this.velocityX = 0; // Initial horizontal velocity
        this.velocityY = 0; // Initial vertical velocity
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

    // Update the position of the ball based on its velocity
    public void updatePosition() {
        circle.setCenterX(circle.getCenterX() + velocityX);
        circle.setCenterY(circle.getCenterY() + velocityY);
    }
}

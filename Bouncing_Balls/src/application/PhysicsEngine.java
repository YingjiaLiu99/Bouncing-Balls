package application;

import java.util.List;

import javafx.scene.layout.Pane;

public class PhysicsEngine {
	private Pane gamePane;
	private double gravity = 0.98; // Adjustable
	private double energyLossFactorBetweenWall = 1.0; // Factor to simulate energy loss in collisions between wall
	private double energyLossFactorBetweenBall = 1.0; // Factor to simulate energy loss in collisions between balls
	public PhysicsEngine(Pane gamePane) {
		this.gamePane = gamePane;
	}
	
	public void updateBall(Ball ball, List<Ball> balls) {
		// Apply gravity
        // ball.setVelocityY(ball.getVelocityY() + gravity);
		
        // Update position based on current velocity
        ball.updatePosition();

        // Handle boundary collisions
        checkBoundaryCollisions(ball);		
        
        // Handle ball collisions
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                if (checkBallsColliding(balls.get(i), balls.get(j))) {
                    handleBallCollision(balls.get(i), balls.get(j));
                }
            }
        }
	}
	
	private boolean checkBallsColliding(Ball ball1, Ball ball2) {
	    double dx = ball1.getX() - ball2.getX();
	    double dy = ball1.getY() - ball2.getY();
	    double distance = Math.sqrt(dx * dx + dy * dy);
	    return distance < (ball1.getCircle().getRadius() + ball2.getCircle().getRadius());
	}
	
	private void handleBallCollision(Ball ball1, Ball ball2) {
	    double dx = ball2.getX() - ball1.getX();
	    double dy = ball2.getY() - ball1.getY();
	    double collisionAngle = Math.atan2(dy, dx);

	    double speed1 = Math.sqrt(ball1.getVelocityX() * ball1.getVelocityX() + ball1.getVelocityY() * ball1.getVelocityY());
	    double speed2 = Math.sqrt(ball2.getVelocityX() * ball2.getVelocityX() + ball2.getVelocityY() * ball2.getVelocityY());

	    double direction1 = Math.atan2(ball1.getVelocityY(), ball1.getVelocityX());
	    double direction2 = Math.atan2(ball2.getVelocityY(), ball2.getVelocityX());

	    double velocityX1 = speed1 * Math.cos(direction1 - collisionAngle);
	    double velocityY1 = speed1 * Math.sin(direction1 - collisionAngle);
	    double velocityX2 = speed2 * Math.cos(direction2 - collisionAngle);
	    double velocityY2 = speed2 * Math.sin(direction2 - collisionAngle);

	    double finalVelocityX1 = velocityX2 * energyLossFactorBetweenBall;
	    double finalVelocityX2 = velocityX1 * energyLossFactorBetweenBall;

	    ball1.setVelocityX(Math.cos(collisionAngle) * finalVelocityX1 + Math.cos(collisionAngle + Math.PI / 2) * velocityY1);
	    ball1.setVelocityY(Math.sin(collisionAngle) * finalVelocityX1 + Math.sin(collisionAngle + Math.PI / 2) * velocityY1);
	    ball2.setVelocityX(Math.cos(collisionAngle) * finalVelocityX2 + Math.cos(collisionAngle + Math.PI / 2) * velocityY2);
	    ball2.setVelocityY(Math.sin(collisionAngle) * finalVelocityX2 + Math.sin(collisionAngle + Math.PI / 2) * velocityY2);
	    adjustPositionsPostCollision(ball1, ball2);
	}
	
	private void adjustPositionsPostCollision(Ball ball1, Ball ball2) {
		double dx = ball1.getX() - ball2.getX();
	    double dy = ball1.getY() - ball2.getY();
	    double distance = Math.sqrt(dx * dx + dy * dy);
	    double overlap = (ball1.getCircle().getRadius() + ball2.getCircle().getRadius()) - distance;
	    double correctionFactor = 0.5; // Adjust each ball by half the overlap distance
	    double correction = overlap * correctionFactor;

	    double angle = Math.atan2(ball2.getY() - ball1.getY(), ball2.getX() - ball1.getX());
	    ball1.setX(ball1.getX() - correction * Math.cos(angle));
	    ball1.setY(ball1.getY() - correction * Math.sin(angle));
	    ball2.setX(ball2.getX() + correction * Math.cos(angle));
	    ball2.setY(ball2.getY() + correction * Math.sin(angle));
	}

	
	
	private void checkBoundaryCollisions(Ball ball) {
        boolean collisionDetected = false;
        double radius = ball.getCircle().getRadius();

        // Top collision
        if (ball.getY() - radius <= 0) {
            ball.setY(radius); // Correct the position
            ball.setVelocityY(-ball.getVelocityY() * energyLossFactorBetweenWall);
            collisionDetected = true;
        }

        // Bottom collision
        if (ball.getY() + radius >= gamePane.getHeight()) {
            ball.setY(gamePane.getHeight() - radius); // Correct the position
            ball.setVelocityY(-ball.getVelocityY() * energyLossFactorBetweenWall);
            collisionDetected = true;
        }

        // Left collision
        if (ball.getX() - radius <= 0) {
            ball.setX(radius); // Correct the position
            ball.setVelocityX(-ball.getVelocityX() * energyLossFactorBetweenWall);
            collisionDetected = true;
        }

        // Right collision
        if (ball.getX() + radius >= gamePane.getWidth()) {
            ball.setX(gamePane.getWidth() - radius); // Correct the position
            ball.setVelocityX(-ball.getVelocityX() * energyLossFactorBetweenWall);
            collisionDetected = true;
        }

        // Similarly, check and handle collisions with other boundaries

        // If a collision was detected and you want to include more realistic physics,
        // might adjust the velocityX based on friction, for example.
        if (collisionDetected) {
            // Adjust velocityX if necessary
        }
    }
    
	

}

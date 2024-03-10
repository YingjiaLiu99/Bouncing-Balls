package application;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.geometry.Point2D;

import java.io.File;
import java.nio.file.Files;

public class Controller {	
	
	@FXML	
	private Slider size_slider;
	@FXML
	private Slider speed_slider;
	
	@FXML
	private Pane ballPane;
	
	private PhysicsEngine physicsEngine;
	private List<Ball> ball_list = new ArrayList<>();
	
	public void initialize() {		
		size_slider.valueProperty().addListener(new ChangeListener<Number>() {	
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// Could be used as change a specific ball's size
			}			
		});		
		speed_slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean wasChanging, Boolean isChanging) {
				if (!isChanging) {											
					double coeff = speed_slider.getValue();
					Constants.SPEED_COEFFICIENT = coeff;					
				}
			}
		});		
		physicsEngine = new PhysicsEngine(ballPane);		
		setupAnimationLoop();
	}
	
	private void setupAnimationLoop() {	
		
		for(Point2D point : Constants.TRAIL_POINTS) {
			double radius = 10*2;
			Color color = new Color(Math.random(),Math.random(),Math.random(),1.0);
			Ball ball = new Ball(point.getX(), point.getY(), radius, color);
			ball.setVelocityX(0);
			ball.setVelocityY(0);
			ball_list.add(ball);
	        ballPane.getChildren().add(ball.getCircle());
		}
		
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// update each ball's position
				for(Ball ball : ball_list) {
					physicsEngine.updateBall(ball, ball_list);
					if(isBallOnTrail(ball, Constants.TRAIL_POINTS, Constants.TRAILCOLLISIONTHRESHOLD)) {
						ball.setVelocityX(0);
			    		ball.setVelocityY(0);			    		
					}
				}
			}
		}.start();
	}
	
	// ----------------------- Buttons Functionalities ------------------------------ //
	// Method to add one Ball at a random location
	@FXML
	void addBall(ActionEvent e) {
		double radius = size_slider.getValue() * 10;
        Color color = new Color(Math.random(),Math.random(),Math.random(),1.0); // Random color
        Ball ball = new Ball(Math.random()*850, 20, radius, color); // Starting position and size
        ball_list.add(ball);
        ballPane.getChildren().add(ball.getCircle()); // Add the ball's Circle to the pane		
	}
	
	// Method to change the color of all balls
    @FXML
    void changeColor(ActionEvent e) {
    	for (Ball ball : ball_list) {
            Color newColor = new Color(Math.random(), Math.random(), Math.random(), 1.0); // Random color
            Stop[] stops = new Stop[] {
                    new Stop(0, newColor.brighter()),  
                    new Stop(1, newColor.darker().darker())
            };
            RadialGradient gradient = new RadialGradient(0, 0, 0.7, 0.3, 0.5, true, CycleMethod.NO_CYCLE, stops);
            ball.getCircle().setFill(gradient);
        }
    }
	
	// Clear the board(remove all the balls)
    @FXML
    void clearBoard(ActionEvent e) {
    	// Iterate through the list of balls and remove each from the gamePane
        for (Ball ball : ball_list) {
        	ballPane.getChildren().remove(ball.getCircle());
        }        
        // Clear the list of Ball objects
        ball_list.clear();
    }
    
    // Freeze all the balls on the board
    @FXML
    void freezeBoard(ActionEvent e) {
    	for (Ball ball : ball_list) {
    		ball.setVelocityX(0);
    		ball.setVelocityY(0);
    	}
    }
    // ----------------------------------------------------------------------- //
    
    // --------------------------- Helper Functions -------------------------- //
    // hidden trail file loader:
    public List<Point2D> loadTrailPoints(String filePath) {
        List<Point2D> trailPoints = new ArrayList<>();
        try {
        	//File trail_file = new File(filePath);
        	List<String> lines = Files.readAllLines(new File(filePath).toPath());
            for (String line : lines) {
                if(line.startsWith("#") || line.trim().isEmpty()) continue; // Ignore comments and empty lines
                String[] parts = line.split(":")[1].trim().split(",");
                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());
                trailPoints.add(new Point2D(x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trailPoints;
    }
    
    // Check collision with hidden trail
    public boolean isBallOnTrail(Ball ball, Set<Point2D> trailPoints, double threshold) {
        Point2D ballPosition = new Point2D(ball.getX(), ball.getY());
        for(Point2D trailPoint : trailPoints) {
            if(ballPosition.distance(trailPoint) < threshold) {
            	ball.setX(trailPoint.getX());
            	ball.setY(trailPoint.getY());
                return true;
            }
        }
        return false;
    }
}

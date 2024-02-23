package application;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;

public class Controller {	
	
	@FXML	
	private Slider size_slider;
	
	@FXML
	private Pane ballPane;
	
	private PhysicsEngine physicsEngine;
	private List<Ball> ball_list = new ArrayList<>();
	
	public void initialize() {
		
		size_slider.valueProperty().addListener(new ChangeListener<Number>() {	
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// int radius = (int)size_slider.getValue() * 10;
				// ball.setRadius(radius);				
			}
			
		});	
		
		physicsEngine = new PhysicsEngine(ballPane);
		
		setupAnimationLoop();
	}
	
	private void setupAnimationLoop() {
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// update each ball's position
				for(Ball ball : ball_list) {
					physicsEngine.updateBall(ball, ball_list);
				}
			}
		}.start();
	}
	
	
	@FXML
	void addBall(ActionEvent e) {
		double radius = size_slider.getValue() * 10;
        Color color = new Color(Math.random(),Math.random(),Math.random(),1.0); // Random color
        Ball ball = new Ball(Math.random()*500, Math.random()*200, radius, color); // Starting position and size
        ball_list.add(ball);
        ballPane.getChildren().add(ball.getCircle()); // Add the ball's Circle to the pane		
	}
	
	
	
	// Method to change the color of all balls
    @FXML
    void changeColor(ActionEvent event) {
        for (Ball ball : ball_list) {
            Color newColor = new Color(Math.random(),Math.random(),Math.random(),1.0); // Random color
            ball.getCircle().setFill(newColor);
        }
    }
	
	

}

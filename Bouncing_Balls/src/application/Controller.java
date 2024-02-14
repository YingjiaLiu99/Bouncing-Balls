package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {	
	
	@FXML
	private Circle ball;
	private Color color;	
	
	public void changeColor(ActionEvent e) {
		// Generate random values for red, green, and blue components
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        // Create a new Color instance with the random values
        color = new Color(red, green, blue, 1.0);        
        ball.setFill(color);
	}

}

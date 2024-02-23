package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			String main_scene_css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(main_scene_css);
			
			primaryStage.setTitle("Bouncing Ball");
			Image appIcon = new Image("icon-ball.jpg");
			primaryStage.getIcons().add(appIcon);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

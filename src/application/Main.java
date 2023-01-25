package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PitagorasView.fxml"));
			ScrollPane scrollPane = loader.load();
	
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			Scene mainScene = new Scene(scrollPane);
			stage.setScene(mainScene);
			stage.setResizable(false);
			stage.setTitle("CROMAI - Teorema de Pitágoras");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagens/cromaiLogo.png")));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

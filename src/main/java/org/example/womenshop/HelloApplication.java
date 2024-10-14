package org.example.womenshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("connexionInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 404, 265.0);
        stage.setTitle("Connection");
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(265);
        stage.setMaxWidth(500);
        stage.setMaxHeight(265);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
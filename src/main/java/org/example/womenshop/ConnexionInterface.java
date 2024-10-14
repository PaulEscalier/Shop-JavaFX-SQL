package org.example.womenshop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.example.womenshop.HelloApplication.showAlert;

public class ConnexionInterface{
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void onLoginButtonClick(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        boolean login = false;
        String connectionQuery = "SELECT * FROM Users;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(connectionQuery);

            while (queryResult.next()) {
                if ((usernameField.getText().equals(queryResult.getString("email")) || usernameField.getText().equals(queryResult.getString("Firstname"))) && passwordField.getText().equals(queryResult.getString("Password"))) {
                    login = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        if(login){

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
        }else{
            showAlert("Error", "Invalid username or password.");
        }
    }

    public void onCreateAccountButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/womenshop/accountCreation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Account");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
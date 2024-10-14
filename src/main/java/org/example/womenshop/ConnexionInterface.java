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
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        boolean isAdmin = false;
        String connectionQuery = "SELECT * FROM Users;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(connectionQuery);

            while (queryResult.next()) {
                if ((usernameField.getText().equals(queryResult.getString("email")) || usernameField.getText().equals(queryResult.getString("Firstname"))) && passwordField.getText().equals(queryResult.getString("Password"))) {
                    login = true;
                    isAdmin = queryResult.getBoolean("Admin");
                    System.out.println(isAdmin);
                    System.out.println(queryResult);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        // Prise en charge de la connexion
        if(login){
            try {
                FXMLLoader fxmlLoader;
                Stage stage = new Stage();
                if (isAdmin) {
                    // Affichage de l'interface administrateur
                    fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/womenshop/adminInterface.fxml"));
                    stage.setTitle("Welcome Administrator");
                } else {
                    // Affichage de l'interface utilisateur du magasin
                    fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/womenshop/userInterface.fxml"));
                    stage.setTitle("Welcome to the Store");
                }
                Parent root = fxmlLoader.load();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
        } else {
            // Affichage d'une alerte indiquant que le nom d'utilisateur ou le mot de passe est incorrect
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
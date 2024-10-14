package org.example.womenshop;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.example.womenshop.HelloApplication.showAlert;

public class AccountCreation {
    @FXML
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;


    public void onCreateAccountButtonClick(ActionEvent actionEvent) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Password confirmation does not match.");
            return;
        }

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            String checkEmailQuery = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(checkEmailQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                showAlert("Error", "Email is already registered.");
            } else {
                String insertQuery = "INSERT INTO Users (Firstname, Lastname, Email, Password, Admin) VALUES (?, ?, ?, ?,false)";
                preparedStatement = connectDB.prepareStatement(insertQuery);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.executeUpdate();
                showAlert("Success", "Account created successfully.");
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


}

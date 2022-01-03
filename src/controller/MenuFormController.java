package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuFormController {
    @FXML
    private Button btnAddProgram;

    @FXML
    private Button lblAddStudent;

    @FXML
    void addProgramOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnAddProgram.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddProgram.fxml")));
        primaryStage.setTitle("Register Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void addStudentOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) lblAddStudent.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/StudentRegistration.fxml")));
        primaryStage.setTitle("Register Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

package GUI;

import BL.Managers.LogInManager;
import BL.Managers.Session;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.sql.SQLOutput;

public class AuthorizationStage extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("authorization.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("SAMPLE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args){
        launch(args);
    }


}

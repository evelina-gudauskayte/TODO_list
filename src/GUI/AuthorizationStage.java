package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuthorizationStage extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("authorization.fxml"));
        //Button enter = FXMLLoader.load(getClass().getResource(""))
        //root.get
        Scene scene = new Scene(root);
        primaryStage.setTitle("SAMPLE");
        primaryStage.setScene(scene);
        primaryStage.show();
        //while ()
    }



    public static void main(String[] args){
        launch(args);
    }
}

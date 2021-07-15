package gui;

import imageConversion.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    public void start(Stage primaryStage) throws Exception{
        initializePrimaryStage(primaryStage);
    }

    private void initializePrimaryStage(Stage primaryStage) throws IOException {
        primaryStage.setOnCloseRequest(event->{
            System.exit(0);
        });
        Parent root = new FXMLLoader(getClass().getResource("appView.fxml")).load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

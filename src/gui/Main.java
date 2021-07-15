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

    public void oldMain(String[] args) {
        if (args.length != 4){
            System.err.println("Arguments error! Usage: {source_image} {destination_image} {width} {height}");
            System.exit(8);
        }

        String inputFile = args[0];
        String outputFile = args[1];
        int width = Integer.parseInt(args[2]);
        int height = Integer.parseInt(args[3]);

        try {
            Image source = new Image(inputFile);
            source.generateDiceArt(width, height, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

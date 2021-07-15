package gui;

import imageConversion.Image;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.IOException;


public class Controller {

    FileChooser fileChooser = new FileChooser();
    @FXML TextField inputFileTextField;
    @FXML TextField widthText;
    @FXML TextField heightText;
    @FXML Text statusText;

    public void loadImage(){
        Window stage = inputFileTextField.getScene().getWindow();
        loadFileWindow("Loading input image", "monkey.jpg", "*.*");

        inputFileTextField.setText(fileChooser.showOpenDialog(stage).getAbsolutePath());
    }

    public void saveImage(){
        Window stage = inputFileTextField.getScene().getWindow();
        loadFileWindow("Saving output image", "output.png", "*.png");
        String outputFilePath = fileChooser.showSaveDialog(stage).getAbsolutePath();
        makeArt(outputFilePath);
    }

    private void makeArt(String outputFilePath) {
        String inputFilePath = inputFileTextField.getText();
        int width = Integer.parseInt(widthText.getText());
        int height = Integer.parseInt(heightText.getText());
        try {
            Image source = new Image(inputFilePath);
            source.generateDiceArt(width, height, outputFilePath);
            statusText.setFill(Color.GREEN);
            statusText.setText("Image saved successfully !");
        } catch (Exception e) {
            statusText.setFill(Color.RED);
            statusText.setText(e.getMessage());
        }
    }

    private void loadFileWindow(String title, String defaultFileName, String extention) {
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(defaultFileName);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", extention));
    }
}

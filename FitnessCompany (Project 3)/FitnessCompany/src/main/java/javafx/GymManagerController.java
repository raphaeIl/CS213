package javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GymManagerController implements Initializable {

    @FXML
    private static TextArea console;

    public static void log(String message) {
        System.out.println(message);
        if (!console.getText().isEmpty())
            console.setText(console.getText() + "\n");

        console.setText(console.getText() + message);
    }

    public void setConsole(TextArea textArea) {
        System.out.println(textArea);
        console = textArea;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void add(ActionEvent event) {

    }

    public void remove(ActionEvent event) {

    }



}
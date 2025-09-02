package stewie.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stewie.Stewie;

/**
 * A GUI for Stewie using FXML.
 */
public class Main extends Application {

    private static Stewie stewie = new Stewie();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/stewie/gui/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setStewie(stewie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


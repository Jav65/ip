package stewie.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import stewie.Stewie;
import stewie.command.CommandType;
import stewie.ui.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Stewie stewie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/stewie/gui/images/user.png"));
    private Image stewieImage = new Image(this.getClass().getResourceAsStream("/stewie/gui/images/stewie.png"));

    /** Initialize GUI layout */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getStewieDialog(Ui.showGreeting(false),
                                                                        stewieImage, CommandType.UNKNOWN));
    }

    /** Injects the Stewie instance */
    public void setStewie(Stewie d) {
        stewie = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Stewie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stewie.getResponse(input);
        CommandType commandType = stewie.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getStewieDialog(response, stewieImage, commandType)
        );
        userInput.clear();
    }
}

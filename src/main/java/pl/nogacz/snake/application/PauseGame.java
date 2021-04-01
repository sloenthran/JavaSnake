package pl.nogacz.snake.application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import static pl.nogacz.snake.board.Board.isPressedP;

public class PauseGame {
    public PauseGame(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Paused game");
        alert.setContentText("You have paused the game. Do you want to save?");
        ButtonType saveButton = new ButtonType("Save");
        ButtonType resumeButton = new ButtonType("Resume");

        alert.getButtonTypes().setAll(saveButton, resumeButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == saveButton){
            System.out.println("save");
        } else {
            isPressedP =false;
        }
    }
}

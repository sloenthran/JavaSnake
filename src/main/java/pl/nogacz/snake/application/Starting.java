package pl.nogacz.snake.application;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Starting {
    public GridPane gridPane = new GridPane();

    public Starting() {
        createBoardBackground();
    }

    private void createBoardBackground() {
        Image background = new Image(Resources.getPath("background.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(715, 715, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        gridPane.setBackground(new Background(backgroundImage));
        //do this.
        gridPane.add(new Button(),300,300);
    }
}

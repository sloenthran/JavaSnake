package pl.nogacz.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.nogacz.snake.application.Design;
import pl.nogacz.snake.application.Starting;
import pl.nogacz.snake.board.Board;

import java.util.Optional;

/**
 * @author Dawid Nogacz on 19.05.2019
 */
public class Snake extends Application {
    //Design design = new Design();
    //Board board = new Board(design);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Starting start=new Starting(primaryStage);
        primaryStage.setScene(new Scene(start.gridPane,715,715,Color.BLACK));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

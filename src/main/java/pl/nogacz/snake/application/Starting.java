package pl.nogacz.snake.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.nogacz.snake.board.Board;
import pl.nogacz.snake.board.Coordinates;
import pl.nogacz.snake.pawn.Pawn;
import pl.nogacz.snake.pawn.PawnClass;



public class Starting {
    public GridPane gridPane;
    Stage s;
    final double MAX_FONT_SIZE = 25.0;

    public Starting(Stage s) {
        this.s=s;
        Design d=new Design();// some designs for starting scene
        for(int i = 0; i < 22; i++) {
            d.addPawn(new Coordinates(0, i), new PawnClass(Pawn.BRICK));
            d.addPawn(new Coordinates(21, i), new PawnClass(Pawn.BRICK));
            d.addPawn(new Coordinates(i, 0), new PawnClass(Pawn.BRICK));
            d.addPawn(new Coordinates(i, 21), new PawnClass(Pawn.BRICK));
            if(i>=2 &&i<=20){
                d.addPawn(new Coordinates(1,i),new PawnClass(Pawn.SNAKE_BODY));
                d.addPawn(new Coordinates(20,i),new PawnClass(Pawn.SNAKE_BODY));
            }
        }
        d.addPawn(new Coordinates(1,1),new PawnClass(Pawn.SNAKE_HEAD));
        d.addPawn(new Coordinates(20,1),new PawnClass(Pawn.SNAKE_HEAD));

        gridPane=d.getGridPane();
        createStartingMenu();
    }

    private void createStartingMenu() {
        Label label=new Label("Do you want to load a game or start a new game?");
        label.setFont(new Font(MAX_FONT_SIZE));

        gridPane.add(label,1,5,20,1);

        Button b1=new Button("Load Game");
        b1.setFont(new Font(MAX_FONT_SIZE));

        Button b2=new Button("New Game");
        b2.setFont(new Font(MAX_FONT_SIZE));

        gridPane.add(b1,5,10,7,4);
        gridPane.add(b2,10,10,7,4);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("save");
                System.exit(0);
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Design design=new Design();
                Board board=new Board(design);
                Scene scene = new Scene(design.getGridPane(), 715, 715, Color.BLACK);
                scene.setOnKeyReleased(event -> board.readKeyboard(event));
                s.setTitle("JavaSnake");
                s.setScene(scene);
            }
        });


    }
}

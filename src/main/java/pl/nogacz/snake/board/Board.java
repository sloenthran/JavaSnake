package pl.nogacz.snake.board;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.nogacz.snake.application.Design;
import pl.nogacz.snake.pawn.Pawn;
import pl.nogacz.snake.pawn.PawnClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dawid Nogacz on 19.05.2019
 */
public class Board {
    private HashMap<Coordinates, PawnClass> board = new HashMap<>();
    private Design design;
    private boolean isEndGame = false;

    private int direction = 1; // 1 - UP || 2 - BOTTOM || 3 - LEFT || 4 - RIGHT

    private Coordinates snakeHeadCoordinates = new Coordinates(19, 10);
    private PawnClass snakeHeadClass = new PawnClass(Pawn.SNAKE_HEAD);

    public Board(Design design) {
        this.design = design;

        addStartSnake();
        addBricks();
        displayAllImage();

        mapTask();
    }

    private void addStartSnake() {
        board.put(snakeHeadCoordinates, snakeHeadClass);
    }

    private void addBricks() {
        int i = 0;

        for(i = 0; i < 22; i++) {
            board.put(new Coordinates(0, i), new PawnClass(Pawn.BRICK));
            board.put(new Coordinates(39, i), new PawnClass(Pawn.BRICK));
        }

        for(i = 1; i < 39; i++) {
            board.put(new Coordinates(i, 0), new PawnClass(Pawn.BRICK));
            board.put(new Coordinates(i, 21), new PawnClass(Pawn.BRICK));
        }
    }

    private void checkMap() {
        removeAllImage();
        runSnake();
        displayAllImage();
    }

    private void removeAllImage() {
        for(Map.Entry<Coordinates, PawnClass> entry : board.entrySet()) {
            design.removePawn(entry.getKey());
        }
    }

    private void displayAllImage() {
        for(Map.Entry<Coordinates, PawnClass> entry : board.entrySet()) {
            design.addPawn(entry.getKey(), entry.getValue());
        }
    }

    private void runSnake() {
        switch(direction) {
            case 1: moveSnake(new Coordinates(snakeHeadCoordinates.getX(), snakeHeadCoordinates.getY() - 1)); break;
            case 2: moveSnake(new Coordinates(snakeHeadCoordinates.getX(), snakeHeadCoordinates.getY() + 1)); break;
            case 3: moveSnake(new Coordinates(snakeHeadCoordinates.getX() - 1, snakeHeadCoordinates.getY())); break;
            case 4: moveSnake(new Coordinates(snakeHeadCoordinates.getX() + 1, snakeHeadCoordinates.getY())); break;
        }
    }

    private void moveSnake(Coordinates coordinates) {
        if(coordinates.isValid()) {
            if(isFieldNotNull(coordinates)) {

            } else {
                board.remove(snakeHeadCoordinates);
                board.put(coordinates, snakeHeadClass);

                snakeHeadCoordinates = coordinates;
            }
        }
    }

    private void mapTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                return null;
            }
        };

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if(!isEndGame) {
                    checkMap();
                    mapTask();
                }
            }
        });

        new Thread(task).start();
    }

    public void readKeyboard(KeyEvent event) {
        switch(event.getCode()) {
            case W: direction = 1; break;
            case S: direction = 2; break;
            case A: direction = 3; break;
            case D: direction = 4; break;

            case UP: direction = 1; break;
            case DOWN: direction = 2; break;
            case LEFT: direction = 3; break;
            case RIGHT: direction = 4; break;
        }
    }

    private boolean isFieldNotNull(Coordinates coordinates) {
        return getPawn(coordinates) != null;
    }

    private PawnClass getPawn(Coordinates coordinates) {
        return board.get(coordinates);
    }
}

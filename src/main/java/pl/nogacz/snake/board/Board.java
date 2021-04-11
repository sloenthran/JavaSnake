package pl.nogacz.snake.board;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import pl.nogacz.snake.application.Design;
import pl.nogacz.snake.application.EndGame;
import pl.nogacz.snake.pawn.Daire;
import pl.nogacz.snake.pawn.Kare;
import pl.nogacz.snake.pawn.Pawn;
import pl.nogacz.snake.pawn.PawnClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Dawid Nogacz on 19.05.2019
 */
public class Board {
    private HashMap<Coordinates, PawnClass> board = new HashMap<>();
    private Design design;
    private Random random = new Random();

    private boolean isEndGame = false;
    private boolean canDie=true;
    private static int direction = 1; // 1 - UP || 2 - BOTTOM || 3 - LEFT || 4 - RIGHT
    private int tailLength = 0;

    private Coordinates snakeHeadCoordinates = new Coordinates(10, 10);
    private Coordinates kareCoordinates=new Coordinates(-1,-1); //henuz yok
    private Coordinates daireCoordinates=new Coordinates(-1,-1);
    private Coordinates oldCoordinates=new Coordinates(10, 10);
    
    private PawnClass snakeHeadClass = new PawnClass(Pawn.SNAKE_HEAD);
    private PawnClass snakeBodyClass = new PawnClass(Pawn.SNAKE_BODY);
    private PawnClass foodClass = new PawnClass(Pawn.FOOD);
    private PawnClass kareClass=new PawnClass(Pawn.Kare); 
    private PawnClass daireClass=new PawnClass(Pawn.Daire);

    private Kare kare=new Kare();
    private Daire daire = new Daire();


    private ArrayList<Coordinates> snakeTail = new ArrayList<>();

    public Board(Design design) {
        this.design = design;

        addStartEntity();
        mapTask();
    }

    private void addStartEntity() {
        board.put(snakeHeadCoordinates, snakeHeadClass);

        for(int i = 0; i < 22; i++) {
            board.put(new Coordinates(0, i), new PawnClass(Pawn.BRICK));
            board.put(new Coordinates(21, i), new PawnClass(Pawn.BRICK));
            board.put(new Coordinates(i, 0), new PawnClass(Pawn.BRICK));
            board.put(new Coordinates(i, 21), new PawnClass(Pawn.BRICK));
        }

        addEat();
        int x=random.nextInt(10000);
        if(x==2){
            addKare();
        }
        displayAllImage();
    }

    private void checkMap() {
        removeAllImage();
        moveSnake();
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

    private void moveSnake() {
        
        oldCoordinates=snakeHeadCoordinates;

        switch(direction) {
            case 1: moveSnakeHead(new Coordinates(snakeHeadCoordinates.getX(), snakeHeadCoordinates.getY() - 1)); break;
            case 2: moveSnakeHead(new Coordinates(snakeHeadCoordinates.getX(), snakeHeadCoordinates.getY() + 1)); break;
            case 3: moveSnakeHead(new Coordinates(snakeHeadCoordinates.getX() - 1, snakeHeadCoordinates.getY())); break;
            case 4: moveSnakeHead(new Coordinates(snakeHeadCoordinates.getX() + 1, snakeHeadCoordinates.getY())); break;
        }
    }

    private void moveSnakeHead(Coordinates coordinates) {

        if(coordinates.isValid()) {

            
            if(kare.varMi()){
                
                if(kare.tur==kare.turSayisi){
                    canDie=true;
                    kare=new Kare();
                    board.remove(kareCoordinates);
                   
                }
            }
            

            
            if(!kare.varMi()){
                int x=random.nextInt(60);
                if(x==2){
                    kare.var=true;
                    addKare();
                }

            }

            if(daire.varMi()){
                       
                if(daire.tur==daire.turSayisi){
                    canDie=true;
                    daire=new Daire();
                    board.remove(daireCoordinates);
                   
                }
            }
            

            
            if(!daire.varMi()){
                int x=random.nextInt(90); //nadir
                if(x==2){
                    daire.var=true;
                    addDaire();
                }

            }
            
            if(isFieldNotNull(coordinates)) {
                if(getPawn(coordinates).getPawn().isFood()) {
                    board.remove(snakeHeadCoordinates);
                    board.put(snakeHeadCoordinates, snakeBodyClass);
                    board.put(coordinates, snakeHeadClass);
                    snakeTail.add(snakeHeadCoordinates);
                    tailLength++;

                    snakeHeadCoordinates = coordinates;

                    addEat();
                } 
                else if(getPawn(coordinates).getPawn().isKare()){
                    board.remove(snakeHeadCoordinates);
                    board.put(snakeHeadCoordinates, snakeBodyClass);
                    board.put(coordinates, snakeHeadClass);
                    snakeTail.add(snakeHeadCoordinates);
                    tailLength++;

                    snakeHeadCoordinates = coordinates;
                    canDie=false;
                    
                    kare.tur=0;
                }
                else if(getPawn(coordinates).getPawn().isDaire()){
                    board.remove(snakeHeadCoordinates);
                    board.put(snakeHeadCoordinates, snakeBodyClass);
                    board.put(coordinates, snakeHeadClass);
                    snakeTail.add(snakeHeadCoordinates);
                    tailLength++;

                    snakeHeadCoordinates = coordinates;
                    canDie=false;
                    
                    daire.tur=0;
                }
                else {
                    
                    if(canDie){
                        isEndGame = true;

                        new EndGame("End game...\n" +
                                "You have " + tailLength + " points. \n" +
                                "Maybe try again? :)");
                    }
                    else{ 
                        int newDirection=1;
                        coordinates=oldCoordinates;
                        //tam zit yeni yon
                        if(direction==1){
                            newDirection=2;
                        }
                        else if(direction==2){
                            newDirection=1;
                        }
                        if(direction==3){
                            newDirection=4;
                        }
                        else if(direction==4){
                            newDirection=3;
                        }
                        
                        direction=newDirection;
                        boolean kenarda=(coordinates.getX()==1)|(coordinates.getX()==20)|(coordinates.getY()==1)|(coordinates.getY()==20);
                        
                        if(kenarda){ //kenarlara carpmasi
                            if(direction==3){
                                coordinates=new Coordinates(coordinates.getX()-tailLength,coordinates.getY());
                            }
                            else if(direction==4){
                                coordinates=new Coordinates(coordinates.getX()+tailLength,coordinates.getY());
                            }
                            else if(direction==1){
                                coordinates=new Coordinates(coordinates.getX(),coordinates.getY()-tailLength);
                            }
                            else if(direction==2){
                                coordinates=new Coordinates(coordinates.getX(),coordinates.getY()+tailLength);
                            }
                        }
                        else{ //kendine carpmasi
                            if(direction==1 || direction==2){
                                //yon sag veya sol olarak degisecek
                                //hangisinde gidebilecegi daha fazla alan varsa
                                if(coordinates.getX()>10){
                                    newDirection=3; //left
                                }
                                else{
                                    newDirection=4; //rigth 
                                }
                            }
    
                            else{
                                //yon yukari veya asagi olarak degisecek
                                //hangisinde gidebilecegi daha fazla alan varsa
                                if(coordinates.getY()>10){
                                    newDirection=1; //up
                                }
                                else{
                                    newDirection=2; //buttom
                                }
                            }
                            direction=newDirection;
                        }

                        board.remove(snakeHeadCoordinates);
                        board.put(coordinates, snakeHeadClass);

                        
                        snakeHeadCoordinates = coordinates;

                        if(tailLength > 0) {
                            moveSnakeBody();
                        }

                    }
                }
            } else {
            
                board.remove(snakeHeadCoordinates);
                board.put(coordinates, snakeHeadClass);

                snakeHeadCoordinates = coordinates;

                if(tailLength > 0) {
                    moveSnakeBody();
                }
            }
        }
    }

    private void moveSnakeBody() {
        switch(direction) {
            case 1: moveSnakeBodyHandler(new Coordinates(snakeHeadCoordinates.getX(), snakeHeadCoordinates.getY() + 1)); break;
            case 2: moveSnakeBodyHandler(new Coordinates(snakeHeadCoordinates.getX(), snakeHeadCoordinates.getY() - 1)); break;
            case 3: moveSnakeBodyHandler(new Coordinates(snakeHeadCoordinates.getX() + 1, snakeHeadCoordinates.getY())); break;
            case 4: moveSnakeBodyHandler(new Coordinates(snakeHeadCoordinates.getX() - 1, snakeHeadCoordinates.getY())); break;
        }
    }

    private void moveSnakeBodyHandler(Coordinates coordinates) {
        if(tailLength == snakeTail.size()) {
            Coordinates endTail = snakeTail.get(0);
            board.remove(endTail);
            snakeTail.remove(endTail);
        }

        board.put(coordinates, snakeBodyClass);
        snakeTail.add(coordinates);
    }

    private void addEat() {
        Coordinates foodCoordinates;

        do {
            foodCoordinates = new Coordinates(random.nextInt(21), random.nextInt(21));
        } while(isFieldNotNull(foodCoordinates));

        board.put(foodCoordinates, foodClass);
    }

    private void addKare() {
        //kare ekleme
        do {
            kareCoordinates = new Coordinates(random.nextInt(21), random.nextInt(21));
        } while(isFieldNotNull(kareCoordinates));

        board.put(kareCoordinates, kareClass);
    }
   
    private void addDaire() {
        //daire ekleme
        do {
            daireCoordinates = new Coordinates(random.nextInt(21), random.nextInt(21));
        } while(isFieldNotNull(daireCoordinates));

        board.put(daireCoordinates, daireClass);
    }


    private void mapTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(140);
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
        if(kare.varMi()){
            kare.tur++;
            //bir tur gecti
        }
        if(daire.varMi()){
            daire.tur++;
        }
        
        switch(event.getCode()) {
            case W: changeDirection(1); break;
            case S: changeDirection(2); break;
            case A: changeDirection(3); break;
            case D: changeDirection(4); break;

            case UP: changeDirection(1); break;
            case DOWN: changeDirection(2); break;
            case LEFT: changeDirection(3); break;
            case RIGHT: changeDirection(4); break;
        }
    }

    private void changeDirection(int newDirection) {
    
        if(newDirection == 1 && direction != 2) {
            direction = 1;
        } else if(newDirection == 2 && direction != 1) {
            direction = 2;
        } else if(newDirection == 3 && direction != 4) {
            direction = 3;
        } else if(newDirection == 4 && direction != 3) {
            direction = 4;
        }
    }

    private boolean isFieldNotNull(Coordinates coordinates) {
        return getPawn(coordinates) != null;
    }

    private PawnClass getPawn(Coordinates coordinates) {
        return board.get(coordinates);
    }

    public static int getDirection() {
        return direction;
    }
}

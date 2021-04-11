package pl.nogacz.snake.pawn;

/**
 * @author Dawid Nogacz on 19.05.2019
 */
public enum Pawn {
    FOOD,
    BRICK,
    SNAKE_HEAD,
    SNAKE_BODY,
    Kare,
    Daire;

    public boolean isFood() {
        return this == FOOD;
    }
    
    public boolean isKare() {
        return this == Kare;
    }
    public boolean isDaire() {
        return this == Daire;
    }

    public boolean isHead() {
        return this == SNAKE_HEAD;
    }
}

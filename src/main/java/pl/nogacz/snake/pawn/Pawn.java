package pl.nogacz.snake.pawn;

/**
 * @author Dawid Nogacz on 19.05.2019
 */
public enum Pawn {
    FOOD,
    BRICK,
    SNAKE_HEAD,
    SNAKE_BODY;

    public boolean isFood() {
        return this == FOOD;
    }

    public boolean isBrick() {
        return this == BRICK;
    }

    public boolean isBody() {
        return this == SNAKE_BODY;
    }
}

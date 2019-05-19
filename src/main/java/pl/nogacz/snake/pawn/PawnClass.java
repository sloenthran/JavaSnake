package pl.nogacz.snake.pawn;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.nogacz.snake.application.Resources;

/**
 * @author Dawid Nogacz on 19.05.2019
 */
public class PawnClass {
    private Pawn pawn;

    public PawnClass(Pawn pawn) {
        this.pawn = pawn;
    }

    public ImageView getImage() {
        Image image = new Image(Resources.getPath(pawn + ".png"));
        return new ImageView(image);
    }

    public Pawn getPawn() {
        return pawn;
    }
}

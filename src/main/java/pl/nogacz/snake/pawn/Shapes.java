package pl.nogacz.snake.pawn;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.nogacz.snake.application.Resources;


public class Shapes{

    private Shapes shape;
    int turSayisi;

    public int getTurSayisi(){
        return turSayisi;
    }

    public Shapes() {
       
    }

    public ImageView getImage() {
        Image image = new Image(Resources.getPath(shape + ".png"));
        return new ImageView(image);
    }

    public ImageView getImageDirection(int direction) {
        String direct = "";

        switch(direction) {
            case 1: direct = "UP"; break;
            case 2: direct = "BOTTOM"; break;
            case 3: direct = "LEFT"; break;
            case 4: direct = "RIGHT"; break;
        }

        Image image = new Image(Resources.getPath(shape + "_" + direct + ".png"));
        return new ImageView(image);
    }

    public Shapes getShape() {
        return shape;
    }

    
}

package pl.nogacz.snake.pawn;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.nogacz.snake.application.Resources;


public class Shapes{

    public final int turSayisi=0; //etkili oldugu tur sayisi

    public boolean var;
    public int tur=0;

    public Shapes() {
        var=false;
        tur=0;
    }
    
    public int getTurSayisi(){
        return turSayisi;
    }

    public boolean varMi(){
        //tur=tur+1;
        return var;
    }

    
}

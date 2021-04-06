package pl.nogacz.snake.pawn;

//import javafx.scene.shape.Shape;

public class Kare extends Shapes {
    
    public boolean kareVar;
    public int tur=0;

    public Kare() {
        kareVar=false;
        tur=0;
    }
    public final int turSayisi=4; //etkili oldugu tur sayisi
    public int getTurSayisi(){
        return turSayisi;
    }
    

    public boolean kareVarMi(){
        tur=tur+1;
        return kareVar;
    }
    
}

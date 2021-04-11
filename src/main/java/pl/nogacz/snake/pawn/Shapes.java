package pl.nogacz.snake.pawn;

public class Shapes{

    public final int turSayisi=0;
    //daire daha nadir cikan ve uzun bir sure koruyan kare ise daha fazla cikan ve daha az koruyan bir sekil 

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

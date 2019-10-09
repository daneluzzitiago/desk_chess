package pecas;

import java.awt.Graphics2D;
import java.io.IOException;

public class Rei  extends Peca{

    public Rei(Cor cor, int x, int y) {
        super(cor, x, y);
        this.sourei = true;
        this.nome = "REI";
    }

    @Override
    public void draw(Graphics2D g) {
        
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;
        
        int x0 = quadrante.x * squareWidth;
        int y0 = quadrante.y * squareHeight;
        int x1 = x0 + squareWidth;
        int y1 = y0 + squareHeight;
        
        if(this.cor == Peca.Cor.PRETO){
            g.drawImage(pecasImg, x0, y0, x1, y1, 20, 20, 60, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 20, 72, 60, 112, null);
        }
    }
    
    @Override
    public String toString() {
        return null;
    }
    
//    @Override
//    public void setQuadrante(int x, int y) {
//        if(casaPossivel(x, y)){
//            quadrante.setLocation(x, y);
//        }
//        else System.out.println("Posiçao invalida");
//    }

    @Override
    public boolean casaPossivel(int x, int y) {
        return Math.abs(quadrante.x - x) <= 1 && Math.abs(quadrante.y - y) <= 1;
    }
}
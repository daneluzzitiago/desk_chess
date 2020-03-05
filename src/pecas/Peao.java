/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecas;

import model.Chess;
import java.awt.Graphics2D;


public class Peao  extends Peca{

    public Peao(Cor cor, int x, int y)  {
        super(cor, x, y);
        this.soupeao = true;
        this.nome = "PEÃO";
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 320, 20, 360, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 320, 72, 360, 112, null);
        }
    }
    
    @Override
    public String toString() {
        return null;
    }

//    @Override
//    public void setQuadrante(int x, int y) {
//        if (casaPossivel(x, y)) //movemento desde posicao inicial
//            quadrante.setLocation(x, y);
//        else
//            System.out.println("Posiçao invalida");
//    }

    @Override
    public boolean casaPossivel(int x, int y) {
        if (this.cor == Peca.Cor.BRANCO) {
            if (quadrante.y == 6 && (x == quadrante.x) ) {       //movemento desde posicao inicial
                return y == 5 || y == 4 ;
            }
            else if ((quadrante.y < 6) && (x == quadrante.x) ) { //movemento no meio do tabuleiro
                return y - quadrante.y == -1;
            }
            else return false;
        }
        else {
            if (quadrante.y == 1 && (x == quadrante.x)) {       //movemento desde posicao inicial
                return y == 2 || y == 3 ;
            }
            else if ((quadrante.y > 1) ) { //movemento no meio do tabuleiro
                return y - quadrante.y == +1;
            }

            else return false;
        }
    }
}
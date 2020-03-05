/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecas;

import java.awt.Graphics2D;
import java.util.ArrayList;
import model.Chess;
import java.io.Serializable;

/**
 *
 * @author felipelageduarte
 */
public class Rainha extends Peca{

    public Rainha(Cor cor, int x, int y) {
        super(cor, x, y);
        this.nome = "RAINHA";
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 80, 20, 120, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 80, 72, 120, 112, null);
        }
    }
    
    @Override
    public String toString() {
        return null;
    }
    
    
//    @Override
//    public void setQuadrante(int x, int y) {
//        if( casaPossivel(x, y) && !pula(x,y)) {
//            
//            quadrante.setLocation(x, y);
//        }
//            
//            
//        else
//            System.out.println("Posiçao invalida");
//    }

    @Override
    public boolean casaPossivel(int x, int y) {
        return Math.abs(quadrante.x - x) == Math.abs(quadrante.y - y) || (quadrante.x - x) == 0 || (quadrante.y - y) == 0;
    }
}
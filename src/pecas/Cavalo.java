/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecas;

import java.awt.Graphics2D;

/**
 *
 * @author felipelageduarte
 */
public class Cavalo extends Peca{

    public Cavalo(Cor cor, int x, int y)  {
        super(cor, x, y);
        this.soucavalo = true;
        this.nome = "CAVALO";
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 260, 20, 300, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 260, 72, 300, 112, null);
        }
    }
    
    @Override
    public String toString() {
        return null;
    }
    
    
    @Override
    public void setQuadrante(int x, int y) {
        if(casaPossivel(x, y)) {
            quadrante.setLocation(x, y);       
        }
        else
            System.out.println("Posiçao invalida");
    }

    @Override
    public boolean casaPossivel(int x, int y) {
        return (Math.abs(quadrante.x - x) + Math.abs(quadrante.y - y)) == 3 && (Math.abs(quadrante.x - x) == 1 || Math.abs(quadrante.y - y) == 1);
    }


    
}

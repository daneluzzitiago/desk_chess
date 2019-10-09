/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pecas;

import Model.Chess;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
//import Model.Chess.*;
//import static Model.Chess.findPeca; //por que fazendo static rola e sem fazer nao?

public abstract class Peca implements Serializable{

    protected boolean soupeao = false;
    protected final static String imgPath = "img/pecas.png";
    protected static BufferedImage pecasImg = null;    
    protected Cor cor;
    protected Point quadrante;
    protected boolean sourei = false;
    protected boolean soucavalo = false;
    protected String nome;
    
    public String getName(){
        return this.nome;
    }
    public boolean getSouPeao(){
        return soupeao;
    }
   
    public boolean getSouCavalo(){
        return this.soucavalo;
    }
    
    public boolean getSouRei(Peca P){
        return this.sourei;
    }
    
    public Point getQuadrante() {
        return quadrante;
    }
    
    public void setQuadrante(int x, int y){
            quadrante.setLocation(x, y);
    }
        
    public enum Cor{
        PRETO,
        BRANCO,
    }
    
   public boolean souBranca(){
       return this.cor == Cor.BRANCO;
   }
    
    public Peca(Cor cor, int x, int y){
        this.cor = cor;
        this.quadrante = new Point(x,y);
        if(pecasImg == null){
            
        }
    }
    
    public boolean inSquare(int x, int y){
        if(x == quadrante.x && y == quadrante.y) return true;
        else return false;
    }
    
    public abstract boolean casaPossivel(int x, int y);
//    {
//        Peca pecaFim = findPeca(x, y);
//        if (pecaFim == null)
//            System.out.println("Casa vazia");
//            return true;
//       return (pecaFim.getCor()!=this.getCor());
//    }
    
    
    public Cor getCor() {
        return this.cor;
    }
    
    
    
    public abstract void draw(Graphics2D g);
}

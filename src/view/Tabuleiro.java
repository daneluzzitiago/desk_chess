package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel implements Serializable{
    
    private ArrayList<Observer> observers;

    
    
    public Tabuleiro() {
        //super();
        observers = new ArrayList<Observer>();
        
    }
    
    public void delChess(Observer ob) {
        observers.remove(ob);
    }
    
    public static void showWinner(){
        Winner frame = new Winner();
        frame.setVisible(true);
    }
          
    public void registerObserver(Observer ob){
        observers.add(ob);
    }
    
    private void drawBoard(Graphics2D g){
        
        Color Amarelo = new Color (255, 255, 50);
        Color Cinza = new Color (50, 50, 50);
        
        
        g.setBackground(Cinza);
        g.setColor(Amarelo);
                 
        float maxWidth=this.getWidth();
        float maxHeight=this.getHeight();
        float boardSize = (maxWidth < maxHeight) ? maxWidth : maxHeight;
        int spotSize = Math.round(boardSize/8.0f);
                  
        for(int i = 0; i<8; ++i){
            for(int j = 0; j<8; ++j){
                //varia a cor do quadrante
                if(g.getColor() == Cinza){
                    if (j<4){
                        g.setColor(Color.WHITE);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                }
                else g.setColor(Cinza);

                //Desenha o tabuleiro
                g.fillRect(i*spotSize,j*spotSize,(i*spotSize)+spotSize, (j*spotSize)+spotSize);
            }

            if(g.getColor() == Cinza) g.setColor(Color.WHITE);
            else g.setColor(Cinza);
        }
    }
    
    /*
    Metodo super importante: ele é a porta de entrada para desenhos no canvas.
    Este metodo callback será invocado toda vez que o jPanel precisar ser
    desenhado (janela mudou de tamanho, janela foi sobreposta por outra e depois
    revelada e tb será invocado forcadamente quando for feita a chamada ao método
    repaint() !!!!! e  mais
    o argumento Graphics g é o elemento que contem toda a funcionalidade para
    desenha (tracar linhas, desenhar letras, figuras, etc....) ele é fundamental
    */

    @Override //sobrescrita do metodo paintComponent da classe JPanel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
             
        Graphics2D g2 = (Graphics2D)g;
        drawBoard(g2);
        for(Observer ob : observers){
            ob.update(null, g);
        }
    }   

}

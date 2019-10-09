package Model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import pecas.Bispo;
import pecas.Cavalo;
import pecas.Peao;
import pecas.Peca;
import pecas.Rainha;
import pecas.Rei;
import pecas.Torre;
import Controller.ChessController;
import java.io.Serializable;


public class Chess implements Observer, Serializable  {
    
    private ArrayList<Peca> pecasPretas;  // uma lista das pecas pretas
    private ArrayList<Peca> pecasBrancas; // uma lista das pecas brancas
    public ArrayList<Peca> casasVazias; // uma lista das casas sem peças
    private Point mouseCoord;    // coordenada onde o mouse foi clicado 
    private Peca selectedPeca = null;   // qual peca foi selecionada (se eh que foi) quando o mouse foi clicado
    private boolean turmaBranca = true;
    public ArrayList<Peca> casasPossivels;
    public int tdec = 0;
    public int tseg = 0;
    public int tmin = 0;
    public int bdec = 0;
    public int bseg = 0;
    public int bmin = 15;
    public int pdec = 0;
    public int pseg = 0;
    public int pmin = 15;
    
    public Chess()  {
        this.pecasPretas = new ArrayList<Peca>();
        this.pecasBrancas  = new ArrayList<Peca>();
        this.casasVazias = new ArrayList<Peca>();
        mouseCoord = new Point();
        
        init();
    }
    
    public enum Cor{
        PRETO,
        BRANCO,
    }
    
    public boolean casaPossivelComer(Peca p,int x, int y) {
        if ( p.getSouPeao() ){
            if ( p.souBranca() && p.getQuadrante().y-1 == y && (p.getQuadrante().x+1 == x || p.getQuadrante().x-1 == x) ){
                Peca pecaProPeaoComer = findPeca (x,y);
                if (pecaProPeaoComer == null){
                    return false;
                }
                else return pecaProPeaoComer.getCor() != p.getCor();
            }
            else if ( !p.souBranca() && p.getQuadrante().y+1 == y && (p.getQuadrante().x+1 == x || p.getQuadrante().x-1 == x) ){
                Peca pecaProPeaoComer = findPeca (x,y);
                if (pecaProPeaoComer == null){
                    return false;
                }
                else return pecaProPeaoComer.getCor() != p.getCor();
            }
            else return false;
        }
        else if ( p.casaPossivel( x,y ) ) {
            Peca pecaPraComer = findPeca(x, y);
            return pecaPraComer.getCor() != p.getCor();
        } else {
            return false;
    }
}
    
public void setCasasPossivels(Peca peca) {
        casasPossivels = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ( peca.casaPossivel(i, j) || casaPossivelComer(peca,i, j)) {
                    Peca p = findPeca(i, j);
                    casasPossivels.add(p);
                }
            }
        }
    }
 
    
    public boolean getTurma(){
        return this.turmaBranca;
    }
    
    public Point getMouseCoord() {
        return mouseCoord;
    }

    public void setMouseCoord(Point mouseCoord) {
        this.mouseCoord = mouseCoord;
    }

    public Peca getSelectedPeca() {
        return selectedPeca;
    }
    public void setSelectedPeca(Peca selectedPeca) {
        this.selectedPeca = selectedPeca;
    }

    public boolean isTurmaBranca() {
        return turmaBranca;
    }

    public void changeTurmaBranca() {
        this.turmaBranca = !this.turmaBranca;
    }

    
    private void init() {
        
        //inicializa time branco
        pecasBrancas.add(new Rei(Peca.Cor.BRANCO,4,7));
        pecasBrancas.add(new Rainha(Peca.Cor.BRANCO,3,7));
        pecasBrancas.add(new Bispo(Peca.Cor.BRANCO,5,7));
        pecasBrancas.add(new Bispo(Peca.Cor.BRANCO,2,7));
        pecasBrancas.add(new Cavalo(Peca.Cor.BRANCO,1,7));
        pecasBrancas.add(new Cavalo(Peca.Cor.BRANCO,6,7));        
        pecasBrancas.add(new Torre(Peca.Cor.BRANCO,0,7));
        pecasBrancas.add(new Torre(Peca.Cor.BRANCO,7,7));
        pecasBrancas.add(new Peao(Peca.Cor.BRANCO,0,6));
        pecasBrancas.add(new Peao(Peca.Cor.BRANCO,1,6));
        pecasBrancas.add(new Peao(Peca.Cor.BRANCO,2,6));
        pecasBrancas.add(new Peao(Peca.Cor.BRANCO,3,6));
        pecasBrancas.add(new Peao(Peca.Cor.BRANCO,4,6));
        pecasBrancas.add(new Peao(Peca.Cor.BRANCO,5,6));
        pecasBrancas.add(new Peao(Peca.Cor.BRANCO,6,6));
        pecasBrancas.add(new Peao(Peca.Cor.BRANCO,7,6));
        
        //inicializa time preto
        pecasBrancas.add(new Rei(Peca.Cor.PRETO,4,0));
        pecasBrancas.add(new Rainha(Peca.Cor.PRETO,3,0));
        pecasBrancas.add(new Bispo(Peca.Cor.PRETO,2,0));
        pecasBrancas.add(new Bispo(Peca.Cor.PRETO,5,0));
        pecasBrancas.add(new Cavalo(Peca.Cor.PRETO,1,0));
        pecasBrancas.add(new Cavalo(Peca.Cor.PRETO,6,0));
        pecasBrancas.add(new Torre(Peca.Cor.PRETO,7,0));
        pecasBrancas.add(new Torre(Peca.Cor.PRETO,0,0));
        pecasBrancas.add(new Peao(Peca.Cor.PRETO,0,1));
        pecasBrancas.add(new Peao(Peca.Cor.PRETO,1,1));
        pecasBrancas.add(new Peao(Peca.Cor.PRETO,2,1));
        pecasBrancas.add(new Peao(Peca.Cor.PRETO,3,1));
        pecasBrancas.add(new Peao(Peca.Cor.PRETO,4,1));
        pecasBrancas.add(new Peao(Peca.Cor.PRETO,5,1));
        pecasBrancas.add(new Peao(Peca.Cor.PRETO,6,1));
        pecasBrancas.add(new Peao(Peca.Cor.PRETO,7,1));
               
        
    }
    public boolean remove(int x,int y){
        
        for(int i = 0 ; i < pecasBrancas.size() ; i++){
            Peca p = pecasBrancas.get(i);
            if(p.inSquare(x,y)){
                pecasBrancas.remove(p);
                if ( p.getSouRei(p)){
                    
                    return true;
                }
            }
        }
        
        for(int i = 0 ; i < pecasBrancas.size() ; i++){
            Peca p = pecasBrancas.get(i);
            if(p.inSquare(x,y)){
                pecasPretas.remove(p);
                if ( p.getSouRei(p)){
                   
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    public Peca findPeca(int x, int y) {
        Peca peca = null;
        
        //BUsca: sera que eh branca????
        for(Peca p : this.pecasBrancas){
            if(p.inSquare(x,y)){
                return p;
            }
        }
        
        //BUsca: sera que eh preta????
        for(Peca p : this.pecasPretas){
            if(p.inSquare(x,y)){
                return p;
            }
        }
        
        return peca;
    }
    
    

    public  ArrayList<Peca> getCasasPossivels() {
        return casasPossivels;
    }
    
    /*
        Possibilita que as peças recebam o vetor de casas vazias,
    garantindo assim que não está pulando nenhuma peça;
    */
    public ArrayList<Peca> getCasasVazias() {
        return this.casasVazias;
    }
    
    public boolean salta(Peca p, int x, int y){
        if (p.getSouCavalo()){
            return false;
        }
        int i = p.getQuadrante().x;
        int j = p.getQuadrante().y;
        if ( i < x) i++;
        if ( i > x) i--;
        if ( j < y) j++;
        if ( j > y) j--;
        
        
        Peca outra;
        
        while ( i != x || j != y){
            outra = findPeca(i, j);
            if ( outra != null ){
                
                return true; //TA PULANDO
            }
            else{
                if ( i < x) i++;
                if ( i > x) i--;
                if ( j < y) j++;
                if ( j > y) j--;
            }
        }
        
        outra = findPeca(x, y);
        if (outra == null) 
            return false;
        else {
            if ( outra.getCor() == p.getCor()){
                return true;
            }
        }
        
        return false;
    }   
    
//    public boolean haCheque() {
//        for (Peca p : pecasBrancas) {
//            setCasasPossivels(p);
//            if (casasPossivels.contains(?????))) { //Nao sei como meter o Rei como argumento aqui
//                System.out.println("BRANCAS FAZEM CHEQUE AS PRETAS");
//                return true;
//            } else return false;
//        }
//        
//        for (Peca p : pecasPretas) {
//            setCasasPossivels(p);
//            if (casasPossivels.contains(?????))) { //Nao sei como meter o Rei como argumento aqui
//                System.out.println("PRETAS FAZEM CHEQUE AS BRANCAS");
//                return true;
//            } else return false;
//        }
//    }

    public void draw(Graphics2D g){
        //desenha pecas Brancas
        for(Peca p : pecasBrancas){
            p.draw(g);
        }
        
        //desenha pecas pretas
        for(Peca p : pecasPretas){
            p.draw(g);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        draw((Graphics2D) arg);
    }
 }

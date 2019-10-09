/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Chess;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pecas.Peca;
import view.DeskChessFrame;
import view.Tabuleiro;
import view.Winner;


/**
 *
 * @author jbatista
 */
public class ChessController implements MouseListener, MouseMotionListener, ActionListener{

    private boolean pecaSelecionada = false;
    private DeskChessFrame view;
    private Chess model;
    private Winner fim;
    private boolean running = false;
    /*
        Execução da thread com o relógio
    */

    
    public void startClock(){
        new Timer().start();
        running = true;
    }
    
    public void addThings(Chess model,DeskChessFrame view, ChessController controller){
                addView(view);
                addModel(model);
                view.getBoard().registerObserver(view);
                view.getBoard().registerObserver(model);
                view.addController(controller);
    }
    /*
        Controlador de tempo dos relógios
    */
    
    private class Timer extends Thread{
                
        public void run(){
            while (true){
                try {
                    Thread.sleep(100);
                }
                catch (Exception e){
                }
                /*
                    Atualização do relogio, importante para os loads
                */
                if(model.bseg < 10){
                        if (model.bmin < 10){view.Branco.setText( "0" +model.bmin+ ":0" +model.bseg+ ":" +model.bdec );
                        }else view.Branco.setText( model.bmin+ ":0" +model.bseg+ ":" +model.bdec );
                    } else {
                        if (model.bmin < 10){view.Branco.setText( "0" +model.bmin+ ":" +model.bseg+ ":" +model.bdec );
                        }else view.Branco.setText(model.bmin+ ":" +model.bseg+ ":" +model.bdec);
                    }
                if(model.pseg < 10){
                        if (model.pmin < 10){view.Preto.setText( "0" +model.pmin+ ":0" +model.pseg+ ":" +model.pdec );
                        }else view.Preto.setText( model.pmin+ ":0" +model.pseg+ ":" +model.pdec );
                    } else {
                        if (model.pmin < 10){view.Preto.setText( "0" +model.pmin+ ":" +model.pseg+ ":" +model.pdec );
                        }else view.Preto.setText(model.bmin+ ":" +model.pseg+ ":" +model.pdec);
                    }
                
                /*
                    Finaliza jogo por time out, identificando qual jogador teve tempo esgotado
                */
                
                
                if ( model.bmin == 0 && model.bseg == 0 && model.bdec == 0){fechaFrame();}
                if ( model.pmin == 0 && model.pseg == 0 && model.pdec == 0){fechaFrame();}
                /*
                    Operação do relógio principal
                */
                model.tdec++;
                if(model.tdec == 10){
                    model.tdec = 0;
                    model.tseg++;
                    if (model.tseg%15 == 0){
                        autoSave();
                        view.logArea.append("JOGO SALVO AUTOMATICAMENTE\n");
                    }
                    if(model.tseg == 60){
                        model.tseg = 0;
                        model.tmin++;
                    }
                    
                }
                if(model.tseg < 10){
                    if (model.tmin < 10){view.Tempo.setText( "0" +model.tmin+ ":0" +model.tseg+ ":" +model.tdec );
                    }else view.Tempo.setText( model.tmin+ ":0" +model.tseg+ ":" +model.tdec );
                } else {
                    if (model.tmin < 10){view.Tempo.setText( "0" +model.tmin+ ":" +model.tseg+ ":" +model.tdec );
                    }else view.Tempo.setText(model.tmin+ ":" +model.tseg+ ":" +model.tdec);
                }
                
                /*
                    Operação do relógio das turmas
                */
                if (model.getTurma()){                                          ///Turma branca
                    
                    
                    model.bdec--;                                                     ///Aritmética
                    if (model.bdec == -1){
                        model.bdec = 9;
                        model.bseg--;
                        if (model.bseg == -1){                                
                            model.bseg = 59;
                            model.bmin--;
                        }
                    }
                                                                 ///Impressão inteligente
                    if(model.bseg < 10){
                        if (model.bmin < 10){view.Branco.setText( "0" +model.bmin+ ":0" +model.bseg+ ":" +model.bdec );
                        }else view.Branco.setText( model.bmin+ ":0" +model.bseg+ ":" +model.bdec );
                    } else {
                        if (model.bmin < 10){view.Branco.setText( "0" +model.bmin+ ":" +model.bseg+ ":" +model.bdec );
                        }else view.Branco.setText(model.bmin+ ":" +model.bseg+ ":" +model.bdec);
                    }
                    

                }
                
                else{                                                           ///Tumar preta
                   
                    model.pdec--;                                                     ///Aritmética
                    if (model.pdec == -1){
                        model.pdec = 9;
                        model.pseg--;
                        if (model.pseg == -1){                                
                            model.pseg = 59;
                            model.pmin--;
                        }
                    }
                    
                    
                                                                                ///Impressão inteligente
                    if(model.pseg < 10){
                        if (model.pmin < 10){view.Preto.setText( "0" +model.pmin+ ":0" +model.pseg+ ":" +model.pdec );
                        }else view.Preto.setText( model.pmin+ ":0" +model.pseg+ ":" +model.pdec );
                    } else {
                        if (model.pmin < 10){view.Preto.setText( "0" +model.pmin+ ":" +model.pseg+ ":" +model.pdec );
                        }else view.Preto.setText(model.bmin+ ":" +model.pseg+ ":" +model.pdec);
                    }
                }
            }
        
        }
   }

    public void loadGame(Tabuleiro board, ChessController controller){
        try {
            FileInputStream in = new FileInputStream("Saved");
            ObjectInputStream objIn = new ObjectInputStream (in);
            
            this.model = (Chess) objIn.readObject();
            
            board.delChess(view);
            board.registerObserver(view);
            controller.addModel(model);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveGame(){
        try {
            FileOutputStream out = new FileOutputStream("Saved");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(this.model);
            objOut.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadAutoSaved(){
        try {
            FileInputStream in = new FileInputStream("AutoSaved");
            ObjectInputStream objIn = new ObjectInputStream (in);
            
            this.model = (Chess) objIn.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void autoSave(){
        try {
            FileOutputStream out = new FileOutputStream("AutoSaved");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(this.model);
            objOut.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChessController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
        "Copia" o view e model que a Main está usando
    */
    public void addView(Observer view){
        this.view = (DeskChessFrame)view;
    }
    public void addModel(Chess model){
        this.model = model;
    }
    
    /*
        Método para exibir ganhador
    */
    public void fechaFrame(){
        this.fim = new Winner();
        this.fim.setVisible(true);
        this.view.setVisible(false);
       // this.view.setVisible(false);
 
    }
    
    /*
        Analisa se há alguma peça selecionada. Importante para saber se o jogador
        está querendo selecionar uma peça específica ou querendo comer outra
    */
    
    private boolean haPecaSelecionada() {
        return pecaSelecionada;
    }        

    /*
        "Setar" peça selecionada
    */
    private void setPecaSelecionada(boolean pecaSelecionada) {
        this.pecaSelecionada = pecaSelecionada;
    }
    
    
    /*
        Pinta de vermelho o quadrante onde está o mouse
    */
    public void drawMouseQuadrante(Graphics2D g) {
        
        int width = view.getBoardPanel().getWidth()/8;
        int height = view.getBoardPanel().getHeight()/8;
        
        int qx = model.getMouseCoord().x/width;
        int qy = model.getMouseCoord().y/height;
        
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;

        g.setColor(Color.red);
        g.setStroke(new BasicStroke(4));
        g.drawRect(qx * squareWidth, qy * squareHeight, squareWidth, squareHeight);
        g.setColor(Color.BLACK);
    }
    
    public void drawPecaAtualQuadrante(Graphics2D g) {
        int width = view.getBoardPanel().getWidth()/8;
        int height = view.getBoardPanel().getHeight()/8;
        
        int qx = model.getMouseCoord().x/width;
        int qy = model.getMouseCoord().y/height;
        
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;

        g.setColor(Color.blue);
        g.setStroke(new BasicStroke(4));
        g.drawRect(qx * squareWidth, qy * squareHeight, squareWidth, squareHeight);
        g.setColor(Color.BLACK);
    }
    
   
    @Override
    public void mouseClicked(MouseEvent e) {            
    }
    
    public boolean isPecaPossivel(Peca peca) {
        try{    
            if ( ( peca.getCor() != Peca.Cor.BRANCO && model.isTurmaBranca() ) || ( peca.getCor() != Peca.Cor.PRETO && !model.isTurmaBranca()) )
                
                throw new WrongTeamException("Time errado");
            /*
                Aqui acontece algo quando o jogador seleciona uma peça da turma errada
            */
                
        }
        catch (Exception ex){
            view.logArea.append("Turma inválida.\n");
            return false;
        }
        return true;
    }
      
    
    
    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("X: " + e.getX() + "\tY: " + e.getY());
        if (!running){
            return;
        }
        int width = view.getBoardPanel().getWidth()/8;
        int height = view.getBoardPanel().getHeight()/8;
        
        Peca peca = model.findPeca(e.getX()/width, e.getY()/height);
        try{
            if (!haPecaSelecionada()) {
                /*
                    Teste para saber se está clicando em uma peça ou em um lugar vazio
                    quando ainda não há nenhuma outra peça selecionada
                */
                if(peca != null){
                    if (isPecaPossivel(peca)) {
                        setPecaSelecionada(true);
                        model.setSelectedPeca(peca);
                    } 
    //                else {
    //                    /*
    //                        Caso onde está selecionando turma 
    //                    */
    //                    System.out.println("PAMONHA");
    //                    setPecaSelecionada(false);
    //
    //                }
    //                          TUDO ACIMA JÁ ERA TESTADO EM iSPecaPossivel

                } 
                else {
                    throw new EmptyException ("Casa vazia");
                }
            } 
            else /* if (haPecaSelecionada()) <-- Desnecessário */ {
                move(model.getSelectedPeca(), e.getX()/width, e.getY()/height);
                /*
                    O movimento aconteceu !!!
                */
                setPecaSelecionada(false);
            }

            /*
                Nesse xadrez, depois de cada movimento é necessário repintar com as
                novas posições
            */
            view.repaint();
        }
        catch (EmptyException ex){
            view.logArea.append("Selecione uma peça.\n");
        }
        
    }
    
    /*
        Testa se há alguma peça na posição que pretende movimentar
    */
    private boolean alguemAi(int x, int y){
        
        Peca p = model.findPeca(x,y);
        if ( p == null )
            /*
                Não tem nada aqui não
            */
            return false;
        else 
            /*
                Tem coisa aqui sim
            */
            return true;
    }
    
    /*
        Método de "troca de posição" das peças, interpretado como movimento
    */
    public void move(Peca peca, int x, int y) {
            try{
            if ( peca.getSouPeao() && model.casaPossivelComer(peca,x,y)){
                model.remove(x, y);
                peca.setQuadrante(x, y);
                view.logArea.append(peca.getName()+" ("+peca.getCor()+") para ("+x+","+y+")\n");
                view.logArea.append("PEÇA COMIDA\n");
                model.setSelectedPeca(null);
                model.changeTurmaBranca();
                view.mudaTurma();
                return;
            }
            else if (peca.casaPossivel(x, y) && !model.salta(peca,x, y)) {
                /*
                    aqui foi executado o teste de movimentos possíveis específicos
                    dessa peça, e também se ela não pula nenhuma outra
                */
                if (alguemAi(x,y)){
                    if(peca.getSouPeao()){
                        throw new BadMoveException ("Movimento inválido");
                    }
                    /*
                        Será que tem alguém pra onde quer movimentar?
                    */
                    Peca p = model.findPeca(x,y);
                    if (model.casaPossivelComer(peca,x,y)){  
                        if(model.remove(x,y)){ ///retorna true se for um rei
                            if (model.isTurmaBranca()) {
                                ///branca ganhou
                            }
                            else{
                                ///preta ganhou
                            } 
                            fechaFrame();
                        }
                        view.logArea.append("PEÇA COMIDA\n");
                        
                    }
                    
                } 
                else {

                }
                peca.setQuadrante(x, y);
                view.logArea.append(peca.getName()+" ("+peca.getCor()+") PARA ("+x+","+y+")\n");
                model.setSelectedPeca(null);
                model.changeTurmaBranca();
                view.mudaTurma();

            }
            
            else{
                throw new BadMoveException ("Movimento inválido");
            }
        }
        catch (Exception ex){
            this.view.logArea.append("Não é possível fazer esse movimento.\n");
        }
    }
        
   
    public Chess getModel(){
        return this.model;
    }
    
/// atalho : ctrl shift c
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        model.getMouseCoord().setLocation(e.getX(), e.getY());
        view.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.sair();
    }
    
    
    public void startGame(){
        view.setVisible(true);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskchess;

import Controller.ChessController;
import Model.Chess;
import view.Tabuleiro;
import view.DeskChessFrame;


public class Main {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tabuleiro board = new Tabuleiro();
                DeskChessFrame view = new DeskChessFrame(board);
                Chess model = new Chess();
                ChessController controller = new ChessController();
                controller.addThings(model,view,controller);
                controller.startGame();
                //deskchessframe.setVisible(true);
            }
        });
    }
    
}

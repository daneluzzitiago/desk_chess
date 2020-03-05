package deskchess;

import controller.ChessController;
import model.Chess;
import view.Tabuleiro;
import view.DeskChessFrame;


public class Main {
    
    public static void main(String[] args) {
        System.out.println("Hello");
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

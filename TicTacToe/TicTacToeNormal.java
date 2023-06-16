/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Idlan & Addin
 */
public class TicTacToeNormal extends JFrame {
    private Container pane;
    private String currentPlayer;
    private JButton [][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem quit;
    private JMenuItem newGame;
    private ArrayList<int[]> moveHistory;
    
    public TicTacToeNormal(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(5,5));
        setTitle("5x5 Tic Tac Toe PVP");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "x";
        board = new JButton[5][5];
        moveHistory = new ArrayList<>();
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
        JOptionPane.showMessageDialog(null, "Mode: PVP"
                + "\nRules:\nPlayer 1 is 'X' symbol"
                + "\nPlayer 2 is 'O' symbol"
                + "\nFirst to get three 'X' or 'O' in a row wins");
    }
     
    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                resetBoard();
            }
        });
        
        quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoMove();
            }
        });
        
        menu.add(newGame);
        menu.add(quit);
        menu.add(undo);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    private void resetBoard(){
        currentPlayer = "x";
        hasWinner = false;
        for(int i = 0; i <5; i++){
            for(int j = 0; j < 5; j++){
                board[i][j].setText("");
            }
    }
    }
    private void initializeBoard(){
        for (int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                JButton btn = new JButton();
                btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,100));
                board[i][j] = btn;
                int[] move = {i, j}; // Store the move coordinates
                btn.addActionListener(new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                            btn.setText(currentPlayer);
                            moveHistory.add(move); // Add the move to the history
                            if(hasWinner()) {
                                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
                                hasWinner = true;
                            } else
                            if(isBoardFull()) {
                                JOptionPane.showMessageDialog(null, "Draw!");
                                hasWinner = true;
                            }
                            togglePlayer();
                        }
                    }
                });
                pane.add(btn);
            }
        }
    }
    private void togglePlayer(){
        if (currentPlayer.equals("x"))
            currentPlayer = "o";
        else
            currentPlayer = "x";
    }

    private boolean hasWinner() {
        // Check rows
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= 2; j++) {
                if (checkCombination(board[i][j], board[i][j+1], board[i][j+2])) {
                    return true;
                }
            }
        }

        // Check columns
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= 2; j++) {
                if (checkCombination(board[j][i], board[j+1][i], board[j+2][i])) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++){
                if (checkCombination(board[i][j], board[i+1][j+1], board[i+2][j+2])) {
                    return true;
                }
            }
        }

        for (int i = 2; i <= 4; i++) {
            for (int j = 4; j >= 2; j--){
                if (checkCombination(board[i][j-2], board[i-1][j-1], board[i-2][j])) {
                    return true;
                }
            }
        }
        // Check for draw
            if(isBoardFull()){
                return false;
        }
        return false;
    }
    
    private boolean checkCombination(JButton button1, JButton button2, JButton button3) {
        String text = button1.getText();
        if (!text.equals("") && text.equals(button2.getText()) && text.equals(button3.getText())) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(board[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void undoMove() {
        if (!moveHistory.isEmpty() && !hasWinner) {
            int[] lastMove = moveHistory.remove(moveHistory.size() - 1);
            int row = lastMove[0];
            int col = lastMove[1];
            board[row][col].setText("");
            togglePlayer();
        }
    }
    
}

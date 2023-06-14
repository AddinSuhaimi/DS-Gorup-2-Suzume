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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Idlan
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
    
    public TicTacToeNormal(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(5,5));
        setTitle("Tic Tac Toe");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "x";
        board = new JButton[5][5];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
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
        menu.add(newGame);
        menu.add(quit);
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
                btn.addActionListener(new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                            btn.setText(currentPlayer);
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
        JOptionPane.showMessageDialog(null, "Player " + text + " has won");
        hasWinner = true;
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
}

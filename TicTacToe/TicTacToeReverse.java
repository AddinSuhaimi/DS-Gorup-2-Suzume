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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Idlan
 */
public class TicTacToeReverse extends JFrame {
    private Container pane;
    private String currentPlayer;
    private JButton [][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem quit;
    private JMenuItem newGame;
    
    public TicTacToeReverse(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        setTitle("Tic Tac Toe");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "x";
        board = new JButton[3][3];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
    }
     
    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        
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
        for(int i = 0; i <3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j].setText("");
            }
    }
    }
    private void initializeBoard(){
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                JButton btn = new JButton();
                btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,100));
                board[i][j] = btn;
                btn.addActionListener(new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                            btn.setText(currentPlayer);
                            hasWinner();
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
    
    private void hasWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (checkCombination(board[i][0], board[i][1], board[i][2])) {
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (checkCombination(board[0][i], board[1][i], board[2][i])) {
                return;
            }
        }

        // Check diagonal
        if (checkCombination(board[0][0], board[1][1], board[2][2]) || checkCombination(board[2][0], board[1][1], board[0][2]))
            return;

        // Check for draw
        if(isBoardFull()){
            JOptionPane.showMessageDialog(null, "Draw!");
            hasWinner = true;
        }
    }
    
    private boolean checkCombination(JButton button1, JButton button2, JButton button3) {
    String text = button1.getText();
    String reverse = currentPlayer.equals("x") ? "o" : "x";
    
    if (!text.equals("") && text.equals(button2.getText()) && text.equals(button3.getText())) {
        JOptionPane.showMessageDialog(null, "Player " + reverse + " has won");
        hasWinner = true;
        return true;
    }
    return false;
}
    private boolean isBoardFull() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}

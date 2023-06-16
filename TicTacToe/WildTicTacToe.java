/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Idlan & Addin
 */
public class WildTicTacToe extends JFrame {
    private Container pane;
    private String currentPlayer;
    private String currentSymbol;
    private JButton [][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu symbol;
    private JMenuItem quit;
    private JMenuItem newGame;
    private JMenuItem x;
    private JMenuItem o;
    private ArrayList<int[]> moveHistory;
    
    public WildTicTacToe(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        setTitle("Wild Tic Tac Toe PVP");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "1";
        System.out.println("Current Player: 1");
        board = new JButton[3][3];
        hasWinner = false;
        moveHistory = new ArrayList<>();
        initializeBoard();
        initializeMenuBar();
        JOptionPane.showMessageDialog(null, "Mode: PVP"
                + "\nRules:\nBoth players can freely pick their symbol during their turns"
                + "\nFirst to get three 'X' or 'O' in a row wins");
    }
     
    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        symbol = new JMenu("Symbol");
        
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
        
        x = new JMenuItem("X");
        x.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentSymbol = "x";
                System.out.println("Selected Symbol: X");
            }
        });
        
        o = new JMenuItem("O");
        o.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentSymbol = "o";
                System.out.println("Selected Symbol: O");
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
        symbol.add(x);
        symbol.add(o);
        menuBar.add(menu);
        menuBar.add(symbol);
        setJMenuBar(menuBar);
    }
    private void resetBoard(){
        currentPlayer = "1";
        currentSymbol = null;
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
                int[] move = {i, j}; // Store the move coordinates
                btn.addActionListener(new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                            if(currentSymbol == null) {
                                JOptionPane.showMessageDialog(null, "Choose a symbol first!");
                            } else {
                                moveHistory.add(move); // Add the move to the history
                                btn.setText(currentSymbol);
                                if(hasWinner()) {
                                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                                    hasWinner = true;
                                } else
                                if(isBoardFull()) {
                                    JOptionPane.showMessageDialog(null, "Draw!");
                                    hasWinner = true;
                                }
                                togglePlayer();
                            }
                        }
                    }
                });
                pane.add(btn);
            }
        }
    }
    private void togglePlayer(){
        if (currentPlayer.equals("1")) {
            currentPlayer = "2";
            System.out.println("Current player: 2");
        }
        else {
            currentPlayer = "1";
            System.out.println("Current player: 1");
        }
    }
    
    private boolean hasWinner() {
        String[] allButtons = new String[9];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                allButtons[i] = board[i][j].toString();
            }
        }
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (checkCombination(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (checkCombination(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }

        // Check diagonal
        if (checkCombination(board[0][0], board[1][1], board[2][2]) || checkCombination(board[2][0], board[1][1], board[0][2]))
            return true;

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
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
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

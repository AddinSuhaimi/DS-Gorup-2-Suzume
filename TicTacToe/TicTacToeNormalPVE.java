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
import java.util.Random;
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
public class TicTacToeNormalPVE extends JFrame {
    private Container pane;
    private String currentPlayer;
    private String currentSymbol;
    private String currentDifficulty;
    private JButton [][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu difficulty;
    private JMenuItem quit;
    private JMenuItem newGame;
    private JMenuItem easy;
    private JMenuItem medium;
    private JMenuItem hard;
    private int rowCheck;
    private int colCheck;
    private ArrayList<int[]> moveHistory;
    
    public TicTacToeNormalPVE(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(5,5));
        setTitle("5x5 Tic Tac Toe PVE");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "Human";
        currentSymbol = "x";
        board = new JButton[5][5];
        moveHistory = new ArrayList<>();
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
        JOptionPane.showMessageDialog(null, "Mode: PVE"
                + "\nRules:\nHuman starts with 'X' symbol,"
                + "\nAI plays as 'O' symbol,"
                + "\nAfter completing your move, click on any button to allow AI to make a move"
                + "\nFirst to get three 'X' or 'O' in a row wins");
    }
     
    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        difficulty = new JMenu("Difficulty");
        
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
        
        easy = new JMenuItem("Easy");
        easy.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty = "Easy";
                System.out.println("Difficulty: Easy");
            }
        });
        
        medium = new JMenuItem("Medium");
        medium.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty = "Medium";
                System.out.println("Difficulty: Medium");
            }
        });
        
        hard = new JMenuItem("Hard");
        hard.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty = "Hard";
                System.out.println("Difficulty: Hard");
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
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
        menuBar.add(menu);
        menuBar.add(difficulty);
        setJMenuBar(menuBar);
    }
    private void resetBoard(){
        currentPlayer = "Human";
        currentSymbol = "x";
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
                        if(currentPlayer.equals("Human")) {
                            if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                                if(currentDifficulty == null) {
                                    JOptionPane.showMessageDialog(null, "Please select difficulty first");
                                } else {
                                    btn.setText(currentSymbol);
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
                        } else {
                            if(hasWinner == false){
                                moveHistory.add(move); // Add the move to the history
                                JButton ai = getAIMove();
                                ai.setText(currentSymbol);
                                if(hasWinner()) {
                                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
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
        if (currentPlayer.equals("Human")) {
            currentPlayer = "AI";
            currentSymbol = "o";
        }
        else {
            currentPlayer = "Human";
            currentSymbol = "x";
        }
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
    
    //determines the computer player's move based on the selected difficulty level
    private JButton getAIMove() {
        if(currentDifficulty.equals("Easy")) {
            return makeEasyMove();
        } else if(currentDifficulty.equals("Medium")) {
            return makeMediumMove();
        } else if(currentDifficulty.equals("Hard")) {
            return makeHardMove();
        }
        return board[0][0];
    }
    
    //makes a move for the computer player in the "Easy" difficulty
    private JButton makeEasyMove() {
        Random r = new Random();
        for(int n = 0; n < 1000; n++) {
            int i = r.nextInt(5);
            int j = r.nextInt(5);
            if(board[i][j].getText().equals(""))
                return board[i][j];
        }
        return board[0][0];
    }
    
    //checks if there is a winning move available for the human player in the "Medium" difficulty
    //if there is, the AI blocks the human player
    private JButton makeMediumMove() {
        if(checkWinningMove("x")) {
            return board[rowCheck][colCheck];
        }
        return makeEasyMove();
    }
    
    //checks if there is a winning move available for both the human player and AI in the "Hard" difficulty
    //allows the AI to win if there is an opportunity first 
    //if no opportunity to win, it tries to block the human player from winning
    private JButton makeHardMove() {
        if(checkWinningMove("o")) {
            return board[rowCheck][colCheck];
        }
        if(checkWinningMove("x")) {
            return board[rowCheck][colCheck];
        }
        return makeEasyMove();
    }
    
    private boolean checkWinningMove(String symbol) {
        // Check rows
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= 1; j++) {
                if (board[i][j].getText().equals(symbol) && 
                    board[i][j + 1].getText().equals(symbol) && 
                    board[i][j + 2].getText().equals("")) {
                    rowCheck = i;
                    colCheck = j+2;
                    return true;
                }
                if (board[i][j].getText().equals(symbol) && 
                    board[i][j + 1].getText().equals("") && 
                    board[i][j + 2].getText().equals(symbol)) {
                    rowCheck = i;
                    colCheck = j+1;
                    return true;
                }
                if (board[i][j].getText().equals("") && 
                    board[i][j + 1].getText().equals(symbol) && 
                    board[i][j + 2].getText().equals(symbol)) {
                    rowCheck = i;
                    colCheck = j;
                    return true;
                }
            }
        }

        // Check columns
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= 1; j++) {
                if (board[j][i].getText().equals(symbol) && 
                    board[j + 1][i].getText().equals(symbol) && 
                    board[j + 2][i].getText().equals("")) {
                    rowCheck = j+2;
                    colCheck = i;
                    return true;
                }
                if (board[j][i].getText().equals(symbol) && 
                    board[j + 1][i].getText().equals("") && 
                    board[j + 2][i].getText().equals(symbol)) {
                    rowCheck = j+1;
                    colCheck = i;
                    return true;
                }
                if (board[j][i].getText().equals("") && 
                    board[j + 1][i].getText().equals(symbol) && 
                    board[j + 2][i].getText().equals(symbol)) {
                    rowCheck = j;
                    colCheck = i;
                    return true;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j].getText().equals(symbol) && 
                    board[i + 1][j + 1].getText().equals(symbol) && 
                    board[i + 2][j + 2].getText().equals("")) {
                    rowCheck = i+2;
                    colCheck = j+2;
                    return true;
                }
                if (board[i][j].getText().equals(symbol) && 
                    board[i + 1][j + 1].getText().equals("") && 
                    board[i + 2][j + 2].getText().equals(symbol)) {
                    rowCheck = i+1;
                    colCheck = j+1;
                    return true;
                }
                if (board[i][j].getText().equals("") && 
                    board[i + 1][j + 1].getText().equals(symbol) && 
                    board[i + 2][j + 2].getText().equals(symbol)) {
                    rowCheck = i;
                    colCheck = j;
                    return true;
                }
            }
        }

        for (int i = 0; i <= 2; i++) {
            for (int j = 4; j >= 2; j--) {
                if (board[i][j].getText().equals(symbol) && 
                    board[i + 1][j - 1].getText().equals(symbol) && 
                    board[i + 2][j - 2].getText().equals("")) {
                    rowCheck = i+2;
                    colCheck = j-2;
                    return true;
                }
                if (board[i][j].getText().equals(symbol) && 
                    board[i + 1][j - 1].getText().equals("") && 
                    board[i + 2][j - 2].getText().equals(symbol)) {
                    rowCheck = i+1;
                    colCheck = j-1;
                    return true;
                }
                if (board[i][j].getText().equals("") && 
                    board[i + 1][j - 1].getText().equals(symbol) && 
                    board[i + 2][j - 2].getText().equals(symbol)) {
                    rowCheck = i;
                    colCheck = j;
                    return true;
                }
            }
        }

        return false;
    }
    
    private void undoMove() {
        if (!moveHistory.isEmpty() && !hasWinner && currentPlayer.equals("AI")) {
            int[] lastMove = moveHistory.remove(moveHistory.size() - 1);
            int row = lastMove[0];
            int col = lastMove[1];
            board[row][col].setText("");
            togglePlayer();
        } else if(currentPlayer.equals("Human")) {
            JOptionPane.showMessageDialog(null, "Cannot undo AI's move!");
        }
    }
}

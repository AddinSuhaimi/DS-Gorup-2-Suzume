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
public class TicTacToeReversePVE extends JFrame {
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
    
    public TicTacToeReversePVE(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        setTitle("Tic Tac Toe Reverse PVE");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "Human";
        currentSymbol = "x";
        board = new JButton[3][3];
        moveHistory = new ArrayList<>();
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
        JOptionPane.showMessageDialog(null, "Mode: PVE"
                + "\nRules:\nHuman starts with 'X' symbol,"
                + "\nAI plays as 'O' symbol,"
                + "\nAfter completing your move, click on any button to allow AI to make a move"
                + "\nFirst to get three 'X' or 'O' in a row loses");
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
                        if(currentPlayer.equals("Human")) {
                            if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                                if(currentDifficulty == null) {
                                    JOptionPane.showMessageDialog(null, "Please select difficulty first");
                                } else {
                                    moveHistory.add(move); // Add the move to the history
                                    btn.setText(currentSymbol);
                                    if(hasWinner()) {
                                        JOptionPane.showMessageDialog(null, "Player " + (currentPlayer.equals("Human") ? "AI" : "Human") + " has won");
                                        hasWinner = true;
                                    }
                                    else if(isBoardFull()) {
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
                                    JOptionPane.showMessageDialog(null, "Player " + (currentPlayer.equals("Human") ? "AI" : "Human") + " has won");
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
    
    private ArrayList<JButton> getAvailableMoves() {
        ArrayList<JButton> availableMoves = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().equals("")) {
                    availableMoves.add(board[i][j]);
                }
            }
        }

        return availableMoves;
    }

    private boolean hasWinner() {
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

        // Check diagonals
        if (checkCombination(board[0][0], board[1][1], board [2][2])||
            checkCombination(board[2][0], board[1][1], board[0][2]))
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
    
    //makes a random move for the AI in the "Medium" difficulty
    private JButton makeMediumMove() {
        Random r = new Random();
        for(int n = 0; n < 1000; n++) {
            int i = r.nextInt(3);
            int j = r.nextInt(3);
            if(board[i][j].getText().equals(""))
                return board[i][j];
        }
        return board[0][0];
    }

    //checks if there is a losing move available for the AI in the "Easy" difficulty
    //if there is, the AI will make the losing move
    private JButton makeEasyMove() {
        if(checkLosingMove("o")) {
            return board[rowCheck][colCheck];
        }
        return makeMediumMove();
    }
    
    //checks if there is a losing move available for the AI in the "Hard" difficulty
    //the AI will avoid making the losing move
    //if no losing move, the AI will make random move
    private JButton makeHardMove() {
        if(checkLosingMove("o")) {
            ArrayList <JButton> availableMoves = getAvailableMoves();
            for(JButton move : availableMoves){
                if(!move.equals(board[rowCheck][colCheck]))
                    return move;
            }
        }
        return makeMediumMove();
    }
    
    //checks if there is a winning move available for a given symbol (X or O)
    private boolean checkLosingMove(String symbol) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(symbol) && board[i][1].getText().equals(symbol) && board[i][2].getText().equals("")) {
                rowCheck = i;
                colCheck = 2;
                return true;
            }
            if (board[i][0].getText().equals(symbol) && board[i][2].getText().equals(symbol) && board[i][1].getText().equals("")) {
                rowCheck = i;
                colCheck = 1;
                return true;
            }
            if (board[i][1].getText().equals(symbol) && board[i][2].getText().equals(symbol) && board[i][0].getText().equals("")) {
                rowCheck = i;
                colCheck = 0;
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i].getText().equals(symbol) && board[1][i].getText().equals(symbol) && board[2][i].getText().equals("")) {
                rowCheck = 2;
                colCheck = i;
                return true;
            }
            if (board[0][i].getText().equals(symbol) && board[2][i].getText().equals(symbol) && board[1][i].getText().equals("")) {
                rowCheck = 1;
                colCheck = i;
                return true;
            }
            if (board[1][i].getText().equals(symbol) && board[2][i].getText().equals(symbol) && board[0][i].getText().equals("")) {
                rowCheck = 0;
                colCheck = i;
                return true;
            }
        }

        // Check diagonals
        if (board[0][0].getText().equals(symbol) && board[1][1].getText().equals(symbol) && board[2][2].getText().equals("")) {
            rowCheck = 2;
            colCheck = 2;
            return true;
        }
        if (board[0][0].getText().equals(symbol) && board[2][2].getText().equals(symbol) && board[1][1].getText().equals("")) {
            rowCheck = 1;
            colCheck = 1;
            return true;
        }
        if (board[1][1].getText().equals(symbol) && board[2][2].getText().equals(symbol) && board[0][0].getText().equals("")) {
            rowCheck = 0;
            colCheck = 0;
            return true;
        }
        if (board[0][2].getText().equals(symbol) && board[1][1].getText().equals(symbol) && board[2][0].getText().equals("")) {
            rowCheck = 2;
            colCheck = 0;
            return true;
        }
        if (board[0][2].getText().equals(symbol) && board[2][0].getText().equals(symbol) && board[1][1].getText().equals("")) {
            rowCheck = 1;
            colCheck = 1;
            return true;
        }
        if (board[1][1].getText().equals(symbol) && board[2][0].getText().equals(symbol) && board[0][2].getText().equals("")) {
            rowCheck = 0;
            colCheck = 2;
            return true;
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

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
public class WildTicTacToePVE extends JFrame {
    private Container pane;
    private String currentPlayer;
    private String currentHumanSymbol;
    private String currentAISymbol;
    private String currentDifficulty;
    private JButton [][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu symbol;
    private JMenu difficulty;
    private JMenuItem quit;
    private JMenuItem newGame;
    private JMenuItem x;
    private JMenuItem o;
    private JMenuItem easy;
    private JMenuItem medium;
    private JMenuItem hard;
    private int rowCheck;
    private int colCheck;
    private ArrayList<int[]> moveHistory;
    
    
    public WildTicTacToePVE(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        setTitle("Wild Tic Tac Toe PVE");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "Human";
        board = new JButton[3][3];
        moveHistory = new ArrayList<>();
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
        JOptionPane.showMessageDialog(null, "Mode: PVE"
                + "\nRules:\nBoth players can freely pick their symbol during their turns"
                + "\nAfter completing your move, click on any button to allow AI to make a move"
                + "\nFirst to get three 'X' or 'O' in a row wins");
    }
     
    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        symbol = new JMenu("Symbol");
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
        
        x = new JMenuItem("X");
        x.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentHumanSymbol = "x";
                System.out.println("Selected symbol: x");
            }
        });
        
        o = new JMenuItem("O");
        o.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentHumanSymbol = "o";
                System.out.println("Selected symbol: o");
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
        symbol.add(x);
        symbol.add(o);
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
        menuBar.add(menu);
        menuBar.add(symbol);
        menuBar.add(difficulty);
        setJMenuBar(menuBar);
    }
    private void resetBoard(){
        currentPlayer = "Human";
        currentHumanSymbol = null;
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
                                if(currentDifficulty == null || currentHumanSymbol == null) {
                                    JOptionPane.showMessageDialog(null, "Choose a difficulty and symbol first!");
                                } else {
                                    btn.setText(currentHumanSymbol);
                                    moveHistory.add(move); // Add the move to the history
                                    if(hasWinner()) {
                                        JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
                                        hasWinner = true;   
                                    }
                                    if(isBoardFull()){
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
                                ai.setText(currentAISymbol);
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
            System.out.println("Current player: AI ");
        }
        else {
            currentPlayer = "Human";
            System.out.println("Current player: Human \n" + "Selected symbol: " + currentHumanSymbol );
        }
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
    
    //determines the computer player's move based on the selected difficulty level
    private JButton getAIMove() {
        if(currentDifficulty.equals("Easy")) {
            return makeEasyMove();
        } else if(currentDifficulty.equals("Medium")) {
            return makeMediumMove();
        } else if(currentDifficulty.equals("Hard")) {
            //Check if there is winning move first
            if(checkWinningMove("x")) {
                currentAISymbol = "x";
                return board[rowCheck][colCheck];
            }
            if(checkWinningMove("o")) {
                currentAISymbol = "o";
                return board[rowCheck][colCheck];
            }
            
            //If no winning move, calculate the next most optimal move
            int bestScore = Integer.MIN_VALUE;
            JButton bestMove = null;
            ArrayList<JButton> availableMoves = getAvailableMoves();

            for (JButton move : availableMoves) {
                System.out.println("Calculating best move...");
                //By default, set the AI symbol opposite the human since it would be optimal
                //Except for the case that switching the symbol would lead to the AI winning the game
                if(currentHumanSymbol.equals("x"))
                        currentAISymbol = "o";
                    else
                        currentAISymbol = "x";
                
                move.setText(currentAISymbol);
                int score = minimax(board, "AI", 0);
                move.setText(""); // Undo the move

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
                
            }
            
            //Below are a chain of optimal moves 
            //For if the player attempts to trap the AI by putting the first move at the center
            
            if(!board[1][1].getText().equals("") && board[0][1].getText().equals("") && board[1][0].getText().equals("") 
                    && board[1][2].getText().equals("") && board[2][1].getText().equals("")) {
                Random r = new Random();
                int num = r.nextInt(4);
                switch(num) {
                    case 0 -> {return board[0][1];}
                    case 1 -> {return board[1][0];}
                    case 2 -> {return board[1][2];}
                    case 3 -> {return board[2][1];}
                }
            }
            
            if(!board[1][1].getText().equals("") && (board[0][1].getText().equals("") || board[1][0].getText().equals("") 
                    || board[1][2].getText().equals("") || board[2][1].getText().equals(""))) {
                Random r = new Random();
                int num = r.nextInt(2);
                if(!board[0][1].getText().equals("") && !board[2][1].getText().equals("") && !board[0][1].getText().equals(board[2][1].getText())) {
                    if(num==0)
                        return board[1][2];
                    else
                        return board[1][0];
                }
                if(!board[1][0].getText().equals("") && !board[1][2].getText().equals("") && !board[1][0].getText().equals(board[1][2].getText())) {
                    if(num==0)
                        return board[2][1];
                    else
                        return board[0][1];
                }
                if(!board[1][0].getText().equals("") && !board[1][2].getText().equals("") && board[1][0].getText().equals(board[1][2].getText())) {
                    if(currentAISymbol.equals("x"))
                        currentAISymbol = "o";
                    else
                        currentAISymbol = "x";
                    if(num==0)
                        return board[2][1];
                    else
                        return board[0][1];
                }
                if(!board[1][0].getText().equals("") && !board[1][2].getText().equals("") && board[1][0].getText().equals(board[1][2].getText())) {
                    if(currentAISymbol.equals("x"))
                        currentAISymbol = "o";
                    else
                        currentAISymbol = "x";
                    if(num==0)
                        return board[2][1];
                    else
                        return board[0][1];
                }
            }
            
            return bestMove;
        }
        return board[0][0];
    }  

    //makes a move for the computer player in the "Easy" difficulty
    private JButton makeEasyMove() {
        Random r = new Random();
        int sign = r.nextInt(2);
        if(sign==0)
            currentAISymbol = "x";
        else
            currentAISymbol = "o";
        for(int n = 0; n < 1000; n++) {
            int i = r.nextInt(3);
            int j = r.nextInt(3);
            if(board[i][j].getText().equals(""))
                return board[i][j];
        }
        return board[0][0];
    }
    
    //checks if there is a winning move available for the computer player in the "Medium" difficulty
    //switches the AI's symbol to allow it to win the game
    private JButton makeMediumMove() {
        if(checkWinningMove("x")) {
            currentAISymbol = "x";
            return board[rowCheck][colCheck];
        }
        if(checkWinningMove("o")) {
            currentAISymbol = "o";
            return board[rowCheck][colCheck];
        }
        return makeEasyMove();
    }

    //checks if there is a winning move available for a given symbol (X or O)
    private boolean checkWinningMove(String symbol) {
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
    
    //implementation of the minimax algorithm to determine the best move for the computer player in the "Hard" difficulty
    private int minimax(JButton[][] board, String currentPlayer, int depth) {
        if (hasWinner()) {
            if (currentPlayer.equals("AI")) {
                return 1;  // AI wins
            } else {
                return -1; // Human wins
            }
        }

        if (isBoardFull()) {
            return 0; // Draw
        }

        //MAX player, searching for the most optimal moves
        if (currentPlayer.equals("AI")) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].getText().equals("")) {
                        board[i][j].setText(currentAISymbol);
                        int score = minimax(copyBoard(board), "Human", depth + 1);
                        //recurse the method until it finds a winner
                        board[i][j].setText(""); // Undo the move
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            //MIN player, searching for the least optimal moves
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].getText().equals("")) {
                        board[i][j].setText(currentHumanSymbol);
                        int score = minimax(copyBoard(board), "AI", depth + 1);
                        //recurse the method until it finds a winner
                        board[i][j].setText(""); // Undo the move
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
    
    //creates a copy of the game board to simulate different moves during the minimax algorithm
    private JButton[][] copyBoard(JButton[][] board) {
        JButton[][] copy = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = new JButton(board[i][j].getText());
            }
        }
        return copy;
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

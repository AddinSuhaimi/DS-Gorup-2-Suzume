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
public class WildTicTacToeEVE extends JFrame {
    private Container pane;
    private String currentPlayer;
    private String currentAI1Symbol;
    private String currentAI2Symbol;
    private String currentDifficulty1;
    private String currentDifficulty2;
    private JButton [][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu difficulty1;
    private JMenu difficulty2;
    private JMenuItem quit;
    private JMenuItem newGame;
    private JMenuItem easy1;
    private JMenuItem medium1;
    private JMenuItem hard1;
    private JMenuItem easy2;
    private JMenuItem medium2;
    private JMenuItem hard2;
    private int rowCheck;
    private int colCheck;    
    
    public WildTicTacToeEVE(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        setTitle("Wild Tic Tac Toe PVE");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "AI 1";
        board = new JButton[3][3];
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
        difficulty1 = new JMenu("Difficulty AI 1");
        difficulty2 = new JMenu("Difficulty AI 2");
        
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
        
        easy1 = new JMenuItem("Easy");
        easy1.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty1 = "Easy";
                System.out.println("Difficulty: Easy");
            }
        });
        
        medium1 = new JMenuItem("Medium");
        medium1.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty1 = "Medium";
                System.out.println("Difficulty: Medium");
            }
        });
        
        hard1 = new JMenuItem("Hard");
        hard1.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty1= "Hard";
                System.out.println("Difficulty: Hard");
            }
        });
        
        easy2 = new JMenuItem("Easy");
        easy2.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty2 = "Easy";
                System.out.println("Difficulty: Easy");
            }
        });
        
        medium2 = new JMenuItem("Medium");
        medium2.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty2 = "Medium";
                System.out.println("Difficulty: Medium");
            }
        });
        
        hard2 = new JMenuItem("Hard");
        hard2.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty2= "Hard";
                System.out.println("Difficulty: Hard");
            }
        });
        
        menu.add(newGame);
        menu.add(quit);
        difficulty1.add(easy1);
        difficulty1.add(medium1);
        difficulty1.add(hard1);
        difficulty2.add(easy2);
        difficulty2.add(medium2);
        difficulty2.add(hard2);
        menuBar.add(menu);
        menuBar.add(difficulty1);
        menuBar.add(difficulty2);
        setJMenuBar(menuBar);
    }
    private void resetBoard(){
        currentPlayer = "AI 1";
        currentAI1Symbol = null;
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
                        if(currentPlayer.equals("AI 1")) {
                            if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                                if(currentDifficulty1 == null || currentDifficulty2 == null) {
                                    JOptionPane.showMessageDialog(null, "Choose a difficulty for both engines first!");
                                } else {
                                    JButton ai = getAIMove1();
                                    ai.setText(currentAI1Symbol);
                                    if(hasWinner()) {
                                        JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
                                        hasWinner = true;
                                    }else
                                    if(isBoardFull()){
                                        JOptionPane.showMessageDialog(null, "Draw!");
                                        hasWinner = true;
                                    }
                                    togglePlayer();
                                }
                            }
                        } else {
                            if(hasWinner == false){
                                JButton ai = getAIMove2();
                                ai.setText(currentAI2Symbol);
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
        if (currentPlayer.equals("AI 1")) {
            currentPlayer = "AI 2";
            System.out.println("Current player: AI 2");
        }
        else {
            currentPlayer = "AI 1";
            System.out.println("Current player: AI 1 \n" + "Selected symbol: " + currentAI1Symbol );
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
    private JButton getAIMove1() {
        if(currentDifficulty1.equals("Easy")) {
            return makeEasyMove1();
        } else if(currentDifficulty1.equals("Medium")) {
            return makeMediumMove1();
        } else if(currentDifficulty1.equals("Hard")) {
            //Check if there is winning move first
            if(checkWinningMove("x")) {
                currentAI2Symbol = "x";
                return board[rowCheck][colCheck];
            }
            if(checkWinningMove("o")) {
                currentAI2Symbol = "o";
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
                if(currentAI1Symbol.equals("x"))
                        currentAI2Symbol = "o";
                    else
                        currentAI2Symbol = "x";
                
                move.setText(currentAI2Symbol);
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
                    if(currentAI2Symbol.equals("x"))
                        currentAI2Symbol = "o";
                    else
                        currentAI2Symbol = "x";
                    if(num==0)
                        return board[2][1];
                    else
                        return board[0][1];
                }
                if(!board[1][0].getText().equals("") && !board[1][2].getText().equals("") && board[1][0].getText().equals(board[1][2].getText())) {
                    if(currentAI2Symbol.equals("x"))
                        currentAI2Symbol = "o";
                    else
                        currentAI2Symbol = "x";
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

    //determines the computer player's move based on the selected difficulty level
    private JButton getAIMove2() {
        if(currentDifficulty1.equals("Easy")) {
            return makeEasyMove2();
        } else if(currentDifficulty1.equals("Medium")) {
            return makeMediumMove2();
        } else if(currentDifficulty1.equals("Hard")) {
            //Check if there is winning move first
            if(checkWinningMove("x")) {
                currentAI1Symbol = "x";
                return board[rowCheck][colCheck];
            }
            if(checkWinningMove("o")) {
                currentAI1Symbol = "o";
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
                if(currentAI2Symbol.equals("x"))
                        currentAI1Symbol = "o";
                    else
                        currentAI1Symbol = "x";
                
                move.setText(currentAI1Symbol);
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
                    if(currentAI1Symbol.equals("x"))
                        currentAI1Symbol = "o";
                    else
                        currentAI1Symbol = "x";
                    if(num==0)
                        return board[2][1];
                    else
                        return board[0][1];
                }
                if(!board[1][0].getText().equals("") && !board[1][2].getText().equals("") && board[1][0].getText().equals(board[1][2].getText())) {
                    if(currentAI1Symbol.equals("x"))
                        currentAI1Symbol = "o";
                    else
                        currentAI1Symbol = "x";
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
    private JButton makeEasyMove1() {
        Random r = new Random();
        int sign = r.nextInt(2);
        if(sign==0)
            currentAI1Symbol = "x";
        else
            currentAI1Symbol = "o";
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
    private JButton makeMediumMove1() {
        if(checkWinningMove("x")) {
            currentAI1Symbol = "x";
            return board[rowCheck][colCheck];
        }
        if(checkWinningMove("o")) {
            currentAI1Symbol = "o";
            return board[rowCheck][colCheck];
        }
        return makeEasyMove1();
    }

    //makes a move for the computer player in the "Easy" difficulty
    private JButton makeEasyMove2() {
        Random r = new Random();
        int sign = r.nextInt(2);
        if(sign==0)
            currentAI2Symbol = "x";
        else
            currentAI2Symbol = "o";
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
    private JButton makeMediumMove2() {
        if(checkWinningMove("x")) {
            currentAI2Symbol = "x";
            return board[rowCheck][colCheck];
        }
        if(checkWinningMove("o")) {
            currentAI2Symbol = "o";
            return board[rowCheck][colCheck];
        }
        return makeEasyMove2();
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
            if (currentPlayer.equals("AI 1")) {
                return 1;  // AI 1 wins
            } else {
                return -1; // AI 2 wins
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
                        board[i][j].setText(currentAI2Symbol);
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
                        board[i][j].setText(currentAI1Symbol);
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
}
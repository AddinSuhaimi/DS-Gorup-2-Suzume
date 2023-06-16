/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wildtictactoe;

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
public class TicTacToeNormalEVE extends JFrame {
    private Container pane;
    private String currentPlayer;
    private String currentSymbol;
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
    
    public TicTacToeNormalEVE(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(5,5));
        setTitle("5x5 Tic Tac Toe EVE");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "AI 1";
        currentSymbol = "x";
        board = new JButton[5][5];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
        JOptionPane.showMessageDialog(null, "Mode: EVE"
                + "\nRules:\nChoose the difficulty for each engine,"
                + "\nAI 1 plays as 'X' symbol"
                + "\nAI 2 plays as 'O symbol"
                + "\nClick on any button to allow each AI to make a move"
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
                System.out.println("Difficulty AI 1: Easy");
            }
        });
        
        medium1 = new JMenuItem("Medium");
        medium1.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty1 = "Medium";
                System.out.println("Difficulty AI 1: Medium");
            }
        });
        
        hard1 = new JMenuItem("Hard");
        hard1.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty1 = "Hard";
                System.out.println("Difficulty AI 1: Hard");
            }
        });
        
        easy2 = new JMenuItem("Easy");
        easy2.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty2 = "Easy";
                System.out.println("Difficulty AI 2: Easy");
            }
        });
        
        medium2 = new JMenuItem("Medium");
        medium2.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty2 = "Medium";
                System.out.println("Difficulty AI 2: Medium");
            }
        });
        
        hard2 = new JMenuItem("Hard");
        hard2.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                currentDifficulty2 = "Hard";
                System.out.println("Difficulty AI 2: Hard");
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
                btn.addActionListener(new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(currentPlayer.equals("AI 1")) {
                            if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                                if(currentDifficulty1 == null || currentDifficulty2 == null) {
                                    JOptionPane.showMessageDialog(null, "Please select difficulty for each engine first");
                                } else {
                                    JButton ai = getAIMove1();
                                    ai.setText(currentSymbol);
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
                                JButton ai = getAIMove2();
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
        if (currentPlayer.equals("AI 1")) {
            currentPlayer = "AI 2";
            currentSymbol = "o";
        }
        else {
            currentPlayer = "AI 1";
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
    private JButton getAIMove1() {
        if(currentDifficulty1.equals("Easy")) {
            return makeEasyMove();
        } else if(currentDifficulty1.equals("Medium")) {
            return makeMediumMove();
        } else if(currentDifficulty1.equals("Hard")) {
            return makeHardMove();
        }
        return board[0][0];
    }
    
    private JButton getAIMove2() {
        if(currentDifficulty2.equals("Easy")) {
            return makeEasyMove();
        } else if(currentDifficulty2.equals("Medium")) {
            return makeMediumMove();
        } else if(currentDifficulty2.equals("Hard")) {
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
    
}



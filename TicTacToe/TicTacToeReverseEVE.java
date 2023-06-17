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
public class TicTacToeReverseEVE extends JFrame {
    private Container pane;
    private String currentPlayer;
    private String currentSymbol;
    private String currentWinner;
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
    
    public TicTacToeReverseEVE(){
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        setTitle("Tic Tac Toe Reverse PVE");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "AI 1";
        currentSymbol = "x";
        board = new JButton[3][3];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
        JOptionPane.showMessageDialog(null, "Mode: EVE"
                + "\nRules:\nChoose the difficulty for each engine,"
                + "\nAI 1 plays as 'X' symbol"
                + "\nAI 2 plays as 'O symbol"
                + "\nClick on any button to allow each AI to make a move"
                + "\nFirst to get three 'X' or 'O' in a row loses");
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
                            currentWinner = "AI 2";
                            if(((JButton)e.getSource()).getText().equals("") && hasWinner == false){
                                if(currentDifficulty1 == null || currentDifficulty2 == null) {
                                    JOptionPane.showMessageDialog(null, "Please select difficulty for each engine first");
                                } else {
                                    JButton ai = getAIMove1();
                                    ai.setText(currentSymbol);
                                    if(hasWinner()) {
                                        JOptionPane.showMessageDialog(null, "Player " + currentWinner + " has won");
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
                            currentWinner = "AI 1";
                            if(hasWinner == false){
                                JButton ai = getAIMove2();
                                ai.setText(currentSymbol);
                                if(hasWinner()) {
                                    JOptionPane.showMessageDialog(null, "Player " + currentWinner + " has won");
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
    private JButton getAIMove1() {
        if(currentDifficulty1.equals("Easy")) {
            return makeEasyMove1();
        } else if(currentDifficulty1.equals("Medium")) {
            return makeMediumMove1();
        } else if(currentDifficulty1.equals("Hard")) {
            return makeHardMove1();
        }
        return board[0][0];
    }
    
    private JButton getAIMove2() {
        if(currentDifficulty2.equals("Easy")) {
            return makeEasyMove2();
        } else if(currentDifficulty2.equals("Medium")) {
            return makeMediumMove2();
        } else if(currentDifficulty2.equals("Hard")) {
            return makeHardMove2();
        }
        return board[0][0];
    }
    
    //makes a random move for the AI in the "Medium" difficulty
    private JButton makeMediumMove1() {
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
    private JButton makeEasyMove1() {
        if(checkLosingMove("x")) {
            return board[rowCheck][colCheck];
        }
        return makeMediumMove1();
    }
    
    //checks if there is a losing move available for the AI in the "Hard" difficulty
    //the AI will avoid making the losing move
    //if no losing move, the AI will make random move
    private JButton makeHardMove1() {
        if(checkLosingMove("x")) {
            ArrayList <JButton> availableMoves = getAvailableMoves();
            for(JButton move : availableMoves){
                if(!move.equals(board[rowCheck][colCheck]))
                    return move;
            }
        }
        return makeMediumMove1();
    }
    
    
    //makes a random move for the AI in the "Medium" difficulty
    private JButton makeMediumMove2() {
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
    private JButton makeEasyMove2() {
        if(checkLosingMove("o")) {
            return board[rowCheck][colCheck];
        }
        return makeMediumMove2();
    }
    
    //checks if there is a losing move available for the AI in the "Hard" difficulty
    //the AI will avoid making the losing move
    //if no losing move, the AI will make random move
    private JButton makeHardMove2() {
        if(checkLosingMove("o")) {
            ArrayList <JButton> availableMoves = getAvailableMoves();
            for(JButton move : availableMoves){
                if(!move.equals(board[rowCheck][colCheck]))
                    return move;
            }
        }
        return makeMediumMove2();
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
}
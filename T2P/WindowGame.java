package tetris;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class WindowGame {
    public static final int WIDTH = 900, HEIGHT = 639;
  

    private Board board1;
    private Board board2;
    private Title title;
    private JFrame window;

    public WindowGame() {
    	window = new JFrame("T2D");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(null);  // Set layout to null for absolute positioning
        
        // Create two game boards for Player 1 and Player 2
        board1 = new Board(0, 0, true);  // Left board for Player 1
        board2 = new Board(445, 0, false);  // Right board for Player 2
        board1.setBounds(0, 0, 445, 639);   // Define bounds for Player 1 board
        board2.setBounds(445, 0, 445, 639); // Define bounds for Player 2 board
        
        // Set the reference to the other board
        board1.setOtherBoard(board2);
        board2.setOtherBoard(board1);
        
        title = new Title(this); 
        title.setBounds(0, 0, WIDTH, HEIGHT);
        
     // Add a single KeyListener to handle inputs for both players
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                board1.keyPressed(e);
                board2.keyPressed(e);
                title.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                board1.keyReleased(e);
                board2.keyReleased(e);
                title.keyReleased(e);
            }
        });
        
        window.add(title);  // Initially show title screen
        window.setVisible(true);
    
    }
    
    
    // Starts the Tetris game by removing the title screen and displaying both boards
    public void startTetris() {
        window.remove(title);  // Remove title screen
        window.add(board1);    // Add Player 1 board
        window.add(board2);    // Add Player 2 board
        window.revalidate();   // Refresh window
        window.repaint();      // Repaint the window to ensure correct rendering
        board1.startGame();    // Start Player 1's game
        board2.startGame();    // Start Player 2's game
    }

    public static void main(String[] args) {
        new WindowGame();
    }
}
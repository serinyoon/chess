package View;

import Game.*;
import Piece.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Board.*;
import Controller.*;

/**
 * Created by serinyoon on 9/16/14.
 */
public class View extends JPanel implements ActionListener {

    public static final int topMargin = 95;
    public static final int windowWidth = 500;
    public static final int windowHeight = topMargin + windowWidth + 75;
    public static final int numSquare = 10;
    public static final int squareSize = windowWidth/10;
    public static final int offset = squareSize/4;
    public Game game;
    public int[] scores;
    JFrame window = new JFrame("Boring Chess");

    public View(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        JFrame window = new JFrame("Lame Chess");
        window.setSize(windowWidth, windowHeight);
        JPanel myPanel = initializePanel();
        initializeButton(myPanel);
        setUpMenu(window);
        window.setContentPane(myPanel);
        window.add(this);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scores = new int[2];
    }

    public View(Game game){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        this.game = game;
        JFrame window = new JFrame("Lame Chess");
        window.setSize(windowWidth, windowHeight);
        JPanel myPanel = initializePanel();
        initializeButton(myPanel);
        setUpMenu(window);
        window.setContentPane(myPanel);
        window.add(this);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scores = new int[2];
    }

    private void initializeButton(JPanel myPanel) {
        JButton button = new JButton("Undo");
        button.addActionListener(this);
        myPanel.add(button, BorderLayout.SOUTH);
    }

    private JPanel initializePanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(windowWidth,windowHeight));
        myPanel.setLayout(new BorderLayout());
        return myPanel;
    }

    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem forfeit = new JMenuItem("Forfeit");
        JMenuItem exit = new JMenuItem("Exit");
        newGame.addActionListener(this);
        restart.addActionListener(this);
        forfeit.addActionListener(this);
        exit.addActionListener(this);
        file.add(newGame);
        file.add(restart);
        file.add(forfeit);
        file.add(exit);
        menubar.add(file);
        window.setJMenuBar(menubar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if(command.equals("New Game"))  startNewGame();
        if(command.equals("Restart"))   restart();
        if(command.equals("Forfeit"))   forfeit();
        if(command.equals("Exit"))      exit();
        if(command.equals("Undo"))      undo();

        repaint();
    }

    /**
     * Initiate the game board
     * @param board
     */
    public void paintComponent(Graphics board) {
        drawSquares(board);
        drawLabels(board);
        drawPieces(board);
        drawScores(board);

    }

    private void drawSquares(Graphics board) {

        for(int row = 0; row < SquareBoard.WIDTH; row++) {
            for(int column = 0; column < SquareBoard.HEIGHT; column++) {
                if(isEven(row, column)) {
                    board.setColor(Color.white);
                }
                else {
                    board.setColor(Color.lightGray);
                }

                board.fillRect((row+1)*squareSize, (column+1)*squareSize, squareSize, squareSize);
            }
        }

    }

    /**
     * Draws the chessboard labels
     * @param board
     */
    private void drawLabels(Graphics board) {
        board.setColor(Color.black);
        board.setFont(new Font("Calibri", Font.PLAIN, squareSize*2/3));

        //String alphabet = "01234567";
        //String number = "01234567";

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "87654321";
        for(int i = 0; i < SquareBoard.WIDTH; i++) {
            board.drawString(alphabet.substring(i,i+1), (i+1)*squareSize + offset, numSquare*squareSize - 15);
            board.drawString(number.substring(i,i+1), offset+5, squareSize*6/7 + (i+1)*squareSize);
        }

    }

    /**
     * Draws the chess pieces
     */
    private void drawPieces(Graphics gameBoard) {
        gameBoard.setFont(new Font("Tacoma", Font.PLAIN, squareSize));

        //String[] gamePieces = new String[game.board.availablePieces.size()];
        for(int i = 0; i < game.board.availablePieces.size(); i++)
        {
            Piece currentPiece = game.board.availablePieces.elementAt(i);
            //gamePieces[i] = game.board.availablePieces.elementAt(i).symbol;
            gameBoard.drawString(currentPiece.symbol,
                    ((int) currentPiece.location.getY()%game.board.height+1)*squareSize,
                    ((int) currentPiece.location.getX()%game.board.width+2)*squareSize - 10);

        }

    }

    /**
     * Draws score
     * @param gameBoard
     */
    public void drawScores(Graphics gameBoard) {
        gameBoard.setColor(Color.black);
        gameBoard.setFont(new Font("Calibri", Font.PLAIN, squareSize*3/2));

        String scores = "" + game.whitePlayer.score + " : " + game.blackPlayer.score;
        gameBoard.drawString(scores, windowHeight/4 + 15, windowHeight - 90);

        gameBoard.setColor(Color.gray);
        gameBoard.setFont(new Font("Calibri", Font.PLAIN, squareSize*2/5));
        gameBoard.drawString("WHITE       BLACK", windowHeight/4 + 10, windowHeight - 150);

    }


    /**
     * Determines weather given sum of row and column is even
     * @param row
     * @param column
     * @return
     */
    private boolean isEven(int row, int column) {
        return ((row + column) % 2) == 0;
    }

    public void mouseActionListener(Controller c) {
        this.addMouseListener(c);
    }

    /**
     * Invalid move notification
     */
    public void invalidMove() {
        JOptionPane.showMessageDialog(null, "Invalid Move!", "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Start a game
     */
    public void startGame() {

        scores[0] = game.blackPlayer.score;
        scores[1] = game.whitePlayer.score;


        int input = JOptionPane.showConfirmDialog(null, "Do you want to play with special pieces?",
                "Special Pieces", JOptionPane.YES_NO_OPTION);
        if(input == JOptionPane.YES_OPTION) {
            game = new Game('x');
        }
        else {
            game = new Game();
        }

        game.blackPlayer.score = scores[0];
        game.whitePlayer.score = scores[1];
        repaint();
    }

    /**
     * End the game
     */
    public void endGame() {
        setVisible(false);
        window.dispose();
        return;

    }

    /**
     * Start a new game
     */
    public void startNewGame() {
        int input = JOptionPane.showConfirmDialog(null, "Do you really want to start a new game?",
                "New Game", JOptionPane.YES_NO_OPTION);

        if(input == JOptionPane.YES_OPTION) {
            startGame();
        }

    }

    /**
     * Restart the game
     */
    public void restart() {
        String currentPlayer = game.currentPlayer.color? "WHITE": "BLACK";
        String opponentPlayer = game.currentPlayer.color? "BLACK": "WHITE";
        int input1 = JOptionPane.showConfirmDialog(null, currentPlayer + ": Do you really want to restart?",
                "Restart", JOptionPane.YES_NO_OPTION);

        int input2 = JOptionPane.showConfirmDialog(null, opponentPlayer + ": Do you really want to restart?",
                "Restart", JOptionPane.YES_NO_OPTION);

        if(input1 == JOptionPane.YES_OPTION && input2 == JOptionPane.YES_OPTION) {
            startGame();
        }

    }


    /**
     * End the game
     */
    public void forfeit() {
        int input = JOptionPane.showConfirmDialog(null, "Do you really want to forfiet?",
                "Forfeit", JOptionPane.YES_NO_OPTION);

        if(input == JOptionPane.NO_OPTION) {
            return;
        }

        String currentPlayer = game.currentPlayer.color? "WHITE": "BLACK";
        String opponentPlayer = game.currentPlayer.color? "BLACK": "WHITE";

        JOptionPane.showMessageDialog(null, currentPlayer + ": You have forfeited the game", "You Lose", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, opponentPlayer + ": Opponent has forfeited the game", "You WIN", JOptionPane.INFORMATION_MESSAGE);
        endGame();
    }

    /**
     * Exit the game
     */
    public void exit() {
        int input = JOptionPane.showConfirmDialog(null, "Do you really want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);

        if(input == JOptionPane.NO_OPTION) {
            return;
        }

        endGame();

    }

    /**
     * Undo the last move
     */
    public void undo() {
        if(game.executedMoves.size() == 0) {
            JOptionPane.showMessageDialog(null, "No move to be undone", "Undo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int input = JOptionPane.showConfirmDialog(null, "Do you really want to undo your last move?",
                "Undo", JOptionPane.YES_NO_OPTION);
        if(input == JOptionPane.YES_OPTION) {
            game.undoMove();
        }

    }




}



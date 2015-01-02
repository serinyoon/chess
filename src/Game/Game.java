package Game;

import Board.*;
import Util.*;
import Piece.*;
import java.util.Vector;

/**
 * Created by serinyoon on 9/9/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Game {
    public SquareBoard board;
    public Player whitePlayer;
    public Player blackPlayer;
    public Player currentPlayer;
    public Vector<Point[]> executedMoves;

    /**
     * Default constructor
     */
    public Game() {
        this.board = new SquareBoard();
        this.whitePlayer = new Player(Player.WHITE);
        this.blackPlayer = new Player(Player.BLACK);
        this.currentPlayer = whitePlayer;
        executedMoves = new Vector<Point[]>();
    }

    /**
     * Test constructor
     */
    public Game(int x) {
        this.board = new SquareBoard(x);
        this.whitePlayer = new Player(Player.WHITE);
        this.blackPlayer = new Player(Player.BLACK);
        this.currentPlayer = whitePlayer;
        executedMoves = new Vector<Point[]>();
    }

    /**
     * Default constructor with special pieces
     */
    public Game(char x) {
        this.board = new SquareBoard(x);
        this.whitePlayer = new Player(Player.WHITE);
        this.blackPlayer = new Player(Player.BLACK);
        this.currentPlayer = whitePlayer;
        executedMoves = new Vector<Point[]>();
    }

    /**
     * Take a move request, check bounds, validate and execute the move
     * @param startX x coord of start point
     * @param startY y coord of start point
     * @param endX x coord of end point
     * @param endY y coord of end point
     * @return whether or not move was executed
     */
    public boolean requestMove(int startX, int startY, int endX, int endY) {
        boolean success = false;
        String feedback = "";
        printCurrPlayer();

        if(currentPlayer.color != board.pieces[startX][startY].color) feedback = "ERROR: not your turn!";
        else if(board.pieces[startX][startY] == null) feedback = "ERROR: no such piece exists";
        else if(startX == endX && startY == endY) feedback = "ERROR: move your piece";
        else if(!isOnBoard(startX, startY, endX, endY)) feedback = "ERROR: out of bounds";
        else {
            feedback = board.requestMove(startX, startY, endX, endY);
            success = feedback.startsWith("SUCCESS")? true: false;
            if (success) {
                if(feedback.endsWith("CHECKMATE")) return endGame(feedback);
            }
        }

        printFeedback(feedback);
        return endTurn(startX, startY, endX, endY, success);
    }

    /**
     * Generates an object that holds the information about the last move executed
     * @param startX x coord of start point
     * @param startY y coord of start point
     * @param endX x coord of end point
     * @param endY y coord of end point
     * @return whether or not move was executed
     */
    public Point[] generateLastMove(int startX, int startY, int endX, int endY) {
        Point[] lastMove = new Point[2];
        lastMove[0] = new Point(startX, startY);
        lastMove[1] = new Point(endX, endY);
        return lastMove;
    }

    /**
     * Undoes the last move
     * @return
     */
    public boolean undoMove() {
        if(executedMoves.size() == 0) return false;

        Point[] lastMove = executedMoves.elementAt(executedMoves.size()-1);
        executedMoves.removeElementAt(executedMoves.size()-1);
        int startX  = lastMove[0].getX();
        int startY  = lastMove[0].getY();
        int endX    = lastMove[1].getX();
        int endY    = lastMove[1].getY();

        board.revertMove(startX, startY, endX, endY);
        board.printBoard();
        updateTurn();
        printExecutedMoves();

        return true;
    }

    public boolean endGame(String feedback) {
        this.currentPlayer.score++;
        printFeedback(feedback);
        System.out.println("GAME OVER\n");
        return true;
    }

    public boolean endTurn(int startX, int startY, int endX, int endY, boolean success) {
        if(success) executedMoves.add(generateLastMove(startX, startY, endX, endY));
        updateTurn();
//        printExecutedMoves();
        return success? true: false;
    }

    public void updateTurn() {
        currentPlayer = currentPlayer.color? blackPlayer: whitePlayer;
    }

    public boolean isOnBoard(int startX, int startY, int endX, int endY) {
        if(startX >= 0 	&& 	startX < SquareBoard.WIDTH 	&&	endX >= 0	&&	endX < SquareBoard.HEIGHT	&&
                startY >= 0 	&& 	startY < SquareBoard.WIDTH 	&&	endY >= 0	&&	endY < SquareBoard.HEIGHT	)
            return true;
        return false;
    }

    public void printFeedback(String feedback) {
        System.out.println(">\t" + feedback + "\n");
        board.printBoard();
    }

    public void printCurrPlayer() {
        System.out.printf("-----------------------------\n\t\t");
        System.out.println((currentPlayer.color? "White": "Black") + "'s turn");
        System.out.println("-----------------------------\n");
    }

    public void printExecutedMoves() {
        System.out.println("# print executed moves");
        for(int i = executedMoves.size()-1; i >= 0; i--) {
            System.out.printf("\tfrom " + executedMoves.elementAt(i)[0].toString() +
                    " to " + executedMoves.elementAt(i)[1].toString() + "\n");
        }
    }

    public static void main (String[] args) {

    }
}

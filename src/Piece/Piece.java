package Piece;

import Util.Point;
import java.util.Vector;
import Board.*;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Piece {
    public boolean color;
    public String identifier;
    public String symbol;
    public Point location;
    public Vector<Point> validMoves;

    /**
     * Default constructor
     */
    public Piece() {
        this.color = false;
        this.identifier = "";
        this.symbol = "";
        this.location = new Point();
        this.validMoves = new Vector<Point>();
    }

    /**
     * Constructor with arguments
     */
    public Piece(boolean color, Point location) {
        this.color = color;
        this.identifier = "";
        this.symbol = "";
        this.location = location;
        this.validMoves = new Vector<Point>();
    }

    /**
     * Check if piece has can move to the destination specified
     * @param dest destination
     * @return validation result
     */
    public boolean isValidMove(Point dest){
        for(int i = 0; i < validMoves.size(); i++) {
            if(this.validMoves.elementAt(i).equals(dest)) return true;
        }
        return false;
    }

    public boolean validateMove(Board board, Point orig, Point dest) {
        return false;
    }

    /**
     * Determines if the horizontal move between origin and destination is legal
     * @param board chess
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean checkHorizontal(Board board, Point orig, Point dest) {
        if (Math.abs(dest.getX() - orig.getX()) != 0) return false;
        return  isHorizontalClear(board, orig, dest) &&
                isValidDestination(board, orig, dest);
    }

    /**
     * Determines if the vertical move between origin and destination is legal
     * @param board chess
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean checkVertical(Board board, Point orig, Point dest) {
        if (Math.abs(dest.getY() - orig.getY()) != 0) return false;
        return  isVerticalClear(board, orig, dest) &&
                isValidDestination(board, orig, dest);
    }

    /**
     * Determines if the horizontal path between origin and destination is clear
     * @param board chess
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean isHorizontalClear(Board board, Point orig, Point dest) {
        int from = Math.min(orig.getY(), dest.getY());
        int to = Math.max(orig.getY(), dest.getY());

        for(int i = from + 1; i < to; i++) {
            if(board.pieces[orig.getX()][i] != null) return false;
        }
        return true;
    }

    /**
     * Determines if the vertical path between origin and destination is clear
     * @param board chess
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean isVerticalClear(Board board, Point orig, Point dest) {
        int from = Math.min(orig.getX(), dest.getX());
        int to = Math.max(orig.getX(), dest.getX());

        for(int i = from + 1; i < to; i++) {
            if(board.pieces[i][orig.getY()] != null) return false;
        }
        return true;
    }

    /**
     * Determines if the diagonal move between origin and destination is legal
     * @param board chess
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean checkDiagonal(Board board, Point orig, Point dest) {
        int distanceX = dest.getX() - orig.getX();
        int distanceY = dest.getY() - orig.getY();

        if(distanceX == 0) return false;     // divide by 0
        if(Math.abs(distanceX) != Math.abs(distanceY)) return false;
        if(distanceY/distanceX < 0)
            return  isPositiveSlopeClear(board, orig, dest) &&
                    isValidDestination(board, orig, dest);
        else
            return  isNegativeSlopeClear(board, orig, dest) &&
                    isValidDestination(board, orig, dest);
    }

    /**
     * Determines if the diagonal path with positive slope
     * between origin and destination is clear
     * @param board chess
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean isPositiveSlopeClear(Board board, Point orig, Point dest) {
        int maxRow = Math.max(orig.getX(), dest.getX());
        int minCol = Math.min(orig.getY(), dest.getY());
        int numIter = Math.abs(dest.getX() - orig.getX()) - 1;

        //starting at top right of diagonal and walking down and to the left
        for(int i = 0; i < numIter; i++, maxRow--, minCol++){
            if(board.pieces[maxRow-1][minCol+1] != null) return false;
        }
        return true;
    }

    /**
     * Determines if the diagonal path with negative slope
     * between origin and destination is clear
     * @param board chess
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean isNegativeSlopeClear(Board board, Point orig, Point dest) {
        int minRow = Math.min(orig.getX(), dest.getX());
        int minCol = Math.min(orig.getY(), dest.getY());
        int numIter = Math.abs(dest.getX() - orig.getX()) - 1;

        // starts at top left and walks down and to the right
        for(int i = 0; i < numIter; i++, minRow++, minCol++){
            //System.out.println("\t\tneg slope (" + minRow + ", " + minCol + ")");
            if(board.pieces[minRow+1][minCol+1] != null) return false;
        }
        return true;
    }

    /**
     * Determines if the destination is valid - empty or capturable
     * @param board chess
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean isValidDestination(Board board, Point orig, Point dest) {
        Piece destPiece = board.pieces[dest.getX()][dest.getY()];
        if(destPiece == null || isOpponent(destPiece)) return true;
        return false;
    }

    /**
     * Determines if origin and destination are L apart
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    public boolean areLApart(Point orig, Point dest) {
        int distanceX = dest.getX() - orig.getX();
        int distanceY = dest.getY() - orig.getY();
        return  (Math.abs(distanceX) == 2 && Math.abs(distanceY) == 1) ||
                (Math.abs(distanceX) == 1 && Math.abs(distanceY) == 2);
    }

    public boolean isKing() {
        return this.identifier.endsWith("k");
    }

    public boolean isOpponent(Piece piece) {
        return this.color != piece.color;
    }

    public void printValidMoves() {
        System.out.printf("# print valid moves for %s at (%d, %d) \n", identifier, location.getX(), location.getY());
        for(int i = 0; i < validMoves.size(); i++) {
            System.out.printf("\t%d: to (%d, %d)\n", i, validMoves.elementAt(i).getX(), validMoves.elementAt(i).getY());
        }
        System.out.println();
    }
}

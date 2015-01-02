package Piece;

import Board.*;
import Util.Point;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Pawn extends Piece{

    /**
     * Default constructor for Pawn
     * @param color color of the player
     */
    public Pawn(boolean color, Point location) {
        super(color, location);
        this.identifier = color? "wp": "bp";
        this.symbol = color? "♙": "♟";
    }

    /**
     * Validate the move by checking the following conditions:
     *         (1) pawn can move 2 spaces in its first move
     *         (2) pawn can move 1 up/down
     *         (3) pawn can't capture a piece by moving up/down
     *         (4) pawn can only capture a piece by moving up diagonally by 1 space
     *         (5) pawn can't move backward
     * @param board chess board
     * @param orig origin coord
     * @param dest destination coord
     * @return validation result
     */
    @Override
    public boolean validateMove(Board board, Point orig, Point dest) {
        int distanceX = dest.getX() - orig.getX();
        int distanceY = dest.getY() - orig.getY();
        Piece destPiece = board.pieces[dest.getX()][dest.getY()];

        if(isFirstMove(orig.getX(), distanceX, distanceY) || isRegularMove(distanceX, distanceY))
            return  board.pieces[dest.getX()][dest.getY()] == null &&
                    isValidDirection(distanceX);

        return isDiagonalCapture(distanceX, distanceY, destPiece) &&
               isValidDirection(distanceX);
    }

    /**
     * Determine if given move a first move
     * If so 2 step is allowed
     * @param origX x coord of the origin
     * @param distanceX distance in x
     * @param distanceY distance in y
     * @return validation result
     */
    public boolean isFirstMove(int origX, int distanceX, int distanceY) {
        if(distanceY != 0) return false;
        return (origX == 1 || origX == 6) && (Math.abs(distanceX) == 2);
    }

    /**
     * Is given move a regular move -- one step forward
     * @param distanceX distance in x
     * @param distanceY distance in y
     * @return validation result
     */
    public boolean isRegularMove(int distanceX, int distanceY) {
        return Math.abs(distanceX) == 1 && distanceY == 0;
    }

    /**
     * Determine if given move is diagonal and, if it is, is it a capture
     * @param distanceX distanceX distance in x
     * @param distanceY distance in y
     * @param destPiece piece at the destination coord
     * @return validation result
     */
    public boolean isDiagonalCapture(int distanceX, int distanceY, Piece destPiece) {
        if(destPiece == null) return false;
        return Math.abs(distanceX) == 1 && Math.abs(distanceY) == 1 && this.isOpponent(destPiece);
    }

    /**
     * Determine if given piece is moving forward respect to its color -- white up, black down
     * @param distance moving distance
     * @return validation result
     */
    public boolean isValidDirection(int distance) {
        int direction = (color? 1: 0);

        if(distance < 0 && direction == 1) return true;
        if(distance > 0 && direction == 0) return true;
        return false;
    }
}

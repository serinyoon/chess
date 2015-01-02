package Piece;

import Board.*;
import Util.Point;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class King extends Piece {
    /**
     * Default constructor for King
     * @param color color of the player
     */
    public King(boolean color, Point location) {
        super(color, location);
        this.identifier = color? "wk": "bk";
        this.symbol = color? "♔": "♚";
    }

    /**
     * Validate potential move for king by checking following conditions:
     *      (1) is distance in x and y 1 or 0
     *      (2) is the given move going to put itself in check - can any opponent's piece reach the destination
     * @param board chess board
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    @Override
    public boolean validateMove(Board board, Point orig, Point dest){
        int distanceX = Math.abs(dest.getX() - orig.getX());
        int distanceY = Math.abs(dest.getY() - orig.getY());

        if(distanceX > 1 || distanceY > 1) return false;

        for(int apIter = 0; apIter < board.availablePieces.size(); apIter++) {
            Piece currPiece = board.availablePieces.elementAt(apIter);
            for (int vmIter = 0; vmIter < currPiece.validMoves.size(); vmIter++) {
                if (isOpponent(currPiece) &&
                    currPiece.validMoves.elementAt(vmIter).equals(dest.getLocation())) return false;
            }
        }
        return isValidDestination(board, orig, dest);
    }
}

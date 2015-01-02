package Piece;

import Board.*;
import Util.Point;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Queen extends Piece{
    /**
     * Default constructor for Queen
     * @param color color of the player
     */
    public Queen(boolean color, Point location) {
        super(color, location);
        this.identifier = color? "wq": "bq";
        this.symbol = color? "♕": "♛";
    }

    /**
     * Validate potential move for queen
     * @param board chess board
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    @Override
    public boolean validateMove(Board board, Point orig, Point dest){
        return  checkHorizontal(board, orig, dest) ||
                checkVertical(board, orig, dest)   ||
                checkDiagonal(board, orig, dest);
    }
}

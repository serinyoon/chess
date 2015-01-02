package Piece;

import Board.Board;
import Util.Point;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Bishop extends Piece{
    /**
     * Default constructor for Bishop
     * @param color color of the player
     */
    public Bishop(boolean color, Point location) {
        super(color, location);
        this.identifier = color? "wb": "bb";
        this.symbol = color? "♗": "♝";
    }

    /**
     * Validate potential move for bishop
     * @param board chess board
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    @Override
    public boolean validateMove(Board board, Point orig, Point dest){
        return checkDiagonal(board, orig, dest);
    }
}

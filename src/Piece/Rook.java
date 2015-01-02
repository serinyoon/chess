package Piece;

import Board.*;
import Util.Point;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Rook extends Piece{
    /**
     * Default constructor for Rook
     * @param color
     */
    public Rook(boolean color, Point location) {
        super(color, location);
        this.identifier = color? "wr": "br";
        this.symbol = color? "♖": "♜";
    }

    /**
     * Validate potential move for rook
     * @param board chess board
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    @Override
    public boolean validateMove(Board board, Point orig, Point dest){
        return  checkHorizontal(board, orig, dest) ||
                checkVertical(board, orig, dest);
    }
}

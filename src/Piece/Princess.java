package Piece;

import Board.Board;
import Util.Point;

/**
 * Created by serinyoon on 9/16/14.
 * Modified by serinyoon on 12/30/14.
 */
public class Princess extends Piece {   // knight + bishop

    public Princess(boolean color, Point location) {
        super(color, location);
        this.identifier = color ? "w*" : "b*";
        this.symbol = color? "\u2661" : "\u2665";
    }

    /**
     * Validate potential move for princess
     * @param board chess board
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    @Override
    public boolean validateMove(Board board, Point orig, Point dest){
        return areLApart(orig, dest) ||
               checkDiagonal(board, orig, dest);
    }

}

package Piece;

import Board.Board;
import Util.Point;

/**
 * Created by serinyoon on 9/16/14.
 * Modified by serinyoon on 12/30/14.
 */
public class Empress extends Piece {    // knight + rook
    /**
     * Default constructor for Empress
     * @param color color of the player
     */
    public Empress(boolean color, Point location) {
        super(color, location);
        this.identifier = color ? "we" : "be";
        this.symbol = color ? "\u9734" : "\u9733";
    }

    /**
     * Validate potential move for empress
     * @param board chess board
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    @Override
    public boolean validateMove(Board board, Point orig, Point dest){
        return areLApart(orig, dest)               ||
               checkHorizontal(board, orig, dest)  ||
               checkVertical(board, orig, dest);
    }
}

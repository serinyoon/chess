package Piece;

import Board.*;
import Util.Point;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Knight extends Piece{
    /**
     * Default constructor for Knight
     * @param color
     */
    public Knight(boolean color, Point location) {
        super(color, location);
        this.identifier = color ? "wn" : "bn";
        this.symbol = color? "♘": "\u265E";
    }

    /**
     * Validate potential move for knight
     * @param board chess board
     * @param orig origin
     * @param dest destination
     * @return validation result
     */
    @Override
    public boolean validateMove(Board board, Point orig, Point dest){
        return areLApart(orig, dest) &&
               isValidDestination(board, orig, dest);
    }


}

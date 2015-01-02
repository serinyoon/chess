package UnitTest.GameConditionTest;

import Game.*;
import Piece.*;
import Util.Point;
import junit.framework.TestCase;

public class CheckTest extends TestCase {

    public void testInCheck() {
        System.out.println("# in check");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(6, 4)));
        g.board.printBoard();
        assertTrue(g.board.isInCheck(g.currentPlayer.color));
    }

    public void testTwoMoveCheckmate(){
        System.out.println("# two move checkmate");
        Game g = new Game();
        g.board.printBoard();
        assertTrue(g.requestMove(6, 5, 5, 5));  // wp above wb up 1
        assertTrue(g.requestMove(1, 4, 3, 4));  // bp above bk up 2
        assertTrue(g.requestMove(6, 6, 4, 6));  // wp above wn up 2
        assertTrue(g.requestMove(0, 3, 4, 7));  // bq to checkmate!
        assertTrue(g.board.isCheckmate(!g.currentPlayer.color));
        assertTrue(g.board.isStalemate(!g.currentPlayer.color));


    }
//
//    public void testFourMoveCheckmate(){
//        Game g = new Game();
//        g.board.printBoard();
//        System.out.println("# four move checkmate");
//        assertTrue(g.requestMove(6, 4, 4, 4));	// wp above wk up 2
//        assertTrue(g.requestMove(1, 4, 3, 4));  // bp above bk up 2
//        assertTrue(g.requestMove(7, 3, 3, 7));  // wq upright 4
//        assertTrue(g.requestMove(0, 1, 2, 2));  // bn downleft
//        assertTrue(g.requestMove(7, 5, 4, 2));	// wb upleft 3
//        assertTrue(g.requestMove(0, 6, 2, 5));  // bn downright
//        assertTrue(g.requestMove(3, 7, 1, 5));  // wq upleft 2 -- capture bp and checkmate
//        assertTrue(g.board.isCheckmate(!g.currentPlayer.color));
//        assertTrue(g.board.isStalemate(!g.currentPlayer.color));
//    }

}
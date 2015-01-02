package UnitTest.PawnTest;

import Game.*;
import Piece.*;
import Util.Point;
import junit.framework.TestCase;

public class MoveTest extends TestCase {
//
//    public void testFirstMove() {
//        System.out.println("## valid path - first move");
//        Game g = new Game();
//        g.board.printBoard();
//        assertTrue(g.requestMove(6, 3, 4, 3));
//    }
//
//    public void testNotFirstMove() {
//        System.out.println("## invalid path - not first move with 2 steps");
//        Game g = new Game(1);
//        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
//        g.board.printBoard();
//        assertFalse(g.requestMove(5, 0, 3, 0));
//    }
//
//    public void testRegularMoves() {
//        System.out.println("## valid moves");
//        Game g = new Game();
//        g.board.printBoard();
//        assertTrue(g.requestMove(6, 3, 4, 3));
//        assertTrue(g.requestMove(1, 4, 2, 4));
//    }
//
//    public void testNotDiagonalCapture() {
//        System.out.println("## invalid - diagonal path with no capture");
//        Game g = new Game(1);
//        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
//        g.board.printBoard();
//        assertFalse(g.requestMove(5, 0, 4, 1));
//    }
//
//    public void testDiagonalCapture() {
//        System.out.println("## valid - diagonal capture");
//        Game g = new Game(1);
//        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
//        g.board.insertPiece(new Pawn(Player.BLACK, new Point(4, 1)));
//        g.board.printBoard();
//        assertTrue(g.requestMove(5, 0, 4, 1));
//        g.board.printCapturedPieces(g.board.capturedPieces);
//    }
//
//    public void testBackwardMove() {
//        System.out.println("## invalid - backward");
//        Game g = new Game(1);
//        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
//        g.board.insertPiece(new Pawn(Player.BLACK, new Point(5, 1)));
//        g.board.printBoard();
//        assertFalse(g.requestMove(5, 0, 6, 0));
//        assertTrue(g.requestMove(5, 1, 6, 1));
//    }

}
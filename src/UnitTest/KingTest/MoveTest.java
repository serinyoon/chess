package UnitTest.KingTest;

import Game.Game;
import Game.Player;
import Piece.*;
import Util.Point;
import junit.framework.TestCase;

public class MoveTest extends TestCase {

    public void testMoves() {
        System.out.println("## valid move 1");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new King(Player.WHITE, new Point(3, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 1, 2, 0));
    }
    public void testMoves2() {
        System.out.println("## valid move 2");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new King(Player.WHITE, new Point(3, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 1, 2, 1));
    }

    public void testMoves3() {
        System.out.println("## valid move 3");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new King(Player.WHITE, new Point(3, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 1, 2, 2));
    }

    public void testMoves4() {
        System.out.println("## valid move 4");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new King(Player.WHITE, new Point(3, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 1, 3, 0));
    }

    public void testMoves5() {
        System.out.println("## valid move 5");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new King(Player.WHITE, new Point(3, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 1, 3, 2));
    }
    public void testMoves6() {
        System.out.println("## valid move 6");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new King(Player.WHITE, new Point(3, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 1, 4, 0));
    }

    public void testMoves7() {
        System.out.println("## valid move 7");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new King(Player.WHITE, new Point(3, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 1, 4, 1));
    }

    public void testMoves8() {
        System.out.println("## valid move 8");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new King(Player.WHITE, new Point(3, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 1, 4, 2));
    }

    public void testInvalidCapture() {
        System.out.println("## invalid capture");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(4, 5)));
        g.board.printBoard();
        assertFalse(g.requestMove(3, 3, 4, 5));
    }

    public void testLMoveWithBlock() {
        System.out.println("## l move with blocking pieces");
        Game g = new Game();
        g.board.printBoard();
        assertTrue(g.requestMove(7, 1, 5, 2));
    }


}
package UnitTest.KnightTest;

import Game.Game;
import Game.Player;
import Piece.Knight;
import Piece.Pawn;
import Util.Point;
import junit.framework.TestCase;

public class MoveTest extends TestCase {

    public void testLMoves() {
        System.out.println("## l move 1");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 3, 2, 1));
        g.currentPlayer = g.whitePlayer;
        assertTrue(g.requestMove(2, 1, 3, 3));
    }
    public void testLMoves2() {
        System.out.println("## l move 2");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 3, 1, 2));
        g.currentPlayer = g.whitePlayer;
        assertTrue(g.requestMove(1, 2, 3, 3));
    }

    public void testLMoves3() {
        System.out.println("## l move 3 and capture");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 3, 1, 4));
        g.currentPlayer = g.whitePlayer;
        assertTrue(g.requestMove(1, 4, 3, 3));
    }

    public void testLMoves4() {
        System.out.println("## l move 4");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 3, 2, 5));
        g.currentPlayer = g.whitePlayer;
        assertTrue(g.requestMove(2, 5, 3, 3));
    }

    public void testLMoves5() {
        System.out.println("## l move 5");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 3, 4, 1));
        g.currentPlayer = g.whitePlayer;
        assertTrue(g.requestMove(4, 1, 3, 3));

    }
    public void testLMoves6() {
        System.out.println("## l move 6");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 3, 5, 2));
        g.currentPlayer = g.whitePlayer;
        assertTrue(g.requestMove(5, 2, 3, 3));
    }

    public void testLMoves7() {
        System.out.println("## l move 7");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 3, 5, 4));
        g.currentPlayer = g.whitePlayer;
        assertTrue(g.requestMove(5, 4, 3, 3));
    }

    public void testLMoves8() {
        System.out.println("## l move 8");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Knight(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 3, 4, 5));
        g.currentPlayer = g.whitePlayer;
        assertTrue(g.requestMove(4, 5, 3, 3));

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
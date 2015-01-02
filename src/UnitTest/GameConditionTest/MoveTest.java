package UnitTest.GameConditionTest;

import Game.*;
import Piece.*;
import Util.Point;
import junit.framework.TestCase;

public class MoveTest extends TestCase {

    public void testRevertMove(){
        System.out.println("# revert move");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
        g.board.printBoard();

        g.board.executeMove(5, 0, 4, 0);
        g.board.printBoard();

        g.board.revertMove(5, 0, 4, 0);
        g.board.printBoard();
        assertEquals("wp", g.board.pieces[5][0].identifier);
    }

    public void testRevertMoveWithCapture(){
        System.out.println("# revert move with capture");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(4, 1)));
        g.board.printBoard();

        g.board.executeMove(5, 0, 4, 1);
        g.board.printBoard();
        g.board.printCapturedPieces(g.board.capturedPieces);

        g.board.revertMove(5, 0, 4, 1);
        g.board.printBoard();
        g.board.printCapturedPieces(g.board.capturedPieces);

        assertEquals("wp", g.board.pieces[5][0].identifier);
    }

    public void testCapture() {
        System.out.println("# valid capture");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(4, 1)));
        g.board.printBoard();

        assertTrue(g.requestMove(5, 0, 4, 1));
        g.board.printBoard();
        g.board.printCapturedPieces(g.board.capturedPieces);
        assertEquals(1, g.board.capturedPieces.size());
        assertEquals(Player.BLACK, g.board.capturedPieces.elementAt(0).color);
        assertTrue(g.board.capturedPieces.elementAt(0).location.equals(new Point(4, 1)));
        assertEquals("wp", g.board.pieces[4][1].identifier);
    }

    public void testInvalidCapture() {
        System.out.println("# invalid capture");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(4, 1)));
        g.board.printBoard();

        assertFalse(g.requestMove(5, 0, 4, 1));
        g.board.printBoard();
        g.board.printCapturedPieces(g.board.capturedPieces);
        assertEquals(0, g.board.capturedPieces.size());
        assertEquals("wp", g.board.pieces[5][0].identifier);
        assertEquals("wr", g.board.pieces[4][1].identifier);

    }

    public void testUndoMove(){
        System.out.println("# undo move");
        Game g = new Game(1);
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Pawn(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Pawn(Player.WHITE, new Point(5, 0)));
        g.board.printBoard();

        assertTrue(g.requestMove(5, 0, 4, 0));
        assertEquals("wp", g.board.pieces[4][0].identifier);
        assertEquals(null, g.board.pieces[5][0]);
        assertTrue(g.undoMove());
        assertEquals("wp", g.board.pieces[5][0].identifier);
        assertEquals(null, g.board.pieces[4][0]);
    }

}
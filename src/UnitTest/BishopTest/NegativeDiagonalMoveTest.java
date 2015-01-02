package UnitTest.BishopTest;

import Game.*;
import Piece.Bishop;
import Piece.Rook;
import Util.Point;
import junit.framework.TestCase;

public class NegativeDiagonalMoveTest extends TestCase {

    public void testClearPath() {
        System.out.println("## valid path - empty");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Bishop(Player.WHITE, new Point(1, 1)));
        g.board.printBoard();
        assertTrue(g.requestMove(1, 1, 6, 6));
    }

    public void testCaptureOpponent() {
        System.out.println("## valid path - capture opponent");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Bishop(Player.WHITE, new Point(1, 1)));
        g.board.insertPiece(new Bishop(Player.BLACK, new Point(6, 6)));
        g.board.printBoard();
        assertTrue(g.requestMove(1, 1, 6, 6));
        g.board.printCapturedPieces(g.board.capturedPieces);
    }

    public void testCaptureSameTeam(){
        System.out.println("## valid path - capture opponent");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Bishop(Player.WHITE, new Point(6, 6)));
        g.board.insertPiece(new Bishop(Player.WHITE, new Point(1, 1)));
        g.board.printBoard();
        assertFalse(g.requestMove(6, 6, 1, 1));
        g.board.printCapturedPieces(g.board.capturedPieces);
    }

}
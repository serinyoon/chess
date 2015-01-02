package UnitTest.Queen;

import Game.Game;
import Game.Player;
import Piece.Rook;
import Piece.Queen;
import Util.Point;
import junit.framework.TestCase;

public class VerticalMoveTest extends TestCase {

    public void testClearPath() {
        System.out.println("## valid path - empty");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Queen(Player.WHITE, new Point(3, 2)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 2, 6, 2));
    }

    public void testCaptureOpponent() {
        System.out.println("## valid path - capture opponent");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Queen(Player.WHITE, new Point(3, 2)));
        g.board.insertPiece(new Rook(Player.BLACK, new Point(5, 2)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 2, 5, 2));
        g.board.printCapturedPieces(g.board.capturedPieces);
    }

    public void testCaptureSameTeam(){
        System.out.println("## invalid path - piece from same team");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Queen(Player.WHITE, new Point(3, 2)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(5, 2)));
        g.board.printBoard();
        assertFalse(g.requestMove(3, 2, 5, 2));
        g.board.printCapturedPieces(g.board.capturedPieces);
    }
}
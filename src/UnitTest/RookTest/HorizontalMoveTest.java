package UnitTest.RookTest;

import Game.*;
import Piece.Rook;
import Util.Point;
import junit.framework.TestCase;

/**
 * Created by serinyoon on 12/30/14.
 */
public class HorizontalMoveTest extends TestCase {

    public void testClearPath() {
        System.out.println("## valid path - empty");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(3, 0)));
        g.board.printBoard();
//        game.board.printAvailablePieces(game.board.availablePieces);
//        for(int i = 0; i < game.board.availablePieces.size(); i++) {
//            game.board.availablePieces.elementAt(i).printValidMoves();
//        }
        assertTrue(g.requestMove(3, 0, 3, 5));
    }

    public void testNotHorizon() {
        System.out.println("## invalid path - not horizon");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(3, 3)));
        g.board.printBoard();
        assertFalse(g.requestMove(3, 3, 1, 1));
    }

    public void testCaptureOpponent() {
        System.out.println("## valid path - capture opponent");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(3, 0)));
        g.board.insertPiece(new Rook(Player.BLACK, new Point(3, 5)));
        g.board.printBoard();
        assertTrue(g.requestMove(3, 0, 3, 5));
        g.board.printCapturedPieces(g.board.capturedPieces);
    }

    public void testCaptureSameTeam(){
        System.out.println("## invalid path - piece from same team");
        Game g = new Game(1);
        g.board.insertPiece(new Rook(Player.BLACK, new Point(1, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(6, 4)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(3, 0)));
        g.board.insertPiece(new Rook(Player.WHITE, new Point(3, 5)));
        g.board.printBoard();
        assertFalse(g.requestMove(3, 0, 3, 5));
        g.board.printCapturedPieces(g.board.capturedPieces);
    }

}
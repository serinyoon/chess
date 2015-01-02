package Board;

import Game.Player;
import Piece.*;
import Util.*;

import java.util.Vector;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class SquareBoard extends Board{
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    /**
     * Default constructor
     */
    public SquareBoard() {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.pieces = fillBoardWithPieces();
        this.availablePieces = initializeAvailablePieces();
        this.capturedPieces = new Vector<Piece>();
        this.kingsLocation = setKingsLocation();
        updateValidMoves();
    }

    /**
     * Default constructor with special pieces
     */
    public SquareBoard(char x) {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.pieces = fillBoardWithSpecialPieces();
        this.availablePieces = initializeAvailablePieces();
        this.capturedPieces = new Vector<Piece>();
        this.kingsLocation = setKingsLocation();
        updateValidMoves();
    }

    /**
     * Testing constructor
     * @param x dummy
     */
    public SquareBoard(int x) {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.pieces = fillBoardWithKings();
        this.availablePieces = initializeAvailablePieces();
        this.capturedPieces = new Vector<Piece>();
        this.kingsLocation = setKingsLocation();
        updateValidMoves();
    }

    /**
     * Make a WIDTH x HEIGHT board and make each square null
     * @return 2d array of pieces
     */
    public Piece[][] initializePieces() {
        Piece[][] pieces = new Piece[this.width][this.height];
        for(int i = 0; i < SquareBoard.WIDTH; i++) {
            for(int j = 0; j < SquareBoard.HEIGHT; j++) {
                pieces[i][j] = new Piece();
                pieces[i][j] = null;
            }
        }
        return pieces;
    }

    /**
     * Generate a square chess board with 2 kings
     * @return chess board
     */
    public Piece[][] fillBoardWithKings() {
        Piece[][] pieces = initializePieces();
        pieces[0][4] = new King(Player.BLACK, new Point(0, 4));
        pieces[7][4] = new King(Player.WHITE, new Point(7, 4));
        return pieces;
    }

    /**
     * Default board setting with all 32 pieces
     * @return pieces
     */
    public Piece[][] fillBoardWithPieces() {
        Piece[][] pieces = initializePieces();

        // place black pieces
        pieces[0][0] = new Rook(Player.BLACK, new Point(0, 0));
        pieces[0][1] = new Knight(Player.BLACK, new Point(0, 1));
        pieces[0][2] = new Bishop(Player.BLACK, new Point(0, 2));
        pieces[0][3] = new Queen(Player.BLACK, new Point(0, 3));
        pieces[0][4] = new King(Player.BLACK, new Point(0, 4));
        pieces[0][5] = new Bishop(Player.BLACK, new Point(0, 5));
        pieces[0][6] = new Knight(Player.BLACK, new Point(0, 6));
        pieces[0][7] = new Rook(Player.BLACK, new Point(0, 7));

        for(int i = 0; i < SquareBoard.WIDTH; i++) {
            pieces[1][i] = new Pawn(Player.BLACK, new Point(1, i));
        }

        // place white pieces
        pieces[7][0] = new Rook(Player.WHITE, new Point(7, 0));
        pieces[7][1] = new Knight(Player.WHITE, new Point(7, 1));
        pieces[7][2] = new Bishop(Player.WHITE, new Point(7, 2));
        pieces[7][3] = new Queen(Player.WHITE, new Point(7, 3));
        pieces[7][4] = new King(Player.WHITE, new Point(7, 4));
        pieces[7][5] = new Bishop(Player.WHITE, new Point(7, 5));
        pieces[7][6] = new Knight(Player.WHITE, new Point(7, 6));
        pieces[7][7] = new Rook(Player.WHITE, new Point(7, 7));

        for(int i = 0; i < SquareBoard.WIDTH; i++) {
            pieces[6][i] = new Pawn(Player.WHITE, new Point(6, i));
        }
        return pieces;

    }

    /**
     * Special board setting with 4 special pieces
     * @return pieces
     */
    public Piece[][] fillBoardWithSpecialPieces() {
        Piece[][] pieces = initializePieces();

        // place black pieces
        pieces[0][0] = new Empress(Player.BLACK, new Point(0, 0));
        pieces[0][1] = new Knight(Player.BLACK, new Point(0, 1));
        pieces[0][2] = new Princess(Player.BLACK, new Point(0, 2));
        pieces[0][3] = new Queen(Player.BLACK, new Point(0, 3));
        pieces[0][4] = new King(Player.BLACK, new Point(0, 4));
        pieces[0][5] = new Princess(Player.BLACK, new Point(0, 5));
        pieces[0][6] = new Knight(Player.BLACK, new Point(0, 6));
        pieces[0][7] = new Empress(Player.BLACK, new Point(0, 7));

        for(int i = 0; i < SquareBoard.WIDTH; i++) {
            pieces[1][i] = new Pawn(Player.BLACK, new Point(1, i));
        }

        // place white pieces
        pieces[7][0] = new Empress(Player.WHITE, new Point(7, 0));
        pieces[7][1] = new Knight(Player.WHITE, new Point(7, 1));
        pieces[7][2] = new Princess(Player.WHITE, new Point(7, 2));
        pieces[7][3] = new Queen(Player.WHITE, new Point(7, 3));
        pieces[7][4] = new King(Player.WHITE, new Point(7, 4));
        pieces[7][5] = new Princess(Player.WHITE, new Point(7, 5));
        pieces[7][6] = new Knight(Player.WHITE, new Point(7, 6));
        pieces[7][7] = new Empress(Player.WHITE, new Point(7, 7));

        for(int i = 0; i < SquareBoard.WIDTH; i++) {
            pieces[6][i] = new Pawn(Player.WHITE, new Point(6, i));
        }

        return pieces;
    }

    /**
     * Initialize the list of available pieces by adding all 32 pieces
     * @return list of available pieces
     */
    @Override
    public Vector<Piece> initializeAvailablePieces() {
        Vector<Piece> list = new Vector<Piece>();
        for (int i = 0; i < SquareBoard.WIDTH; i ++) {
            for (int j = 0; j < SquareBoard.HEIGHT; j++) {
                if(this.pieces[i][j] != null) list.addElement(pieces[i][j]);
            }
        }
        return list;
    }

    /**
     * Set king's position for both white and black
     * @return vector with positions
     */
    @Override
    public Vector<Point> setKingsLocation() {
        Vector<Point> kingsLocation = new Vector<Point>();
        kingsLocation.add(0, new Point(0, 4));      // black king
        kingsLocation.add(1, new Point(7, 4));      // white king
        return kingsLocation;
    }

    /**
     * Prints the board
     */
    public void printBoard() {
        System.out.println("# print board");
        System.out.println("  -0--1--2--3--4--5--6--7- ");
        for(int i = 0; i < 8; i++) {
            System.out.print(i+"|");
            for( int j = 0; j < 8; j++ ) {
                //char symbol = symbolizePiece(pieces[i][j]);
                if(pieces[i][j] != null) System.out.print(pieces[i][j].identifier + "|");
                else System.out.print("  |");
            }
            System.out.println(i);
        }
        System.out.println("  -0--1--2--3--4--5--6--7- ");
        System.out.println();

//        printKingsLocation();
    }

}

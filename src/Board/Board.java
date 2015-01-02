package Board;

import Piece.*;
import Util.Point;

import java.util.Vector;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Board {
    public int width;
    public int height;

    public Piece[][] pieces;
    public Vector<Piece> availablePieces;
    public Vector<Piece> capturedPieces;
    public Vector<Point> kingsLocation;

    /**
     * Default constructor
     */
    public Board() {
        this.pieces = new Piece[0][0];
        availablePieces = new Vector<Piece>();
        capturedPieces = new Vector<Piece>();
        kingsLocation = new Vector<Point>();
    }

    /**
     * Iterate through every piece on board and
     * update its valid moves
     */
    public void updateValidMoves(){
//        System.out.println("# update valid moves");
        for(int apIter = 0; apIter < availablePieces.size(); apIter++) {
            Piece currPiece = availablePieces.elementAt(apIter);
            if(currPiece.validMoves != null) currPiece.validMoves.clear();
            for(int i = 0; i < SquareBoard.WIDTH; i++ ) {
                for(int j = 0; j < SquareBoard.HEIGHT; j++) {
                    Point currDest = new Point(i, j);
                    if(currPiece.location.equals(currDest)) continue;
                    else if(currPiece.validateMove(this, currPiece.location, currDest.getLocation())) {
                        currPiece.validMoves.add(currDest);
                    }
                }
            }
        }
    }

    /**
     * Check if the give move is valid and check game condition
     * if the king from the same team becomes vulnerable, reject the move
     * if the opponent king becomes vulnerable, check for checkmate condition
     * @param startX start coordinate in x direction
     * @param startY start coordinate in y direction
     * @param endX end coordinate in x direction
     * @param endY end coordinate in y direction
     * @return feedback on whether or not the requested move was executed successfully
     */
    public String requestMove(int startX, int startY, int endX, int endY) {
        Piece currPiece = this.pieces[startX][startY];
        System.out.printf("# request move - %s from %s to %s\n", currPiece.identifier, currPiece.location.toString(), toString(endX, endY));
        boolean currPlayer = pieces[startX][startY].color;
        boolean opponent = !currPlayer;

        if(currPiece.isValidMove(new Point(endX, endY))) {
            executeMove(startX, startY, endX, endY);

            if(isInCheck(currPlayer)) {
                revertMove(startX, startY, endX, endY);
                return "ERROR: your king will be in danger";
            }

            boolean checkmate = isCheckmate(opponent);
            boolean stalemate = isStalemate(opponent);
            System.out.println(checkmate + ", " + stalemate);

            if(checkmate && stalemate) return "SUCCESS: CHECKMATE";
            else if(stalemate) return "SUCCESS: STALEMATE";
            else if(checkmate) return "SUCCESS: IN CHECK";
            else return "SUCCESS";
        }
        return "ERROR: invalid move";
    }

    /**
     * Checks whether the king is in check
     * @param color color of the player
     * @return whether or not the king is in check
     */
    public boolean isInCheck(boolean color) {
//        System.out.printf("# in check - player %s\n", color ? "w" : "b");
        boolean opponent = !color;
        Piece currKing = getKing(color);

        for(int apIter = 0; apIter < availablePieces.size(); apIter++) {
            Piece currPiece = availablePieces.elementAt(apIter);
            if (currPiece.color == opponent) {
                for (int vmIter = 0; vmIter < currPiece.validMoves.size(); vmIter++) {
                    if (currPiece.validMoves.elementAt(vmIter).equals(currKing.location)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Piece getKing(boolean color) {
        Point currKingLocation = getKingsLocation(color);
        return this.pieces[currKingLocation.getX()][currKingLocation.getY()];
    }



    /**
     * Checks whether the game results in checkmate - king has no more possible moves to get himself out of check
     * @param color color of the player
     * @return whether or not the king is in checkmate
     */
    public boolean isCheckmate(boolean color) {
//        System.out.printf("# checkmate - player %s\n", color ? "w" : "b");
        boolean opponent = !color;
        Piece currKing = getKing(color);

        if(!isInCheck(color)) return false;
        if(currKing.validMoves.size() == 0) return true;
        else {
            for (int kingsVMIter = 0; kingsVMIter < currKing.validMoves.size(); kingsVMIter++) {
                Point currMove = currKing.validMoves.elementAt(kingsVMIter);
                for(int apIter = 0; apIter < availablePieces.size(); apIter++) {
                    Piece currPiece = availablePieces.elementAt(apIter);
                    if (currPiece.color == opponent) {
                        for (int vmIter = 0; vmIter < currPiece.validMoves.size(); vmIter++) {
                            int startX = currKing.location.getX();
                            int startY = currKing.location.getY();
                            int endX = currMove.getX();
                            int endY = currMove.getY();
                            executeMove(startX, startY, endX, endY);

                            if (isInCheck(color)) {
                                revertMove(startX, startY, endX, endY);
                                return true;
                            }
                            revertMove(startX, startY, endX, endY);
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks whether the game results in stalemate - no more pieces with legal moves
     * @param color color of the player
     * @return whether or not the king is in stalemate
     */
    public boolean isStalemate(boolean color) {
//        System.out.printf("# stalemate - player %s\n", color? "w": "b");
        for(int apIter = 0; apIter < availablePieces.size(); apIter++) {
            Piece currPiece = availablePieces.elementAt(apIter);
            if (currPiece.color == color) {
                for (int vmIter = 0; vmIter < currPiece.validMoves.size(); vmIter++) {
                    Point currMove = currPiece.validMoves.elementAt(vmIter);
                    int startX = currPiece.location.getX();
                    int startY = currPiece.location.getY();
                    int endX = currMove.getX();
                    int endY = currMove.getY();


                    executeMove(startX, startY, endX, endY);
                    if (!isInCheck(color)) {
                        revertMove(startX, startY, endX, endY);
                        return false;
                    }
                    revertMove(startX, startY, endX, endY);
                }
            }
        }
        return true;
    }

    /**
     * Execute the move
     * @param startX start coordinate in x direction
     * @param startY start coordinate in y direction
     * @param endX end coordinate in x direction
     * @param endY end coordinate in y direction
     */
    public void executeMove(int startX, int startY, int endX, int endY) {
        if(pieces[endX][endY] != null) removePiece(endX, endY);

        pieces[endX][endY] = pieces[startX][startY];
        pieces[endX][endY].location.setLocation(endX, endY);
        pieces[startX][startY] = null;

        if(pieces[endX][endY].isKing()) updateKingsLocation(pieces[endX][endY].color, endX, endY);

        updateValidMoves();
    }

    /**
     * Undo the latest execution of the move
     * @param startX start coordinate in x direction
     * @param startY start coordinate in y direction
     * @param endX end coordinate in x direction
     * @param endY end coordinate in y direction
     */
    public void revertMove(int startX, int startY, int endX, int endY) {
        pieces[startX][startY] = pieces[endX][endY];
        pieces[startX][startY].location.setLocation(startX, startY);
        pieces[endX][endY] = null;

        if(pieces[startX][startY].isKing()) updateKingsLocation(pieces[startX][startY].color, startX, startY);

        if(capturedPieces.size() != 0) {
            Piece lastCapturedPiece = capturedPieces.elementAt(capturedPieces.size() - 1);

            if (lastCapturedPiece.location.equals(new Point(endX, endY))) {
                pieces[endX][endY] = lastCapturedPiece;
                returnPiece(lastCapturedPiece);
            }
        }

        updateValidMoves();
    }



    /**
     * Add a piece by marking it available and update its valid moves
     * @param piece piece to be inserted
     */
    public void insertPiece(Piece piece) {
        pieces[piece.location.getX()][piece.location.getY()] = piece;
        availablePieces.add(piece);
        if(piece.isKing()) updateKingsLocation(piece.color, piece.location.getX(), piece.location.getY());
        updateValidMoves();
    }

    /**
     * Remove a piece by marking it unavailable and captured
     * @param x x coord
     * @param y y coord
     */
    public void removePiece(int x, int y) {
        for(int i = 0; i < availablePieces.size(); i++) {
            if(availablePieces.elementAt(i).equals(this.pieces[x][y])) availablePieces.removeElementAt(i);
        }
        capturedPieces.add(pieces[x][y]);
    }

    /**
     * Put the more recent captured piece back on the board
     * @param piece
     */
    public void returnPiece(Piece piece) {
        capturedPieces.removeElementAt(capturedPieces.size()-1);
        availablePieces.add(piece);
    }

    public void updateKingsLocation(boolean color, int x, int y) {
        int index = color? 1: 0;
        Point point = new Point(x, y);
        kingsLocation.set(index, point);
//        printKingsLocation();
    }

    public Point getKingsLocation(boolean color) {
        int index = color? 1: 0;
        return kingsLocation.elementAt(index);
    }

    public void printKingsLocation() {
        System.out.println("# kings location");
        System.out.printf("\tbk: " + this.kingsLocation.elementAt(0).toString() + "\n");
        System.out.printf("\twk: " + this.kingsLocation.elementAt(1).toString() + "\n");
        System.out.println();
    }

    public void printAvailablePieces(Vector<Piece> list) {
        System.out.printf("# print available pieces - %d pieces\n", list.size());
        for(int i = 0; i < list.size(); i++) {
            System.out.printf("\t%d: %s at (%d, %d)\n", i, list.elementAt(i).identifier, list.elementAt(i).location.getX(), list.elementAt(i).location.getY());
        }
        System.out.println();
    }

    public void printCapturedPieces(Vector<Piece> list) {
        System.out.printf("# print captured pieces - %d captured pieces\n", list.size());
        for(int i = 0; i < list.size(); i++) {
            System.out.printf("\t%d: (%d, %d)\n", i, list.elementAt(i).location.getX(), list.elementAt(i).location.getY());
        }
        System.out.println();
    }

    public String toString(int x, int y) {
        return "(" + x +", " + y + ")";
    }

    public Vector<Point> setKingsLocation() {
        return new Vector<Point>();
    }

    public Vector<Piece> initializeAvailablePieces() {
        return new Vector<Piece>();
    }

}

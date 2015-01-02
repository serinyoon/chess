package Controller;

import View.*;
import Game.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 9/24/14.
 */
public class Controller implements MouseListener{

    public Game game;
    public View view;
    public int[] move;

    public int mousePressedX;
    public int mousePressedY;
    public int mouseReleasedX;
    public int mouseReleasedY;

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
        move = new int[4];
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressedX = e.getX();
        mousePressedY = e.getY();
        System.out.printf("mousePressed: (%d, %d)\n", mousePressedX, mousePressedY);

        move[0] = convertPosition(mousePressedY, view.squareSize);
        move[1] = convertPosition(mousePressedX, view.squareSize);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseReleasedX = e.getX();
        mouseReleasedY = e.getY();
        System.out.printf("mouseReleased: (%d, %d)\n", mouseReleasedX, mouseReleasedY);

        move[2] = convertPosition(mouseReleasedY, view.squareSize);
        move[3] = convertPosition(mouseReleasedX, view.squareSize);

        System.out.printf("%s moving from (%d, %d) to (%d, %d)\n", game.board.pieces[move[0]][move[1]].identifier, move[0], move[1], move[2], move[3]);
        if(game.requestMove(move[0], move[1], move[2], move[3])) {
            view.repaint();
        }
        else {
            view.invalidMove();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Converts the board pixel position to array index
     * @param num pixel position
     * @param offset size of each square
     * @return
     */
    public int convertPosition(int num, int offset) {
        return (num - offset)/offset;
    }



}

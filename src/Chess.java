import Controller.*;
import View.View;
import Game.Game;

/**
 * Created by serinyoon on 9/24/14.
 */
public class Chess {

    public static void main (String[] args) {
        Game game = new Game();
        View view = new View(game);
        Controller controller = new Controller(game, view);
        view.mouseActionListener(controller);
    }
}

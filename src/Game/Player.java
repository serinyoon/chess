package Game;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/27/14.
 */
public class Player {
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    public boolean color;
    public int score;

    /**
     * Default constructor
     * @param color color of the player
     */
    public Player(boolean color) {
        this.color = color;
        this.score = 0;
    }
}

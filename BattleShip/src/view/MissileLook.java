package view;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.Missile;

public class MissileLook extends ElementLook {

    /**
     * Constructor
     * @param element
     */
    public MissileLook(GameElement element) {
        super(element, 1, 1);
    }

    /**
     * Render the missile BLACK_BACKGROUND if it is a miss, RED_BACKGROUND if it is a touch
     */
    protected void render() {

        Missile missile = (Missile) element;
        if (missile.getColor() == missile.Missile_Miss) {
            shape[0][0] = ConsoleColor.WHITE + ConsoleColor.BLACK_BACKGROUND + missile.getSymbol() + ConsoleColor.RESET;
        }
        else {
            shape[0][0] = ConsoleColor.RED + ConsoleColor.BLACK_BACKGROUND + missile.getSymbol() + ConsoleColor.RESET;
        }
    }
}

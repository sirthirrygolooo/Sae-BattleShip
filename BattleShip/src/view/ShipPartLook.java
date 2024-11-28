package view;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.ShipParts;


public class ShipPartLook extends ElementLook {

        /**
         * Constructor
         * @param element
         */
        public ShipPartLook(GameElement element) {
            super(element, 1, 1);
        }

        /**
         * Render the shipPart WHITE_BACKGROUND if it is a miss, RED_BACKGROUND if it is a touch
         */
        protected void render() {

            ShipParts shipParts = (ShipParts) element;
            if (shipParts.getColor() == 1) {
                shape[0][0] = ConsoleColor.BLACK + ConsoleColor.WHITE_BACKGROUND + shipParts.getSymbol() + ConsoleColor.RESET;
            } else {
                shape[0][0] = ConsoleColor.BLACK + ConsoleColor.RED_BACKGROUND + shipParts.getSymbol() + ConsoleColor.RESET;
            }
        }
}

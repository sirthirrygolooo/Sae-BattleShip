package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class ShipParts extends GameElement {
    private final String symbol = "X";
    private boolean isDestroyed = false;

    private int color;

    /**
     * Create a ship part with a given color
     * @param color the color of the ship part
     */
    public ShipParts(int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        // ici ça bloque :/
        // Affiche les différents types de GameElement
        ElementTypes.register("ShipParts", 51);
        type = ElementTypes.getType("ShipParts");
        this.color = color;

    }

    /**
     * Get the symbol of the ship part
     * @return the symbol of the ship part
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Set the ship part as destroyed or not
     * @param isDestroyed true if the ship part is destroyed
     */
    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    /**
     * Check if the ship part is destroyed
     * @return true if the ship part is destroyed
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Destroy the ship part
     */
    public void destroy() {
        isDestroyed = true;
    }

    /**
     * Get the color of the ship part
     * @return the color of the ship part
     */
    public int getColor() {
        return color;
    }

    /**
     * Set the color of the ship part
     * @param color the color of the ship part
     */
    public void setColor(int color) {
        this.color = color;
    }
}

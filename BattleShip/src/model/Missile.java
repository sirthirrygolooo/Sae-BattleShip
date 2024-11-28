package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;
public class Missile extends GameElement {
    private final String symbol = "X";
    private  int color;
    public static final int Missile_Touch = 1;
    public static final int Missile_Miss = 2;

    /**
     * Create a missile with a given color
     * @param color the color of the missile
     */
    public Missile(int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        ElementTypes.register("missile",50);
        type = ElementTypes.getType("missile");
        this.color = color;
    }

    /**
     * Get the color of the missile
     * @return the color of the missile
     */
    public int getColor() {
        return color;
    }

    /**
     * Get the symbol of the missile
     * @return the symbol of the missile
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Set the color of the missile
     * @param color the color of the missile
     */
    public void setColor(int color) {
        this.color = color;
    }
}

package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

import java.util.ArrayList;

public class BoatModel extends GameElement {
    private int size; // size of the boat
    private ArrayList cells; // cells occupied by the boat
    private boolean state; // true if the boat is still floating (not sunk)

    /**
     * Constructor of the BoatModel class
     * @param size the size of the boat
     */
    public BoatModel(int size, GameStageModel gameStageModel) {
        super(gameStageModel);
        this.size = size;
        this.cells = new ArrayList();
        this.state = true;
    }

    /**
     * Getter of the size attribute
     * @return the size of the boat
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter of the cells attribute
     * @return the cells occupied by the boat
     */
    public ArrayList getCells() {
        return cells;
    }

}

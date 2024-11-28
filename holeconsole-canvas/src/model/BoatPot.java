package model;

import boardifier.model.GameStageModel;
import boardifier.model.ContainerElement;

public class BoatPot extends ContainerElement{
    public BoatPot(int x, int y, GameStageModel gameStageModel) {
        // call the super-constructor to create a 4x1 grid, named "boatpot", and in x,y in space
        super("boatpot", x, y, 4, 1, gameStageModel);
    }
}

package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;
import boardifier.model.TextElement;

public class BattleShipStageFactory extends StageElementsFactory{
    private BattleShipStageModel stageModel;

    public BattleShipStageFactory(GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (BattleShipStageModel) gameStageModel;
    }

    @Override
    public void setup() {
        // create the board, pots, pawns and set them in the stage model
        // assign the pawns to their cells in the pots
    }
}

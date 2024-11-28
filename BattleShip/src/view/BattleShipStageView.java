package view;

import boardifier.model.GameStageModel;
import boardifier.view.ClassicBoardLook;
import boardifier.view.GameStageView;

import boardifier.view.TextLook;
import model.StageModel;

public class BattleShipStageView extends GameStageView {
    /**
     * Constructor
     * @param name
     * @param gameStageModel
     */
    public BattleShipStageView(String name, GameStageModel gameStageModel) {
        super(name, gameStageModel);
    }

    /**
     * Create the looks for the game elements
     */
    @Override
    public void createLooks() {
        StageModel model = (StageModel)gameStageModel;

        // create a TextLook for the text element
        addLook(new TextLook(model.getPlayerName()));
        // create 4 ClassicBoardLook (with borders and coordinates) for the main board.
        addLook(new ClassicBoardLook(2, 4, model.getAttackBoard(), 1, 1, true));
        addLook(new ClassicBoardLook(2, 4, model.getAttackBoard2(), 1, 1, true));
        addLook(new ClassicBoardLook(2, 4, model.getBoatBoard(), 1, 1, true));
        addLook(new ClassicBoardLook(2, 4, model.getBoatBoard2(), 1, 1, true));

        for (int i = 0; i < 50; i++) {
            addLook(new MissileLook(model.getMissilesTouch()[i]));
        }

        for (int i = 0; i < 100; i++) {
            addLook(new MissileLook(model.getMissilesMiss()[i]));
        }

        for (int i = 0; i < model.getShipParts().length; i++) {
            addLook(new ShipPartLook(model.getShipParts()[i]));
        }
    }
}
package model;

import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;
import boardifier.model.TextElement;

public class StageFactory extends StageElementsFactory {
    private StageModel stageModel;

    /**
     * Create a factory for the stage
     * @param gameStageModel the game stage model
     */
    public StageFactory(GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (StageModel) gameStageModel;
    }

    /**
     * Setup the stage
     *
     * Create the elements of the stage
     */
    @Override
    public void setup(){
        // Create the elements of the stage

        int ShipPartsNumber = 40;


        ShipParts[] shipParts = new ShipParts[ShipPartsNumber];

        for(int i=0;i<ShipPartsNumber;i++) {
            ShipParts shipPart = new ShipParts(1, stageModel);
            shipPart.setVisible(false);
            shipParts[i] = shipPart;
        }
        stageModel.setShipParts(shipParts);


        Missile[] missilesTouch = new Missile[50];
        for(int i=0;i<50;i++) {
            Missile missile = new Missile(Missile.Missile_Touch, stageModel);
            missile.setVisible(false);
            missilesTouch[i] = missile;
        }
        stageModel.setMissilesTouch(missilesTouch);

        Missile[] missilesMiss = new Missile[100];
        for(int i=0;i<100;i++) {
            Missile missile = new Missile(Missile.Missile_Miss, stageModel);
            missile.setVisible(false);
            missilesMiss[i] = missile;
        }
        stageModel.setMissilesMiss(missilesMiss);

        TextElement text = new TextElement(stageModel.getCurrentPlayerName(), stageModel);
        text.setLocation(0,0);
        stageModel.setPlayerName(text);

        BattleShipBoard AttackBoard = new BattleShipBoard("AttackBoard1", 1, 2, stageModel);
        stageModel.setAttackBoard(AttackBoard);

        BattleShipBoard AttackBoard2 = new BattleShipBoard("AttackBoard2", 1, 2, stageModel);
        AttackBoard2.setVisible(false);
        stageModel.setAttackBoard2(AttackBoard2);

        BattleShipBoard BoatBoard = new BattleShipBoard("BoatBoard1", 51, 2, stageModel);
        BoatBoard.setVisible(false);
        stageModel.setBoatBoard(BoatBoard);

        BattleShipBoard BoatBoard2 = new BattleShipBoard("BoatBoard2", 51, 2, stageModel);
        stageModel.setBoatBoard2(BoatBoard2);
    }
}

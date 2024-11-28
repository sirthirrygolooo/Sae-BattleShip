package control;

import boardifier.control.ActionFactory;
import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.GameElement;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.*;

import java.awt.*;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import model.ShipParts;

import static control.BattleShipController.MISSILE_MISS;
import static control.BattleShipController.MISSILE_TOUCH;

public class BattleShipDecider extends Decider {

    private static final Random randomGenerator = new Random(Calendar.getInstance().getTimeInMillis());
    private boolean firstPlayer;

    /**
     * Constructor
     *
     * @param model   the model
     * @param control the controller
     */
    public BattleShipDecider(boolean player, Model model, Controller control) {
        super(model, control);
        this.firstPlayer = player;
    }

    /**
     * Decide the next action to perform
     *
     * @return the action list
     */
    @Override
    public ActionList decide() {
        // Cast the model to access StageModel attributes
        StageModel stageModel = (StageModel) model.getGameStage();
        BattleShipBoard attackBoard;
        BattleShipBoard defenseBoard;

        if(firstPlayer){
            attackBoard = stageModel.getAttackBoard();
            defenseBoard = stageModel.getBoatBoard2();
        } else {
            attackBoard = stageModel.getAttackBoard2();
            defenseBoard = stageModel.getBoatBoard();
        }


        // Find valid cells on the attack board
        List<Point> validCells = attackBoard.computeValidCells(1);

        // Select a random cell from the valid cells
        Point selectedCell = validCells.get(randomGenerator.nextInt(validCells.size()));

        // Define the destination coordinates
        int rowDest = selectedCell.y;
        int colDest = selectedCell.x;

        GameElement target = defenseBoard.getElement(rowDest, colDest);

        GameElement missileElement;
        if (target != null) {
            missileElement = stageModel.getMissilesTouch()[MISSILE_TOUCH];
            MISSILE_TOUCH++;
            missileElement.setVisible(true);
            ShipParts shipPart = (ShipParts) target;
            ((ShipParts) target).setColor(2);
            shipPart.destroy();
            System.out.println("\n\n\nTouch\n\n\n");

            if(firstPlayer) {
                stageModel.addPlayer1Point(1);
            } else {
                stageModel.addPlayer2Point(1);
            }

        } else {
            missileElement = stageModel.getMissilesMiss()[MISSILE_MISS];
            MISSILE_MISS++;
            missileElement.setVisible(true);
            System.out.println("\n\n\nMiss\n\n\n");
        }

        // Create an action list for placing an element in the container
        ActionList actionList = ActionFactory.generatePutInContainer(model, missileElement, attackBoard.getName(), rowDest, colDest);

        return actionList;
    }
}

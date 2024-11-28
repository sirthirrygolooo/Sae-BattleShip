package control;

import boardifier.control.ActionFactory;
import boardifier.control.ActionPlayer;
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

import static control.BattleShipController.SHIP_PART;

public class BattleShipPlacingDecider extends Decider {
    private static final Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    private final int variant;
    private boolean firstPlayer;

    /**
     * Constructor
     * @param variant
     * @param model
     * @param control
     */
    public BattleShipPlacingDecider(int variant, boolean first, Model model, Controller control) {

        super(model, control);
        this.variant = variant;
        this.firstPlayer = first;
    }

    /**
     * Decide where to place the ships
     * @return actionList
     */
    @Override
    public ActionList decide() {
        // do a cast get a variable of the real type to get access to the attributes of
        // HoleStageModel
        StageModel stage = (StageModel) model.getGameStage();
        BattleShipBoard Defenseboard; // get the board

        if(firstPlayer) {
            Defenseboard = stage.getBoatBoard();
        }
        else {
            Defenseboard = stage.getBoatBoard2();
        }

        int randomPlacing = loto.nextInt(2);

        ActionList actionList = new ActionList();

        if(variant == 1 ) {
            // put the ship in A1A5 or A6A10 then C1C4 or C6C10 then E1E3 or E8E10 then G1G3 or G8G10 then I1I2 or I9I1
            if(randomPlacing == 1) {
                for (int i = 0; i < 5; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 0);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            } else {
                for (int i = 5; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 0);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            }



            randomPlacing = loto.nextInt(2);
            if(randomPlacing == 1) {
                for (int i = 0; i < 4; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 2);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            } else {
                for (int i = 6; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 2);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            }


            randomPlacing = loto.nextInt(2);
            if(randomPlacing == 1) {
                for (int i = 0; i < 3; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 4);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            } else {
                for (int i = 7; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 4);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            }



            randomPlacing = loto.nextInt(2);
            if(randomPlacing == 1) {
                for (int i = 0; i < 3; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 6);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            } else {
                for (int i = 7; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 6);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            }



            randomPlacing = loto.nextInt(2);
            if(randomPlacing == 1) {
                for (int i = 0; i < 2; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 8);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            } else {
                for (int i = 8; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 8);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            }
        } else {
            // 4, 3, 3, 2, 2, 2, 1, 1, 1, 1
            randomPlacing = loto.nextInt(2);
            if (randomPlacing == 1) {
                for (int i = 0; i < 4; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 0);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }

                for(int i = 7; i < 9; i++){
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 0);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            } else {
                for (int i = 6; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 0);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }

                for(int i = 0; i < 2; i++){
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 0);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            }

            randomPlacing = loto.nextInt(2);
            if (randomPlacing == 1) {
                for (int i = 0; i < 3; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 2);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }

                GameElement shipPart = stage.getShipParts()[SHIP_PART];
                SHIP_PART++;
                shipPart.setVisible(true);
                actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), 6, 2);
                actionList.setDoEndOfTurn(true);
                ActionPlayer play = new ActionPlayer(model, control, actionList);
                play.start();
            } else {
                for (int i = 7; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 2);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }

                GameElement shipPart = stage.getShipParts()[SHIP_PART];
                SHIP_PART++;
                shipPart.setVisible(true);
                actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), 2, 2);
                actionList.setDoEndOfTurn(true);
                ActionPlayer play = new ActionPlayer(model, control, actionList);
                play.start();
            }

            randomPlacing = loto.nextInt(2);
            if (randomPlacing == 1) {
                for (int i = 0; i < 3; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 4);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }

                GameElement shipPart = stage.getShipParts()[SHIP_PART];
                SHIP_PART++;
                shipPart.setVisible(true);
                actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), 7, 4);
                actionList.setDoEndOfTurn(true);
                ActionPlayer play = new ActionPlayer(model, control, actionList);
                play.start();
            } else {
                for (int i = 7; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 4);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }

                GameElement shipPart = stage.getShipParts()[SHIP_PART];
                SHIP_PART++;
                shipPart.setVisible(true);
                actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), 3, 4);
                actionList.setDoEndOfTurn(true);
                ActionPlayer play = new ActionPlayer(model, control, actionList);
                play.start();
            }

            randomPlacing = loto.nextInt(2);
            if (randomPlacing == 1) {
                for (int i = 0; i < 2; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 6);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            } else {
                for (int i = 8; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 6);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            }

            randomPlacing = loto.nextInt(2);
            if (randomPlacing == 1) {
                for (int i = 0; i < 2; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 8);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            } else {
                for (int i = 8; i < 10; i++) {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), i, 8);
                    actionList.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, control, actionList);
                    play.start();
                }
            }

            GameElement shipPart = stage.getShipParts()[SHIP_PART];
            SHIP_PART++;
            shipPart.setVisible(true);
            actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), 5, 9);
            actionList.setDoEndOfTurn(true);
            ActionPlayer play = new ActionPlayer(model, control, actionList);
            play.start();


            shipPart = stage.getShipParts()[SHIP_PART];
            SHIP_PART++;
            shipPart.setVisible(true);
            actionList = ActionFactory.generatePutInContainer(model, shipPart, Defenseboard.getName(), 5, 7);
            actionList.setDoEndOfTurn(true);
            play = new ActionPlayer(model, control, actionList);
            play.start();

        }
        return actionList;
    }

}

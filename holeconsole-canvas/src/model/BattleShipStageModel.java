package model;

import boardifier.model.*;

public class BattleShipStageModel extends GameStageModel {
    private int Player1ToPlay;
    private int Player2ToPlay;
    private BattleShipBoard board;
    private BoatPot Player1Pot;
    private BoatPot Player2Pot;
    private Pawn[] Player1Pawns;
    private Pawn[] Player2Pawns;
    private TextElement playerName;

    /**
     * Constructor of the BattleShipStageModel
     * @param name the name of the stage
     * @param model the model of the stage
     */
    public BattleShipStageModel(String name, Model model) {
        super(name, model);
        Player1ToPlay = 4;
        Player2ToPlay = 4;
        setupCallbacks();
    }

    /**
     * Getter of the board
     * @return the board
     */
    public BattleShipBoard getBoard() {
        return board;
    }

    /**
     * Getter of the first player's pot
     * @return the first player's pot
     */
    public BoatPot getPlayer1Pot() {
        return Player1Pot;
    }

    /**
     * Getter of the second player's pot
     * @return the second player's pot
     */
    public BoatPot getPlayer2Pot() {
        return Player2Pot;
    }

    /**
     * Getter of the first player's pawns
     * @return the first player's pawns
     */
    public Pawn[] getPlayer1Pawns() {
        return Player1Pawns;
    }

    /**
     * Getter of the second player's pawns
     * @return the second player's pawns
     */
    public Pawn[] getPlayer2Pawns() {
        return Player2Pawns;
    }

    /**
     * Getter of the player's name
     * @return the player's name
     */
    public TextElement getPlayerName() {
        return playerName;
    }

    /**
     * Setter of the board
     */
    private void setupCallbacks() {
        onPutInContainer( (element, gridDest, rowDest, colDest) -> {
            // just check when pawns are put in 3x3 board
            if (gridDest != board) return;
            Pawn p = (Pawn) element;
            if (p.getColor() == 0) {
                Player1ToPlay--;
            }
            else {
                Player2ToPlay--;
            }
            if ((Player1ToPlay == 0) && (Player2ToPlay == 0)) {
                computePartyResult();
            }
        });
    }

    private void computePartyResult() {
        // TODO
    }


}

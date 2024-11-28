package model;

import boardifier.model.*;

public class StageModel extends GameStageModel {
    private int[] boatSizes = { 5, 4, 3, 3, 2 };
    private String[] boatNames = { "Aircraft Carrier", "Cruiser", "Destroyer", "Destroyer", "Torpedo" };
    private Missile[] missilesTouch;
    private Missile[] missilesMiss;
    private ShipParts[] shipParts;
    private BattleShipBoard AttackBoard;
    private BattleShipBoard AttackBoard2;
    private BattleShipBoard BoatBoard;
    private BattleShipBoard BoatBoard2;
    private TextElement playerName;
    private int player1toPlay = 41;
    private int player2toPlay = 40; // 60 moove or 40 moves depending on the gamemode
    private int player1Point = 0;
    private int player2Point = 0;
    private int maxPlayerPoint = 17; // 17 hit max or 20 hit max depending on the gamemode

    /**
     * @return the default element factory for this model
     *
     * Create all the elements of the stage
     */
    @Override
    public StageElementsFactory getDefaultElementFactory() {
        return new StageFactory(this);
    }

    /**
     * constructor
     *
     * @param name
     * @param model
     */
    public StageModel(String name, Model model) {
        super(name, model);
        setupCallbacks();
    }

    /**
     * setup the callbacks
     */
    public void setupCallbacks() {
        onPutInContainer((element, gridDest, rowDest, colDest) -> {
            if (gridDest != AttackBoard && gridDest != AttackBoard2) {
                return;
            }




            if (gridDest == AttackBoard) {
                player1toPlay--;
                System.out.println("\n\n\nPlayer 1 to play: " + player1toPlay);
            } else {
                player2toPlay--;
                System.out.println("\n\n\nPlayer 2 to play: " + player2toPlay);
            }

            if (player1Point == maxPlayerPoint) {
                computePartyResult();
            } else if (player2Point == maxPlayerPoint) {
                computePartyResult();
            }
            else if (player1toPlay == 0 && player2toPlay == 0) {
                computePartyResult();
            }
        });
    }

    /**
     * Compute the party result
     */
    public void computePartyResult() {
        int idWinner = -1;

        if (player1Point == maxPlayerPoint) {
            idWinner = 1;
        } else if (player2Point == maxPlayerPoint) {
            idWinner = 0;
        } else if (player1Point > player2Point && player1toPlay == 0 && player2toPlay == 0) {
            idWinner = 1;
        } else if (player1Point < player2Point && player1toPlay == 0 && player2toPlay == 0) {
            idWinner = 0;
        }

        if (idWinner == 0) {
            playerName.setText("Player 1 wins");
        } else if (idWinner == 1) {
            playerName.setText("Player 2 wins");
        }
        model.setIdWinner(idWinner);
        model.stopStage();
    }

    /**
     * @return BattleShipBoard
     *
     * Get the attack board
     */
    public BattleShipBoard getAttackBoard() {
        return AttackBoard;
    }

    /**
     * @return BattleShipBoard
     *
     * Get the attack board 2
     */
    public BattleShipBoard getAttackBoard2() {
        return AttackBoard2;
    }

    /**
     * @return battleShipBoard
     *
     * Get the boat board
     */
    public BattleShipBoard getBoatBoard() {
        return BoatBoard;
    }

    /**
     * @return BattleShipBoard
     *
     * Get the boat board 2
     */
    public BattleShipBoard getBoatBoard2() {
        return BoatBoard2;
    }

    /**
     * set the attack board
     *
     * @param AttackBoard
     */
    public void setAttackBoard(BattleShipBoard AttackBoard) {

        this.AttackBoard = AttackBoard;
        addContainer(AttackBoard);
    }

    /**
     * set the boat board
     *
     * @param AttackBoard2
     */
    public void setAttackBoard2(BattleShipBoard AttackBoard2) {
        this.AttackBoard2 = AttackBoard2;
        addContainer(AttackBoard2);
    }

    /**
     * set the boat board
     *
     * @param BoatBoard
     */
    public void setBoatBoard(BattleShipBoard BoatBoard) {
        this.BoatBoard = BoatBoard;
        addContainer(BoatBoard);
    }

    /**
     * set the boat board
     *
     * @param BoatBoard2
     */
    public void setBoatBoard2(BattleShipBoard BoatBoard2) {
        this.BoatBoard2 = BoatBoard2;
        addContainer(BoatBoard2);
    }

    /**
     * set the player name
     *
     * @param playerName
     */
    public void setPlayerName(TextElement playerName) {
        this.playerName = playerName;
        addElement(playerName);
    }

    /**
     * @return TextElement
     */
    public TextElement getPlayerName() {
        return playerName;
    }

    /**
     * set Missiles Touch
     *
     * @param missilesTouch
     */
    public void setMissilesTouch(Missile[] missilesTouch) {
        this.missilesTouch = missilesTouch;
        for (Missile missile : missilesTouch) {
            addElement(missile);
        }
    }

    /**
     * set Missiles Miss
     *
     * @param missilesMiss
     */
    public void setMissilesMiss(Missile[] missilesMiss) {
        this.missilesMiss = missilesMiss;
        for (Missile missile : missilesMiss) {
            addElement(missile);
        }
    }

    /**
     * @return Missile[]
     *
     * Get the missiles touch
     */
    public Missile[] getMissilesTouch() {
        return missilesTouch;
    }

    /**
     * @return Missile[]
     *
     * Get the missiles miss
     */
    public Missile[] getMissilesMiss() {
        return missilesMiss;
    }

    /**
     * set the ship parts
     */
    public void setShipParts(ShipParts[] shipParts) {
        this.shipParts = shipParts;
        for (ShipParts shipPart : shipParts) {
            addElement(shipPart);
        }
    }

    /**
     * @return ShipParts[]
     *
     * Get the ship parts
     */
    public ShipParts[] getShipParts() {
        return shipParts;
    }

    /**
     *
     * @param player1Point
     *
     * add a point to player 1
     */
    public void addPlayer1Point(int player1Point) {
        this.player1Point += player1Point;
        System.out.println("Player 1 point: " + this.player1Point);
    }

    /**
     *
     * @param player2Point
     *
     * add a point to player 2
     */
    public void addPlayer2Point(int player2Point) {
        this.player2Point += player2Point;
        System.out.println("Player 2 point: " + this.player2Point);
    }

    /**
     * @return int
     *
     * Get the player 1 point
     */
    public int getPlayer1Point() {
        return player1Point;
    }

    /**
     * @return int
     *
     * Get the player 2 point
     */
    public int getPlayer2Point() {
        return player2Point;
    }

    /**
     * get the max player point
     * @return the max player point
     */
    public int getMaxPlayerPoint() {
        return maxPlayerPoint;
    }

    /**
     * Change the variant of the game
     */
    public void changeVariant(){
        this.maxPlayerPoint = 20;
        this.player2toPlay = 60;
        this.player1toPlay = 60;
        this.boatSizes = new int[]{4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        this.boatNames = new String[]{"Cruiser", "Destroyer", "Destroyer", "Torpedo", "Torpedo", "Torpedo}", "Submarine", "Submarine", "Submarine", "Submarine"};
    }

    /**
     * @return int[]
     *
     * Get the boat sizes
     */
    public int[] getBoatSizes() {
        return boatSizes;
    }

    /**
     * @return String[]
     *
     * Get the boat names
     */
    public String[] getBoatNames() {
        return boatNames;
    }

    /**
     * @return int
     *
     * Get the player 1 to play
     */
    public int getPlayer1toPlay() {
        return player1toPlay;
    }

    /**
     * @return int
     *
     * Get the player 2 to play
     */
    public int getPlayer2toPlay() {
        return player2toPlay;
    }
}

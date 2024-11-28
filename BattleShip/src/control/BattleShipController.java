package control;

import boardifier.control.ActionFactory;
import boardifier.control.ActionPlayer;
import boardifier.control.Controller;
import boardifier.model.GameElement;
import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.model.action.ActionList;
import boardifier.view.View;
import model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class BattleShipController extends Controller {
    protected static int MISSILE_MISS = 0;
    public static int MISSILE_TOUCH = 0;
    public static int SHIP_PART = 0;
    private BufferedReader consoleIn;
    private boolean isFirstPlayer;
    protected int variant;

    /**
     * Constructor
     * @param model
     * @param view
     */
    public BattleShipController(Model model, View view) {
        super(model, view);
        isFirstPlayer = true;
    }

    /**
     * Defines what to do within the single stage of the single party
     * It is pretty straight forward to write :
     */
    public void stageLoop() {
        consoleIn = new BufferedReader(new InputStreamReader(System.in));
        chooseVariant();
        update();
        for (int i = 0; i < 2; i++) {
            placeBoats();
            isFirstPlayer = !isFirstPlayer;
            endOfTurn();
            changeView();
            update();
        }

        while (!model.isEndStage()) {
            playTurn();
            endOfTurn();
            update();
            changeView();
            update();
        }
        endGameView();
        endGame();
    }

    public void endGameView(){
        StageModel stage = (StageModel) model.getGameStage();
        stage.getAttackBoard().setVisible(true);
        stage.getAttackBoard2().setX(125);
        stage.getAttackBoard2().setVisible(true);
        stage.getBoatBoard().setVisible(true);
        stage.getBoatBoard2().setX(175);
        stage.getBoatBoard2().setVisible(true);
        update();
    }

    /**
     * Choose the variant of the game by the player
     */
    public void chooseVariant(){
        Player currentPlayer = model.getCurrentPlayer();
        String regex = "([1-2])";
        System.out.println("\nChoose the variant of the game: ");
        if (currentPlayer.getType() == Player.COMPUTER) {
            System.out.println("COMPUTER PLAYS");
            Random random = new Random();
            int choice = random.nextInt(2) + 1; // Generates either 1 or 2
            this.variant = choice;
            System.out.println("Computer chooses variant " + choice);
            analyseAndVariant(String.valueOf(choice));
        } else {
            boolean isValidCommand = false;
            while (!isValidCommand) {
                System.out.println("\nVariant 1 : 1 aircraft carrier of 5 slots, 1 cruiser of 4 slots, 2 destroyers of 3 slots, 1 torpedo of 2 slots.");
                System.out.println("Variant 2 : 1 battleship of 4 slots, 2 cruisers of 3 slots, 3 torpedo boats of 2 slots, 4 submarines of 1 slot");
                System.out.print("Enter 1 or 2 > ");
                try {
                    String line = consoleIn.readLine().toUpperCase();
                    if (line.equals("EXIT")) {
                        System.out.println("Game interrupted by the user");
                        System.exit(0);
                    }
                    if (line.matches(regex)) {
                        isValidCommand = analyseAndVariant(line);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading the input");
                }
            }
        }
    }

    /**
     * Analyse the input and change the variant of the game
     * @param line
     * @return
     */
    public boolean analyseAndVariant(String line) {
        StageModel stage = (StageModel) model.getGameStage();
        if(line.equals("1")) {
            this.variant = 1;
            return true;
        }
        if (line.equals("2")) {
            this.variant = 2;
            stage.changeVariant();
            return true;
        }
        return false;
    }


    /**
     * Play a turn during an attack phase
     */
    private void playTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        String regex = "([A-J](10|[1-9]))";
        System.out.println("Current player: " + currentPlayer.getName());
        if (currentPlayer.getType() == Player.COMPUTER) {
            System.out.println("COMPUTER PLAYS");
            BattleShipDecider decider = new BattleShipDecider(isFirstPlayer, model, this);
            ActionPlayer play = new ActionPlayer(model, this, decider, null);
            play.start();
            isFirstPlayer = !isFirstPlayer;
        } else {
            boolean isValidMove = false;
            while (!isValidMove) {
                System.out.print(currentPlayer.getName() + " > ");
                try {
                    String line = consoleIn.readLine().toUpperCase();
                    if (line.equals("EXIT")) {
                        System.out.println("Game interrupted by the user");
                        System.exit(0);
                    }
                    if (line.matches(regex)) {
                        isValidMove = analyseAndPlay(line);
                    }
                    if (!isValidMove) {
                        System.out.println("Invalid move, please retry");
                    }
                } catch (IOException e) {
                    System.out.println("Error reading the input");
                }
            }
        }
    }

    /**
     * End of the turn, change the player
     */
    public void endOfTurn() {
        model.setNextPlayer();
        Player currentPlayer = model.getCurrentPlayer();
        System.out.println("Next player: " + currentPlayer.getName());
        StageModel stageModel = (StageModel) model.getGameStage();
        stageModel.getPlayerName().setText(currentPlayer.getName());
    }

    /**
     * Change the view to display the correct board
     */
    public void changeView() {
        BattleShipBoard attackBoard = ((StageModel) model.getGameStage()).getAttackBoard();
        BattleShipBoard attackBoard2 = ((StageModel) model.getGameStage()).getAttackBoard2();
        BattleShipBoard boatBoard = ((StageModel) model.getGameStage()).getBoatBoard();
        BattleShipBoard boatBoard2 = ((StageModel) model.getGameStage()).getBoatBoard2();

        if (isFirstPlayer) {
            attackBoard.setVisible(true);
            attackBoard2.setVisible(false);
            boatBoard.setVisible(true);
            boatBoard2.setVisible(false);
        } else {
            attackBoard.setVisible(false);
            attackBoard2.setVisible(true);
            boatBoard.setVisible(false);
            boatBoard2.setVisible(true);
        }
    }

    /**
     * Place the boats on the board with regex
     */
    private void placeBoats() {
        StageModel stage = (StageModel) model.getGameStage();
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
////            System.out.println("Game interrupted by the user");
//            System.exit(0);
//        }));
        Player currentPlayer = model.getCurrentPlayer();
        String regex = "([A-J](10|[1-9]))+";
        System.out.println("Current player: " + currentPlayer.getName());
        System.out.println("Place your boats player " + model.getIdPlayer());
//        String[] boatNames = { "Aircraft Carrier", "Cruiser", "Destroyer", "Destroyer", "Torpedo" };
//        int[] boatSizes = { 5, 4, 3, 3, 2 };
        String[] boatNames = stage.getBoatNames();
        int[] boatSizes = stage.getBoatSizes();
        if (currentPlayer.getType() == Player.COMPUTER) {
            System.out.println("COMPUTER PLAYS");
            BattleShipPlacingDecider decider = new BattleShipPlacingDecider(variant, isFirstPlayer, model, this);
            ActionPlayer play = new ActionPlayer(model, this, decider, null);
            play.start();
        } else {
            for (int i = 0; i < boatNames.length; i++) {
                boolean isValidPlacement = false;
                while (!isValidPlacement) {
                    System.out.println("Place your " + boatNames[i] + " (size " + boatSizes[i]
                            + ", format: letterNumberLetterNumber(A1A5)): ");
                    System.out.print(currentPlayer.getName() + " > ");

                    try {
                        String userLine = consoleIn.readLine().toUpperCase();
                        System.out.println("Line: " + userLine);
                        if (userLine.equals("EXIT")) {
                            System.out.println("Game interrupted by the user");
                            System.exit(0);
                        }
                        if (userLine.matches(regex)) {
                            System.out.println(boatNames[i] + " placed on " + userLine);
                            isValidPlacement = analyseAndPlaceShip(userLine, boatSizes[i]);
                            update();
                        }

                        if (!isValidPlacement) {
                            System.out.println("Incorrect format, try again");
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading the input");
                    }
                    // Display the board
                }
            }
        }
    }

    /**
     * Analyse the input and place the ship on the board
     * @param line
     * @param boatSize
     * @return
     */
    public boolean analyseAndPlaceShip(String line, int boatSize) {
        StageModel stage = (StageModel) model.getGameStage();
        BattleShipBoard board;
        BattleShipBoard board2;
        int colDest = 0;
        int rowDest = 0;
        int colDest2 = 0;
        int rowDest2 = 0;

        if (isFirstPlayer) {
            board = stage.getBoatBoard();
            board2 = stage.getBoatBoard2();

            board.setVisible(true);
            board2.setVisible(false);

        } else {
            board = stage.getBoatBoard2();
            board2 = stage.getBoatBoard();

            board.setVisible(true);
            board2.setVisible(false);
        }

        // There is most likely a better way to proceed but it works for now
        if (line.length() == 4) {
            colDest = (int) (line.charAt(0) - 'A');
            rowDest = (int) (line.charAt(1) - '1');
            colDest2 = (int) (line.charAt(2) - 'A');
            rowDest2 = (int) (line.charAt(3) - '1');
        } else if (line.length() == 6) {
            colDest = (int) (line.charAt(0) - 'A');
            rowDest = Integer.parseInt(line.substring(1, 3)) - 1;
            colDest2 = (int) (line.charAt(3) - 'A');
            rowDest2 = Integer.parseInt(line.substring(4, 6)) - 1;
        } else if (line.length() == 5) {
            if (Character.isDigit(line.charAt(2))) {
                colDest = (int) (line.charAt(0) - 'A');
                rowDest = Integer.parseInt(line.substring(1, 3)) - 1;
                colDest2 = (int) (line.charAt(3) - 'A');
                rowDest2 = (int) (line.charAt(4) - '1');
            } else {
                colDest = (int) (line.charAt(0) - 'A');
                rowDest = (int) (line.charAt(1) - '1');
                colDest2 = (int) (line.charAt(2) - 'A');
                rowDest2 = Integer.parseInt(line.substring(3, 5)) - 1;
            }
        } else if (line.length() == 2 && boatSize == 1) {
            colDest = (int) (line.charAt(0) - 'A');
            rowDest = (int) (line.charAt(1) - '1');
            GameElement shipPart = stage.getShipParts()[SHIP_PART];
            SHIP_PART++;
            shipPart.setVisible(true);
            ActionList actions = ActionFactory.generatePutInContainer(model, shipPart, board.getName(), rowDest, colDest);
            actions.setDoEndOfTurn(true);
            ActionPlayer play = new ActionPlayer(model, this, actions);
            play.start();
            return true;
        } else if (line.length() == 3 && boatSize == 1) {
            colDest = (int) (line.charAt(0) - 'A');
            rowDest = Integer.parseInt(line.substring(1, 3)) - 1;
            GameElement shipPart = stage.getShipParts()[SHIP_PART];
            SHIP_PART++;
            shipPart.setVisible(true);
            ActionList actions = ActionFactory.generatePutInContainer(model, shipPart, board.getName(), rowDest, colDest);
            actions.setDoEndOfTurn(true);
            ActionPlayer play = new ActionPlayer(model, this, actions);
            play.start();
            return true;
        }

        else {
            return false;
        }

        System.out.println(
                "colDest: " + colDest + " rowDest: " + rowDest + " colDest2: " + colDest2 + " rowDest2: " + rowDest2);

        if (rowDest < 0 || rowDest >= 10 || colDest < 0 || colDest >= 10 || rowDest2 < 0 || rowDest2 >= 10
                || colDest2 < 0 || colDest2 >= 10) {
            return false;
        }

        if (rowDest != rowDest2 && colDest != colDest2) {
            return false;
        }

        int size = Math.abs(rowDest - rowDest2) + Math.abs(colDest - colDest2) + 1;
        if (size != 5 && size != 4 && size != 3 && size != 2) {
            return false;
        }

        // Check if boatSize corresponds to the size of the ship placed with the
        // coordinates

        if (size != boatSize) {
            System.out.println("La taille ne correspond pas...");
            return false;
        }


        if (rowDest == rowDest2) {
            // Check the corner of the boat

            // Left top corner
            if (rowDest - 1 >= 0  && Math.min(colDest, colDest2) - 1 >= 0 && board.getElement(rowDest - 1, Math.min(colDest, colDest2) - 1) != null) {
                return false;
            }

            // left bot corner Ok
            if (rowDest + 1 < 10  && Math.min(colDest, colDest2) - 1 >= 0 && board.getElement(rowDest + 1, Math.min(colDest, colDest2) - 1) != null) {
                return false;
            }

            // Right top corner
            if (rowDest - 1 >= 0  && Math.max(colDest, colDest2) + 1 < 10 && board.getElement(rowDest - 1, Math.max(colDest, colDest2) + 1) != null) {
                return false;
            }

            // Right bot corner Ok
            if (rowDest + 1 < 10  && Math.max(colDest, colDest2) + 1 < 10 && board.getElement(rowDest + 1, Math.max(colDest, colDest2) + 1) != null) {
                return false;
            }


            for (int i = Math.min(colDest, colDest2); i <= Math.max(colDest, colDest2); i++) {
                // Analyse each cell around for each cell to see if there is a ship part
                if (i - 1 >= 0 && board.getElement(rowDest, i - 1) != null) {
                    return false;
                }

                if (i + 1 < 10 && board.getElement(rowDest, i + 1) != null) {
                    return false;
                }

                if (rowDest - 1 >= 0 && board.getElement(rowDest - 1, i) != null) {
                    return false;
                }

                if (rowDest + 1 < 10 && board.getElement(rowDest + 1, i) != null) {
                    return false;
                }



            }
        } else {

            // Left top corner
            if (colDest - 1 >= 0  && Math.min(rowDest, rowDest2) - 1 >= 0 && board.getElement(Math.min(rowDest, rowDest2) - 1, colDest - 1) != null) {
                return false;
            }

            // Right top corner Ok
            if (colDest + 1 < 10 && Math.min(rowDest, rowDest2) - 1 >= 0 && board.getElement(Math.min(rowDest, rowDest2) - 1, colDest + 1) != null) {
                return false;
            }

            // Left bot corner
            if (colDest - 1 >= 0  && Math.max(rowDest, rowDest2) + 1 < 10 && board.getElement(Math.max(rowDest, rowDest2) + 1, colDest - 1) != null) {
                return false;
            }

            // Right bot corner Ok
            if (colDest + 1 < 10  && Math.max(rowDest, rowDest2) + 1 < 10 && board.getElement(Math.max(rowDest, rowDest2) + 1, colDest + 1) != null) {
                return false;
            }

            for(int i = Math.min(rowDest, rowDest2); i <= Math.max(rowDest, rowDest2); i++) {
                // Analyse x-1, x+1, y-1, y+1 for each cell to see if there is a ship part
                if (i-1 >= 0 && board.getElement(i-1, colDest) != null) {
                    return false;
                }

                if (i+1 < 10 && board.getElement(i+1, colDest) != null) {
                    return false;
                }

                if (colDest-1 >= 0 && board.getElement(i, colDest-1) != null) {
                    return false;
                }

                if (colDest+1 < 10 && board.getElement(i, colDest+1) != null) {
                    return false;
                }

            }
        }


        if (rowDest == rowDest2) {
            for (int i = Math.min(colDest, colDest2); i <= Math.max(colDest, colDest2); i++) {
                if (board.getElement(rowDest, i) != null) {
                    return false;
                } else {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    ActionList actions = ActionFactory.generatePutInContainer(model, shipPart, board.getName(), rowDest,
                            i);
                    actions.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, this, actions);
                    play.start();
                }
            }
        } else {
            for (int i = Math.min(rowDest, rowDest2); i <= Math.max(rowDest, rowDest2); i++) {
                if (board.getElement(i, colDest) != null) {
                    return false;
                } else {
                    GameElement shipPart = stage.getShipParts()[SHIP_PART];
                    SHIP_PART++;
                    shipPart.setVisible(true);
                    ActionList actions = ActionFactory.generatePutInContainer(model, shipPart, board.getName(), i,
                            colDest);
                    actions.setDoEndOfTurn(true);
                    ActionPlayer play = new ActionPlayer(model, this, actions);
                    play.start();

                }
            }
        }
        return true;
    }


    /**
     * Analyse the input and play the attack on the board
     * @param line
     * @return
     */
    public boolean analyseAndPlay(String line) {
        StageModel stage = (StageModel) model.getGameStage();
        BattleShipBoard attackBoard;
        BattleShipBoard attackBoard2;
        BattleShipBoard defenseBoard;
        BattleShipBoard defenseBoard2;

        if (isFirstPlayer) {
            attackBoard = stage.getAttackBoard();
            defenseBoard = stage.getBoatBoard();

            attackBoard.setVisible(true);
            defenseBoard.setVisible(true);

            attackBoard2 = stage.getAttackBoard2();
            defenseBoard2 = stage.getBoatBoard2();

            attackBoard2.setVisible(false);
            defenseBoard2.setVisible(false);
        } else {
            attackBoard = stage.getAttackBoard2();
            defenseBoard = stage.getBoatBoard2();

            attackBoard.setVisible(true);
            defenseBoard.setVisible(true);

            attackBoard2 = stage.getAttackBoard();
            defenseBoard2 = stage.getBoatBoard();

            attackBoard2.setVisible(false);
            defenseBoard2.setVisible(false);
        }

        int colDest;
        int rowDest;

        isFirstPlayer = !isFirstPlayer;

        if (line.length() == 2) {
            colDest = (int) (line.charAt(0) - 'A');
            rowDest = (int) (line.charAt(1) - '1');
        } else if (line.length() == 3) {
            colDest = (int) (line.charAt(0) - 'A');
            rowDest = Integer.parseInt(line.substring(1)) - 1;
        } else {
            return false;
        }

        // Check the defense board of the opponent for the presence of a ship part
        GameElement target = defenseBoard2.getElement(rowDest, colDest);

        GameElement missileElement;
        if (target != null) {
            missileElement = stage.getMissilesTouch()[MISSILE_TOUCH];
            MISSILE_TOUCH++;
            missileElement.setVisible(true);
            ShipParts shipPart = (ShipParts) target;
            ((ShipParts) target).setColor(2);
            shipPart.destroy();
            System.out.println("\n\n\nTouch\n\n\n");
            if(isFirstPlayer) {
                stage.addPlayer1Point(1);
            } else {
                stage.addPlayer2Point(1);
            }
        } else {
            missileElement = stage.getMissilesMiss()[MISSILE_MISS];
            MISSILE_MISS++;
            missileElement.setVisible(true);
            System.out.println("\n\n\nMiss\n\n\n");
        }

        if (!attackBoard.canReachCell(rowDest, colDest)) {
            return false;
        }

        // Place the missile on the attack board
        ActionList actions = ActionFactory.generatePutInContainer(model, missileElement, attackBoard.getName(), rowDest,
                colDest);
        actions.setDoEndOfTurn(true);
        ActionPlayer play = new ActionPlayer(model, this, actions);
        play.start();
        return true;
    }
}

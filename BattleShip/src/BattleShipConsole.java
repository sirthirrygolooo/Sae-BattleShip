import boardifier.control.Logger;
import boardifier.model.GameException;
import boardifier.view.View;
import control.BattleShipController;

import boardifier.control.StageFactory;
import boardifier.model.Model;
import java.util.Scanner;


public class BattleShipConsole {
    /**
     * Main method to launch the game
     * @param args
     */
    public static String[] filePositions = new String[4];
    public static void main(String[] args) {
        Logger.setLevel(Logger.LOGGER_TRACE);
        Logger.setVerbosity(Logger.VERBOSE_HIGH);
        int mode = 0;
        boolean useFile = false;
        System.out.println("args length: " + args.length);
        if (args.length == 1) {
            try {
                mode = Integer.parseInt(args[0]);
                if ((mode < 0) || (mode > 2)) mode = 0;
                System.out.println("mode: " + mode);
            } catch (NumberFormatException e) {
                mode = 0;
            }
        } else if (args.length == 3 && args[1].equals("<")) {
            useFile = true;
            int count = 0;
            mode = 0;
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                filePositions[count] = scanner.nextLine();
                count++;
            }
        }
        Model model = new Model();
        if (mode == 0) {
            model.addHumanPlayer("player1");
            model.addHumanPlayer("player2");
        }
        else if (mode == 1) {
            model.addHumanPlayer("player");
            model.addComputerPlayer("computer");
        }
        else if (mode == 2) {
            model.addComputerPlayer("computer1");
            model.addComputerPlayer ("computer2");
        }



        StageFactory.registerModelAndView("BattleShip", "model.StageModel", "view.BattleShipStageView");
        View battleShipView = new View(model);
        BattleShipController control = new BattleShipController(model,battleShipView);
        control.setFirstStageName("BattleShip");
        try {
            control.startGame();
            control.stageLoop();
        }
        catch(GameException e) {
            System.out.println("Game does not start correctly.");
        }
    }
}

package model;

import boardifier.control.Logger;
import boardifier.model.GameStageModel;
import boardifier.model.ContainerElement;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;


public class BattleShipBoard extends ContainerElement{
    /**
     * Create a new BattleShipBoard
     * @param name the name of the board
     * @param x the x position of the board
     * @param y the y position of the board
     * @param gameStageModel the gameStageModel
     */
    public BattleShipBoard(String name, int x, int y, GameStageModel gameStageModel) {
        // call the super-constructor to create a 10x10 grid, named "BattleShip Board", and in x,y in space
        super(name, x, y, 10 , 10, gameStageModel);
    }

    /**
     * Set the cells that are reachable by the player
     * @param number the number of cells that are reachable
     */
    public void setValidCells(int number) {
        Logger.debug("called",this);
        resetReachableCells(false);
        java.util.List<Point> valid = computeValidCells(number);
        if (valid != null) {
            for(Point p : valid) {
                reachableCells[p.y][p.x] = true;
            }
        }
    }

    /**
     * Compute the cells that are reachable by the player
     * @param number the number of cells that are reachable
     * @return the list of reachable cells
     */
    public java.util.List<Point> computeValidCells(int number) {
        List<Point> lst = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (reachableCells[j][i]) {
                    lst.add(new Point(i,j));
                }
            }
        }
        return lst;
    }

    /**
     * Set the x position of the board
     * @param x the x position of the board
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the y position of the board
     * @param y the y position of the board
     */
    public void setY(int y) {
        this.y = y;
    }
}

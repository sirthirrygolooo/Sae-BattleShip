package model;

import boardifier.control.Logger;
import boardifier.model.GameStageModel;
import boardifier.model.ContainerElement;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class BattleShipBoard extends ContainerElement {
    /**
     * BattleShip main board represent the element where missile are shoot when played
     * @param x
     * @param y
     * @param gameStageModel
     */
    public BattleShipBoard(int x, int y, GameStageModel gameStageModel) {
        // call the super-constructor to create a 10x10 grid, named "battleboard", and in x,y in space
        super("battleboard", x, y, 10 , 10, gameStageModel);
    }

    /**
     * Permit to set the valid cells to shoot in the board with a given value
     * @param number
     */
    public void setValidCells(int number) {
        resetReachableCells(false);
        List<Point> valid_player1 = computeValidCells(number);
        List<Point> valid_player2 = computeValidCells(number);
        if (valid_player1 != null) {
            for(Point p : valid_player1) {
                reachableCells[p.y][p.x] = true;
            }
        }
        if (valid_player2 != null) {
            for(Point p : valid_player2) {
                reachableCells[p.y][p.x] = true;
            }
        }
    }


    public List<Point> computeValidCells(int number) {
        List<Point> lst = new ArrayList<>();
        Pawn p = null;
        // if the grid is empty, is it the first turn and thus, all cells are valid
        if (isEmpty()) {
            // i are rows
            for(int i=0;i<10;i++) {
                // j are cols
                for (int j = 0; j < 10; j++) {
                    // cols is in x direction and rows are in y direction, so create a point in (j,i)
                    lst.add(new Point(j,i));
                }
            }
            return lst;
        }
        // else, take each empty cell and check if it is valid
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                if (isEmptyAt(i,j)) {
                    // check adjacence in row-1
                    if (i-1 >= 0) {
                        if (j-1>=0) {
                            p = (Pawn)getElement(i-1,j-1);

                            // check if same parity
                            if ((p != null) && ( p.getNumber()%2 == number%2)) {
                                lst.add(new Point(j,i));
                                continue; // go to the next point
                            }
                        }
                        p = (Pawn)getElement(i-1,j);
                        if ((p != null) && ( p.getNumber()%2 == number%2)) {
                            lst.add(new Point(j,i));
                            continue; // go to the next point
                        }
                        if (j+1<3) {
                            p = (Pawn)getElement(i-1,j+1);
                            if ((p != null) && ( p.getNumber()%2 == number%2)) {
                                lst.add(new Point(j,i));
                                continue; // go to the next point
                            }
                        }
                    }
                    // check adjacence in row
                    if (j-1>=0) {
                        p = (Pawn)getElement(i,j-1);
                        if ((p != null) && ( p.getNumber()%2 == number%2)) {
                            lst.add(new Point(j,i));
                            continue; // go to the next point
                        }
                    }
                    if (j+1<3) {
                        p = (Pawn)getElement(i,j+1);
                        if ((p != null) && ( p.getNumber()%2 == number%2)) {
                            lst.add(new Point(j,i));
                            continue; // go to the next point
                        }
                    }
                    // check adjacence in row+1
                    if (i+1<3) {
                        if (j - 1 >= 0) {
                            p = (Pawn) getElement(i + 1, j - 1);
                            if ((p != null) && (p.getNumber() % 2 == number % 2)) {
                                lst.add(new Point(j, i));
                                continue; // go to the next point
                            }
                        }
                        p = (Pawn) getElement(i + 1, j);
                        if ((p != null) && (p.getNumber() % 2 == number % 2)) {
                            return lst;
                        }
                    }
                }
            }
        }
        return lst;
    }
}

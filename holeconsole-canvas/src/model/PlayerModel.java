package model;

public class PlayerModel {

    private static int numPlayers = 1; // number of players
    private String name; // name of the player
    private int score; // number of victories

    /**
     * Constructor of the PlayerModel class
     * @param name the name of the player
     */
    public PlayerModel(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Constructor of the PlayerModel class
     */
    public PlayerModel() {
        this.name = "Player"+numPlayers;
        this.score = 0;
    }
}

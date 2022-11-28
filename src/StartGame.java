import Card.*;
import Game.GameModel;
import Player.Player;
import Tools.InputValidation;

import java.util.*;

public class StartGame {
    GameModel gm;
    InputValidation inputValidationTool;

    /**
     * 1. Read user input
     * 2. Get the number of players
     * 3. Get players' names and sort them
     * 4. Get the points that need to win
     */
    public void setup() {
        int num = 0, points = 0;

        ArrayList<Player> players = new ArrayList<>(num);

        System.out.println("Please enter the number of players:");
        boolean isNum = false;
        while (!isNum) {
            String input = inputValidationTool.readUser();
            if (inputValidationTool.inputValidation(input, "NUM")) {
                num = Integer.parseInt(input);
                isNum = true;
            } else {
                System.out.println("Input wrong. Please enter again.");
            }
        }

        for (int i = 1; i <= num; i++) {
            System.out.println("Please enter the name of player " + i + ":");
            boolean isName = false;
            while (!isName) {
                String input = inputValidationTool.readUser();
                if (inputValidationTool.inputValidation(input, "NAME")) {
                    String name = input;
                    players.add(new Player(Optional.ofNullable(name)));
                    isName = true;
                } else {
                    System.out.println("Input wrong. Please enter again.");
                }
            }
        }

        System.out.println("Please enter the points need to win:");
        boolean isPoints = false;
        while (!isPoints) {
            String input = inputValidationTool.readUser();
            if (inputValidationTool.inputValidation(input, "POINTS")) {
                points = Integer.parseInt(input);
                isPoints = true;
            } else {
                System.out.println("Input wrong. Please enter again.");
            }
        }

        gm = GameModel.getInstance(Optional.ofNullable(players), Optional.ofNullable(points));
        gm.sortOrder();
    }

    /**
     * @return names of players as string[]
     */
    public String[] play() {
        String winner[] = new String[gm.getPlayers().size()];
        // The round is continued until each player has the same number of turns
        for (int i = 0; i < gm.getPlayers().size(); i++) {

            //Get the current player
            int currentPlayScore = gm.getCurrentPlayerPoint(i);
            String currentPlayerName = gm.getCurrentPlayerName(i);

            System.out.println("----------Player " + (i + 1) + ": " + currentPlayerName + " turn ------------------------------------");

            // All points gained from dice
            int playPoints = 0;

            // If nextPlay=true, pass the dice to the next player
            boolean nextPlay = false;

            while (!nextPlay) {
                System.out.println("Choose Roll the dice or Display the current score(entering R or D):");
                String input = inputValidationTool.readUser();
                boolean isContinuous = true; //If continuous after TUTTO
                boolean isNullability = false; //If there's nullability in this round
                if (input.equals("R")) {
                    boolean isPM = false;
                    while (isContinuous) {
                        // Draw card randomly
                        Card card = gm.drawCard();
//                        Card card=new StraightCard(Optional.ofNullable(Suit.STRAIGHT));
                        if (card.getSuit() == Suit.BONUS) {
                            System.out.println("You have drawn Bonus Card, the bonus points are " + ((BonusCard) card).getBonus());
                        } else {
                            System.out.println("You have drawn " + card.getSuit() + " Card.");
                        }

                        // Deal with PM Card-------------------------------------
                        if (card.getSuit() == Suit.PM) {
                            isPM = true;
                        }

                        if (card.getSuit() == Suit.LEAF) {
                            if (gm.leaf_isWin(card)) {
                                winner[i] = currentPlayerName;
                                break;
                            }
                        }

                        //Play game
                        Optional<Integer> pointsFromCard = gm.playGame(card);
                        // Null
                        if (pointsFromCard.isEmpty()) {
                            // If null, reset all playpoints this round
                            playPoints = 0;
                            isNullability = true;
                            break;
                        } else {
                            playPoints = pointsFromCard.get() + playPoints;
                        }

                        isContinuous = gm.isContinuous(card);
                        //System.out.println("Value of isContinuous - "+isContinuous);
                    }

                    //If current player is not leading player, deduct 1000 for leading player
                    if (isPM && (!isNullability)) {
                        List<String> leadingPlay = gm.getLeadingPlayers();
                        for (int l = 0; l < leadingPlay.size(); l++) {
                            if (!leadingPlay.get(l).equals(currentPlayerName)) {
                                gm.setCurrentPlayerPointByName(currentPlayScore - 1000, leadingPlay.get(l));
                            }
                        }
                    }

                    currentPlayScore = currentPlayScore + playPoints;
                    gm.setCurrentPlayerPointByName(currentPlayScore, currentPlayerName);
                    System.out.println("Your current score is: " + currentPlayScore);
                    nextPlay = true;

                    // choose display ------------------------------------------------------------------------------------------------
                } else if (input.equals("D")) {
                    nextPlay = false;
                    System.out.println("Your current score is: " + currentPlayScore);
                } else {
                    nextPlay = false;
                    System.out.println("Input wrong. Please enter again.");
                }
            }

            if (currentPlayScore >= gm.getWinningPoints()) {
                winner[i] = currentPlayerName;
            }
        }

        return winner;
    }

    public static void main(String[] args) {
        StartGame startGame = new StartGame();
        // Get user input and initialize the game
        startGame.setup();

        boolean isFinish = false;
        while (!isFinish) {
            String[] winner = startGame.play();
            if (winner.length == 0) {
                isFinish = false;
            } else {
                System.out.println("Game end! Winner is " + Arrays.toString(winner));
                isFinish = true;
            }
        }
    }
}

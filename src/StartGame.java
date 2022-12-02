import Card.LeafCard;
import Game.GameModel;
import Tools.InputValidation;

import java.util.*;

public class StartGame {
    GameModel gm;
    InputValidation inputValidationTool = new InputValidation();

    /**
     * 1. Read user input
     * 2. Get the number of players
     * 3. Get players' names and sort them
     * 4. Get the points that need to win
     */
    public void setup() {
        int num = 0, points = 0;

        gm = GameModel.getInstance(Optional.ofNullable(points));

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
                    gm.addPlayer(name);
                    isName = true;
                } else {
                    System.out.println("Input wrong. Please enter again.");
                }
            }
        }

        System.out.println("Please enter the points need to win (Integer):");
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
        gm.setPoints(Optional.ofNullable(points));
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
                    boolean isLeaf=false;
                    while (isContinuous) {
                        // Draw card randomly
                        gm.drawCard();
//                        Card card=new LeafCard();

                        if (gm.getClassName().equals("BonusCard")) {
                            System.out.println("You have drawn Bonus Card, the bonus points are " + gm.getBonus());
                        } else {
                            System.out.println("You have drawn " + gm.getClassName() + " Card.");
                        }

                        //Play game
                        Optional<Integer> pointsFromCard = gm.playGame();

                        // Deal with PM Card-------------------------------------
                        if (gm.getClassName().equals("PMCard")) {
                            isPM = true;
                        }

                        if (gm.getClassName().equals("LeafCard")) {
                            if (pointsFromCard.get()==99999) {
                                winner[i] = currentPlayerName;
                                isLeaf=true;
                                break;
                            }
                        }

                        // Null
                        if (pointsFromCard.isEmpty()) {
                            // If null, reset all playpoints this round
                            playPoints = 0;
                            isNullability = true;
                            break;
                        } else {
                            playPoints = pointsFromCard.get() + playPoints;
                        }

                        isContinuous = gm.isContinuous();
                        if(isContinuous){
                            System.out.println("You chose continue after TUTTO. Draw card again.");
                        }

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
                    if(!isLeaf) {
                        System.out.println("Your current score is: " + currentPlayScore);
                    }else{
                        System.out.println("You win! Please wait for the end of this round.");
                    }
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

            List<String> w = new ArrayList<>();
            for(int i=0;i<winner.length;i++){
                if(winner[i]!=null){
                    w.add(winner[i]);
                }
            }

            if (w.size()==0) {
                isFinish = false;
            } else {
                String str = "";
                for(int i=0;i<w.size();i++){
                    str = str+w.get(i);
                }
                System.out.println("Game end! Winner is " + str);
                isFinish = true;
            }
        }
    }
}

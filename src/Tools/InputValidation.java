package Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class InputValidation {
    /**
     * Get user input from terminal
     * @return
     */
    public String readUser(){
        String inputLine = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            inputLine = in.readLine();
            if (inputLine.length() == 0)
                return null;
        }catch ( IOException e){
            System.out.println("IOException: "+e);
        }

        return inputLine;
    }

    /**
     * Input validation:
     * 1. validate the number of players: int, 2-4
     * 2. validate names
     * 3. validate the points need to win: int>=0
     * @param input
     *        type: NUM, NAME, POINTS
     * @return
     */
    public boolean inputValidation(String input, String type){
        if (input == null) {
            return false;
        }
        switch (type) {
            case "NUM":
                if (input.equals(" ") || input.length() != 1) {
                    return false;
                }
                for (int i = 0; i < input.length(); i++) {
                    if (!Character.isDigit(input.charAt(i))) {
                        return false;
                    }
                }
                int numOfPlayer = Integer.parseInt(input);
                if (numOfPlayer < 2 || numOfPlayer > 4) {
                    return false;
                }
                break;
            case "NAME":
                if (input.contains(" ")) {
                    return false;
                }
                break;
            case "POINTS":
                for (int i = 0; i < input.length(); i++) {
                    if (!Character.isDigit(input.charAt(i))) {
                        return false;
                    }
                }
                int points = Integer.parseInt(input);
                if (points < 0) {
                    return false;
                }
                break;
            default:
                return false;
        }

        return true;
    }

    /**
     * validate the select index: int<=len+1 and int>=1
     * @param input
     * @param len
     * @return
     */
    public boolean validateSelectNum(String input, int len){
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        int inputNum = Integer.parseInt(input);
        if (inputNum < 1 || inputNum > len + 1) {
            return false;
        }
        //input为用户输入的选择keep的骰子编号，从1开始
        //首先验证是否为数字，其次数字范围
        return true;
    }

    public boolean validateSelectStraight(List<Integer> input, List<Integer> allValidDice){
        if (input == null) {
            return false;
        }

        return allValidDice.containsAll(input);
    }

}

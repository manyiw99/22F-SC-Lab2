package Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InputValidation {
    /**
     * Get user input from terminal
     * @return
     */
    public static String readUser(){
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
    public static boolean inputValidation(String input, String type){

        return true;
    }

    /**
     * validate the select index: int<=len+1 and int>=1
     * @param input
     * @param len
     * @return
     */
    public static boolean validateSelectNum(String input, int len){
        //input为用户输入的选择keep的骰子编号，从1开始
        //首先验证是否为数字，其次数字范围
        return true;
    }

}

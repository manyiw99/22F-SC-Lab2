package Tools;

import java.util.*;

import static Tools.InputValidation.*;

public class DiceCalculation extends DiceCalculationAll {


    /**
     * Count all the valid dice from the input and calculate the final points
     *
     * @param dice
     * @return
     */
    public static int calculateSingleDices(int[] dice, int result) {
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == 1) {
                result += 100;
            } else {
                result += 50;
            }
            dice[i] = 0;
        }
        return result;
    }

    public static int calculateThreeDices(int[] dice, int result) {
        var counter = count(dice);

        // find some value that occurs three times and replace it by 0
        int j = 0, found = 0, value = -1;
        for (; j < dice.length; j++) {
            if (counter[dice[j]] >= 3) {
                value = dice[j];
                dice[j] = 0;
                found = 1;
                break;
            }
        }

        if (value == -1) {
            System.out.println("no value occurs three times");
            return -1;
        }
        ;

        // replace two other occurrences of value in dice with 0
        for (; j < dice.length && found < 3; j++) {
            if (dice[j] == value) {
                dice[j] = 0;
                found++;
            }
        }
        return result + ((value == 1) ? 1000 : value * 100);
    }

    public static boolean exitThreeDices(int[] dice) {
        int[] counter = count(dice);
        for (int i = 1; i < 8; i++) {
            if (counter[i] == 3) {
                return true;
            }
        }
        return false;
    }

    public static int calculatePoints(int[] dice) {
        int len = dice.length, point = 0;
        if (len == 1 || len == 2) {
            point = calculateSingleDices(dice, point);
        } else {
            int sum = Arrays.stream(dice).sum();
            while (sum != 0) {
                boolean existThree = exitThreeDices(dice);
                if (existThree) {
                    calculateThreeDices(dice, point);
                } else {
                    calculateSingleDices(dice, point);
                }
                sum = Arrays.stream(dice).sum();
            }
        }
        return point;


    }
    // 最后返回的是输入的骰子可以获得的最大points，dice有可能是六位也有可能是12345位

}

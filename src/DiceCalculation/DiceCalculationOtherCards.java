package DiceCalculation;

import Tools.InputValidation;

import java.util.*;

public class DiceCalculationOtherCards extends DiceCalculation {

    public DiceCalculationOtherCards(InputValidation inputValidation) {
        super(inputValidation);
    }

    public List<String> formatSelectedInput(String input) {
        List<String> formatInput = new ArrayList<>();
        int length = 0;
        while (length < input.length()) {
            //System.out.println("length===="+length);
            int index = input.indexOf("],[", length);

            if (index != -1) {
                String sub = input.substring(length, index + 1);
                formatInput.add(sub);
                length = index + 2;
            } else {
                String sub = input.substring(length);
                formatInput.add(sub);
                length = input.length();
            }
        }
        return formatInput;
    }

    public List<int[]> selectDice(int[] dice) {
        List<int[]> selectedDice = new ArrayList<>();
        boolean isSelected = false;
        while (!isSelected) {
            System.out.println("Please enter the valid dice you want to keep( eg. [1],[2,2,2],[5] ): ");
            String selectedInput = inputValidation.readUser();
            if (validateSelectedDice(selectedInput, dice)) {
                String[] dices = null;
                if (selectedInput.contains("],[")) {
                    List<String> formatInput = formatSelectedInput(selectedInput);
                    dices = formatInput.toArray(new String[formatInput.size()]);

                } else {
                    dices = new String[]{selectedInput};
                }

                //System.out.println(Arrays.toString(dices));
                for (int i = 0; i < dices.length; i++) {
                    //System.out.println("输入逗号分割 - "+ dices[i].length()+ " "+ dice[i]);
                    if (dices[i].length() == 3) {
                        int[] formatDice = new int[1];
                        formatDice[0] = Integer.parseInt(dices[i].substring(1, 2));
                        selectedDice.add(formatDice);
                    } else if (dices[i].length() == 7) {
                        int[] formatDice = new int[3];
                        formatDice[0] = Integer.parseInt(dices[i].substring(1, 2));
                        formatDice[1] = Integer.parseInt(dices[i].substring(1, 2));
                        formatDice[2] = Integer.parseInt(dices[i].substring(1, 2));
                        selectedDice.add(formatDice);
                    } else {
                        System.out.println("Your input is invalid dice, please enter again.");
                        break;
                    }

                    isSelected = true;
                }
                //selectedDice = allValidDice.get(Integer.parseInt(selectedInput) - 1);

            } else {
                System.out.println("Your input is invalid dice, please enter again.");
            }
        }

        return selectedDice;
    }

    /**
     * 1. not null, not empty
     * 2. isdigit()
     * 3. int[] format, split by comma
     * 4. overall length < dice.length()
     * 5. valid
     *
     * @param input
     * @param dice
     * @return
     */
    public boolean validateSelectedDice(String input, int[] dice) {
        // System.out.println("test(manyi) - "+input);   // OK
        if (input == null || !input.contains("[")) {
            return false;
        }
        int[] counter = count(dice);
        List<String> separatedInput = formatSelectedInput(input);
        //System.out.println(separatedInput);
        for (String i : separatedInput) {
            //System.out.println(i);
            if (i.length() == 3) {
                char c = i.charAt(1);
                if ((c != '1' && c != '5') || counter[Character.getNumericValue(c)] < 1) {
                    return false;
                }
            } else if (i.length() == 7) {
                StringBuilder sb = new StringBuilder();
                for (int j = 1; j < i.length(); j += 2) {
                    sb.append(i.charAt(j));
                }
                String value = sb.toString();
                //System.out.println(value);
                char target = value.charAt(0);
                for (int j = 1; j < value.length(); j++) {
                    if (value.charAt(j) != target) {
                        return false;
                    }
                    if (counter[Character.getNumericValue(target)] < 3) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        return true;
    }

    int[] count(int[] dice) {
        var counter = new int[7];
        for (int i = 0; i < dice.length; i++) {
            counter[dice[i]] += 1;
        }
        return counter;
    }

    /**
     * Return all possible combinations of valid dice
     *
     * @param dice
     * @return
     */

    public List<Integer> allValidValue(int[] dice) {
        List<Integer> pos = new ArrayList<Integer>();
        int[] counter = count(dice);
        for (int i = 1; i < 7; i++) {
            if (counter[i] >= 3 || ((i == 1 || i == 5) && counter[i] > 0)) {
                int size = counter[i];
                for (int j = 0; j < size; j++) {
                    pos.add(i);
                }
            }
        }
        return pos;
    }


    /**
     * Count all the valid dice from the input and calculate the final points
     *
     * @param dice
     * @return
     */
    int calculateSingleDices(int[] dice, int result) {
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == 1) {
                result += 100;
            } else {
                result += 50;
            }
//            dice[i] = 0;
        }
        return result;
    }

    int calculateThreeDices(int[] dice, int result) {
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

    boolean exitThreeDices(int[] dice) {
        int[] counter = count(dice);
        for (int i = 1; i < 7; i++) {
            if (counter[i] == 3) {
                return true;
            }
        }
        return false;
    }

    // Use of overload
    public int calculatePoints(List<int[]> dice) {
        int point = 0;
        for (int[] i : dice) {
            if (i.length == 1) {
                point = calculateSingleDices(i, point);
            } else {
                point = calculateThreeDices(i, point);
            }
        }
        return point;
    }

    public int calculatePoints(int[] dice) {
        int point = 0;
        boolean existThree = exitThreeDices(dice);
        while (existThree) {
            point += calculateThreeDices(dice, point);
            existThree = exitThreeDices(dice);
        }
        for (int i : dice) {
            if (i == 1) {
                point += 100;
            } else if (i == 5) {
                point += 50;
            }
        }
        return point;
    }

    /**
     * Check if the dice valid
     *
     * @param dice
     * @return
     */
    public boolean isValidate(Optional<int[]> dice) {
        //return true as long as the dice is not null
        int[] result = new int[6];
        if (dice.isPresent()) {
            result = dice.get();
        }
        int[] counter = count(result);
        for (int i = 1; i < 7; i++) {
            if (i == 1 || i == 5) {
                if (counter[i] > 0) {
                    return true;
                }
            } else {
                if (counter[i] >= 3) {
                    return true;
                }
            }
        }
        return false;
    }


}

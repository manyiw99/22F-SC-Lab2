package Tools;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputValidationTest {
    Tools.InputValidation inputValidation = new Tools.InputValidation();

    @Test
    public void validInputValidationNUM(){
        String type = "NUM";
        String input = "3";
        assertEquals(true, inputValidation.inputValidation(input, type));
    }

    @Test
    public void validInputValidationNAME(){
        String type = "NAME";
        String input = "duanran";
        assertEquals(true, inputValidation.inputValidation(input, type));
    }

    @Test
    public void validInputValidationPOINTS(){
        String type = "POINTS";
        String input = "150";
        assertEquals(true, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidInputValidationNULL(){
        String type = "NUM";
        String input = "";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidInputValidationNUM(){
        String type = "NUM";
        String input = "6";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidLengthValidationNUM(){
        String type = "NUM";
        String input = "6g";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidSpaceInputValidationNUM(){
        String type = "NUM";
        String input = " ";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidCharacterInputValidationNUM(){
        String type = "NUM";
        String input = "s";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidInputValidationType(){
        String type = "NUME";
        String input = "6";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidInputValidationNAME(){
        String type = "NAME";
        String input = " f";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidInputValidationPOINTS(){
        String type = "POINTS";
        String input = "34g5";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void invalidNegInputValidationPOINTS(){
        String type = "POINTS";
        String input = "-100";
        assertEquals(false, inputValidation.inputValidation(input, type));
    }

    @Test
    public void readUser(){
        InputValidation inputValidation1 = mock(InputValidation.class);
        when(inputValidation1.readUser()).thenReturn("[1]");
        String input = "[1]";
        assertEquals(input, inputValidation1.readUser());
    }

    @Test
    public void trueValidateSelectedStraight() {
        List<Integer> input = new ArrayList<>();
        input.add(1);

        List<Integer> allValidDice = new ArrayList<>();
        allValidDice.add(1);
        allValidDice.add(2);

        assertTrue(inputValidation.validateSelectStraight(input, allValidDice));
    }

    @Test
    public void falseValidateSelectedStraight() {
        List<Integer> input = new ArrayList<>();
        input.add(1);

        List<Integer> allValidDice = new ArrayList<>();
        allValidDice.add(3);
        allValidDice.add(2);

        assertFalse(inputValidation.validateSelectStraight(input, allValidDice));
    }

    @Test
    public void nullValidateSelectedStraight() {
        List<Integer> input = null;

        List<Integer> allValidDice = new ArrayList<>();
        allValidDice.add(3);
        allValidDice.add(2);

        assertFalse(inputValidation.validateSelectStraight(input, allValidDice));
    }
}

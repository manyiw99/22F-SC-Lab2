package Tool;

import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class InputValidation {
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

}

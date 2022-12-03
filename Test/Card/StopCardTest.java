package Card;

import DiceCalculation.DiceCalculationOtherCards;
import Tools.InputValidation;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class StopCardTest {
    DiceCalculationOtherCards diceTool = new DiceCalculationOtherCards(new InputValidation());
    InputValidation inputValidation = new InputValidation();
    StopCard stopCard = new StopCard(diceTool,inputValidation);

    @Test
    public void stopCardTest(){
        assertEquals(Optional.ofNullable(0),stopCard.playGame());
    }
}

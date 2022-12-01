package Card;

import Tools.DiceCalculationOtherCards;
import Tools.InputValidation;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class StopCardTest {
    DiceCalculationOtherCards diceTool = new DiceCalculationOtherCards();
    InputValidation inputValidation = new InputValidation();
    StopCard stopCard = new StopCard(Optional.ofNullable(Suit.STOP),diceTool,inputValidation);

    @Test
    public void stopCardTest(){
        assertEquals(Optional.ofNullable(0),stopCard.playGame());
    }
}

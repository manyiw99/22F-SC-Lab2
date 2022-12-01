package CardTest;

import Card.StraightCard;
import Card.Suit;
import Tools.DiceCalculationOtherCards;
import Tools.DiceCalculationStraight;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Optional;

public class BonusCardTest {

    private DiceCalculationOtherCards diceTool = mock(DiceCalculationOtherCards.class);

    @Test
    void tuttoTest(){
        int[] dice1 = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);
        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
    }

}

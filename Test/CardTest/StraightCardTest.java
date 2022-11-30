package CardTest;

import Card.StraightCard;
import Card.Suit;
import Tools.DiceCalculationAllCards;
import Tools.DiceCalculationStraight;
import Tools.InputValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

public class StraightCardTest {

    @Mock
    private DiceCalculationStraight diceCalculationStraight;

    @Mock
    private DiceCalculationAllCards diceCalculationAllCards;

    StraightCard straightCard = new StraightCard(Optional.of(Suit.STRAIGHT));
    public DiceCalculationStraight diceTool = new DiceCalculationStraight();
    int[] dice1 = {1,2,3,4,5,6};
    Optional<int[]> tuttoDice = Optional.of(dice1);

    @Test
    public void playGameTestTutto(){
//        InputStream stdin1 = System.in;
//        System.setIn(new ByteArrayInputStream("1,2,3".getBytes()));
//        InputStream stdin2 = System.in;
//        System.setIn(new ByteArrayInputStream("1,2,3".getBytes()));
//
//        straightCard.playGame();
//        System.setIn(stdin1);
//        System.setIn(stdin2);
//        when(generateDice(6)).thenReturn(tuttoDice);

        assertEquals(Optional.of(2000),straightCard.playGame());
    }
}

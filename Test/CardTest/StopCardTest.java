package CardTest;

import Card.StopCard;
import Card.Suit;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class StopCardTest {
    StopCard stopCard = new StopCard(Optional.ofNullable(Suit.STOP));

    @Test
    public void stopCardTest(){
        assertEquals(Optional.ofNullable(0),stopCard.playGame());
    }
}

package Card;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Mul2Card extends Card {
    public Mul2Card(Optional<Suit> suit) {
        super(suit);
    }

    @Override
    public int finalPoints(int playPoints){
        return playPoints * 2;
    }
}

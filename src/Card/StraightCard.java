package Card;

import java.util.Optional;

public class StraightCard extends Card{
    public StraightCard(Optional<Suit> suit) {
        super(suit);
    }
    @Override
    public int playGame() {
        return 0;
    }
}

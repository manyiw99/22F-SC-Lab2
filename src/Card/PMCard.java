package Card;

import java.util.Optional;

public class PMCard extends Card{
    public PMCard(Optional<Suit> suit) {
        super(suit);
    }
    @Override
    public int playGame() {
        return 0;
    }
}

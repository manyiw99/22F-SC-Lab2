package Card;

import java.util.Optional;

public class StopCard extends Card{
    public StopCard(Optional<Suit> suit) {
        super(suit);
    }

    /**
     * Next turn
     * @return
     */
    @Override
    public int playGame() {
        super.continuousAfterTutto=false;
        return 0;
    }
}

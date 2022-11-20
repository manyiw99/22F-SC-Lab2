package Card;

import java.util.Optional;

//Composite design pattern - leaf
public class FireworksCard extends Card{
    public FireworksCard(Optional<Suit> suit) {
        super(suit);
    }
    @Override
    public Optional<Integer> playGame() {

        super.continuousAfterTutto=false;
        return Optional.ofNullable(0);
    }
}

package Card;

import java.util.Optional;

//Composite design pattern - leaf
public class FireworksCard extends Card{
    public FireworksCard(Optional<Suit> suit) {
        super(suit);
    }
    @Override
    public int playGame() {
        return 0;
    }
}

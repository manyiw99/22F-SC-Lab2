package Card;

import java.util.Optional;

public class Mul2Card extends Card{
    public Mul2Card(Optional<Suit> suit) {
        super(suit);
    }
    @Override
    public int playGame() {
        return 0;
    }
}

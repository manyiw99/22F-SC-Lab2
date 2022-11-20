package Card;

import java.util.Optional;

// Prototype design pattern
public class LeafCard extends Card{

    public LeafCard(Optional<Suit> leaf) {
        super(leaf);
    }

    @Override
    public Optional<Integer> playGame() {
        return Optional.ofNullable(0);
    }

    public boolean isWin(){
        return true;
    }
}

package Card;

import java.util.Optional;

// Prototype design pattern
public class LeafCard extends Card{

    public LeafCard(Optional<Suit> leaf) {
        super(leaf);
    }

    @Override
    public int playGame() {
        return 0;
    }

    public boolean isWin(){
        return true;
    }
}

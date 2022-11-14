package Card;

import java.util.Hashtable;
import java.util.Optional;

public class CardCache {
    private static Hashtable<String, Card> cardMap = new Hashtable<String, Card>();

    public static Card getCard(String cardKey) {
        Card cachedCard = cardMap.get(cardKey);
        return (Card) cachedCard.clone();  //shallow clone
    }

    public static void loadCache() {
        cardMap.put("LeafCard",new LeafCard(Optional.ofNullable(Suit.LEAF)));
        cardMap.put("FireworksCard",new FireworksCard(Optional.ofNullable(Suit.FIREWORKS)));
        cardMap.put("StopCard",new StopCard(Optional.ofNullable(Suit.STOP)));
        cardMap.put("StraightCard", new StraightCard(Optional.ofNullable(Suit.STRAIGHT)));
        cardMap.put("PMCard",new PMCard(Optional.ofNullable(Suit.PM)));
        cardMap.put("Mul2Card",new Mul2Card(Optional.ofNullable(Suit.MUL2)));
        cardMap.put("Bonus200",new BonusCard(Optional.ofNullable(Suit.BONUS),200));
        cardMap.put("Bonus300",new BonusCard(Optional.ofNullable(Suit.BONUS),300));
        cardMap.put("Bonus400",new BonusCard(Optional.ofNullable(Suit.BONUS),400));
        cardMap.put("Bonus500",new BonusCard(Optional.ofNullable(Suit.BONUS),500));
        cardMap.put("Bonus600",new BonusCard(Optional.ofNullable(Suit.BONUS),600));
    }
}

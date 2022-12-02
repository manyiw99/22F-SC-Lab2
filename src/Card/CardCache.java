package Card;

import DiceCalculation.DiceCalculationOtherCards;
import DiceCalculation.DiceCalculationStraight;
import Tools.InputValidation;

import java.util.Hashtable;
import java.util.Optional;

public class CardCache {
    private static Hashtable<String, Card> cardMap = new Hashtable<String, Card>();

    public static Card getCard(String cardKey) {
        Card cachedCard = cardMap.get(cardKey);
        return (Card) cachedCard.clone();  //shallow clone
    }

    public static void loadCache() {
        cardMap.put("LeafCard",new LeafCard(Optional.ofNullable(Suit.LEAF), new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("FireworksCard",new FireworksCard(Optional.ofNullable(Suit.FIREWORKS), new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("StopCard",new StopCard(Optional.ofNullable(Suit.STOP), new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("StraightCard", new StraightCard(Optional.ofNullable(Suit.STRAIGHT), new InputValidation(), new DiceCalculationStraight()));
        cardMap.put("PMCard",new PMCard(Optional.ofNullable(Suit.PM), new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("Mul2Card",new Mul2Card(Optional.ofNullable(Suit.MUL2), new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("Bonus200",new BonusCard(Optional.ofNullable(Suit.BONUS),200, new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("Bonus300",new BonusCard(Optional.ofNullable(Suit.BONUS),300, new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("Bonus400",new BonusCard(Optional.ofNullable(Suit.BONUS),400, new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("Bonus500",new BonusCard(Optional.ofNullable(Suit.BONUS),500, new DiceCalculationOtherCards(), new InputValidation()));
        cardMap.put("Bonus600",new BonusCard(Optional.ofNullable(Suit.BONUS),600, new DiceCalculationOtherCards(), new InputValidation()));
    }
}

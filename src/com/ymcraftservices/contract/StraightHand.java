package com.ymcraftservices.contract;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.List;

public interface StraightHand {

    default boolean isFromTenToAs(Card card) {
        return Arrays.asList(NumberCard.AS, NumberCard.KING, NumberCard.DAME, NumberCard.VALLEE, NumberCard.DIX)
                .contains(card.getNumberCard());

    }

    default boolean areCardsStraightNotToAs(List<Card> cards) {
        int count = 0;
        for (int j = 1; j < cards.size(); j++) {
            count = getCount(cards, count, j);
            if (count == 4) {
                return true;
            }
        }

        return false;
    }

    default int getCount(List<Card> cards, int count, int i) {
        if (cards.get(i).getNumberCard().getNumber()
                == cards.get(i-1).getNumberCard().getNumber() + 1) {
            count++;
        } else {
            count = 0;
        }
        return count;
    }

    default Integer getStraightTo(List<Card> cards) {
        int count = 0;
        for (int j = 1; j < cards.size(); j++) {
            count = getCount(cards, count, j);
            if (count == 4) {
                return j+1;
            }
        }
        return -1;
    }
}

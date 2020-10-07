package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestDoublePairMessage {

    @Test
    public void should_return_this_message() {
        DoublePairMessage doublePairMessage = new DoublePairMessage();
        String doublePairCard1 = NumberCard.AS.toString();
        String doublePairCard2 = NumberCard.KING.toString();
        String kicker = NumberCard.QUEEN.toString();
        String message = doublePairMessage.apply(Arrays.asList(doublePairCard1, doublePairCard2,kicker));
        assertThat(message, is("double pair of (AS,KING) with QUEEN as a kicker"));
    }
}

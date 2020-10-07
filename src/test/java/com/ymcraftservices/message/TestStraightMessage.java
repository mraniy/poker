package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStraightMessage {

    @Test
    public void should_return_this_message() {
        String straightMessage = new StraightMessage().apply(Arrays.asList(NumberCard.KING.toString()));
        assertThat(straightMessage , is("Straight to KING"));
    }
}

package com.ymcraftservices;

import com.ymcraftservices.model.*;
import com.ymcraftservices.service.PokerGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestPokerGame {

    private Player sam;
    private Player joe;
    private Player brian;

    @BeforeEach
    public void beforeEach() {
        Card flop1 = new Card(LabelCard.CARREAU, NumberCard.CINQ);
        Card flop2 = new Card(LabelCard.CARREAU, NumberCard.AS);
        Card flop3 = new Card(LabelCard.TREFLE, NumberCard.TROIS);
        Card turn = new Card(LabelCard.CARREAU, NumberCard.DEUX);
        Card river = new Card(LabelCard.COEUR, NumberCard.VALLEE);
        Card samCard1 = new Card(LabelCard.PIQUE, NumberCard.DEUX);
        Card samCard2 = new Card(LabelCard.CARREAU, NumberCard.DAME);
        Card brianCard1 = new Card(LabelCard.COEUR, NumberCard.TROIS);
        Card briancard2 = new Card(LabelCard.PIQUE, NumberCard.TROIS);
        Card joeCard1 = new Card(LabelCard.CARREAU, NumberCard.SEPT);
        Card joeCard2 = new Card(LabelCard.CARREAU, NumberCard.VALLEE);
        List<Card> potCards = Arrays.asList(flop1, flop2, flop3, turn, river);
        List<Card> samCards = Arrays.asList(samCard1, samCard2);
        List<Card> brianCards = Arrays.asList(brianCard1, briancard2);
        List<Card> joeCards = Arrays.asList(joeCard1, joeCard2);
        Arrays.asList(joeCard1,joeCard2);
        sam = new Player("jackson","samuel",500,new Hand(potCards,samCards));
        joe = new Player("big","joe",600,new Hand(potCards,joeCards));
        brian = new Player("adams","brian",700,new Hand(potCards,brianCards));
    }

    @Test
    public void should_return_brian_as_winner_as_he_has_the_best_hand() {
        // given in the setup
        // when
        PokerGame pokerGame = new PokerGame();
        List<Player> winners = pokerGame.getWinner(Arrays.asList(joe, sam, brian));
        // assertThat
        assertThat(winners.get(0).getFirstName() , is(joe.getFirstName()));
    }
}

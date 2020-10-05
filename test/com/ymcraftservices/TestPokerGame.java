package com.ymcraftservices;

import com.ymcraftservices.model.*;
import com.ymcraftservices.service.PokerGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestPokerGame {

    private Player sam;
    private Player joe;
    private Player brian;


    @AfterEach
    public void sautdeligne() {
        System.out.println("\n");
    }

    @Test
    public void should_return_brian_as_winner_as_he_has_the_best_hand() {
        // given in the setup
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
        sam = new Player("jackson","samuel",500,new Hand(potCards,samCards));
        joe = new Player("big","joe",600,new Hand(potCards,joeCards));
        brian = new Player("adams","brian",700,new Hand(potCards,brianCards));
        // when
        PokerGame pokerGame = new PokerGame();
        List<Player> winners = pokerGame.getWinners(Arrays.asList(joe, sam, brian));
        // assertThat
        assertThat(winners.size(), is(1));
        assertThat(winners.get(0).getFirstName() , is(joe.getFirstName()));
    }

    @Test
    public void should_return_brian_as_winner_as_he_has_the_best_double_pair() {
        // given
        Card flop1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card flop2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card flop3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card turn = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEPT);
        Card river = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);

        Card samCard1 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card samCard2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);

        Card brianCard1 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card briancard2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);

        List<Card> brianCards = Arrays.asList(brianCard1, briancard2);
        List<Card> samCards = Arrays.asList(samCard1, samCard2);

        List<Card> potCards = Arrays.asList(flop1, flop2, flop3, turn, river);
        sam = new Player("jackson","samuel",500,new Hand(potCards,samCards));
        brian = new Player("adams","brian",700,new Hand(potCards,brianCards));

        // when
        PokerGame pokerGame = new PokerGame();
        List<Player> winners = pokerGame.getWinners(Arrays.asList(sam, brian));
        // assertThat
        assertThat(winners.size(), is(1));
        assertThat(winners.get(0).getFirstName() , is(brian.getFirstName()));
    }

    @Test
    public void should_return_brian_and_joe_are_winners() {
        // given in the setup
        Card flop1 = new Card(LabelCard.CARREAU, NumberCard.CINQ);
        Card flop2 = new Card(LabelCard.COEUR, NumberCard.AS);
        Card flop3 = new Card(LabelCard.TREFLE, NumberCard.TROIS);
        Card turn = new Card(LabelCard.CARREAU, NumberCard.DEUX);
        Card river = new Card(LabelCard.COEUR, NumberCard.VALLEE);

        Card samCard1 = new Card(LabelCard.PIQUE, NumberCard.DEUX);
        Card samCard2 = new Card(LabelCard.CARREAU, NumberCard.DAME);

        Card brianCard1 = new Card(LabelCard.COEUR, NumberCard.QUATRE);
        Card briancard2 = new Card(LabelCard.PIQUE, NumberCard.TROIS);

        Card joeCard1 = new Card(LabelCard.CARREAU, NumberCard.SEPT);
        Card joeCard2 = new Card(LabelCard.CARREAU, NumberCard.QUATRE);

        List<Card> potCards = Arrays.asList(flop1, flop2, flop3, turn, river);
        List<Card> samCards = Arrays.asList(samCard1, samCard2);
        List<Card> brianCards = Arrays.asList(brianCard1, briancard2);
        List<Card> joeCards = Arrays.asList(joeCard1, joeCard2);
        Arrays.asList(joeCard1,joeCard2);
        sam = new Player("jackson","samuel",500,new Hand(potCards,samCards));
        joe = new Player("big","joe",600,new Hand(potCards,joeCards));
        brian = new Player("adams","brian",700,new Hand(potCards,brianCards));
        // when
        PokerGame pokerGame = new PokerGame();
        List<Player> winners = pokerGame.getWinners(Arrays.asList(joe, sam, brian));
        // assertThat
        assertThat(winners.size(), is(2));
        assertThat(winners.containsAll(Arrays.asList(brian, joe)) , is(true));
    }
}

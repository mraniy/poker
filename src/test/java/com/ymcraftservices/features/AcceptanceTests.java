package com.ymcraftservices.features;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.Player;
import com.ymcraftservices.model.Pot;
import com.ymcraftservices.service.EarningUpdater;
import com.ymcraftservices.service.WinnerFinder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AcceptanceTests implements ContractStepDefs{


    protected Player aria = new Player("Stark","Aria",null, null);;

    protected Player littleFinger = new Player("Finger","Little",null, null);

    protected Player boltonRamsey =  new Player("Ramsey","Bolton",null, null);;

    protected List<Card> potCards;

    protected Pot pot;


    protected WinnerFinder winnerFinder = new WinnerFinder();

    protected EarningUpdater earningUpdater = new EarningUpdater();



    @Given("the flop , the turn and the river are")
    public void the_flop_the_turn_and_the_river_are(List<List<String>> cards) {
        potCards = retrieveCards(cards);
    }


    @Given("Players Earning at this moment are")
    public void players_earning_at_this_moment_are(List<List<String>> earningsByPlayer) {
        aria.setPotScore(retrieveEarning(earningsByPlayer, aria));
        littleFinger.setPotScore(retrieveEarning(earningsByPlayer, littleFinger));
        boltonRamsey.setPotScore(retrieveEarning(earningsByPlayer, boltonRamsey));
    }

    @Given("Aria has this hand")
    public void aria_has_this_hand(List<List<String>> cards) {
        List<Card> ariaCards = retrieveCards(cards);
        aria.setHand(new Hand(potCards,ariaCards));
    }


    @Given("LittleFinger has this hand")
    public void little_finger_has_this_hand(List<List<String>> cards) {
        List<Card> litllerFingerCards = retrieveCards(cards);
        littleFinger.setHand(new Hand(potCards,litllerFingerCards));
    }

    @Given("Bolton ramsey has this hand")
    public void bolton_ramsey_has_this_hand(List<List<String>> cards) {
        List<Card> boltonRamseyCards = retrieveCards(cards);
        boltonRamsey.setHand(new Hand(potCards,boltonRamseyCards));
    }

    @Given("the pot is {string}")
    public void the_pot_is(String potString) {
        pot = new Pot(Arrays.asList(aria,littleFinger,boltonRamsey), Integer.valueOf(potString));
    }




    @Then("both bolton and aria wins the tie with a straight to NINE")
    public void both_bolton_and_aria_wins_the_tie_with_a_straight_to_nine() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(2));
        assertThat(winners.containsAll(Arrays.asList(boltonRamsey,aria)), is(true));
    }

    @Then("little finger is the winner of the tie as he has queen as second best high card")
    public void little_finger_is_the_winner_of_the_tie_as_he_has_queen_as_second_best_high_card() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(littleFinger), is(true));
    }

    @Then("bolton ramsey is the winner of the tie as he has king ten that is best than seven king")
    public void bolton_ramsey_is_the_winner_of_the_tie_as_he_has_king_ten_that_is_best_than_seven_king() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(boltonRamsey), is(true));
    }

    @Then("little finger is the winner of the tie as he has queen as second kicker")
    public void little_finger_is_the_winner_of_the_tie_as_he_has_queen_as_second_kicker() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(littleFinger), is(true));
    }

    @Then("Aria is the winner of the tie as she has queen as kicker")
    public void aria_is_the_winner_of_the_tie_as_she_has_queen_as_kicker() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(aria), is(true));
    }

    @Then("LittleFinger is the winner of the tie as he has jack as kicker")
    public void little_finger_is_the_winner_of_the_tie_as_he_has_jack_as_kicker() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(littleFinger), is(true));
    }

    @Then("Aria is the winner of the tie as she has AS as last card")
    public void aria_is_the_winner_of_the_tie_as_she_has_as_as_last_card() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(aria), is(true));
    }

    @Then("LittleFinger is the winner of the tie as HE has JACK")
    public void little_finger_is_the_winner_of_the_tie_as_he_has_jack() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(littleFinger), is(true));
    }

    @Then("little finger is the winner of the tie as he has king as square")
    public void little_finger_is_the_winner_of_the_tie_as_he_has_king_as_square() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(littleFinger), is(true));
    }

    @Then("Aria is the winner of the tie as she has a royal flush")
    public void aria_is_the_winner_of_the_tie_as_she_has_a_royal_flush() {
        List<Player> winners = winnerFinder.getWinners(Arrays.asList(aria, littleFinger, boltonRamsey));
        assertThat(winners.size(), is(1));
        assertThat(winners.contains(aria), is(true));
    }

    @Then("Players Earnings become")
    public void players_earnings_become(List<List<String>> earningsByPlayer) {
        earningUpdater.updateEarnings(pot);
        Integer ariaNewEarning = retrieveEarning(earningsByPlayer, aria);
        Integer boltonNewEarning = retrieveEarning(earningsByPlayer, boltonRamsey);
        Integer littleNewEarning = retrieveEarning(earningsByPlayer, littleFinger);
        assertThat(aria.getPotScore(), is(ariaNewEarning));
        assertThat(boltonRamsey.getPotScore(), is(boltonNewEarning));
        assertThat(littleFinger.getPotScore(), is(littleNewEarning));
    }

}

package com.ymcraftservices.features;

import com.ymcraftservices.model.Player;
import com.ymcraftservices.model.Pot;
import com.ymcraftservices.service.EarningUpdater;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EarningFeature implements ContractStepDefs {

    private Player aria;

    private Player littleFinger;

    private Player boltonRamsey;

    private Pot pot;

    private List<Pot> pots;

    protected EarningUpdater earningUpdater = new EarningUpdater();

    @Given("Players Earning at this moment are")
    public void players_earning_at_this_moment_are(List<List<String>> earningsByPlayer) {
        aria = HandComparaisonFeature.getAria();
        littleFinger = HandComparaisonFeature.getLittleFinger();
        boltonRamsey = HandComparaisonFeature.getBoltonRamsey();
        littleFinger.setPotScore(retrieveEarning(earningsByPlayer, littleFinger));
        aria.setPotScore(retrieveEarning(earningsByPlayer, aria));
        boltonRamsey.setPotScore(retrieveEarning(earningsByPlayer, boltonRamsey));
    }

    @Given("pots regarding players are the following")
    public void pots_regarding_players_are_the_following(List<List<String>> potsAndPlayers) {
        pots = potsAndPlayers.subList(1, potsAndPlayers.size())
                .stream()
                .map(this::mapPlayersAndPots)
                .collect(Collectors.toList());
    }

    public Pot mapPlayersAndPots(List<String> playersAndPot) {
        List<String> playersFullName = Arrays.asList(playersAndPot.get(0).split(","));
        List<Player> players = playersFullName
                .stream()
                .map(this::getPlayer)
                .collect(Collectors.toList());
        return new Pot(players, Integer.parseInt(playersAndPot.get(1)));
    }

    public Player getPlayer(String playerFullName) {
        return Arrays.asList(aria, boltonRamsey, littleFinger)
                .stream()
                .filter(player -> player.getFullName().equals(playerFullName))
                .findFirst()
                .orElseGet(() -> null);
    }



    @Given("the pot is {string}")
    public void the_pot_is(String potString) {
        pot = new Pot(Arrays.asList(aria, littleFinger, boltonRamsey), Integer.valueOf(potString));
        pots = Arrays.asList(pot);
    }

    @Then("Players Earnings become")
    public void players_earnings_become(List<List<String>> earningsByPlayer) {
        pots.forEach(pot1 -> earningUpdater.updateEarnings(pot1));
        retrieveAndAssertEarning(earningsByPlayer);
    }



    private void retrieveAndAssertEarning(List<List<String>> earningsByPlayer) {
        Integer ariaNewEarning = retrieveEarning(earningsByPlayer, aria);
        Integer boltonNewEarning = retrieveEarning(earningsByPlayer, boltonRamsey);
        Integer littleNewEarning = retrieveEarning(earningsByPlayer, littleFinger);
        assertThat(aria.getPotScore(), is(ariaNewEarning));
        assertThat(boltonRamsey.getPotScore(), is(boltonNewEarning));
        assertThat(littleFinger.getPotScore(), is(littleNewEarning));
    }


}

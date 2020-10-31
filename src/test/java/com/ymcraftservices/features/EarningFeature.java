package com.ymcraftservices.features;

import com.ymcraftservices.model.Player;
import com.ymcraftservices.model.Pot;
import com.ymcraftservices.service.EarningUpdater;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EarningFeature implements ContractStepDefs {

    private Player aria;

    private Player littleFinger;

    private Player boltonRamsey;

    private Pot pot;

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

    @Given("the pot is {string}")
    public void the_pot_is(String potString) {
        pot = new Pot(Arrays.asList(aria, littleFinger, boltonRamsey), Integer.valueOf(potString));
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

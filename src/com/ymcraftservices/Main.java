package com.ymcraftservices;

import com.ymcraftservices.model.*;
import com.ymcraftservices.service.PokerGame;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        List<Card> cards = IntStream.range(0, 52).boxed()
                .collect(toShuffledList())
                .stream()
                .map(integer -> fromNumberToCard(integer))
                .collect(Collectors.toList());
        List<Card> potCards = cards.subList(0,5);
        System.out.println("the pot");
        potCards.forEach(System.out::println);
        System.out.println("\n\n\nSam cards");
        List<Card> samCards = cards.subList(5,7);
        samCards.forEach(System.out::println);
        System.out.println("\n\n\nJoe cards");
        List<Card> joeCards = cards.subList(7,9);
        joeCards.forEach(System.out::println);
        Player sam = new Player("jackson","samuel",500,new Hand(potCards,samCards));
        Player joe = new Player("big","joe",600,new Hand(potCards,joeCards));
        List<Player> winners = new PokerGame().getWinners(Arrays.asList(joe, sam));


    }

    private static Card fromNumberToCard(Integer digit) {
        LabelCard labelCard = null;
        if (digit / 13 == 0) labelCard = LabelCard.CARREAU;
        if (digit / 13 == 1) labelCard = LabelCard.COEUR;
        if (digit / 13 == 2) labelCard = LabelCard.TREFLE;
        if (digit / 13 == 3) labelCard = LabelCard.PIQUE;
        int value = digit % 13 +1;
        NumberCard numberCard = NumberCard.valueOf(value);
        return new Card(labelCard, numberCard);
    }

    private static final Collector<?, ?, ?> SHUFFLER = Collectors.collectingAndThen(
            Collectors.toCollection(ArrayList::new),
            list -> {
                Collections.shuffle(list);
                return list;
            }
    );

    @SuppressWarnings("unchecked")
    public static <T> Collector<T, ?, List<T>> toShuffledList() {
        return (Collector<T, ?, List<T>>) SHUFFLER;
    }
}

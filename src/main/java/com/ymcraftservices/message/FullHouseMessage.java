package com.ymcraftservices.message;

import java.util.List;
import java.util.function.Function;

public class FullHouseMessage implements Function<List<String>, String> {

    final String message = "Full House from %s To %s";
    @Override
    public String apply(List<String> params) {
        return String.format(message, params.get(0), params.get(1));
    }
}

package com.ymcraftservices.features;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableTransformer;
import lombok.Data;

import java.util.List;
import java.util.Locale;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }


    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(
                Card.class,
                (TableTransformer<Card>) table -> new Card(table.cells())));

    }

    @Data
    class Card {
        private List<List<String>> names;

        public Card(List<List<String>> names) {
            this.names = names;
        }
    }
}
package com.ymcraftservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Combination {

      private CombinationScore combinationScore;

      private Integer strenght;

      private String message;

      public Integer getScore() {
            return  getCombinationScore().getScore() + getStrenght();
      }



}

Feature: two players have square

  As a poker system , i should be able to retrieve the winner of two full squares(even if it s rare).

  Scenario: Creating players and their hands and do it in order to have multiple squares
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | SEVEN  |
      | TREFLE | KING   |
      | COEUR  | SEVEN  |
      | COEUR  | EIGHT  |

    And LittleFinger has this hand
      | label   | number |
      | PIQUE   | KING   |
      | CARREAU | KING   |

    And Bolton ramsey has this hand
      | label   | number |
      | TREFLE  | SEVEN  |
      | CARREAU | SEVEN  |

    And Aria has this hand
      | label  | number |
      | COEUR  | AS     |
      | TREFLE | TWO    |

    Then little finger is the winner of the tie as he has king as square

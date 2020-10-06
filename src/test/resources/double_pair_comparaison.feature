Feature: two players have same double pair , but one have stronger kicker

  As a poker system , i should be able to retrieve the winner of two double pairs.

  Scenario: Creating players and their hands and do it in order to have multiple double pairs
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | KING   |
      | TREFLE | AS     |
      | COEUR  | FIVE   |
      | COEUR  | EIGHT  |

    And LittleFinger has this hand
      | label | number |
      | PIQUE | TWO    |
      | COEUR | QUEEN  |

    And Bolton ramsey has this hand
      | label  | number |
      | TREFLE | AS     |
      | COEUR  | TEN    |

    And Aria has this hand
      | label  | number |
      | COEUR  | AS     |
      | TREFLE | QUEEN  |

    Then Aria is the winner of the tie as she has queen as kicker

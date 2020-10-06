Feature: two players have pair , but one have stronger kickers

  As a poker system , i should be able to retrieve the winner of two pairs.

  Scenario: Creating players and their hands and do it in order to have multiple pairs
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | SEVEN  |
      | TREFLE | AS     |
      | COEUR  | FIVE   |
      | COEUR  | EIGHT  |

    And LittleFinger has this hand
      | label | number |
      | PIQUE | KING   |
      | COEUR | QUEEN  |

    And Bolton ramsey has this hand
      | label  | number |
      | TREFLE | KING   |
      | COEUR  | TWO    |

    And Aria has this hand
      | label  | number |
      | COEUR  | TEN    |
      | TREFLE | SIX    |

    Then little finger is the winner of the tie as he has queen as second kicker

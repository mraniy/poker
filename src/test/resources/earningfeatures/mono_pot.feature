Feature: when a single player wins the pot

  As a poker system , i should be able to retrieve the winner of two high cards.

  Scenario: Creating players and their hands and do it in order to have multiple high cards
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | SEVEN  |
      | TREFLE | AS     |
      | COEUR  | FIVE   |
      | COEUR  | EIGHT  |

    And Players Earning at this moment are

      | Player        | earning |
      | Little Finger | 1000    |
      | Aria Stark    | 2000    |
      | Bolton Ramsey | 3000    |

    And the pot is "500"

    And LittleFinger has this hand
      | label | number |
      | PIQUE | JACK   |
      | COEUR | QUEEN  |

    And Bolton ramsey has this hand
      | label  | number |
      | TREFLE | THREE  |
      | COEUR  | SIX    |

    And Aria has this hand
      | label  | number |
      | COEUR  | TEN    |
      | TREFLE | SIX    |

    Then Players Earnings become

      | Player        | earning |
      | Little Finger | 1500    |
      | Aria Stark    | 2000    |
      | Bolton Ramsey | 3000    |


Feature: when we have a pot i should be able to handle it

  As a poker system , i should be able to handle mono pots

  Scenario: Creating players and their hands and do it in order to have multiple high cards
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | SEVEN  |
      | TREFLE | NINE   |
      | COEUR  | FIVE   |
      | COEUR  | EIGHT  |

    And Players Earning at this moment are

      | player        | earning |
      | Little Finger | 0       |
      | Aria Stark    | 0       |
      | Bolton Ramsey | 500     |

    And the pot is "1000"


    And LittleFinger has this hand
      | label | number |
      | PIQUE | JACK   |
      | PIQUE | SIX    |

    And Bolton ramsey has this hand
      | label  | number |
      | TREFLE | THREE  |
      | COEUR  | SIX    |

    And Aria has this hand
      | label  | number |
      | COEUR  | AS     |
      | TREFLE | SIX    |

    Then Players Earnings become

      | Player        | earning |
      | Little Finger | 333     |
      | Aria Stark    | 333     |
      | Bolton Ramsey | 833     |


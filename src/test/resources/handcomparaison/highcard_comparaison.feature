Feature: two players have just high card

  As a poker system , i should be able to retrieve the winner of two high cards.

  Scenario: Creating players and their hands and do it in order to have multiple high cards
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | SEVEN  |
      | TREFLE | AS   |
      | COEUR  | FIVE   |
      | COEUR  | EIGHT  |

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
      | COEUR  | TEN     |
      | TREFLE | SIX    |

    Then little finger is the winner of the tie as he has queen as second best high card

Feature: two players have both straight hands , but one have stronger last card

  As a poker system , i should be able to retrieve the winner of two straight hands.

  Scenario: Creating players and their hands and do it in order to have multiple straights
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | QUEEN  |
      | TREFLE | TEN    |
      | COEUR  | FIVE   |
      | COEUR  | EIGHT  |

    And LittleFinger has this hand
      | label | number |
      | PIQUE | JACK   |
      | COEUR | NINE   |

    And Bolton ramsey has this hand
      | label  | number |
      | TREFLE | KING   |
      | COEUR  | TEN    |

    And Aria has this hand
      | label  | number |
      | COEUR  | AS     |
      | TREFLE | JACK   |

    Then Aria is the winner of the tie as she has AS as last card

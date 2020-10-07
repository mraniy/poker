Feature: two players have both flush , but one have one stronger card

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
      | COEUR | JACK   |
      | COEUR | NINE   |

    And Bolton ramsey has this hand
      | label | number |
      | COEUR | THREE  |
      | COEUR | TEN    |

    And Aria has this hand
      | label  | number |
      | COEUR  | AS     |
      | TREFLE | JACK   |

    Then LittleFinger is the winner of the tie as HE has JACK

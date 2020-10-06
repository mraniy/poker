Feature: two players have same three of a kind , but one have stronger kicker

  As a poker system , i should be able to retrieve the winner of two three of a kind.

  Scenario: Creating players and their hands and do it in order to have multiple three of a kind
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | KING   |
      | TREFLE | AS     |
      | COEUR  | FIVE   |
      | COEUR  | EIGHT  |

    And LittleFinger has this hand
      | label | number |
      | PIQUE | KING   |
      | COEUR | JACK   |

    And Bolton ramsey has this hand
      | label  | number |
      | TREFLE | KING   |
      | COEUR  | TEN    |

    And Aria has this hand
      | label  | number |
      | COEUR  | AS     |
      | TREFLE | QUEEN  |

    Then LittleFinger is the winner of the tie as he has jack as kicker

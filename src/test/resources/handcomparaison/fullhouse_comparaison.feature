Feature: two players have full house

  As a poker system , i should be able to retrieve the winner of two full house.

  Scenario: Creating players and their hands and do it in order to have multiple full houses
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | SEVEN  |
      | TREFLE | KING   |
      | COEUR  | TEN    |
      | COEUR  | EIGHT  |

    And LittleFinger has this hand
      | label | number |
      | PIQUE | JACK   |
      | COEUR | QUEEN  |

    And Bolton ramsey has this hand
      | label | number |
      | PIQUE | KING   |
      | PIQUE | TEN    |

    And Aria has this hand
      | label  | number |
      | COEUR  | SEVEN  |
      | TREFLE | SEVEN  |

    Then bolton ramsey is the winner of the tie as he has king ten that is best than seven king

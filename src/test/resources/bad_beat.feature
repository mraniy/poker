Feature: bad beat : a straight flush  a royal flush and square

  As a poker system , i should be able to retrieve the winner of a bad beat.

  Scenario: Creating players and their hands and do it in order to have straight flush , royal flush and square
    When the flop , the turn and the river are
      | label  | number |
      | TREFLE | JACK   |
      | TREFLE | QUEEN  |
      | TREFLE | TEN    |
      | COEUR  | TEN    |
      | COEUR  | EIGHT  |

    And LittleFinger has this hand
      | label  | number |
      | TREFLE | NINE   |
      | TREFLE | EIGHT  |

    And Bolton ramsey has this hand
      | label   | number |
      | PIQUE   | TEN    |
      | CARREAU | TEN    |

    And Aria has this hand
      | label  | number |
      | TREFLE  | AS     |
      | TREFLE | KING   |

    Then Aria is the winner of the tie as she has a royal flush

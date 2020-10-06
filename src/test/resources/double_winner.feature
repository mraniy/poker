Feature: N players win the pot , here 2 players

  As a poker system , i should be able to retrieve multiple winners when it's the case.

  Scenario: Creating players and their hands and do it in order to have multiple winners
    When the flop , the turn and the river are
      | label  | number |
      | COEUR  | KING   |
      | PIQUE  | SEVEN  |
      | TREFLE | NINE   |
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
      | COEUR  | AS     |
      | TREFLE | SIX    |

    Then both bolton and aria wins the tie with a straight to NINE

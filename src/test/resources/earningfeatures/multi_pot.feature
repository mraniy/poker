Feature: when we have a lot of pots and each player wins one

  As a poker system , i should be able to retrieve the winner of two high cards.

  Scenario: Creating players and their hands and do it in order to have multiple high cards
    When the flop , the turn and the river are
      | label  | number |
      | TREFLE | JACK   |
      | TREFLE | QUEEN  |
      | TREFLE | TEN    |
      | COEUR  | TEN    |
      | COEUR  | EIGHT  |

    And Players Earning at this moment are

      | player        | earning |
      | Little Finger | 0       |
      | Aria Stark    | 0       |
      | Bolton Ramsey | 500     |

    And pots regarding players are the following

      | players                                | pots |
      | Little Finger,Aria Stark,Bolton Ramsey | 1000 |
      | Aria Stark,Bolton Ramsey               | 300  |

    And LittleFinger has this hand
      | label  | number |
      | TREFLE | AS     |
      | TREFLE | KING   |


    And Bolton ramsey has this hand
      | label   | number |
      | PIQUE   | TEN    |
      | CARREAU | TEN    |

    And Aria has this hand
      | label  | number |
      | TREFLE | NINE   |
      | TREFLE | EIGHT  |


    Then Players Earnings become

      | Player        | earning |
      | Little Finger | 1000    |
      | Aria Stark    | 300     |
      | Bolton Ramsey | 500     |


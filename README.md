Kata permettant de savoir le ou les joueurs ayant la meilleure main.

Projet réalisé en TDD , BDD , programmation fonctionnelle, implémentation de designs pattern en lambda .

L idée est de comparer des main d'une manière fluide et agréable : 

Chaque main a une combinaison, chaque combinaison représente un score, ensuite les cartes faisant la différence (kickers) sont utilisés pour calculer un score secondaire sur la formule suivante.

Supposons que les kickers d'une paire d as sont 10 9 7 le score intermédiaire serait de 10 * 100 + 9 * 10 + 7 = 1097 .

Chaque prédicat est séparé dans une classe différente et couvert par les tests , chaque calculateur de score est séparé dans une classe différente et couvert par les tests pour respecter le s et le o  de SOLID.

L enchaînement des predicats et des calculs se fait grâce au pattern chain of responsability qui permet de variabiliser les appels des prédicats et des fonctions d'une manière récursive.
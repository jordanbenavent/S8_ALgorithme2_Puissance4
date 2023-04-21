# S8_ALgorithme2_Puissance4

Durant ce projet de recherche, nous avons pour mission de développer une IA efficace pour joueur au jeu Puissance 4. Notre programme exposera une api REST conforme aux descriptions demandées. 
Pour mettre en oeuvre notre IA, nous avons décider d'utiliser l'algorithme MiniMax. Notre travail de recherche s'est effectué en 3 principales phases : 
* Elaboration de la fonction d'évaluation (heuristique)
* Implémentation de l'algorithme MiniMax (ainsi que test, optimisation)
* Implémentation de l'api REST, des codes d'erreurs, containerisation...


## Fonction d'évaluation 

Dans un jeu de Puissance 4, une heuristique permet d'évaluer la qualité d'un board pour un joueur donné. Plus cette valeur est élevée, plus la position est favorable pour le joueur. Dans ce rapport, nous allons expliquer comment nous calculons cette valeur heuristique (aussi appelé utility dans les consignes).

Nous allons procéder en calculant chaque pion de la couleur du joueur présent sur le board. Pour chaque pion, nous allons explorer toutes les directions (horizontale, verticale, diagonales) et additionner le nombre de pions alignés dans chaque direction. Nous allons ensuite soustraire à ce nombre les blocages causés par les pièces jaunes ou les bords du jeu. Par exemple, 3 pièces alignées verticalement auront une grande valeur, car cela indique une potentielle victoire, mais si un pion adverse est placé au-dessus de ces 3 pions, ils perdent leur valeur  dans l’alignement vertical car l’avantage de victoire disparait. Si un pion est entouré de pièces jaunes, sa contribution à la valeur heuristique sera nulle.

En ce qui concerne les directions horizontales, nous allons compter uniquement les pions alignés à condition que le milieu soit présent. Cela permet d'éviter de compter plusieurs fois les mêmes pions alignés et de comptabiliser des alignements non importants, car il est impossible de gagner en diagonale si nous n’avons pas la case du milieu correspondant. 

Enfin, si nous trouvons 4 pions alignés, nous allons multiplier la valeur obtenue par 1000 pour refléter la force de cette configuration.

Une fois que nous avons calculé la valeur pour chaque pion, nous additionnons toutes ces valeurs pour obtenir la valeur heuristique totale du board.

En résumé, notre heuristique calcule la valeur de chaque pion en explorant toutes les directions, en soustrayant les blocages et en prenant en compte les configurations de 4 pions alignés. Nous espérons que cette méthode permettra d'obtenir des résultats satisfaisants dans la résolution du jeu de Puissance4.






## Implémentation du MiniMax

Suite à la réalisation de notre fonction d'évaluation la semaine dernière, nous avons commencé à implémenter notre algorithme de minimax. Nous travaillons encore sur l'implémentation mais avons déjà une IA capable de jouer "correctement" en contrant les attaques adverses et en essayant d'aligner des pions pour gagner. Nous essayons également de modifier la profondeur de la depth et d’évaluer les performances. Nous rencontrons quelques problèmes dans la planification de nos coups et cas d’une défaite
Nous avons également commencé à implémenter les fonctions de conversion de la chaîne de caractères reçue en tableau en 2 dimensions, ainsi que les méthodes de vérification sur l'entrée (nombre de caractères corrects, tableau correctement constitué, etc...).


## API, gestion des erreurs

La dernière étape du projet a été d'implémenter une API REST afin que notre service puisse être appelé de l'extérieur. Les informations sur la partie en cours sont envoyés en paramètre de requêtes sous la forme d'une chaîne de caractères. Une fois la chaîne reçue, on effectue différents tests pour s'assurer que la chaîne soit correctement constituées (taille et caractères correctes) puis nous vérifions que le plateau soit possible (au niveau de la disposition, du nombre de pion de chacun des joueurs) et renvyons un code d'erreur en cas de problème (en respectant les consignes données dans le sujet du TD). Si la chaîne est correcte, alors on appelle notre algorithme de MiniMax pour trouver le coup optimal et le renvoyer en JSON. On renvoie le numéro de la colonne à jouer. 

L'API est codée en Python avec le module Flask. Notre projet est disponible en tant qu'image Docker. 



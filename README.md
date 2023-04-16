# NPSE_ProjetFilRouge
Dossier du code du projet fil rouge de l'UPSSITECH 1A SRI
Manuel d’utilisation


I. Récupération du code
- Récupérer le code sur github.
- Mettre tous les fichiers utiles pour la recherche dans le dossier DATA_PFR.
Pour le texte, les fichiers sont de type ‘.xml’; pour l’image, de type ‘.txt’ avec
soit ‘.bmp’ soit ‘.jpg’; pour le son, de type ‘.wav’ et ‘_fi.txt’.
- Installer Eclipse 09/22, la librairie JavaFX, Scene Builder et les paramétrer
pour qu’ils fonctionnent ensemble.
- Cliquer sur le fichier “Main.java” dans Eclipse puis sur Run.

II. Mode Utilisateur

Il existe plusieurs types de recherche : texte par comparaison, texte par mots clés,
image par comparaison, image par couleur, son.
- Cas recherche texte par comparaison :
● Cliquer sur l’icône texte.
● Cliquer sur le bouton “Choisir un fichier”.
● Sélectionner le fichier ‘.xml’ à comparer puis cliquer sur ouvrir.
● Glisser la barre de pourcentage pour atteindre le pourcentage de
ressemblance voulu.
● Cliquer sur l’icône rechercher.

- Cas recherche texte par mots clefs :
● Cliquer sur l’icône texte.
● Entrer les mots clés voulus avec un ‘+’ ou rien devant et les mots non
voulus avec un ‘-’ devant dans la barre de recherche.
ex : “+musique -danse” ou “musique -danse”, “musique”, “musique danse” ou
“+musique +danse”

● Cliquer sur l’icône rechercher.
Remarque : la fonctionnalité ne marche que pour 2 mots.
- Cas recherche image par comparaison :
● Cliquer sur l’icône image.
● Cliquer sur le bouton “Choisir un fichier”.
● Sélectionner le fichier ‘.jpg’ (image couleur) ou ‘.bmp’ (image noir et
blanc) à comparer puis cliquer sur ouvrir.
● Glisser la barre de pourcentage pour atteindre le pourcentage de
ressemblance voulu.
● Cliquer sur l’icône rechercher.

- Cas recherche image par couleur :
● Cliquer sur l’icône image.
● Cliquer sur l’icône palette.
● Sélectionner la couleur souhaitée dans la palette.
● Glisser la barre de pourcentage pour atteindre le pourcentage de
ressemblance voulu.
● Cliquer sur l’icône rechercher.
Remarque : La fonctionnalité ne marche que pour les couleurs bleu, kaki, cyan et violet.

- Cas recherche son :
● Cliquer sur l’icône son.
● Cliquer sur le bouton “Choisir un fichier”.
● Sélectionner le fichier ‘.wav’ à comparer puis cliquer sur ouvrir.

● Glisser la barre de pourcentage pour atteindre le pourcentage de
ressemblance voulu.
● Cliquer sur l’icône rechercher.

Pour accéder à l’historique, il suffit de cliquer sur le bouton historique en bas à droite
ou à gauche de l’écran en fonction des différentes scènes.
Pour revenir en arrière, cliquer sur l'icône retour en haut à droite de l’écran.

III. Mode Administrateur

En tant qu’administrateur, il est possible de modifier les paramètres de recherche et
d’indexation et de visualiser les descripteurs.
● Pour accéder au menu administrateur, cliquer sur l’icône administrateur en
haut à gauche de l’écran.
● Entrer le mot de passe dans l’espace dédié. (“feur”)
● Après changement des paramètres, il est possible de les enregistrer ou de les
annuler en bas à droite de l’écran.
● Pour revenir au menu principal afin d’effectuer une recherche ou de consulter
l’historique, cliquer sur le bouton retour.
Les différents paramètres modifiables:
- Mode de recherche :
● Recherche Fermée : Les résultats des recherches effectuées dans ce
mode ne sont que les fichiers du dossier DATA_PFR préalablement
indexés.

● Recherche Ouverte : Si l’on ajoute des fichiers dans le dossier
DATA_PFR pendant que l’on effectue une recherche, ces fichiers
pourront faire partie des potentiels résultats de la recherche.

- Image : Le bit de quantification peut être soit 2 soit 3.
- Texte : Le nombre d'occurrence du mot voulu dans un texte.
- Son : Le nombre d’échantillons et le nombre d’intervalles.

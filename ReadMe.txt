Fonctionelle :
------------------------
Client :
SQL ET LISTE_Memoire

-Ajout d'un client
-Suppression d'un client
-Modification d'un client
-Importer le fichier CSV
- Normalisation de l'adresse du client
-------------------------
Revue :
SQL ET LISTE_Memoire

-Ajout d'une Revue
-Suppression d'une Revue
-Modification d'une Revue
-Ajout d'une imageView sur l'application pour visuel
--------------------------
Periodicite :
SQL ET LISTE_Memoire

-Ajout d'une periodicite
-Suppression d'une Periodicite
-Modification d'une periodicite
--------------------------
Abonnement :
SQL ET LISTE_Memoire

-Ajout d'un abonnement
-Suppression d'un abonnement
-Modification d'un abonnement
---------------------------
-Changement des différentes fenetres 
---------------------------
Pas Fonctionnelle :
---------------------------
Abonnement :
Lors de la creation et de la modification en Liste Memoire:
-  la date n'est pas du type YYYY-MM-DD mais (Jour en lettre,mois en lettre, jour, heure:minute:seconde,annee) à cause de l'utilisation du type Date et pas LocalDate
---------------------------
Revue : 
L'ajout d'une image dans la base de données ne fonctionne pas
---------------------------
Client :
L'import du fichier CSV ne marche pas pour les caractères spéciaux en SQL 
exemple: ("é")

---------------------------------------------------------------------------
Investissement de chacun :
35% Adrien Destremont
65% Krebs Jeremie
---------------------------------------------------------------------------
Repartition des taches :
---------------------------
KREBS JEREMIE :
 -Creation des controlleurs avec les méthodes ajouter ,modifier,supprimer ainsi que les methodes permettant de changer de fenetre
 -Creation des méthodes optionnelles refreshTable,SelectPutInto,ConversionDate etc...
 -Creation de la partie graphique sur SceneBuilder (FenetreAbonnement,FenetreAccueil,FenetreClient,FenetreRevue,FentrePeriodicite)
------------------------------------
KREBS JEREMIE ET ADRIEN DESTREMONT :
 - Creation de toute la daoFACTORY en TD
 - Creation des methodes de Normalisation

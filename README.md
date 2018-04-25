# HEIGVD-RES-2018-Labo-SMTP
�
Auteur: Zacharie Nguefack 
Date: 17.04.2018
[Liens du repos] 

## But

Le but de ce projet est de d'implmenter une application client SMTP qui envoie les e-mails � un groupe de personnes en choisisssant al�atoirement
un exp�diteur et une liste des destinataires.

##Fonctionnement

Au d�marrage, notre application lit d'abord les fichiers de configuration. Ces fichiers se trouvent dans le dossier "configuration". Ce dossier 
contient les fichiers suivants:
  * configuration.properties, ce fichier contient les informations sur les param�tres des compagnes de plaisanterie. Il indique o� se trouve le server
    smtp,le nombre de groupes de victimes et pr�cise s'il y'a une personne qui peut �tre mise en copie.
  * victims.utf8, ce fichier contient la liste des victimes qu'on va utilis� pour constituer les groupes. 
  * Le messages.utf8, ce fichier contient les messages que nous voullons envoyer aux diff�rentes victimes.
En suite nous avons une liste de classes qui implmentent la logique de l'applicaton(Prank,Group,Mail,Person,MailGenerator) et en fin nous avons 
la classe MailSmtp qui implmente le protocole smtp.


## Description de la mise en �uvre

Dans ce projet, nous avons d�velopp� une application client en java en utilisant l'API Socket pour se connecter au 
un serveur SMTP et les flux d'entr�es-sorties pour la communication avec le serveur.

Nous avons choisi de d�finir une classe MailSmtp qui joue le r�le de notre client smtp. Cette classe contient une classe 
interne appel�e smtpException qui d�coule d'une exception dont le r�le consiste � lancer  une exception � chaque fois que
nous avons une r�ponse n�gative du serveur, par ce que nous enregistrons la derni�re commande envoy�e par l'utilisateur.
Cela d�termine pourquoi l'exception a �t� lanc�e.

Nous avons un paquetage de configuration dans lequel nous avons une classe nomm�e ConfigurationManager dont le r�le est de 
lire le contenu des fichiers de configuration (configuration.properties).
Dans le package model.mail, nous avons 3 classes.
   *La classe Group est une structure de donn�es qui recevra des instances de classe Person �galement trouv�e dans ce paquet. 
Les groupes de personnes (victims) sont stock�s dans cette structure de donn�es sous la forme d'une liste.
   *La classe Person d�finit une personne en mettant � disposition ses informations personnelles (nom, pr�nom et adresse).
   *La classe Mail � elle seule poss�de toutes les informations d'en-t�te n�cessaires pour envoyer un courrier. Les membres
de champ sont initialis�s � partir des fichiers de configuration.
Dans le mod�le package prank
Nous avons 2 classes.
La classe PrankGenerator qui g�n�re un groupe de personnes et une liste de farces (prank).
La classe Prank contient toutes les informations requises pour effectuer un mail (emetteur et destinataires)


## Installation et configuration du serveur mockmock
Pour utiliser notre application, vous devez installer un simulateur de seveur smtp. Dans ce projet, nous avons utilis� un serveur mockmock.
vous trouverez ce serveur et sa configuration ici: https://github.com/tweakers-dev/MockMock . Ce serveur contient dans son arborescence, un  
dossier target qui contient un jar ex�cutable, qui lance une simulation d'un serveur smtp que l'on peut se connecter depuis notre navigateur.
NB: l'hors du lancement du serveur mockmock, vous devez faire un port mapping.



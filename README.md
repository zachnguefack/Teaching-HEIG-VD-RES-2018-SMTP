# HEIGVD-RES-2018-Labo-SMTP
 
Auteur: Zacharie Nguefack 
Date: 17.04.2018
[Liens du repos] 

## But

Le but de ce projet est de d'implmenter une application client SMTP qui envoie les e-mails à un groupe de personnes en choisisssant aléatoirement
un expéditeur et une liste des destinataires.

##Fonctionnement

Au démarrage, notre application lit d'abord les fichiers de configuration. Ces fichiers se trouvent dans le dossier "configuration". Ce dossier 
contient les fichiers suivants:
  * configuration.properties, ce fichier contient les informations sur les paramètres des compagnes de plaisanterie. Il indique où se trouve le server
    smtp,le nombre de groupes de victimes et précise s'il y'a une personne qui peut être mise en copie.
  * victims.utf8, ce fichier contient la liste des victimes qu'on va utilisé pour constituer les groupes. 
  * Le messages.utf8, ce fichier contient les messages que nous voullons envoyer aux différentes victimes.
En suite nous avons une liste de classes qui implmentent la logique de l'applicaton(Prank,Group,Mail,Person,MailGenerator) et en fin nous avons 
la classe MailSmtp qui implmente le protocole smtp.


## Description de la mise en œuvre

Dans ce projet, nous avons développé une application client en java en utilisant l'API Socket pour se connecter au 
un serveur SMTP et les flux d'entrées-sorties pour la communication avec le serveur.

Nous avons choisi de définir une classe MailSmtp qui joue le rôle de notre client smtp. Cette classe contient une classe 
interne appelée smtpException qui découle d'une exception dont le rôle consiste à lancer  une exception à chaque fois que
nous avons une réponse négative du serveur, par ce que nous enregistrons la dernière commande envoyée par l'utilisateur.
Cela détermine pourquoi l'exception a été lancée.

Nous avons un paquetage de configuration dans lequel nous avons une classe nommée ConfigurationManager dont le rôle est de 
lire le contenu des fichiers de configuration (configuration.properties).
Dans le package model.mail, nous avons 3 classes.
   *La classe Group est une structure de données qui recevra des instances de classe Person également trouvée dans ce paquet. 
Les groupes de personnes (victims) sont stockés dans cette structure de données sous la forme d'une liste.
   *La classe Person définit une personne en mettant à disposition ses informations personnelles (nom, prénom et adresse).
   *La classe Mail à elle seule possède toutes les informations d'en-tête nécessaires pour envoyer un courrier. Les membres
de champ sont initialisés à partir des fichiers de configuration.
Dans le modèle package prank
Nous avons 2 classes.
La classe PrankGenerator qui génère un groupe de personnes et une liste de farces (prank).
La classe Prank contient toutes les informations requises pour effectuer un mail (emetteur et destinataires)


## Installation et configuration du serveur mockmock
Pour utiliser notre application, vous devez installer un simulateur de seveur smtp. Dans ce projet, nous avons utilisé un serveur mockmock.
vous trouverez ce serveur et sa configuration ici: https://github.com/tweakers-dev/MockMock . Ce serveur contient dans son arborescence, un  
dossier target qui contient un jar exécutable, qui lance une simulation d'un serveur smtp que l'on peut se connecter depuis notre navigateur.
NB: l'hors du lancement du serveur mockmock, vous devez faire un port mapping.



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prank;
import configuration.IconfigManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import mail.Group;
import mail.Mail;
import mail.Person;

/**
 *
 * @author zacharie && Lankeu cedric
 * Dans cette classe, nous definirons la structure logique et fonctionnelle de notre
 *Prank tout en respectant les consignes et specifications du cahier de charge
 */


public class GeneratePrank {
    
    
    private final  IconfigManager configuration;
    private final List<Person> availableVictims = new LinkedList<>();

    public GeneratePrank(IconfigManager configurationManager) {
        this.configuration = configurationManager;

    }

    // genere et rempli les groupes avec des differentes victimes de la liste
    public List<Group> generateGroups(List<Person> victims, int numberOfGroups) {

        Collections.shuffle(victims); //melange les élèments de la liste
        //initialise la liste des victimes, car elle va etre modifiée;
        victims.forEach((p) -> {
            availableVictims.add(p);
        });
        
        // on crée les différents groupes qu'on met dans une liste de groupes
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; i++) {
            Group group = new Group();
            groups.add(group);
        }
        // remplir chaque groupe avec des Personnes(victimes)
        int index = 0; 
        Group groupeCibler;
        while(availableVictims.size() > 0) {  
            groupeCibler =groups.get(index) ;           
            Person victim = availableVictims.remove(0); // on recupere la personne en 1ere position 
            groupeCibler.add(victim); //on ajoute la victime dans son groupe
            index = (index + 1) % groups.size(); // on incremente l'index (modulo = rotation)
        }
        return groups;
    }

   
    public List<Prank> generatePranks() {
        List<Prank> pranks = new ArrayList<>();
        List<Mail> messages = configuration.getMessages();
        int numberOfGroups = configuration.getNumberOfGroups();
        int numberOfVictims = configuration.getVictims().size();
        
        // on garanti le fait que chaque groupe aura 1 sender et au minimum 2 receivers (donc 3 victimes)         
        if (numberOfVictims / numberOfGroups < 3) {
            numberOfGroups = numberOfVictims / 3;
        }       
        
        List<Group> groups = generateGroups(configuration.getVictims(), numberOfGroups); // appel de la fonction generategroups afin de generer les grpes
        Person sender = null;
        int i = 0;       
        
        for (Group group : groups) {
            Prank prank = new Prank();

            List<Person> victimsPerGroup = group.getMembers();
            Collections.shuffle(victimsPerGroup); // on melange 
            
            if (!victimsPerGroup.isEmpty()) {
                sender = victimsPerGroup.remove(0);//on retire le sender de la liste car il ne doit pas recevoir son propre mail
            }
            
            prank.addVictimRecipients(victimsPerGroup);

            int indexMessage = 0;
            Mail message = messages.get(indexMessage);
            indexMessage = (indexMessage + 1) % messages.size();
            prank.setVictimSender(sender);
            prank.setMessage(message);
            System.out.println(prank.getMessage().getBody());// affiche le Body pour un message donné
            pranks.add(prank);
        }
        return pranks;
    }
    
    
}

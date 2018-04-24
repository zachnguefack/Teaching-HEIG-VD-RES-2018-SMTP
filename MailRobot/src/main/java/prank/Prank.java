/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prank;

import java.util.ArrayList;
import java.util.List;
import mail.Mail;
import mail.Person;

/*
 *@authors  zacharie nguefack et lankeu cedric
 *
 *  structure logique exacte de notre Prank(un emetteur,d'un message et  
 *  nombreux destinataires en copie)
 */
public class Prank 
{
	private final List<Person> witnessRecepients = new ArrayList<>();
    private final List<Person> victimRecipients  = new ArrayList<>();
    private Mail message;
    private Person sender;


    public void setVictimSender(Person victimSender) {
        this.sender = victimSender;
    }
    
    public Person getVictimSender() {
        return sender;
    }

    public void setMessage(Mail message) {
        this.message = message;
    }
    
    public Mail getMessage() 
    {
        return message;
    }
    
    public List<Person> getVictimRecipient() {
        return victimRecipients;
    }
    
    public void addVictimRecipients(List<Person> victimRecipients) {
        this.victimRecipients.addAll(victimRecipients);
    }

     public void addWitnessvictim(List<Person> witnessrecipient) {
        this.witnessRecepients.addAll(witnessrecipient);
    }
     
     public List<Person> getwitnessrecipient() {
        return witnessRecepients;
    }
    
}
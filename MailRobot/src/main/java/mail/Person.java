/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;
 /*
 *@authors   zacharie nguefack et lankeu cedric
 *
 * Permet de stocker des personnes(nom , prenom et adresse mail des victimes)
 *
 */
public class Person 
{
	private String firstname;
    private String lastname;
    private String emailAddress;

    public Person(String firstname, String lastname, String mailAdress) {
        this.emailAddress = mailAdress;
        this.firstname = firstname;
        this.lastname = lastname;  
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMailAdress() {
        return emailAddress;
    }

    public void setMailAdress(String mailAdress) {
        this.emailAddress = mailAdress;
    }
    
}

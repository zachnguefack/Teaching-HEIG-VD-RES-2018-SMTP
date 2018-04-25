/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;
<<<<<<< HEAD

/**
 * cette classe a pour role de definir et stocker des personnes qui sont en fait
 * constitué du nom , prenom et adresse mail des victimes
=======
 /*
 *@authors   zacharie nguefack et lankeu cedric
 *
 * Permet de stocker des personnes(nom , prenom et adresse mail des victimes)
>>>>>>> 6c3254e5d3ab3b86b5ba632ad8e8f13cf41df293
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

<<<<<<< HEAD
    private String firstname;
    private String lastname;
    private String mailAdress;

    public Person(String firstname, String lastname, String mailAdress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mailAdress = mailAdress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
=======
    public void setMailAdress(String mailAdress) {
        this.emailAddress = mailAdress;
>>>>>>> 6c3254e5d3ab3b86b5ba632ad8e8f13cf41df293
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

/**
 *cette classe a pour role de definir et stocker des personnes qui sont en fait
 * constitué du nom , prenom et adresse mail des victimes
 * @author zacharie && Lankeu
 */
public class Person {

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.IOException;
import java.util.List;
import mail.Mail;
import mail.Person;

/**
 *
 * @author zacharie && Lankeu
 */
public interface IconfigManager {

    public List<Mail> loadMailFromFile(String filename) throws IOException;

    public List<Person> loadAddressFromFile(String filename) throws IOException;

    public void loadPropertie(String filename) throws IOException;

    public List<Mail> getMessages();

    public List<Person> getVictims();

    public int getNumberOfGroups();

    public String getSubject();

    public String getServerAddress();

    public int getServerport();

}

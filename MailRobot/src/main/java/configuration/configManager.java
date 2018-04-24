/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import mail.Mail;
import mail.Person;

/**
 *
 * @author zacharie && Lankeu
 */
public class configManager implements IconfigManager{
    private String addressOfServer;
    private int serverport;
    private List<Person> victims;
    private List<Mail> messages;
    private int numberOfGroups;
    private String subject = null;
    final String ENCODING = "UTF-8";
    
    public configManager() throws IOException{
        victims = loadAddressFromFile("victims.utf8");
        messages = loadMailFromFile("messages.utf8");
        loadPropertie("config.properties");
    }

    @Override
    public List<Mail> loadMailFromFile(String filename) throws IOException {
         List<Mail> listOfMail;
         
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), ENCODING));
            listOfMail = new LinkedList<>();
            String line = reader.readLine();
            while (line != null) {
                Mail mail = new Mail();
                StringBuilder body = new StringBuilder(); // usage de cette structure pour gagner en memoire lors des "append"
                while ((line != null) && (!line.equals("--"))) { //parcours jusqu'au separateur de mail "@@@"

                    if (line.indexOf("subject") != -1) { //on regarde s'il y'a du texte après "subject" 
                        String str[] = line.split(":");
                        mail.setSubject(str[1]); //on recupere le sujet (objet du mail)
                    } else {
                        body.append(line); // concatenation de chaque ligne du body
                        body.append("\r\n");
                    }
                    mail.setBody(body.toString()); //on ajoute le corps du message a notre variable Body
                    line = reader.readLine(); //on passe a la ligne suivante
                }

                listOfMail.add(mail);

                line = reader.readLine();
            }
            return listOfMail;
        
    }

    @Override
    public List<Person> loadAddressFromFile(String filename) throws IOException {
         List<Person> listOfPersons = new LinkedList<>();
         
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            String adr = reader.readLine();

            while (adr != null) {
                //definir une personne à partir de son adresse 
                String str[] = adr.split("@");  //str[0] = prenom 
                                                    // str[1] = nom
                str = str[0].split("\\.");    
                Person person = new Person(str[1], str[0], adr);
                // constuire une personne à partir de son adresse
                listOfPersons.add(person); 
                 // lire la prochaine adresse
                adr = reader.readLine();
            }
        }

        return listOfPersons;

    }

    @Override
    public void loadPropertie(String filename) throws IOException {
        
         FileInputStream fileInputStream = new FileInputStream(filename);
         
        Properties properpties = new Properties();
        properpties.load(fileInputStream);

        this.addressOfServer = properpties.getProperty("smtpserverAddress");
        this.serverport = Integer.parseInt(properpties.getProperty("smtpServerport")); 
        
        this.numberOfGroups = Integer.parseInt(properpties.getProperty("numberOfGroups")); 
        
        this.subject = properpties.getProperty("Subject");
    }

    @Override
    public List<Mail> getMessages() {
       return messages;
    }

    @Override
    public List<Person> getVictims() {
       return victims;
    }

    @Override
    public int getNumberOfGroups() {
      return numberOfGroups;
    }

    @Override
    public String getSubject() {
        return subject;
    
    }

    @Override
    public String getServerAddress() {
     return addressOfServer;
    }

    @Override
    public int getServerport() {
       return serverport;
    }
    
}

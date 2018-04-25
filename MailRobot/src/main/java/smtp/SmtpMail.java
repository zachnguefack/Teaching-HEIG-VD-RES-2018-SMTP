/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smtp;
import configuration.configManager;
import configuration.IconfigManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import mail.Mail;
import mail.Person;
import prank.Prank;
import prank.GeneratePrank;

/**
 *
 * @author zacharie && Lankeu
 */




/*
 *@authors  zacharie nguefack et lankeu cedric 
 *
 *Dans cette classe, nous realisons la partie communicationnelle(échange) entre
 *le client et le serveur tout en respectant de facon minitieuse les differentes
 *spécifications du protocole SMTP et nous gérons egalement a chaque envoie, la reponse
 *du serveur et renvoyons une exception en cas de problème. ( Cf. RFC 5321 SMTP)
 */
public class SmtpMail {
    
    private Mail mail = new Mail();
    private Prank prank = null;
    private String message = null;
    private boolean debug;
    private InetAddress IPserverAddress = null; //l'adresse su serveur smtp
    private int serverPort = -1; // le port utilise par le client smtp
    private Person sender;    
    String lastMessageOfServer = null; // derniere commande recue
    private String subject = null;
    private final int VALIDATION = 2;



    private static Object MailAddress;

    public SmtpMail() {
        super();
    }

    public void simpleSend(String serverName, int port, Prank prank)
            throws SmtpException, UnknownHostException, IOException {
        IPserverAddress = InetAddress.getByName(serverName); //renvoie ladres IP du serveur
        this.prank = prank;
        serverPort = port;
        mail = this.prank.getMessage();// message du prank considéré
        message = mail.getBody();
        sender = this.prank.getVictimSender();
        this.subject = mail.getSubject();
        send();
    }

    protected void send()
            throws SmtpException, IOException {

        Socket socket = null;

        try {
            //setup connection 
            
            socket = new Socket(IPserverAddress, serverPort);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            int ConnectionCode = getReponse(input);

            if (ConnectionCode / 100 != VALIDATION) {  //teste si le code de connexion est diffrent de 250 ou 221..
                throw new SmtpException("CONNECT", ConnectionCode, lastMessageOfServer);
            }
            
            sendcommand(output, "EHLO " + InetAddress.getLocalHost().getHostName(), input, VALIDATION);
            sendcommand(output, "MAIL FROM: " + sender.getMailAdress(), input, VALIDATION);

            for (int i = 0; i < prank.getVictimRecipient().size(); ++i) {
                sendcommand(output, "RCPT TO: " + prank.getVictimRecipient().get(i).getMailAdress(), input,VALIDATION);
            }
            sendcommand(output, "DATA ", input, 3);

            sendEmail(output);
            ConnectionCode = getReponse(input);
            if (ConnectionCode / 100 != VALIDATION) {
                throw new SmtpException("Send Email", ConnectionCode, lastMessageOfServer);
            }

            sendcommand(output, "QUIT", input, VALIDATION);
            
            
        } catch (IOException e1) {
            if (socket != null) {
                socket.close();
            }
            throw e1;
        } catch (SmtpException e2) {
            if (socket != null) {
                socket.close();
            }
            throw e2;
        }

    }

    /**
     *Cette methode nous permettra de recupérer le code de retour du serveur 
     * qui nous permettra de savoir si la connexion a eu lieu ou pas et ainsi 
     * nous pourrons aussi recupere l'historique du denier message envoyé
     */
    public int getReponse(BufferedReader input) throws IOException {
        int code;
        boolean derniereLigne = false;
        String line;
        StringBuffer text = new StringBuffer();

        do {
            line = input.readLine(); 
           derniereLigne = (line.charAt(3) == '-');
            text.append((line.substring(4, line.length())));
        } while (derniereLigne);

        code = Integer.parseInt(line.substring(0, 3));        
        lastMessageOfServer = text.toString();

        return code;

    }

    /**
     * Lève une exception de type SMTPException si le code n'est pas valide
     * c'est à dire le premier caractére retourner par le serveur
    
     */
    public void sendcommand(PrintWriter out, String cmd, BufferedReader in,int validation)
            throws SmtpException, IOException {

        out.write(cmd);
        out.write("\r\n");
        out.flush();

        int ConnectionCode = getReponse(in);

        if (ConnectionCode / 100 != validation) {
            throw new SmtpException(cmd, validation, lastMessageOfServer);
        }

    }

    /**
     * c'est cette methode qui nous permet d'envoyer la partie apres la commande 
     * "DATA" au seveur
     *On commence par envoyer l'entete puis on fera appel a la fonction "bodymessage" 
     * qui se chargera d'y ajouter le corps du mail
     */
    public void sendEmail(PrintWriter out) {
        // entête du mail

        out.write("Content-Type: text/plain; charset=\"UTF-8\"");
        out.write("\r\n");
        out.write("From: " + sender.getMailAdress() + "\r\n");
        out.write("To: " + prank.getVictimRecipient().get(0).getMailAdress()); //on met d'abord la premiere victime

        for (int i = 0; i < prank.getVictimRecipient().size(); ++i) {
            out.write("," + prank.getVictimRecipient().get(i).getMailAdress());
        }

        out.write("\r\n");
        out.write("Cc: " + prank.getVictimRecipient().get(0).getMailAdress());

        for (int i = 0; i < prank.getVictimRecipient().size(); ++i) {
            out.write("," + prank.getVictimRecipient().get(i).getMailAdress());
        }

        out.write("\r\n");
        out.write("Subject: " + mail.getSubject() + "\r\n");
        out.write("\r\n"); // fin de l'entête

        //message a envoyer
        String data = bodyMessage(message);
        out.write(data);

        //fin du message marque par <CR/LF>.<CR/LF>
        out.write("\r\n.\r\n");
        out.flush();
    }

    /**
     * convertir le String au format compris par le serveur. Le format de
     * message attendu est le suivant: les lignes séparées par saut de ligne Le
     * format de données SMTP est : lignes séparées by <lt> <LF> <LF>;
     *
     * @param message message a convertir
     * @return le message convertir
     */
    public String bodyMessage(String message) {
        StringBuffer buffer = new StringBuffer();
        String line;
        int start = 0;
        int end = 0;
        if (message != null) {
            buffer.ensureCapacity(message.length() + 100);
            do {
                end = message.indexOf('\n', start);
                if (end == -1) {
                    line = message.substring(start);
                } else {
                    line = message.substring(start, end);
                    end++;
                }

                if (line.length() > 0 && line.charAt(0) == '.') {
                    buffer.append('.');
                }

                buffer.append(line);
                if (end != -1) {
                    buffer.append("\r\n");
                }

                start = end;
            } while (end != -1);
        }

        return buffer.toString();
    }

  

    public static void main(String[] argv) throws IOException {

        //recupere les données neccessaire pour genere un prank  
        IconfigManager conf = new configManager();

        //liste contenant les prank
        List<Prank> pranks = new LinkedList<Prank>();

        GeneratePrank gPranks = new GeneratePrank(conf);
        //génération de la liste de panks 

        pranks = gPranks.generatePranks();

        //le nom du serveur 
        String serverName = conf.getServerAddress();

        //le numèro du port 
        int serverPort = conf.getServerport();

        SmtpMail smtp = new SmtpMail();
        try {
            for (Prank p : pranks) {
                smtp.simpleSend(serverName, serverPort, p);
            }
        } catch (IOException | SmtpException e) {
            System.err.println("Error while sending: " + e.toString());
            System.exit(1);
        }

        System.exit(0);
    }
}

class SmtpException extends Exception {

    /**
     *
     * cree une nouvelle exception
     *
     * @param lastCmd la dernière commande envoyer avent l'erreur.
     * @param errorCode le code d'erreur retourne par le serveur.
     * @param errorMsg le message d'erreur.
     */
    public SmtpException(String lastCmd, int errorCode, String errorMsg) {
        this.lastCmd = lastCmd;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * convertir l'exception en String
     *
     * @return String representation of Exception
     */
    public String toString() {
        StringBuffer buff = new StringBuffer();

        buff.append("Error while executing cmd " + lastCmd + ":"
                + errorCode + "-" + errorMsg);

        return buff.toString();
    }

    String lastCmd = null;
    int errorCode = -1;
    String errorMsg = null;

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.ArrayList;
import java.util.List;

/*
 *@authors  zacharie nguefack  et lankeu
 *  definir et stocker les différentes parties du mail(definir la structure 
 *  concrete d'un mail)
 */
public class Mail 
{
	
	private String subject;
    private String address;
    private String body;
    private String name;
    private String from;
    private final List<String> to = new ArrayList<>();
    private final List<String> cc = new ArrayList<>();
    
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        if (name != null) 
        {
            buffer.append("\"" + name + "\"");
        }
        
        if (address != null) 
        {
            buffer.append("<" + address + ">");
        } 
        else 
        {
            return null;
        }
        return buffer.toString();
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

<<<<<<< HEAD
    private String subject;
    private String address;
    private String body;
    private String name;
    private String from;
    private final List<String> to = new ArrayList<>();
    private final List<String> cc = new ArrayList<>();

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        if (name != null) {
            buffer.append("\"" + name + "\"");
        }

        if (address != null) {
            buffer.append("<" + address + ">");
        } else {
            return null;
        }
        return buffer.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
=======
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public String getBody() {
        return body;
    }
    
    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> s) {
        this.to.addAll(s);
    }

    public void setCc(List<String> s) {
        this.cc.addAll(s);
    }
    
    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
>>>>>>> 6c3254e5d3ab3b86b5ba632ad8e8f13cf41df293
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> s) {
        this.to.addAll(s);
    }

    public void setCc(List<String> s) {
        this.cc.addAll(s);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

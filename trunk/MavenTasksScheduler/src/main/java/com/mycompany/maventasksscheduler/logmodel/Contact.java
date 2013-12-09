/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;

/**
 * This class describes contact.
 * @author Сергей
 * 
 */
public class Contact implements Cloneable {
    
    private String name;
    private int phoneNumber;
    private String email;
    
    
    /**
     * This constructor creates empty contact.
     */
    public Contact(){
        name = null;
        phoneNumber = 0;
        email = null;
    }
    
    /**
     * This constructor creates contact with name.
     * @param name Contact's name.
     */
    public Contact(String name){
        this.name = name;
        phoneNumber = 0;
        email = "";
    }
    
    /**
     * This constructor creates contact with name and phone number.
     * @param name Contact's name.
     * @param number Contact's phone number.
     */
    public Contact(String name, int number){
        this.name = name;
        phoneNumber = number;
        email = "";
    }
    
    /**
     * This constructor creates contact with name, phone number and email
     * @param name Contact's name.
     * @param number Contact's phone number.
     * @param email  Contact's email.
     */
    public Contact(String name, int number, String email){
        this.name = name;
        phoneNumber = number;
        this.email = email;
    }
    
    /**
     * Returns contact's name.
     * @return A String containing the name.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Returns contact's phone number.
     * @return int - phone number 
     */
    public int getPhoneNumber(){
        return phoneNumber;
    }
    
    /**
     * Returns contact's email
     * @return A String containing the email
     */
    public String getMail(){
        return email;
    }
    
    /**
     * This method set contact's name.
     * @param name The name of contact. 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * This method set contact's phone number.
     * @param number The contact's phone number.
     */
    public void setPhoneNumber(int number){
        this.phoneNumber = number;
    }
    
    /**
     * This method set contact's mail.
     * @param mail The contact's email.
     */
    public void setMail(String mail){
        this.email = mail;
    }
    /**
     * Returns information about contact.
     * @return A String containing name or name and phone number
     * or name and email or name and phone number and email
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if(name.length() > 0 && phoneNumber <= 0 && email.equals(""))
            return sb.append("Contact's name - " + name).toString();
        if(name.length() > 0 && phoneNumber > 0 && email.equals(""))
            return sb.append("Contact's name - " + name + ", with phone number - "
                      + phoneNumber).toString();
        if(name.length() > 0 && phoneNumber <= 0 && email.length() > 0)
            return sb.append("Contact's name - " + name + ", with email - "
                      + email).toString();
        if(name.length() > 0 && phoneNumber > 0 && email.length() > 0)
            return sb.append("Contact's name - "+name+", with phone number - "+ 
                    phoneNumber + " and email - "+ email).toString();
        
        return sb.append("No information about contact").toString();
    }

    /**
     * Compares this object with the specified object
     * @param o object for check
     * @return true if objects are equal and both belongs Contact,
     * false - in all other cases
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Contact && this.name.equals(((Contact) o).name)
                && this.phoneNumber == ((Contact) o).phoneNumber &&
                   this.email.equals(((Contact) o).email);
    }

    /**
     * Generate hashCode for this object
     * @return hashCode which generate for this object.
     */
    @Override
    public int hashCode() {
        return phoneNumber + (name == null ? 0 : name.hashCode()) + (email == null ? 0 : email.hashCode());
    }

    /**
     * Returns the object copy.
     * @return the object copy
     */
    @Override
    public Object clone() {
        Contact result = null;
        try {
            result = (Contact) super.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return result;
    }
    
}

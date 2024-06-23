
package model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate; 

public class Event implements Serializable,Comparable<Event>{ 
    private int ID;
    private String nameEvent;
    private LocalDate date;
    private String location;
    private int numberOfAttedees;
    private boolean status;
    private boolean deleteFlag; 
    public Event() {
    }

    public Event(int ID, String nameEvent, LocalDate date, String location, int numberOfAttedees, boolean status, boolean deleteFlag) {
        this.ID = ID;
        this.nameEvent = nameEvent;
        this.date = date;
        this.location = location;
        this.numberOfAttedees = numberOfAttedees;
        this.status = status;
        this.deleteFlag = deleteFlag;
    }    
    
    public Event(String nameEvent, LocalDate date, String location, int numberOfAttedees, boolean status, boolean deleteFlag) {        
        this.nameEvent = nameEvent;
        this.date = date;
        this.location = location;
        this.numberOfAttedees = numberOfAttedees;
        this.status = status;
        this.deleteFlag = deleteFlag;
    }   
    public Event(String nameEvent, LocalDate date, String location, int numberOfAttedees, boolean status) {        
        this.nameEvent = nameEvent;
        this.date = date;
        this.location = location;
        this.numberOfAttedees = numberOfAttedees;
        this.status = status;
    }   
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
     
    public String getLocation() {
        return location;
    }
   
    public void setNumberOfAttedees(int numberOfAttedees) {
        this.numberOfAttedees = numberOfAttedees;
    }
    
    public int getNumberOfAttedees() {
        return numberOfAttedees;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
    
    private String statusToString(boolean status){
        String result = status ? "Available" : "Unavailable";
        return result;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    @Override
    public int compareTo(Event o) {
        if(date.compareTo(o.date)==0){
            return nameEvent.compareTo(o.nameEvent);
        }
        return date.compareTo(date);
    }
    
    @Override
    public String toString() {        
        return "Event{"                 
               + "ID= " + ID                 
               + ", Name= " + nameEvent 
               + ", Date= " + date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))
               + ", Location= " + location 
               + ", NumberOfAttedees= " + numberOfAttedees 
               + ", Status= " + statusToString(status) + "}";
    }
    
    public String toDatFormat(){
        return ID + ":" + 
               nameEvent + ":" +
               date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + ":" +
               location + ":" +
               numberOfAttedees + ":" + 
               status + ":" +
               deleteFlag; 
    }
}

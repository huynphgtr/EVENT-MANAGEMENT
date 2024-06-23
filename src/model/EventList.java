
package model;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventList {
    List<Event> listEvent = new ArrayList<>();

    public EventList() {}    
    public void setList(ArrayList<Event> list) {
        this.listEvent = list;
    }    
    public List<Event> getList() {
        return listEvent;
    }    
    public void add(Event event) {
        int maxID = 0;
        for (int i = 0; i < listEvent.size(); i++) {
            int currentID = listEvent.get(i).getID();
            if (currentID > maxID) {
                maxID = currentID;
            }
        }
        event.setID(maxID + 1);
        event.setDeleteFlag(false);
        listEvent.add(event);
    }    
    public void delete(int ID) {
       Event event = getEventByID(ID);
       event.setDeleteFlag(true);
    }  
    public void sort() {
        Collections.sort(listEvent);
    }
//------------------------------------------------------------------------------
    public Event getEventByID(int ID) {
        for (Event e : listEvent) {
            if (e.getID() == ID)
                return e;
        }
        return null;
    }     
    public List<Event> searchById(int ID){
        List<Event> result = new ArrayList<>(); 
        for(Event e:listEvent){
          if (Integer.toString(e.getID()).contains(Integer.toString(ID))) {
                result.add(e);
            }  
        }
        return result; 
    }
//------------------------------------------------------------------------------
    public List<Event> searchByLocation(String location) {
        List<Event> result = new ArrayList<>();
        for (Event e : listEvent) {
            if (e.getLocation().toLowerCase().contains(location.toLowerCase())) {
                result.add(e);
            }
        }
        return result;
    }  
    public void updatedEvent(Event updateEvent){
        for(Event e : listEvent){
            if (e.getID() == updateEvent.getID()){
                e.setNameEvent(updateEvent.getNameEvent());
                e.setDate(updateEvent.getDate());
                e.setLocation(updateEvent.getLocation());
                e.setNumberOfAttedees(updateEvent.getNumberOfAttedees());
                e.setStatus(updateEvent.getStatus());
            }
        }
    }
//------------------------------------------------------------------------------   
    public void readData(String fName) {
        File f = new File(fName);
        if (!f.exists()) {
            throw new RuntimeException(fName + "does not exist!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (Scanner sc = new Scanner(new FileReader(fName));) {
            while (sc.hasNextLine()) {
                String stdLine = sc.nextLine();
                String[] s = stdLine.split(":");
                if (s.length == 7) {
                    var event = new Event(Integer.parseInt(s[0]), s[1], LocalDate.parse(s[2], formatter), s[3], Integer.parseInt(s[4]), Boolean.parseBoolean(s[5]),Boolean.parseBoolean(s[6]));
                    listEvent.add(event);
                }
            }
            sc.close();
        } catch (Exception ex) {
            Logger.getLogger(EventList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void saveData(String fName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fName))) {
            for (Event e : listEvent) {
                pw.println(e.toDatFormat());
            }
            pw.close();
        } catch (Exception ex) {
            Logger.getLogger(EventList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

}


package controller;
import model.*;
import view.*; 
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


public class EventManagement  extends Menu{
    
    static String[] mainMenu = {"Create a new event", 
                                "Check if an event exists",
                                "Search by location", "Update and delete event",
                                "Save to file", "Print list from file",
                                "Others - Quit"};
    
    private static EventList listEvent;
    private static Event event;
    private static List<Event> eventList; 
    public EventManagement(EventList eventList){
        super("EVENT MANAGEMENT", mainMenu); 
        EventManagement.listEvent = eventList;
    }    
    
    @Override
    public void execute(int n){
       switch (n) {
          case 1 -> Helper.checkConfirm(() -> doCreate());
          case 2 -> Helper.checkConfirm(()-> doCheckById());
          case 3 -> Helper.checkConfirm(()->doSearchByLocation());
          case 4 -> doUpdate(); 
          case 5 -> {listEvent.saveData("events.dat"); 
                    System.out.println("Save Success");}
          case 6 -> {
              List<Event> displayList = listEvent.getList(); 
              doDisplay(displayList);}                 
          case 7 -> System.exit(0);
          }
    }
    //--------------------------------------------------------------------------
    public void doCreate(){
        String name = Helper.getString("Enter name");
        LocalDate date = Helper.getLocalDate("Enter date with fomart dd/MM/yyyy");
        String location = Helper.getString("Enter location"); 
        int attendees = Helper.getInt("Enter number of attendees"); 
        boolean status = Helper.getStatus("Enter status (1-Available  0-Unavailable)"); 
        event = new Event(name, date, location, attendees, status);
        listEvent.add(event);       
    }    
    //--------------------------------------------------------------------------
    public boolean doCheck(){
        int id = Helper.getInt("Enter ID"); 
        event = listEvent.getEventByID(id);
        if (event == null) return false;                    
        else return true;      
    } 
    public void doCheckById(){
        int id = Helper.getInt("Enter ID"); 
        List<Event> idList = listEvent.searchById(id);
        doDisplay(idList);           
    }
    //--------------------------------------------------------------------------    
    public void doSearchByLocation(){
        String location = Helper.getString("Enter location");
        List<Event> nameList = listEvent.searchByLocation(location);
        doDisplay(nameList);    
           
    }      
    //--------------------------------------------------------------------------
    public void doUpdate() {
        String[] updateMenu = {"Update event", "Delete event", "Others - Quit"};
        new Menu("UPDATE MENU", updateMenu){
            
            @Override
            public void execute(int n){
                switch(n){
                    case 1: Helper.checkConfirm(()->doUpdateEvent()); break;
                    case 2: Helper.checkConfirm(()->doDeleteEvent()); break;
                    case 3: new EventManagement(listEvent).run();
                }
            }
        }.run();
    }
    public void doUpdateEvent(){
        int id = Helper.getInt("Enter ID"); 
        event = listEvent.getEventByID(id);
        if(event == null) System.out.println("Student is not exist");
        else{
            System.out.println(event);
            event.setNameEvent(Helper.getString("Enter name"));
            event.setDate(Helper.getLocalDate("Enter date with fomart dd/MM/yyyy"));
            event.setLocation(Helper.getString("Enter location"));
            event.setNumberOfAttedees(Helper.getInt("Enter number of attendees"));
            event.setStatus(Helper.getStatus("Enter status (1-Available  0-Unavailable)"));
            listEvent.saveData("events.dat");
            System.out.println("Update Success");       
            }  
    }
    public void doDeleteEvent(){
        if (!doCheck()) System.out.println("Event is not exist!");                     
        else {
             listEvent.delete(event.getID()); 
             System.out.println("Delete Success");
        }
    }
    //--------------------------------------------------------------------------
    private void doDisplay(List<Event> eventList) {
        if (eventList.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            for (Event e : eventList) {
                if(!e.isDeleteFlag())
                   System.out.println(e);
            }
        }

    }
    //--------------------------------------------------------------------------
}


package controller;
import model.EventList;
public class Main {
    public static void main(String[] args) {
        EventList eventList = new EventList(); 
        eventList.readData("events.dat");
        EventManagement manage = new EventManagement(eventList); 
        manage.run();

    }
}

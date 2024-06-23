
package view;
import java.util.ArrayList;
public abstract class Menu {
    protected String title;
    protected ArrayList<String> options;

    public Menu() {
    }
//------------------------------------------------------------------------------
    public Menu(String td, String[] menuOptions) {
        title = td;                 
        options = new ArrayList<>();  
        for (String option : menuOptions) {
            options.add((String) option);
        }
    }
//------------------------------------------------------------------------------
    public void displayMenu() {
        System.out.println("");
        System.out.println(title);
        System.out.println("--------------------------------");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "." + options.get(i));
        }
        System.out.println("--------------------------------");
    }
//------------------------------------------------------------------------------    
    public int getSelected() {        
        displayMenu();        
        int n = Helper.getInt("Select Option");  
            if (n < 1 || n > options.size()) {
                System.out.print("Please enter choice from 1 to " + options.size());
           
            }        
        return n;
        
    }
//------------------------------------------------------------------------------
    public abstract void execute(int n);
//------------------------------------------------------------------------------
    public void run() {
        while (true) {
            int n = getSelected();
            execute(n);
        }
    }   
}

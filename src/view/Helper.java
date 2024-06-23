
package view;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Helper {
    
    private Helper() {

    }
//------------------------------------------------------------------------------    
    public static String getString(String msg) {        
        String str = "";
        while (true) {
            try {
                System.out.print(msg + " : ");
                str = new Scanner(System.in).nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
            }
        }
        return str;
    }
//------------------------------------------------------------------------------    
    public static int getInt(String msg) {         
        int tg;
        while (true) {
            try {                
                System.out.print(msg + " : ");
                tg = new Scanner(System.in).nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");             
            }
            
        }
        return tg;        
    }
//------------------------------------------------------------------------------
    public static double getDouble(String msg) {
        double db;
        while (true) {
            try {
                System.out.print(msg + " : ");
                db = new Scanner(System.in).nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
            }
        }
        return db;
    }
//------------------------------------------------------------------------------
    public static LocalDate getLocalDate(String msg) {
        LocalDate result;
        String str;
        while (true) {
            try {
                System.out.print(msg + " : ");                
                str = new Scanner(System.in).nextLine();                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");                
                result = LocalDate.parse(str, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
            }
        }
        return result;
    }
//------------------------------------------------------------------------------   
    public static boolean getStatus(String msg) {
        int i;
        while (true) {
            try {
                System.out.print(msg + " : ");
                i = new Scanner(System.in).nextInt();
                if (i == 0) {
                    return false;
                }
                if (i == 1) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
            }
        }
    }
//------------------------------------------------------------------------------
    public static boolean getConfirm(){
        while(true){
            var cf = Helper.getString("Do you want to continue? (Y-Yes, N-No)"); 
            if(cf.equalsIgnoreCase("Y")) return true;
            if(cf.equalsIgnoreCase("N")) return false;    
            System.out.println("Enter Y or N!");
        }
    }
//------------------------------------------------------------------------------
    public static void checkConfirm(Runnable function){
        while(true){
            function.run();  
            if(!getConfirm()) break;       
        }
    }
}

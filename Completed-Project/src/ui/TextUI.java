package ui;
import java.util.Scanner;

import app.*;

/**
 * @author KabianDavidson
 */
public class TextUI {
    private int birthYear=0;
    private int brideYear=0;
    private int groomYear=0;

    /**
     * A constructor for the TextUI class.
     * Takes in a SNIDApp object.
     * Creates the User Command Line Interface in which:
     * New Citizen's Information can be entered,
     * Citizen's Information can be updated,
     * Ability to locate a citizen,
     * Files for marriages and deaths are created,
     * and Citizen's main files are updated.
     * Text Command Line Interface is only closed when the User select the specific command to close the interface.
     * @param app The SNIDApp object that interacts directly with the Text Command Line Interface
     */
    public void go(SNIDApp app){
    Scanner sc = new Scanner(System.in);
    String answer="";
    String cls = "\033[H\033[2J";
    while(true){
        while(!(answer.equals("a") || answer.equals("b") || answer.equals("c") || 
            answer.equals("d") || answer.equals("e") || answer.equals("f") ||
            answer.equals("g") || answer.equals("h"))){
            System.out.print(cls);
            System.out.println("a. Register a Birth");
            System.out.println("b. Update Parent Data");
            System.out.println("c. Update the Citizen Address");
            System.out.println("d. Register a Death");
            System.out.println("e. Register a Marriage");
            System.out.println("f. Generate a Mailing Label");
            System.out.println("g. Search");
            System.out.println("h. Exit Application");
            System.out.print("Selection: ");
            answer = sc.nextLine().toLowerCase();
            }
            
            switch (answer) {
                case "a":
                        
                        char gender='M';
                        int yob;
                        String fName, mName, lName,fatherID0,motherID0;
                        String street0, town0, parish0, country0;
                        String response;
                        Boolean isDigit=true;
                        String testYOB;
                    
                        System.out.print(cls);
                        System.out.println("===================");
                        System.out.println("REGISTERING A BIRTH");
                        System.out.println("===================");
                        System.out.println();
                    
                    System.out.print("Enter The Citizen's Father ID: ");
                    fatherID0=sc.nextLine();

                    System.out.print("Enter The Citizen's Mother ID: ");
                    motherID0=sc.nextLine();
        
                    if((app.checkIDS(fatherID0)+app.checkIDS(motherID0))!=2){
                            System.out.println("====================================================");
                            System.out.println("ONE OF OR BOTH OF THE PARENTS IDS ARE NOT REGISTERED");
                            System.out.println("====================================================");
                            System.out.println();
                            System.out.println("REVERTING BACK TO MAIN MENU...........");
                        try {
                            Thread.sleep(3000);
                            break;
                            } catch (Exception e) {
                                e.getMessage();
                        }
                    }
                    
                    do{
                        System.out.print(cls);

                        System.out.println("Enter The Citizen's Gender: ");
                        System.out.println("1. Female");
                        System.out.println("2. Male");
                        System.out.print("Entry: ");
                        response = sc.nextLine();
                    }while(!(response.equals("1")) && !(response.equals("2")) || !(response.matches("\\d+")));
                        
                    System.out.println();
                        if(response.equals("1")){
                            gender='F';
                        }
                        
                    do{
                        System.out.println(cls);
                        System.out.print("Enter The Citizen's Year of Birth: ");
                        testYOB = sc.nextLine();
                        isDigit = testYOB.matches("\\d{4}+");
                    }while (!(isDigit));
                        
                        yob = Integer.parseInt(testYOB);    
                        System.out.println();

                        System.out.print("Enter The Citizen's First Name: ");
                        fName=sc.nextLine();
                        
                        System.out.print("Enter The Citizen's Middle Name: ");
                        mName=sc.nextLine();

                        System.out.print("Enter The Citizen's Last Name: ");
                        lName=sc.nextLine();

                        System.out.print("Enter The Citizen's Street: ");
                        street0=sc.nextLine();
                        
                        System.out.print("Enter The Citizen's Town: ");
                        town0=sc.nextLine();

                        System.out.print("Enter The Citizen's Parish: ");
                        parish0=sc.nextLine();

                        System.out.print("Enter The Citizen's Country: ");
                        country0=sc.nextLine();

                        app.registerBirth(gender,yob,fName,mName,lName);
                        app.updateAddress(app.getCurrentCitizenID(), street0, town0, parish0, country0);
                        app.addParentData(app.getCurrentCitizenID(), fatherID0, motherID0);
                        break;

                case "b":
                        String citizenID0, motherID,fatherID;
                        System.out.print(cls);
                        System.out.println("==============================");
                        System.out.println("UPDATING A CITIZEN PARENT DATA");
                        System.out.println("==============================");
                        System.out.println();

                        System.out.print("Enter The Citizen's ID: ");
                        citizenID0=sc.nextLine();
                        
                        System.out.print("Enter The Citizen's Father ID: ");
                        fatherID=sc.nextLine();

                        System.out.print("Enter The Citizen's Mother ID: ");
                        motherID=sc.nextLine();

                        String[] familyIDS={citizenID0,fatherID,motherID};
                        int check=0;
                        for(int count =0;count<3;count++){
                            check+=app.checkIDS(familyIDS[count]);
                        }
                        if(check!=3 || (citizenID0.equals(fatherID)||citizenID0.equals(motherID)||fatherID.equals(motherID))){
                            System.out.println("========================================");
                            System.out.println("IDs ARE EITHER SIMILAR OR NOT REGISTERED");
                            System.out.println("========================================");
                            System.out.println();
                            System.out.println("REVERTING BACK TO MAIN MENU...........");
                            try {
                                Thread.sleep(3000);
                                break;
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                        app.addParentData(citizenID0, fatherID, motherID);
                        break;

                case "c":
                        String citizenID1, street, town, parish, country;
                
                        System.out.print(cls);
                        System.out.print("Enter The Citizen's ID: ");
                        citizenID1= sc.nextLine();
                        System.out.println();

                        if(app.checkIDS(citizenID1)==0){
                            System.out.println("=========================");
                            System.out.println("THIS ID IS NOT REGISTERED");
                            System.out.println("=========================");
                            System.out.println();
                            System.out.println("REVERTING BACK TO MAIN MENU...........");
                            try {
                                Thread.sleep(3000);
                                break;
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }

                        System.out.println("===========================");
                        System.out.println("ENTER THE CITIZEN'S ADDRESS");
                        System.out.println("===========================");
                        System.out.println();

                        System.out.print("Enter The Citizen's Street: ");
                        street=sc.nextLine();
                        
                        System.out.print("Enter The Citizen's Town: ");
                        town=sc.nextLine();

                        System.out.print("Enter The Citizen's Parish: ");
                        parish=sc.nextLine();

                        System.out.print("Enter The Citizen's Country: ");
                        country=sc.nextLine();

                        app.updateAddress(citizenID1, street, town, parish, country);
                        break;
                
                case "d":
                        String citizenID2, cause, place, deathDate;

                        System.out.print(cls);
                        System.out.print("Enter The Citizen's ID: ");
                        citizenID2= sc.nextLine();
                        System.out.println();

                        if(app.checkIDS(citizenID2)==0){
                            System.out.println("=========================");
                            System.out.println("THIS ID IS NOT REGISTERED");
                            System.out.println("=========================");
                            System.out.println();
                            System.out.println("REVERTING BACK TO MAIN MENU...........");
                            try {
                                Thread.sleep(3000);
                                break;
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }

                        if(app.checkCitizenDeathInfo(citizenID2)){
                            System.out.println("============================================");
                            System.out.println("THIS CITIZEN ALREADY HAS A DEATH CERTIFICATE");
                            System.out.println("============================================");
                            System.out.println();
                            System.out.println("REVERTING BACK TO MAIN MENU...........");
                            try {
                                Thread.sleep(3000);
                                break;
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                        birthYear=app.getCitizen(citizenID2).getYOB();
                        System.out.println("==============================");
                        System.out.println("ENTER THE CITIZEN'S DEATH INFO");
                        System.out.println("==============================");
                        System.out.println();

                        System.out.print("Enter The Citizen's Cause of Death: ");
                        cause=sc.nextLine();

                        System.out.print("Enter The Citizen's Place of Death: ");
                        place=sc.nextLine();

                        System.out.print("Enter The Citizen's Death Day(Format: DD/MM/YYYY): ");
                        deathDate=sc.nextLine();

                        if(!isValidDate(deathDate,1)){
                            do{
                            System.out.println("======================================================");
                            System.out.println("THIS IS NOT A VALID DATE, THE CITIZEN WAS BORN IN "+app.getCitizen(citizenID2).getYOB());
                            System.out.println("======================================================");
                            System.out.println();
                            System.out.print("Enter The Citizen's Death Day(Format: DD/MM/YYYY): ");
                            deathDate=sc.nextLine();
                            }while(!isValidDate(deathDate,1));
                        }
                            deathDate=convertDate(deathDate);
                        
                        app.registerDeath(citizenID2, cause, place, deathDate);
                        break;
                
                case "e":
                        String groomID, brideID, date2;
                        System.out.print(cls);
                        System.out.println("=============");
                        System.out.println("MARRIAGE INFO");
                        System.out.println("=============");
                        System.out.println();

                        do{
                            System.out.print("Enter The Groom's ID: ");
                            groomID=sc.nextLine();

                            System.out.print("Enter The Bride's ID: ");
                            brideID=sc.nextLine();

                            if(brideID.equals(groomID)){
                                System.out.println("======================");
                                System.out.println("IDs CANNOT BE THE SAME");
                                System.out.println("======================");
                            }
                        }while(brideID.equals(groomID));

                        if((app.checkIDS(groomID)+app.checkIDS(brideID))!=2){
                            System.out.println("=========================================");
                            System.out.println("ONE OR MORE OF THE IDS ARE NOT REGISTERED");
                            System.out.println("=========================================");
                            System.out.println();
                            System.out.println("REVERTING BACK TO MAIN MENU...........");
                            try {
                                Thread.sleep(3000);
                                break;
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }

                        System.out.print("Enter The Date of Marriage(Format: DD/MM/YYYY): ");
                        date2=sc.nextLine();
                        
                        brideYear=app.getCitizen(brideID).getYOB();
                        groomYear=app.getCitizen(groomID).getYOB();
                        if(!isValidDate(date2,0)){
                            do{
                            System.out.println("===========================================================================");
                            System.out.println("THIS IS NOT A VALID DATE, THE BRIDE WAS BORN IN "+brideYear+" AND THE GROOM IN "+groomYear);
                            System.out.println("===========================================================================");
                            System.out.println();
                            System.out.print("Enter The Date of Marriage(Format: DD/MM/YYYY): ");
                            date2=sc.nextLine();
                            }while(!isValidDate(date2,0));
                        }
                            date2=convertDate(date2);
                        app.registerMarriage(groomID, brideID, date2);
                        break;

                case "f":
                        String citizenID3;
                        System.out.print(cls);
                        System.out.println("=============");
                        System.out.println("MAILING LABEL");
                        System.out.println("=============");
                        System.out.println();

                        System.out.print("Enter The Citizen's ID: ");
                        citizenID3= sc.nextLine();
                        System.out.println();

                        if(app.checkIDS(citizenID3)==0){
                            System.out.println("=========================");
                            System.out.println("THIS ID IS NOT REGISTERED");
                            System.out.println("=========================");
                            System.out.println();
                            System.out.println("REVERTING BACK TO MAIN MENU...........");
                            try {
                                Thread.sleep(3000);
                                break;
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                        System.out.println(app.mailingLabel(citizenID3));
                        break;
                
                case "g":
                        String selection="";
                        while(!(selection.equals("1") || selection.equals("2"))){
                            System.out.print(cls);
                            System.out.println("=================");
                            System.out.println("SEARCHING OPTIONS");
                            System.out.println("=================");
                            System.out.println("1. By ID Number");
                            System.out.println("2. By Citizen's Name");
                            selection = sc.nextLine();   
                        }
                        if(selection.equals("1")){
                            System.out.print(cls);
                            System.out.println("=================");
                            System.out.println("SEARCHING OPTIONS");
                            System.out.println("=================");
                            System.out.print("\nEnter the Citizen's ID: ");
                            String thisID=sc.nextLine();

                            String foundCitizen=app.search(thisID);
                            if(foundCitizen.equals("")){
                                System.out.println("\n================");
                                System.out.println("NO CITIZEN FOUND");
                                System.out.println("================");
                            }else{
                                String[] printCitizen=foundCitizen.split(",");
                                System.out.println("\n=============");
                                System.out.println("CITIZEN FOUND");
                                System.out.println("=============\n");
                                System.out.println("Citizen's ID: "+printCitizen[0]);
                                System.out.println("Citizen's Gender: "+printCitizen[1]);
                                System.out.println("Citizen's Name: "+printCitizen[2]+" "+printCitizen[3]+" "+printCitizen[4]);
                            }
                        }
                        if(selection.equals("2")){
                            System.out.print(cls);
                            System.out.println("=================");
                            System.out.println("SEARCHING OPTIONS");
                            System.out.println("=================");

                            System.out.print("\nEnter the Citizen's First Name: ");
                            String firstName=sc.nextLine();

                            System.out.print("\nEnter the Citizen's Last Name: ");
                            String lastName=sc.nextLine();

                            String[] printCitizen=app.search(firstName,lastName);

                            if(printCitizen==null){
                                System.out.println("\n================");
                                System.out.println("NO CITIZEN FOUND");
                                System.out.println("================");
                            }else{
                                System.out.println("\n=============");
                                System.out.println("CITIZEN FOUND");
                                System.out.println("=============\n");
                                System.out.println("Citizen's ID: "+printCitizen[0]);
                                System.out.println("Citizen's Gender: "+printCitizen[1]);
                                System.out.println("Citizen's Name: "+printCitizen[2]+" "+printCitizen[3]+" "+printCitizen[4]);
                            }
                        }   
                        break;
                default:
                        break;
                }
                if(answer.equals("h")){
                    sc.close();  
                    app.shutdown();
                    break;
                } 

                System.out.println("=========================");
                System.out.println("PRESS <ENTER> TO CONTINUE");
                System.out.print("=========================");
                answer=sc.nextLine();

            }        
        }
    
    /**
     * A method to convert a date given in the format(DD/MM/YYYY).
     * @param date A string representing a date given in the format (DD/MM/YYYY)
     * @return A string representation of the given date in the format (Month Day, Year)
     */
    public String convertDate(String date){
            String[] arr={null,"January","Febraury","March","April","May","June","July","August","September","October","November","December"};
            String[] datePieces=date.split("/");
            return arr[Integer.parseInt(datePieces[1])]+" "+Integer.parseInt(datePieces[0])+", "+datePieces[2];
    }
    
    /**
     * A method to check if a given year(integer) is a Leap Year.
     * @param year An integer representation of the year to be verified
     * @return A boolean to signify if a year is a leap year
     */
    public boolean isLeap(int year) { 
        return (((year % 4 == 0) && (year % 100 != 0)) ||  (year % 400 == 0)); 
    }
    
    /**
     * A method to verify if a date in the given format is a valid date.
     * It checks if the given date matches the given format, false is return if it doesn't.
     * When the type is equal to 1, it checks if the year of the given date is before a citizen's birth year, this is done for when converting the date a citizen die. False is return if it less than the birth year.
     * When the type is equal to 2, it checks if the year of the given date is before each citizen birth year, this is done for when converting the date two citizens marry. False is return if it less than any one of the citizen's birth year.
     * False is return if the given date is before the year 1400 and if it is after the year 9999.
     * @param date String representation of the date in the format (DD/MM/YYYY)
     * @param type An Integer to signify which operation the given date is to undertake, 1 for a death date, 0 for a marriage date
     * @return  A boolean signifying the validity of the given date
     */
    public boolean isValidDate(String date,int type){//DD/MM/YYYY
            final int MAX_VALID_YR = 9999; 
            final int MIN_VALID_YR = 1400;
    
            String[] arr=date.split("/");
        try{

            if(type==1 && Integer.parseInt(arr[2])<birthYear){
                    return false;
            }

            if(type==0 && (Integer.parseInt(arr[2])<brideYear || Integer.parseInt(arr[2])<groomYear)){
                return false;
            }
            int d=Integer.parseInt(arr[0]);
            int m=Integer.parseInt(arr[1]);
            int y=Integer.parseInt(arr[2]);
    
            if (y > MAX_VALID_YR || y < MIN_VALID_YR) 
                return false; 
            if (m < 1 || m > 12) 
                return false; 
            if (d < 1 || d > 31) 
                return false; 
      
            if (m == 2)  
            { 
                if (isLeap(y)) 
                    return (d <= 29); 
                else
                    return (d <= 28); 
            } 
      
            if (m == 4 || m == 6 ||  
                m == 9 || m == 11) 
                return (d <= 30); 
      
            return true; 
        }catch(Exception e){
            return false;
        }
    }
}
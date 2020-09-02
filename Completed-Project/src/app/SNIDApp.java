package app;
import data.*;
import snid.*;
import java.util.*;
import java.io.*;

/**
 * @author KabianDavidson
 */
public class SNIDApp{
    private SNIDDb data;
    private ArrayList<Citizen> records;
    private char delimiter;
    private String currentCitizenID;
    private File idFile = new File("idFile.txt");
    private File deathRefNoFile = new File("deathRefNo.txt");
    private File marriageCertsFile = new File("Marriage_Certificates.txt");
    private File marriageRefNoFile = new File("marriageRefNo.txt");

    /**
     * A Constructor for the SNIDApp class.
     * Takes in a file and a character separator.
     * Reads the Information from the file and creates an arrayList to store Citizen's Information.
     * Instanstiate a SNIDDb object using the same file and character separator.
     * @param filename Stores the name of the file as a string.
     * @param delimiter Stores the chracter separator to split each line in the file. 
     */
    public SNIDApp(String filename,char delimiter){
        this.delimiter=delimiter;
        records = new ArrayList<Citizen>();
        data = new SNIDDb(filename,delimiter);
        while(data.hasNext()){
            String[] arr=data.getNext();
            boolean isDigit = arr[0].matches("\\d+"); //Checks if the first element is a digit, matching it against the regular expression(Predefined Character)
            
            if(arr.length<14){ //Fills the array with empty string for missing citizen information
                for(int fillIndex=arr.length;fillIndex<14;fillIndex++){
                        arr=addElement(arr,"");
                }
            }

            if(isDigit){  
                try{
                    Citizen myCitizen = new Citizen(arr[0], arr[1], arr[2], arr[3], arr[4].charAt(0), Integer.valueOf(arr[5]), arr[6], arr[7], arr[8], arr[9], arr[10], arr[11], arr[12], arr[13]);
                    records.add(myCitizen);
                } catch (NullPointerException|IndexOutOfBoundsException ex) {
                    ex.getMessage();
                    continue;
                } 
           }
       }
    }

    /**
     * A method to return a Citizen given their ID.
     * @param id String representation of a Citizen's Unique Identification.
     * @return A Citizen object with the given ID.
     */
    public Citizen getCitizen(String id){
        for(Citizen thisCitizen:records){
            if(thisCitizen.getId().equals(id)){
                return thisCitizen;
            }
        }
        return null;
    }

    /**
     * A method to add a given element to a given array.
     * @param arr The array in which the element is to be added to.
     * @param element The element to be added to the array.
     * @return An array with the element added to the array.
     */

    public String[] addElement(String[] arr, String element) { 

        List<String> arrlist = new ArrayList<String>(Arrays.asList(arr)); 
        arrlist.add(element);
        arr = arrlist.toArray(arr); 
        return arr; 
    } 
    
    /**
     * A method to add a citizen to the arrayList created in the Constructor.
     * It checks if the IdFile exists(That is the file that stores the ID of the last Citizen registered).
     * If the file exists it reads the value from this file, increment it by one and set the new value as the new Citizen ID whilst storing this value back to the file.
     * If it doesn't exists, it creates the file, placing the static ID in the file, that is to be read from and incremented when another citizen is to be registered.
     * Sets the Citizen's lifestatus to "Alive".
     * Files are created and/or open with try-resources so the scanner and writer are automatically closed.
     * @param gender The gender of the citizen, represented as a character 'M'/'F'.
     * @param yob    The year in which the Citizen was born.
     * @param fName  The first name of the Citizen.
     * @param mName  The middle name of the Citizen.
     * @param lName  The last name of the Citizen.
     */
    public void registerBirth(char gender,int yob, String fName, String mName, String lName){
        String thisID="";
            if(idFile.exists()){
                try (Scanner sc=new Scanner(idFile)) {
                    thisID="00"+String.valueOf(Integer.valueOf(sc.nextLine())+1);
                }
                catch (Exception e) {
                    e.getMessage();
                }
                try (FileWriter fWriter= new FileWriter(idFile)) {
                    fWriter.write(thisID);
                    Citizen myCitizen = new Citizen(gender,yob,fName,mName,lName);
                    myCitizen.setID(thisID);
                    this.currentCitizenID=myCitizen.getId();
                    myCitizen.setLifeStatus(0);
                    records.add(myCitizen);
                } catch (Exception e) {
                    e.getMessage();
                }
            }else{
                try (FileWriter fWriter= new FileWriter(idFile)){
                    idFile.createNewFile();
                    Citizen myCitizen = new Citizen(gender,yob,fName,mName,lName);
                    this.currentCitizenID=myCitizen.getId();
                    myCitizen.setLifeStatus(0);
                    records.add(myCitizen);
                    fWriter.write(this.currentCitizenID);
                }
                catch (Exception e) {
                    e.getMessage();
                }
            }
    }

    /**
     * A method to get the ID of the Citizen currently being registered.
     * @return A string representation of the Citizen's Unique Identification.
     */
    public String getCurrentCitizenID(){
        return currentCitizenID;
    }

    /**
     * A method to link a child to his/her Parents.
     * The method checks if each of the ID given are valid.
     * If the IDs given are all valid, the method "setParent" is called, by which the Parent's Information is added to child.
     * @param childID   The string representation of the child's ID.
     * @param fatherID  The string representation of the father's ID.
     * @param motherID  The string representation of the mother's ID.
     */
    public void addParentData(String childID, String fatherID, String motherID){
        int childIndex=-1;
        for(int count=0;count<records.size();count++){
            if(records.get(count).getId().equals(childID)){
                childIndex=count;
                break;
            }
        }
        if(childIndex!=-1){
            for(Citizen parent:records){
                if(parent.getId().equals(fatherID)){
                    records.get(childIndex).setParent('F', parent);
                    records.get(childIndex).setDadID(fatherID);
                }
                if(parent.getId().equals(motherID)){
                    records.get(childIndex).setParent('M', parent);
                    records.get(childIndex).setMomID(motherID);
                }
            }
        }
    }

    /**
     * A method to check if the ID given exists.
     * returns 1 for true, 0 for false.
     * @param ID The ID to be verified.
     * @return An Integer signifying validity.
     */
    public int checkIDS(String ID){
        for(Citizen thisCitizen:records){
            if(thisCitizen.getId().equals(ID)){
                return 1;
            }
        }
        return 0;
    }

    /**
     * A method to change where a citizen lives.
     * @param citizenID The ID of the citizen's address to be changed.
     * @param street    The citizen's new street.
     * @param town      The citizen's new town.
     * @param parish    The citizen's new parish.
     * @param country   The citizen's new country.
     */
    public void updateAddress(String citizenID, String street, String town, String parish, String country){
        for(int index=0;index<records.size();index++){
            if(records.get(index).getId().equals(citizenID)){
                records.get(index).setAddress(new Address(street+"|"+town+"|"+parish+"|"+country));
                break;
            }
        }
    }

    /**
     * A method to a register a citizen as deceased.
     * It instantiate a DeathCert object.
     * It checks if the deathRefNoFile exists(That is the file that stores the Death Certificate ID of the last Citizen registered).
     * If the file exists it reads the value from this file, increment it by one and set the new value as the new Death Certificate ID whilst storing this value back to the file.
     * If it doesn't exists, it creates the file, placing the static ID in the file, that is to be read from and incremented when another citizen is to be registered.
     * Sets the Citizen's lifestatus to "Deceased".
     * Creates a file for each citizen that stores their death certificate inside of the folder Registered Deceased.
     * Files are created and/or open with try-resources so the scanner and writer are automatically closed.
     * @param citizenID A String representation of the citizen's ID.
     * @param cause     The reason for the citizen's death.
     * @param place     The place the citizen died.
     * @param date      The date the citizeb died.
     */
    public void registerDeath(String citizenID, String cause, String place, String date){
        DeathCert deathCert = new DeathCert(cause, date, place);
        String newRef="";

        for(int index=0;index<records.size();index++){
            if(records.get(index).getId().equals(citizenID)){
                records.get(index).addDeathCert(deathCert);
                records.get(index).setLifeStatus(1);
                break;
            }
        }

        File citizenFile = new File("Registered Deceased", citizenID+".txt"); //Used to check if the citizen has already been registered for a death certificate 

        if(deathRefNoFile.exists()){
            try (FileWriter fWriter= new FileWriter(citizenFile); Scanner sc = new Scanner(deathRefNoFile)){
                newRef=Integer.toString(Integer.valueOf(sc.nextLine())+1);
                deathCert.setRefNo(newRef);
                fWriter.write("Citizen's ID: "+citizenID+"\n"+deathCert.toString());
            }
            catch (Exception e) {
                e.getMessage();
            }
            try (FileWriter fWriter= new FileWriter(deathRefNoFile)) {
                fWriter.write(newRef);
            } catch (Exception e) {
                e.getMessage();
            }
        }else{
            try (FileWriter fWriter= new FileWriter(citizenFile);FileWriter fWriter2= new FileWriter(deathRefNoFile)){
                citizenFile.createNewFile();
                deathRefNoFile.createNewFile();
                fWriter2.write(deathCert.getRefNo());
                fWriter.write("Citizen's ID: "+citizenID+"\n"+deathCert.toString());
            }
            catch (Exception e) {
                e.getMessage();
            }
        }
    }

    /**
     * A method to check whether a citizen has been registered as deceased.
     * It checks for the citizen death certificate file, which is the name of their ID.
     * @param ID The string representation of the Citizen ID.
     * @return A boolean that signifies the existence of a citizen death certificate.
     */
    public boolean checkCitizenDeathInfo(String ID){
        return new File("Registered Deceased", ID+".txt").exists();
    }

    /**
     * A method to a register two citizens as marrried.
     * It checks if each of the given ids are valid.
     * It instantiate a MarrriageCert object.
     * It checks if the marriageRefNoFile exists(That is the file that stores the Marriage Certificate ID of the last Citizen registered).
     * If the file exists it reads the value from this file, increment it by one and set the new value as the new Marriage Certificate ID whilst storing this value back to the file.
     * If it doesn't exists, it creates the file, placing the static ID in the file, that is to be read from and incremented when two other citizens are to be married.
     * It changes the bride's last name to the groom's last name.
     * It creates a marriage certificate files that stores the marriage certificates of all citizens.
     * Files are created and/or open with try-resources so the scanner and writer are automatically closed.
     * @param groomID   The String representation of the Groom ID.
     * @param brideID   The String representation of the Bride ID.
     * @param date      The Date in which the two citizens married.
     */
    public void registerMarriage(String groomID, String brideID, String date){
        String groomLastName="";
        String newRef="";
        MarriageCert marriageCert = new MarriageCert(date, brideID, groomID);

        for(int groomIndex=0;groomIndex<records.size();groomIndex++){
            if(records.get(groomIndex).getId().equals(groomID)){
                records.get(groomIndex).addMarriageCert(marriageCert);
                groomLastName = records.get(groomIndex).getFullName().getLastName();
                break;
            }
        }
        if(!groomLastName.equals("")){
            for(int brideIndex=0;brideIndex<records.size();brideIndex++){
                if(records.get(brideIndex).getId().equals(brideID)){
                    records.get(brideIndex).addMarriageCert(marriageCert);
                    records.get(brideIndex).changeLastName(groomLastName);
                    break;
                }
            }
        }
        if(marriageCertsFile.exists() && marriageRefNoFile.exists()){
            try (FileWriter fWriter= new FileWriter(marriageCertsFile, true); Scanner sc = new Scanner(marriageRefNoFile)){
                newRef=Integer.toString(Integer.valueOf(sc.nextLine())+1);
                marriageCert.setRefNo(newRef);
                fWriter.write(marriageCert.toString()+"\n\n");
            }
            catch (Exception e) {
                e.getMessage();
            }
            try (FileWriter fWriter= new FileWriter(marriageRefNoFile)) {
                fWriter.write(newRef);
            } catch (Exception e) {
                e.getMessage();
            }
        }else{
            try (FileWriter fWriter= new FileWriter(marriageCertsFile);FileWriter fWriter2= new FileWriter(marriageRefNoFile)){
                marriageCertsFile.createNewFile();
                marriageRefNoFile.createNewFile();
                fWriter2.write(marriageCert.getRefNo());
                fWriter.write(marriageCert.toString()+"\n\n");
            }
            catch (Exception e) {
                e.getMessage();
            }
        }     
    }

    /**
     * A method to return a Citizen's Mailing Label.
     * @param citizenID The string representation of the Citizen ID.
     * @return A string to represent the Citizen's mailing label.
     */
    public String mailingLabel(String citizenID){
        for(Citizen thisCitizen:records){
            if(thisCitizen.getId().equals(citizenID)){
                return thisCitizen.getName()+"\n"+thisCitizen.getAddress();
            }
        }
        return "";
    }

    /**
     * A method to get a citizen's mother information.
     * @param citizenID The string representation of the citizen ID.
     * @return  A string representation of the mother's information - ID, First Name, Middle Name, Last Name.
     */
    public String getMother(String citizenID){
        String motherID="";
        for(Citizen thisCitizen:records){
            if(thisCitizen.getId().equals(citizenID)){
                motherID=thisCitizen.getParent('M').getId();
                break;
            }
        }

        if(motherID.equals("")){    //The citizen doesn't exist, return empty string
            return motherID;
        }else{
            for(Citizen thisCitizen:records){
                if(thisCitizen.getId().equals(motherID)){
                    return thisCitizen.getId()+","
                           +thisCitizen.getFullName().getFirstName()+","
                           +thisCitizen.getFullName().getMiddleName()+","
                           +thisCitizen.getFullName().getLastName();
                }
            }
            return "";  //The mother doesn't exist, return empty string
        }
    }

    /**
     * A method to get a citizen's father information.
     * @param citizenID The string representation of the citizen ID.
     * @return  A string representation of the father's information - ID, First Name, Middle Name, Last Name.
     */
    public String getFather(String citizenID){
        String fatherID="";
        for(Citizen thisCitizen:records){
            if(thisCitizen.getId().equals(citizenID)){
                fatherID=thisCitizen.getParent('F').getId();
                break;
            }
        }

        if(fatherID.equals("")){    //The citizen doesn't exist, return empty string
            return fatherID;
        }else{
            for(Citizen thisCitizen:records){
                if(thisCitizen.getId().equals(fatherID)){
                    return thisCitizen.getId()+","
                           +thisCitizen.getFullName().getFirstName()+","
                           +thisCitizen.getFullName().getMiddleName()+","
                           +thisCitizen.getFullName().getLastName();
                }
            }
            return "";  //The father doesn't exist, return empty string
        }
    }

    /**
     * A method to locate a citizen, given their ID.
     * An empty string is returned if citizen is not found.
     * @param citizenID The string representation of the citizen ID.
     * @return  A string representation of the citizen's information - ID, Gender, First Name, Middle Name, Last Name.
     */
    public String search(String citizenID){
        String citizenGender="";
        for(Citizen thisCitizen:records){
            if(thisCitizen.getId().equals(citizenID)){
                if(thisCitizen.getGender()=='M'){
                    citizenGender="Male";
                }else{
                    citizenGender="Female";
                }
                return thisCitizen.getId()+","
                       +citizenGender+","
                       +thisCitizen.getFullName().getFirstName()+","
                       +thisCitizen.getFullName().getMiddleName()+","
                       +thisCitizen.getFullName().getLastName();
            }
        }
        return "";
    }

    /**
     * A method to get the ArrayList of Citizens.
     * @return an ArrayList with the information of each citizen.
     */
    public List<Citizen> getRecords(){
        return records;
    }

    /**
     * A method to locate a citizen, given their first and last name.
     * null is returned if citizen is not found.
     * @param firstName The Citizen's First Name.
     * @param lastName  The Citizen's Last Name.
     * @return A string array of the citizen's information - ID, Gender, First Name, Middle Name, Last Name.
     */
    public String[] search(String firstName, String lastName){
        for(Citizen thisCitizen:records){
            if(thisCitizen.getFullName().getFirstName().equalsIgnoreCase(firstName) &&
               thisCitizen.getFullName().getLastName().equalsIgnoreCase(lastName)){
                   return search(thisCitizen.getId()).split(",");
               }
            }
        return null;
    }
    /**
     * A method to close the file from which the citizen's information was in and open it for writing.
     * The updated information of each citizen is written back to the file.
     */
    public void shutdown(){
        data.rewrite();
        String lifeStatus;
        int stop=records.size();
        int count=1;
        for(Citizen thisCitizen:records){
            if(thisCitizen.getLifeStatus()=='A'){
                lifeStatus="Alive";
            }else{
                lifeStatus="Deceased";
            }
            String[] citizenInfo={thisCitizen.getId(),thisCitizen.getFullName().getFirstName(),
                                  thisCitizen.getFullName().getMiddleName(),thisCitizen.getFullName().getLastName(),
                                  Character.toString(thisCitizen.getGender()),Integer.toString(thisCitizen.getYOB()),
                                  lifeStatus,""};

            String[] addressInfo=thisCitizen.getAddress().toString().split("\n");

            String[] parentsInfo = {Character.toString(delimiter),thisCitizen.getDadID(),thisCitizen.getMomID()};
            
            if(count==stop){
                data.putNext(citizenInfo);
                data.putNext(addressInfo);
                data.putNext(parentsInfo);    
            }else{
                data.putNext(citizenInfo);
                data.putNext(addressInfo);
                data.putNext(parentsInfo);  
                String[] newLine={"\n"};
                data.putNext(newLine);
            }
        count++;
        }
        try {
            System.out.println("TERMINATING SYSTEM......");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.getMessage();
        }
        data.close();
        System.exit(0);
    }
}
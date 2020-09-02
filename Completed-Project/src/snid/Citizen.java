package snid;
import java.util.ArrayList;

/**
 * @author KabianDavidson
 */
public class Citizen extends Person implements Comparable<Citizen>{
    private String ID;
    private Address address;
    private Name name;
    private String momID;
    private String dadID;
    private ArrayList<CivicDoc> papers = new ArrayList<CivicDoc>();
    
    static int Index=9100;

    /**
     * Used for "creating" new citizens info.
     * A constructor for the Citizen class.
     * Instantiate a Name object.
     * @param Gender for the gender of the Citizen class
     * @param YOB for the year of birth of the Citizen class
     * @param Fname for the First Name of the Citize class
     * @param Mname for the Middle Name of the Citizen class
     * @param Lname for the Last Name of the Citizen class
     */
    public Citizen(char Gender, int YOB, String Fname, String Mname, String Lname){
        super(Gender,YOB);
        this.ID="00"+Integer.toString(Index++);
        name=new Name(Fname, Mname, Lname);
    }

    /**
     * Used for "accepting" citizens info.
     * A constructor for the Citizen class.
     * Instantiate a Name object.
     * Instantiate an Address object.
     * @param citizenID     A String representation of the citizen's ID
     * @param Fname         The citizen's first name
     * @param Mname         The citizen's middle name
     * @param Lname         The citizen's last name
     * @param Gender        The citizen's gender name
     * @param YOB           The citizen's year of birth name
     * @param lifeStatus    The citizen's life status - Alive/Deceased
     * @param Address1      The citizen's Address first line
     * @param Address2      The citizen's Address second line
     * @param Address3      The citizen's Address third line
     * @param Address4      The citizen's Address fourth line
     * @param Address5      The citizen's Address fifth line
     * @param momID         The citizen's Mother ID
     * @param dadID         The citizen's Father ID
     */
    public Citizen(String citizenID,String Fname, String Mname, String Lname, char Gender, int YOB, String lifeStatus, String Address1, String Address2, String Address3, String Address4, String Address5, String dadID, String momID){
        super(Gender,YOB);
        this.ID=citizenID;
        name=new Name(Fname, Mname, Lname);
        address=new Address(Address1+"|"+Address2+"|"+Address3+"|"+Address4+"|"+Address5);
        if(lifeStatus.equals("Deceased")){
            setLifeStatus(1);
        }
        if(lifeStatus.equals("Alive")){
            setLifeStatus(0);
        }
        this.momID=momID;
        this.dadID=dadID;
    }
    
    /**
     * A method to return the Citizen's ID.
     * @return a String to represent the Citizen's ID.
     */
    public String getId(){
        return ID;
    }

    /**
     * A method to return the Citizen's Father ID.
     * @return a String to represent the Citizen's Father ID.
     */
    public String getDadID(){
        return dadID;
    }

    /**
     * A method to return the Citizen's Mother ID.
     * @return a String to represent the Citizen's Mother ID.
     */
    public String getMomID(){
        return momID;
    }

    /**
     * A method to set the Citizen's Mother ID.
     * @param momID a String representing the Mother's ID.
     */
    public void setMomID(String momID){
        this.momID=momID;
    }

    /**
     * A method to set the Citizen's Father ID.
     * @param dadID a String representing the Father's ID.
     */
    public void setDadID(String dadID){
        this.dadID=dadID;
    }

    /**
     * A method to add marriage certificate to the arrayList "papers" that stores a citizen's certificates.
     * @param cert The MarriageCert object to be added to the arrayList.
     */
    public void addMarriageCert(MarriageCert cert){
        papers.add(cert);
    }

    /**
     * A method to add death certificate to the arrayList "papers" that stores a citizen's certificates.
     * @param cert The DeathCert object to be added to the arrayList.
     */
    public void addDeathCert(DeathCert cert){
        papers.add(cert);
    } 

    /**
     * A method to set the citizen's ID, based on info from the file that stores the last entered citizen.
     * @param fileID a String reoresentation of the ID that is to be the Citizen's ID.
     */
    public void setID(String fileID){
        this.ID=fileID;
    }
       
    /**
     * A method that accepts an address and sets it as the Citizen's Address.
     * @param address to represent the Citizen's Address.
     */
    public void setAddress(Address address){
        this.address=address;
    }

    /**
     * A method to get the Citizen's Address.
     * @return An Address object that represents the Citizen's Address.
     */
    public Address getAddress(){
        return address;
    }

    /**
     * A method that accepts a string that represents the Citizen's New Last name.
     * and sets this as the Citizen's name.
     * @param newLast a string that represents the Citizen's New Last name.
     */
    public void changeLastName(String newLast){
        name.setLastName(newLast);
    }

    /**
     * A method that gets the Citizen's Name.
     * @return A String to represent the Citizen's name in a specific format.
     */
    public String getName(){
        if(name.getMiddleName().equals("")){
            return name.getLastName().toUpperCase()+", "+name.getFirstName()+".";
        }
        return name.getLastName().toUpperCase()+", "+name.getFirstName()+" "+
        Character.toUpperCase(name.getMiddleName().charAt(0))+".";
    }

    /**
     * A method that gets the Name object of a Citizen.
     * @return A Name object for the citizen.
     */
    public Name getFullName(){
        return name;
    }

    /**
     * A method that accepts and a Citizen object and Compares it to another.
     * @return An int to represent how close one's Citizen Id is to another.
     */

    public int compareTo(Citizen other){
        Integer ID1 = Integer.valueOf(getId());
        Integer ID2 = Integer.valueOf(other.getId());

        return ID1.compareTo(ID2);
    }
}
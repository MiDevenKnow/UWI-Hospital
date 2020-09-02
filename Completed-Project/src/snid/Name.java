package snid;

/**
 * @author KabianDavidson
 */
public class Name{
    private String fname;
    private String mname;
    private String lname;
    
    /**
     * A constructor for the Name Class.
     * @param fname for the First Name of the object
     * @param mname for the Middle Name of the object
     * @param lname for the Last Name of the object
     */
    public Name(String fname, String mname, String lname){
        this.fname=fname;
        this.mname=mname;
        this.lname=lname;
    }

    /**
     * A method that gets the First Name of the object.
     * @return a string that represents the object's First Name
     */
    public String getFirstName(){
        return fname;
    }

    /**
     * A method that gets the Middle Name of the object.
     * @return a string that represents the object's Middle Name
     */
    public String getMiddleName(){
        return mname;
    }

    /**
     * A method that gets the Last Name of the object.
     * @return a string that represents the object's Last Name
     */
    public String getLastName(){
        return lname;
    }

    /**
     * A method that sets the object's Last Name.
     * @param lastName to represent the new object's Last Name
     */
    public void setLastName(String lastName){
        lname=lastName;
    }

    /**
     * A method that depicts the object's representation.
     * @return A string representation of the Name object 
     */
    public String toString(){
        return fname + " " + mname + " " + lname;
    }

    /**
     * A method that checks if two objects of the Name class are the same.
     * @param PersonName for the object to be compared
     * @return A boolean(True or False)
     */
    public boolean equals(Name PersonName){
        return fname.equals(PersonName.getFirstName()) && mname.equals(PersonName.getMiddleName()) && lname.equals(PersonName.getLastName());
    }
}
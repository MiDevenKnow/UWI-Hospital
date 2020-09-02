package snid;

/**
 * @author KabianDavidson
 */
public class MarriageCert implements CivicDoc {
    
    private String refNo;
    private String brideID;
    private String groomID;
    private String date;
    private static int ref = 2000;
    
    /**
     * A constructor for the MarriageCert Class.
     * @param date      a String representing the date of marriage
     * @param brideID   a String representing Groom's ID
     * @param groomID   a String representing Bride's ID
     */
    public MarriageCert(String date, String brideID, String groomID)    
    {
        refNo = Integer.toString(ref++);
        this.date=date;
        this.brideID=brideID;
        this.groomID=groomID;
    }

    /**
     * A method to get the reference number of the Marriage Certificate.
     * @return A String representing the marriage certificate reference number
     */
    public String getRefNo()
    {
        return refNo;
    }

    /**
     * A method to set the reference number of the marriage certificate.
     * @param ref A String representing the marriage certificate reference number
     */
    public void setRefNo(String ref){
        this.refNo=ref;
    }

    /**
     * A method that depicts the object's representation.
     * @return A String representation of the MarriageCert Class
     */
    public String toString()
    {
        return "Marriage Cert#: " + this.refNo + "\n" 
                + "Bride's ID: " + this.brideID + "\n" 
                + "Groom's ID: " + this.groomID + "\n"
                + "Date: " + this.date;
    }
}
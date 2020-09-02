package snid;

/**
 * @author KabianDavidson
 */
public class DeathCert implements CivicDoc
{
    private String cause;
    private String date;
    private String place;
    private String refNo;
    private static int ref = 1000;
    
    /**
     * A constructor for the DeathCert object.
     * @param cause a String representing the cause of death
     * @param date  a String representing the date of death
     * @param place a String representing the place of death
     */
    public DeathCert(String cause, String date, String place)
    {
        refNo=Integer.toString(ref++);
        this.cause=cause;
        this.date=date;
        this.place=place;
    }
    
    /**
     * A method to get the reference number of the death certificate.
     * @return A String representing the death certificate reference number
     */
    public String getRefNo()
    {
        return refNo;
    }

    /**
     * A method to set the reference number of the death certificate.
     * @param ref A String representing the death certificate reference number
     */
    public void setRefNo(String ref){
        this.refNo=ref;
    }

    /**
     * A method that depicts the object's representation.
     * @return A String representation of the DeathCert Class
     */
    public String toString()
    {
        return "Death Cert#: "+refNo + "\n" 
                + "Cause: " + this.cause + "\n" 
                + "Date: " + this.date + "\n" 
                + "Place: " + this.place;
    } 
}
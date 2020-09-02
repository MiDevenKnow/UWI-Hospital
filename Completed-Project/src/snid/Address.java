package snid;
import java.util.*;
/**
 * @author KabianDavidson-620129358, KadianDavidson-620128568, DoniqueForbes-620129382
 */

public class Address{
    private String[] AddressParts = new String[100];

     /**
      * A constructor for the Address class.
      * @param Location for the Location of the object.
      */
    public Address(String Location){
        AddressParts = Location.split("\\|");
        
        for(int i=0;i<AddressParts.length;i++){
            if(AddressParts[i].equals("")){
                continue;
            }
            else{
                this.AddressParts[i]=AddressParts[i];
            }
        }

        if(AddressParts.length<5){
            for(int count =0;count<5;count++){
                AddressParts=addElement(AddressParts, "");
            }
        }
    }

     /**
      * A method to get the country of the Address class
      * @return A String that represents the object's country
      */
    public String getCountry(){
            return AddressParts[AddressParts.length-2];
    }

    /**
     * A method to add a given element to a given array
     * @param arr The array in which the element is to be added to
     * @param element The element to be added to the array
     * @return An array with the element added to the array
     */
    public String[] addElement(String[] arr, String element) { 
        List<String> arrlist = new ArrayList<String>(Arrays.asList(arr)); 
        arrlist.add(element); 
        arr = arrlist.toArray(arr); 
        return arr; 
    } 

    /**
     * A method that depicts the object's representation
     * @return A String representation of the Address class
     */
    public String toString(){
        return AddressParts[0]+"\n"+AddressParts[1]+"\n"+AddressParts[2]+"\n"+AddressParts[3]+"\n"+AddressParts[4];
    }
}
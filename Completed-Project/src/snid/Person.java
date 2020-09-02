package snid;

/**
 * @author KabianDavidson
 */
public abstract class Person{

    protected char gender;
    protected int year;
    protected char lifeStatus;
    protected Person[] parents = new Person[2];  //More than 1 parent so the incorporation of an array is needed
    protected String tag;

    /**
     * A Constructor for the Person class.
     * @param gender for the gender of the Person Class
     * @param year for the Year of Birth of the Person Class
     */
    public Person(char gender, int year){
        this.gender=Character.toUpperCase(gender);
        this.year=year; 
   }

   /**
    * An abstract method to get the Person's ID.
    * @return A String to represent the Person's ID
    */
    public abstract String getId(); //Abstract String method

    /**
     * A method to get the Person's Gender.
     * @return A char to represent the Person's Gender
     */
    public char getGender(){
        return gender;
    }

    /**
     * A method to get the Person's Year of Birth.
     * @return An int to represent the Person's Year of Birth
     */
    public int getYOB(){
        return year;
    }

    /**
     * A method that accepts the Person's Life Status as an int and sets the corresponding char value.
     * @param status An int to represent the Person's status
     */
    public void setLifeStatus(int status){
        if(status==0){
            lifeStatus='A';
        }
        if(status==1){
            lifeStatus='D';
        }
    }

    /**
     * A method to get the Person's Life Status.
     * @return A char to reprsent the Person's Life Status
     */
    public char getLifeStatus(){
        return lifeStatus;
    }

    /**
     * A method that accepts a char to represent the type of person and a Person object
     * and sets the Parent object to its particular location corresponding to its type.
     * @param type A char to represent the type of Person, M-Mother / F-Father
     * @param parent The Parent object to be stored
     */
    public void setParent(char type,Person parent){
        type=Character.toUpperCase(type);

        if (parent.getGender()=='M' && type=='F'){ 
            this.parents[0]=parent;         
        }                           
        if (parent.getGender()=='F' && type=='M'){ 
            this.parents[1]=parent;         
        }     
    }

    /**
     * A method that accepts a char to represent the type of Person and gets its corresponding type.
     * @param type A char to represent the type of Person, M-Mother / F-Father
     * @return The Person object corresponding to the given type 
     */
    public Person getParent(char type){

        type=Character.toUpperCase(type);

        if (type=='F'){ 
            return parents[0];         
        }   

        if (type=='M'){ 
            return parents[1];         
        }     
        return null;
    }

      /**
       * A method that depicts the object's representation.
       * @return A String representation of the Person object
       */
    public String toString(){
        return "Gender: "+getGender()+"\nYear: "+getYOB()+"\nLife Status: "+getLifeStatus();
    }

}
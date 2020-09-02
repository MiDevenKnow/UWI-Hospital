package data;
import java.io.*;

/**
 * @author KabianDavidson
 */
public class SNIDDb {
    private String filename,line="";
    private char delimiter;
    private FileReader myReaderFile;
    private FileWriter myWriterFile;
    private BufferedReader bReader;
    private BufferedWriter bWriter;

    /**
     * A Constructor for the SNIDDb class.
     * Takes in a file and a character separator.
     * Opens the given file for reading,
     * @param filename Stores the name of the file as a string.
     * @param delimiter Stores the chracter separator to split each line in the file.
     */
    public SNIDDb(String filename, char delimiter){
        this.filename=filename;
        this.delimiter=delimiter;

        try {
            myReaderFile = new FileReader(filename);
            bReader = new BufferedReader(myReaderFile);

        } catch (FileNotFoundException ex) {
            System.out.println("CANNOT ACCESS FILE");
        }
        
    }

    /**
     * A method to check if the given file has a next line.
     * @return A boolean signifying the existence of a next line in the file.
     */
    public boolean hasNext(){
        try {
            if((line=bReader.readLine())!=null){
                return true;
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
        return false;
    }

    /**
     * A method to get the line in the file at the current cursor position.
     * @return A String array of the current line of information stored in the file.
     */
    public String[] getNext(){
        return line.split(String.valueOf(delimiter));
    }

    /**
     * A method to close the file and re-open it for writing.
     */
    public void rewrite(){
        try {
            bReader.close();
            myWriterFile = new FileWriter(filename);
            bWriter = new BufferedWriter(myWriterFile);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * A method to write the information stored in the given array to the file, each index separated by the delimeter.
     * @param arr an Array with the information to be entered.
     */
    public void putNext(String[] arr){
        try {
            for(int count=0;count<arr.length;count++){
                if(count==arr.length-1){
                    bWriter.write(arr[count]);
                }else{
                    bWriter.write(arr[count]+Character.toString(delimiter));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * A method to close the file after it's been opened for writing.
     */
    public void close(){
        try{
        bWriter.close();
        }catch(Exception e){
            e.getMessage();
        }
    }
}
package launch;

import app.*;
import ui.*;
import gui.*;

/**
 * @author KabianDavidson
 */
public class Main{
    
    public static void main(String[] args){
        
        SNIDApp app = new SNIDApp("SNID0.txt",',');
        TextUI textUI = new TextUI();
        String ans;

            do{
                    System.out.println("\033[H\033[2J");
                    System.out.println("System For National Identification (SNID)");
                    System.out.println("=========================================");
                    System.out.println("                MAIN MENU                ");
                    System.out.println("=========================================");
                    System.out.println();
                    System.out.println("1.LAUNCH THE TEXT-LINE INTERFACE\n");
                    System.out.println("2.LAUNCH THE GRAPHICAL-USER INTERFACE");
                    System.out.print("\nENTRY: ");
                    ans=System.console().readLine();
            }while(!ans.equals("1") && !ans.equals("2"));
        
        
        if(ans.equals("1")){
            textUI.go(app);
        }
        new Gui(app);
    }
}
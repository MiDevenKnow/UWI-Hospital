package gui;

import app.*;
import snid.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.Position;

/**
 * @author KabianDavidson-620129358, KadianDavidson-620128568, DoniqueForbes-620129382
 */
public class Gui {
    private ArrayList<String> names = new ArrayList<String>();

    /**
     * A constructor for the Gui class.
     * The constructor accepts and SNIDApp that allows data to be read from a file.
     * Creates the specifc gui and allows for search operations by id and last name.
     * @param app The SNIDApp with the filename and delimeter to allow for data to be read.
     */
    public Gui(SNIDApp app) {
        JFrame guiFrame = new JFrame("System For National Identification (SNID)");

        ButtonGroup radios = new ButtonGroup();
        ButtonGroup buttons = new ButtonGroup();

        JRadioButton id = new JRadioButton("Search By ID"); 
        JRadioButton name = new JRadioButton("Search By Name");
        JRadioButton biometric = new JRadioButton("Biometric Search");

        JButton search = new JButton("SEARCH"); search.setBackground(Color.green);
        JButton clear = new JButton("CLEAR"); clear.setBackground(Color.green);
        JButton quit = new JButton("QUIT"); quit.setBackground(Color.green);

    JPanel guiPanel = new JPanel();

        DefaultListModel<String> idList = new DefaultListModel<>();
        JList<String> idArea = new JList<>(idList);  
        JScrollPane scrollPane = new JScrollPane(idArea);
        JLabel listHeader = new JLabel("LIST OF IDs");
    
    JTextArea displayArea = new JTextArea();
    JTextField textField = new JTextField("User Enter Search String Here", 30);


        //OPERATIONS ON THE LIST AREA OF IDS
        scrollPane.setPreferredSize(new Dimension(200,50));
        scrollPane.setColumnHeaderView(listHeader);
        listHeader.setFont(listHeader.getFont().deriveFont(Font.ITALIC + Font.BOLD));
        listHeader.setHorizontalAlignment(0);
        listHeader.setForeground(Color.RED);
        idArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        idArea.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){
                String[] citizenInfo = app.search(idArea.getSelectedValue()).split(",");
                String text= "Citizen's ID: "+citizenInfo[0]+"\n"
                            +"Citizen's Gender: "+citizenInfo[1]+"\n"+
                             "Citizen's Name: "+citizenInfo[2]+" "+citizenInfo[3]+" "+citizenInfo[4]+"\n"+
                             "Citizen's Address: "+"\n\n"+app.getCitizen(idArea.getSelectedValue()).getAddress().toString();
                
                displayArea.setText(text);
                displayArea.setFont(displayArea.getFont().deriveFont(Font.ITALIC + Font.BOLD));
            }
        });


    //OPERATIONS ON THE DISPLAY AREA - TEXTAREA
    displayArea.setEditable(false);
    displayArea.setPreferredSize(new Dimension(1,1)); 


    //OPERATIONS ON THE TEXTFIELD
    textField.setBounds(20,90,470,30);
    textField.setFont(new Font("Arial", Font.ITALIC+Font.BOLD, 20));

    textField.addMouseListener(new MouseAdapter(){ //Used to clear textfield when clicked
        @Override
        public void mouseClicked(MouseEvent event){
            textField.setText("");
        }
    });


    //OPERATIONS ON THE BUTTON GROUP FOR THE RADIO BUTTONS 
    radios.add(id); 
    radios.add(name); 
    radios.add(biometric);


        //OPERATIONS ON THE BUTTON GROUP FOR THE JBUTTONS
        buttons.add(search); 
        buttons.add(clear); 
        buttons.add(quit);


    //OPERATIONS ON THE <SEARCH> BUTTON 
    search.setBounds(510, 90, 100, 30); 
    search.addActionListener(event -> {

        if(id.isSelected()){    //ONLY DONE IF THE <ID> RADIO BUTTON IS SELECTED

            if(idList.getSize()!=0 && idArea.getNextMatch(textField.getText(), 0, Position.Bias.Forward)!=-1){
                displayArea.setText("<<CITIZEN HAS ALREADY BEEN FOUND>>\n===================================\n               LOOK TO THE LEFT");
                displayArea.setFont(displayArea.getFont().deriveFont(Font.ITALIC + Font.BOLD));
            }
            //Only execute search if information is entered within the textfield
            //Adding an element first to prevent IllegalArugementException for index starting
            if(idList.getSize()==0 && !app.search(textField.getText()).equals("")){
                idList.addElement(textField.getText());
                displayArea.setText("");
            } 

            //This Prevents Duplicates
            if(idList.getSize()!=0 && (idArea.getNextMatch(textField.getText(), 0, Position.Bias.Forward))==-1 &&
                !app.search(textField.getText()).equals("")){
                idList.addElement(textField.getText());
                displayArea.setText("");
            }

            if(app.search(textField.getText()).equals("")){
                displayArea.setText("<<CITIZEN NOT FOUND>>");
                displayArea.setFont(displayArea.getFont().deriveFont(Font.ITALIC + Font.BOLD));
            } 
        }
        
        if(name.isSelected()){

            int count=0;
            if(names.contains(textField.getText().toLowerCase())){
                displayArea.setText(" <LAST NAME WAS ALREADY LOCATED>\n===================================\n         CHECK THE IDS TO THE LEFT");
                displayArea.setFont(displayArea.getFont().deriveFont(Font.ITALIC + Font.BOLD));
            }else{

                for(Citizen thisCitizen:app.getRecords()){

                    if(idList.getSize()==0 && thisCitizen.getFullName().getLastName().equalsIgnoreCase(textField.getText())){
                        idList.addElement(thisCitizen.getId());
                        names.add(textField.getText().toLowerCase());
                        displayArea.setText("");
                    } 

                    //Prevents Duplicates
                    if(thisCitizen.getFullName().getLastName().equalsIgnoreCase(textField.getText()) 
                        && idList.getSize()!=0 
                        && (idArea.getNextMatch(thisCitizen.getId(), 0, Position.Bias.Forward))==-1){
                            idList.addElement(thisCitizen.getId());
                            names.add(textField.getText().toLowerCase());
                            displayArea.setText("");
                    }

                    //INCREMENTS EVERYTIME A LAST NAME IS FOUND
                    if(thisCitizen.getFullName().getLastName().equalsIgnoreCase(textField.getText())){
                        count++;
                    }
                }
            }
                //THIS CHECKS IF A GIVEN LAST NAME HAVE BEEN FOUND
                if(count==0 && !names.contains(textField.getText().toLowerCase())){
                    displayArea.setText("<<NO CITIZEN WITH SUCH LAST NAME>>");
                    displayArea.setFont(displayArea.getFont().deriveFont(Font.ITALIC + Font.BOLD));
                }  

                //THIS CHECKS IF A GIVEN LAST NAME BEEN FOUND, BUT WAS ALREADY FOUND BY ID
                if(!names.contains(textField.getText().toLowerCase()) && count!=0){
                    displayArea.setText("<<THE CITIZEN(S) WITH THIS LAST\nNAME WERE ALREADY FOUND BY ID>>");
                    displayArea.setFont(displayArea.getFont().deriveFont(Font.ITALIC + Font.BOLD));
                }  
            }
    });


        //OPERATIONS ON THE <CLEAR> BUTTON
        clear.setBounds(510, 140, 100, 30); 
        clear.addActionListener(event -> textField.setText(""));
 

    //OPERATIONS ON THE <QUIT> BUTTON
    quit.setBounds(510, 190,100, 30);
    quit.addActionListener(event -> System.exit(0));


        //OPERATIONS ON THE <ID> RADIO BUTTON
        id.setBounds(20, 30, 100, 30); 
    
        
    //OPERATIONS ON THE <NAME> RADIO BUTTON
    name.setBounds(130, 30, 120, 30); 
    

        //OPERATIONS ON THE <BIOMETRIC> RADIO BUTTON
        biometric.setBounds(270, 30, 150, 30);

    
    //OPERATIONS ON THE FRAME
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setMinimumSize(new Dimension(700, 600));
    guiFrame.setLocation(450, 100);            
    
        guiFrame.add(id); //ADDING RADIO BUTTONS TO FRAME
        guiFrame.add(name);
        guiFrame.add(biometric);

        guiFrame.add(search); //ADDING BUTTONS TO FRAME
        guiFrame.add(clear); 
        guiFrame.add(quit);
 
    guiFrame.add(textField);
    
    guiFrame.setSize(300, 300);
    guiFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(9, 10, 9, 10, Color.WHITE));

        guiFrame.getContentPane().setLayout(null);  //FREE THE PANEL RESTRICTIONS ~ MEANING THE PANEL IS FREE OF ANY LAYOUT THIS WAY ITS BOUNDS CAN BE SET
                                                    //LOOK BACK ON THE PANEL OPERATIONS

        guiPanel.setLayout(new BorderLayout(20,0)); //THIS OPERATION ON THE PANEL IS PLACED HERE, AS IT IS DONE AFTER THE PANEL IS FREE
                
        //OPERATIONS ON THE PANEL
        guiPanel.setBackground(Color.YELLOW);
        guiPanel.setBounds(20, 150,470,380); 
        guiPanel.add(scrollPane,BorderLayout.LINE_START);
        guiPanel.add(displayArea, BorderLayout.CENTER);


    guiFrame.getContentPane().add(guiPanel); 
    guiFrame.getContentPane().setBackground(Color.YELLOW);

        guiFrame.pack();
        guiFrame.setVisible(true);
    }
    
}
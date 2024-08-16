/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.universitymavenproject.gui;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.universitymavenproject.dao.SubjectDAO;
import za.ac.cput.universitymavenproject.domain.Subject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class AddSubjectGUI extends JFrame implements ActionListener {
    // Importing necessary packages for GUI components and event handling
    private JPanel East, Centre, West, South; // Panels for layout
    private JLabel subjectCode, subjectDescription; // Labels for subject code and description
    private JTextField text1, text2;// Text fields for input
    private DefaultTableModel tableModel;// Table model for managing table data
    private JTable table; // Table for displaying subjects
    private JButton saveButton, cancelButton,readButton,fillComboButton; // Buttons for actions
    private JScrollPane scrollPane; // Scroll pane for table
    private JComboBox<String> comboBox; // Combo box for selection
    Subject subject; // Subject instance
    SubjectDAO dao; // Data Access Object for subject operations
    ArrayList<Subject> subjList = new ArrayList<>(); // List of subjects
    
    

    public AddSubjectGUI() {
        super("University Application");
        
         // Initializing labels
        subjectCode = new JLabel("Subject Code: ");
        subjectDescription = new JLabel("Subject Description: ");
        
        // Initializing text fields
        text1 = new JTextField();
        text2 = new JTextField();
        
        // Initializing table and table model
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        
        // Adding columns to the table model
        tableModel.addColumn("Subject Code");
        tableModel.addColumn("Descrition");
        
        // Initializing scroll pane with the table
        scrollPane = new JScrollPane(table);
        
        // Initializing combo box
        comboBox = new JComboBox();
        
        // Initializing buttons
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        readButton = new JButton("Read");
        fillComboButton = new JButton("Fill Combo");
        
        // Initializing panels
        East = new JPanel();
        Centre = new JPanel();
        West = new JPanel();
        South = new JPanel();
       
        // Initializing the DAO and creating the subject table in the database
        dao = new SubjectDAO();
        dao.createTable(); 
        
        
    }
    
    // Method to set up the GUI layout and add components
    public void setGui(){
        
       // Setting layout and adding components to the West pane
        West.setLayout(new GridLayout(2,2));
        West.add(subjectCode);
        West.add(text1);
        West.add(subjectDescription);
        West.add(text2);
       
       // Setting layout and adding components to the South panel
         South.setLayout(new GridLayout(1, 4));
         South.add(saveButton);
         South.add(cancelButton);
         South.add(readButton);
         South.add(fillComboButton);
      
       // Setting layout and adding components to the East panel
         East.setLayout(new FlowLayout(FlowLayout.CENTER));
         East.add(comboBox);

      // Adding action listeners to buttons and combo box
         saveButton.addActionListener(this);
         cancelButton.addActionListener(this);
         readButton.addActionListener(this);
         fillComboButton.addActionListener(this);
         comboBox.addActionListener(this);
       
       // Adding panels to the frame
          this.add(West, BorderLayout.WEST);
          this.add(scrollPane,BorderLayout.CENTER);
          this.add(East, BorderLayout.EAST);
          this.add(South, BorderLayout.SOUTH);
          
    }
    
  
    
 @Override
    public void actionPerformed(ActionEvent e) {
      // Handling save button click
        if (e.getSource() == saveButton) {
            String subjectCode = text1.getText(); // Get text from subject code field
            String subjectDescription = text2.getText(); // Get text from subject description field
            
            Subject sub = new Subject(subjectCode, subjectDescription); // Create new Subject instance
            
            subject = dao.save(sub);  // Save subject to database using DAO
            text1.setText("");
            text2.setText("");
             if (subject.equals(sub)) {
                JOptionPane.showMessageDialog(null, "Successfull");
            }else{
                JOptionPane.showMessageDialog(null, "Failure");
             }
             
        }
        if(e.getSource()==cancelButton){
             System.exit(0);
        }
        
         if (e.getSource() == readButton) {
            try {
                subjList = dao.read(); // Read subjects from database using DAO
                // Add each subject to the table model
                for (Subject subject : subjList) {
                    Object[] rowData = {subject.getSubjectCode(), subject.getSubjectDecription()};
                    tableModel.addRow(rowData);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddSubjectGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
    }
}
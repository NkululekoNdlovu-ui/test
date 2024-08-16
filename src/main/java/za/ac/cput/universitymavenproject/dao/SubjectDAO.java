/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.universitymavenproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import za.ac.cput.universitymavenproject.domain.Subject;
import java.util.*;
import za.cput.ac.universitymavenproject.connection.DBConnection;

/**
 *
 * @author nkulu
 */
public class SubjectDAO {

    private Connection conn; // Connection object to manage database connection
    private Statement stmt; // Statement object to execute SQL queries
    private PreparedStatement pstmt; // PreparedStatement object to execute precompiled SQL queries

    
  // Constructor to establish database connection  
    public SubjectDAO() {
        try {
            this.conn = DBConnection.derbyConnection();  // Get the database connection
            JOptionPane.showMessageDialog(null, "Connection established");
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex); // Log any SQL exceptions
        }

    }

     // Method to create the Subject table
    public void createTable() {

        String createTable = "create table Subject (Subject_Code  VARCHAR(180), Subject_Description VARCHAR(180))"; // SQL query to create table
        try {
            pstmt = this.conn.prepareStatement(createTable); // Prepare the SQL query
            pstmt.executeUpdate(); // Execute the query to create the table
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    
     // Method to save a Subject to the database
    public Subject save(Subject subject) {

        int ok;
        String insertSql = "INSERT INTO SUBJECT VALUES(?,?)"; // SQL query to insert subject
        try {
            pstmt = this.conn.prepareStatement(insertSql);// Prepare the SQL query
            pstmt.setString(1, subject.getSubjectCode());// Set the subject code
            pstmt.setString(2, subject.getSubjectDecription()); // Set the subject description
            ok = pstmt.executeUpdate(); // Execute the query
            if (ok > 0) {
                return subject;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    
    // Method to read all subjects from the database
    public ArrayList<Subject> read()throws SQLException {
           ArrayList<Subject> subject = new ArrayList<>(); // List to store subjects
           
           String retrieveValueSql = "SELECT * FROM SUBJECT"; // SQL query to select all subjects
        try {
           stmt = conn.createStatement(); // Create a statement
            ResultSet rs =  stmt.executeQuery(retrieveValueSql);// Execute the query and get the result set
            
            // Iterate through the result set and add subjects to the list
            if(rs != null)
            while(rs.next()){
                String  code = rs.getString(1);// Get subject code
                String name = rs.getString(2); // Get subject description
                subject.add(new Subject(code,name)); // Get subject description
            }
            
            rs.close();

     } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subject;// Return the list of subjects
    }
}


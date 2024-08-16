/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package za.ac.cput.universitymavenproject;

import javax.swing.JFrame;
import za.ac.cput.universitymavenproject.gui.AddSubjectGUI;

/**
 *
 * @author DELL
 */
public class UniversityMavenproject {

    public static void main(String[] args) {
        AddSubjectGUI frame = new AddSubjectGUI();
        frame.setVisible(true);
        frame.setGui();
        frame.setTitle("UNIVERSITY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
    }
}

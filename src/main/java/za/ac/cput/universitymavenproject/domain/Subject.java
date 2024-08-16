/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.universitymavenproject.domain;

/**
 *
 * @author DELL
 */
public class Subject {
    private String SubjectCode;
    private String SubjectDecription;

    public Subject() {
    }

    public Subject(String SubjectCode, String SubjectDecription) {
        this.SubjectCode = SubjectCode;
        this.SubjectDecription = SubjectDecription;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public String getSubjectDecription() {
        return SubjectDecription;
    }

    public void setSubjectDecription(String SubjectDecription) {
        this.SubjectDecription = SubjectDecription;
    }

    @Override
    public String toString() {
        return "Subject{" + "SubjectCode=" + SubjectCode + ", SubjectDecription=" + SubjectDecription + '}';
    }
    
    
}

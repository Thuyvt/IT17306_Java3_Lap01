/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Subject;
import Repository.SubjectRepositoy;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author THUYVU
 */
public class SubjectService {
    SubjectRepositoy repository = new SubjectRepositoy();

    public SubjectService() {
    }
    
  
    // thay doi du lieu trong file
    public boolean changeToFile(List<Subject> listSubject) {
        return repository.saveToFile(listSubject);// goi repository de them du lieu vao file
    }
    
    // Mo file 
    public List<Subject> getDataFromFile() {
        return repository.openFile();
    }
    
}

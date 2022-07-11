/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Subject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author THUYVU
 */
public class SubjectRepositoy {

    public SubjectRepositoy() {
    }
    
    public boolean saveToFile(List<Subject> listSubject) {
        boolean response = false;
         try {
            // TODO add your handling code here:
            FileOutputStream fos = new FileOutputStream("thithu.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(listSubject);
            response = true;
         } catch (FileNotFoundException ex) {
            response = false;
        } catch (IOException ex) {
            response = false;
        }
        return response;
    }
    
      
    public List<Subject> openFile() {
        List<Subject> listSubject = new ArrayList<>();
        try {
            // TODO add your handling code here:
            FileInputStream fis = new FileInputStream("thithu.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            listSubject = (List<Subject>) ois.readObject();
        } catch (FileNotFoundException ex) {
            listSubject = null;
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            listSubject = null;
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            listSubject = null;
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listSubject;
    }        
}

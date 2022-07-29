/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Book;
import Model.BookCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThuyVT
 */
public class BookRepository {
    DBConnection dbConnection;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst = null;
    List<Book> listBook = null;
    List<BookCategory> listBookCategory = new ArrayList<>();

    public BookRepository() {
    }
    
    public List<Book> getListBook() {
        String select = "SELECT B.ID, B.NAME, B.AUTHOR, BC.NAME FROM BOOK B " +
        "INNER JOIN BOOK_CATEGORY BC ON B.CATEGORY = BC.CODE";
        listBook =  new ArrayList<>();
        try {
            st = dbConnection.openDbConnection().createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                listBook.add(new Book(rs.getInt(1), rs.getNString(2),
                        rs.getNString(3), rs.getNString(4)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBook;
        
    }
    
    public String insertToDB(Book book) {
        try {
            String insert = "INSERT INTO BOOK (NAME, AUTHOR,CATEGORY) VALUES (?,?,?)";
            pst = dbConnection.openDbConnection().prepareStatement(insert);
            pst.setString(1, book.getName());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getCategory());
            
            pst.executeUpdate();
            
            return "Thêm du lieu thanh công";
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
            return "Thêm du lieu that bai";
        }
    }
    
    public String updateToDB(Book book) {
        try {
            String update = "UPDATE BOOK SET NAME = ?, AUTHOR = ? WHERE ID = ? ";
            pst = dbConnection.openDbConnection().prepareStatement(update);
            pst.setString(1, book.getName());
            pst.setString(2, book.getAuthor());
            pst.setInt(3, book.getId());
            
            pst.executeUpdate();
            return "Sua du lieu thanh công";
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
            return "Sua du lieu that bai";
        }        
    }
    
    public String deleteToDb(Book book) {
      try {
            String delete = "DELETE FROM BOOK WHERE ID = ?" ;
            pst = dbConnection.openDbConnection().prepareStatement(delete);
            pst.setInt(1, book.getId());
            pst.executeUpdate();
            
            return "Xoa du lieu thanh công";
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
            return "Xoa du lieu that bai";
        }      
    
    }
      
    // KIEM TRA ID SACH DA TON TAI HAY CHUA
    public int getIndexBook(int Id) {
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getId() == Id) {
                return i;
            }
        }
        
        return -3;
    }    
    
    public List<BookCategory> getListBookCategory() {
        String select = "SELECT * FROM BOOK_CATEGORY";
        List<BookCategory> category =  new ArrayList<>();
        try {
            st = dbConnection.openDbConnection().createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                category.add(new BookCategory(rs.getString(1), rs.getNString(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
 
    }
    
    public List<Book> search(String temp) {
        List<Book> listTemp = new ArrayList<>();
        for (Book x: listBook) {
            if (x.getName().equalsIgnoreCase(temp.toLowerCase()) ||
                    x.getAuthor().equalsIgnoreCase(temp.toLowerCase())) {
                listTemp.add(x);
            }
        }
        return listTemp;
    }
    
    public List<Book> sort(int temp) {// Neu la 1 = ASC, 0 = DESC
        if (listBook != null && !listBook.isEmpty()) {
            if (temp == 1) {
                listBook.sort(Comparator.comparing(Book::getName));
            } else {
                listBook.sort(Comparator.comparing(Book::getName).reversed());
            }
        }       
        return listBook;

    }
}

package newpackage1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class FileManagerBinary implements Serializable {
    public boolean write (String FilePath, Object data) {
        try {
            System.out.println("\nWriting in : " + FilePath);
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(FilePath));
            writer.writeObject(data);
            System.out.println("  Done!");
            writer.close();
            return true;
        }catch (IOException e){
            System.out.println("\ncan't write : " + e);
        }
        return false;
    }
    
    public Object read (String FilePath) {
        
        Object Result = null;
        
        try {
            System.out.println("\nReading from : " + FilePath);
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FilePath));
            Result = reader.readObject();
            reader.close();
        }catch (IOException e){
            System.out.println("\ncan't read : " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileManagerBinary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
    
    
}

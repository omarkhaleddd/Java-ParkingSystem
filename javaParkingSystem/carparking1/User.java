 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarParking1;


import java.io.IOException;
import newpackage1.FileManagerBinary;
import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Omar
 */

public class User implements Serializable{
    private String UserName;
    private String UserPass;
    private String UserRole;

    static FileManagerBinary Fm = new FileManagerBinary();

    public static ArrayList<User> Users = new ArrayList<User>();

    public void setUserName(String UserName){this.UserName = UserName;}
    public void setUserPass(String UserPass){this.UserPass = UserPass;}
    public void setUserRole(String UserRole){this.UserRole = UserRole;}

    public String getUserName(){return this.UserName;}
    public String getUserPass(){return this.UserPass;}
    public String getUserRole(){return this.UserRole;}

    public static void loadFromFileU(){
        Users = (ArrayList<User>) Fm.read("UserFile.bin");
    }
    public static boolean commitToFileU(){
        return Fm.write("UserFile.bin", Users);
    }
//    private int getById(int Id){
//        for (int i = 0; i < Users.size(); i++) {
//            if (Users.get(i).getId() == Id) {
//                return i;
//            }
//        }
//        return -1;
//    }
    
    public boolean LogIn(User u2,char[] password) throws IOException {     
        u2.setUserPass(String.copyValueOf(password));
        loadFromFileU();
        for (int i = 0; i < Users.size(); i++) {
            if (Users.get(i).getUserRole().equals(u2.getUserRole()) && Users.get(i).getUserName().equals(u2.getUserName()) && (Users.get(i).getUserPass() == null ? u2.getUserPass() == null : Users.get(i).getUserPass().equals(u2.getUserPass()))){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "\nthe user's name : " + this.UserName + " | the user's password : " + this.UserPass + " | the user's role : " + this.UserRole;
    }
}
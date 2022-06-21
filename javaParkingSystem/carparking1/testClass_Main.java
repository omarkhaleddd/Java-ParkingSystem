package CarParking1;



import CarParking1.*;
import static CarParking1.EntryStationOperator.Fm;
import static CarParking1.User.*;
import static CarParking1.Slot.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omar
 */
public class testClass_Main {
    public static void main(String[] args) throws IOException {

        String ProjectPath = System.getProperty("user.dir");
        System.out.println("Project Path : " + ProjectPath);
        File currentDir = new File(ProjectPath);
        checkDirectorycontents(currentDir);

          Admin a = new Admin();

           User a1 = new Admin();
           a1.setUserName("admin");
           a1.setUserPass("admin");
           a1.setUserRole("Admin");
    
            a.AddUser(a1);
            a.addslots(5);
      
            loadFromFileU();

            System.out.println(Users);
            
            EntryStationOperator en = new EntryStationOperator();
            en.makeFreeSlots();
            loadFromFileS();
            System.out.println(Slots);

    }

    public static void checkDirectorycontents(File dir) {
        File[] files = dir.listFiles();
        boolean SlotFile = true;
        boolean UserFile = true;
        boolean ParkedCarsFile = true;
        boolean ShiftReportFile = true;
        boolean FreeSlotsFile = true;

        for (File file:files){
            if (file.getName().contains("SlotFile.bin")) {
                SlotFile = false;
            }
            if (file.getName().contains("UserFile.bin")) {
                UserFile = false;
            }
            if (file.getName().contains("ParkedCarsFile.bin")) {
                ParkedCarsFile = false;
            }
            if (file.getName().contains("ShiftReportFile.bin")) {
                ShiftReportFile = false;
            }
            if (file.getName().contains("FreeSlotsFile.bin")) {
                FreeSlotsFile = false;
            }
        }

        if (SlotFile) {
            commitToFileS();
        }
        if (UserFile) {
            commitToFileU();
        }
        if (ParkedCarsFile) {
            Fm.write("ParkedCarsFile.bin", Slots);
        }
        if (ShiftReportFile) {
            Fm.write("ShiftReportFile.bin", Slots);
        }
        if (FreeSlotsFile) {
            Fm.write("FreeSlotsFile.bin", Slots);
        }
    }
}


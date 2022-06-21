package CarParking1;

import static CarParking1.Slot.*;
import java.io.Serializable;
import java.util.ArrayList;
import newpackage1.FileManagerBinary;

public  class EntryStationOperator extends User implements Serializable {
    private int AdvisedSlot = -1;
    
    static FileManagerBinary Fm = new FileManagerBinary();

    public static ArrayList<Slot> FreeSlots = new ArrayList<Slot>();

    public void makeFreeSlots(){
        loadFromFileS();
        FreeSlots = (ArrayList<Slot>) Fm.read("FreeSlotsFile.bin");
        for (int i = 0; i < Slots.size(); i++) {
            if (Slots.get(i).getIsFree() == true) {
                FreeSlots.add(Slots.get(i));
                Fm.write("FreeSlotsFile.bin", Slots);
            }
        }
    }
    
    public ArrayList<Slot> viewFreeSlots(){
        FreeSlots = (ArrayList<Slot>) Fm.read("FreeSlotsFile.bin");
        return FreeSlots;
    } 

    public void setAdvisedSlot(int AdvisedSlot){this.AdvisedSlot = AdvisedSlot;}
    public int getAdvisedSlot(){return this.AdvisedSlot;}


    
}
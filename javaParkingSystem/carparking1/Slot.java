/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarParking1;

import java.io.Serializable;
import java.util.ArrayList;
import newpackage1.FileManagerBinary;

/**
 *
 * @author Omar
 */
public class Slot implements Serializable {
    private int SlotNo;
    private boolean IsFree;
    private Customer cust;
    private String Shift;
    private double Payment;
    private String strLeavingDate;

    static FileManagerBinary Fm = new FileManagerBinary();

    public static ArrayList<Slot> Slots = new ArrayList<Slot>();
    
    public Slot(){}//to avoid the constructor's error

    public Slot(int SlotNo){
        this.SlotNo = SlotNo;
        this.IsFree = true;
        this.cust = null;
        this.Shift = null;
        this.Payment = 0.0;
        this.strLeavingDate = null;
    }

    public int getSlotNo(){return this.SlotNo;}
    public Customer getCustData(){return this.cust;}
    public boolean getIsFree(){return this.IsFree;}
    public String getShift(){return this.Shift;}
    public String getStrLeavingDate(){return this.strLeavingDate;}
    public double getPayment(){return this.Payment;}

    public void setCustData(Customer c){this.cust = c;}
    public void setIsFree(boolean b){this.IsFree = b;}
    public void setPayment(double payment){this.Payment = payment;}
    public void setShift(String shift){this.Shift = shift;}
    public void setStrLeavingDate(String strLeavingDate){this.strLeavingDate = strLeavingDate;}
    public void setSlotNo(int sltnum){this.SlotNo = sltnum;}

    public static void loadFromFileS(){Slots = (ArrayList<Slot>) Fm.read("SlotFile.bin");}
    public static boolean commitToFileS(){return Fm.write("SlotFile.bin", Slots);}

    @Override
    public String toString(){
        if (this.cust == null) {
            return "\n slot number : " + this.SlotNo + " Isfree? : " + this.IsFree+" payment is : "+this.Payment + " leaving date is" + this.strLeavingDate ;
        }else{
            return "\n slot number : " + this.SlotNo + " Isfree? : " + this.IsFree+ " Ticket id : " + this.cust.getTicket().GetId() + " platenumber is :  "+ this.cust.getTicket().GetPlateNumber() +"transaction date is : " + this.cust.getTicket().GetTransDate() + " payment is : "+this.Payment + " leaving date is" + this.strLeavingDate ;
        }
    }
}
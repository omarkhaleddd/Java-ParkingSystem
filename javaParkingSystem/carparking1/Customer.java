package CarParking1;


import static CarParking1.Slot.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import newpackage1.FileManagerBinary;


public class Customer implements Serializable{
    private Ticket CustTicket;
    private int PlateNumber;
    
    static FileManagerBinary Fm = new FileManagerBinary();

    public static ArrayList<Slot> ParkedCars = new ArrayList<Slot>();
    public static ArrayList<Slot> ShiftReport = new ArrayList<Slot>();
    
    Slot TakenSlot = new Slot();
    
    public void printTicket(){
        this.CustTicket = new Ticket(this.PlateNumber);
        System.out.println("cust id : " + this.CustTicket.GetId() + " cust platenumber : " + this.CustTicket.GetPlateNumber() + " cust transdate : " + this.CustTicket.GetTransDate());
        EntryStationOperator en = new EntryStationOperator();
        if (en.getAdvisedSlot() != -1) {
            this.TakenSlot = this.getBySlotNo(en.getAdvisedSlot());
        }else{this.TakenSlot = this.FirstFree();}
        makeParkedCarsReport(this);
    }

    public double pay(int Id) throws IOException{
        Admin a = new Admin();
        ExitStationOperator ex = new ExitStationOperator(this.TakenSlot);
        System.out.println("\nthe leaving date is : " + this.TakenSlot.getStrLeavingDate() + "\n");
        double payment = ex.CalculateTotalHours(Id) * a.getFeePerHour();
        this.TakenSlot.setPayment(payment);
        loadFromFileS();
        System.out.println(Slots);
        for (int i = 0; i < Slots.size(); i++) {
            if (Slots.get(i).getSlotNo() == this.TakenSlot.getSlotNo()) {
                Slots.get(i).setPayment(this.TakenSlot.getPayment());
                Slots.get(i).setStrLeavingDate(this.TakenSlot.getStrLeavingDate());
                System.out.println(Slots.get(i).toString());
                commitToFileS();
            }
        }
        makeShiftReport(this);
        return payment;
    }

    public void setPlateNumber(int PlateNumber){
        this.PlateNumber = PlateNumber;
    }

    public Ticket getTicket(){
        return this.CustTicket;
    }

    public Slot getBySlotNo(int slNum){
        loadFromFileS();
        System.out.println(Slots);
        for (int i = 0; i < Slots.size(); i++) {
            if (Slots.get(i).getSlotNo() == slNum) {
                Slots.get(i).setIsFree(false);
                Slots.get(i).setCustData(this);
                commitToFileS();
                return Slots.get(i);
            }
        }
        return null;
    }

    public Slot FirstFree(){
        loadFromFileS();
        System.out.println(Slots);
        for (int i = 0; i < Slots.size(); i++) {
            if (Slots.get(i).getIsFree() == true) {
                Slots.get(i).setIsFree(false);
                Slots.get(i).setCustData(this);
                System.out.println(Slots.get(i).toString());
                commitToFileS();
                return Slots.get(i);
            }
        }
        loadFromFileS();
        System.out.println(Slots);
        return null;
    }
    
    public Slot getSlotById(int id){
        loadFromFileS();
        System.out.println(Slots);
        for (int i = 0; i < Slots.size(); i++){
            if (Slots.get(i).getCustData().getTicket().GetId() == id) {
                loadFromFileS();
                System.out.println(Slots);
                return Slots.get(i);
            }
        }
        return null;
    }
    
    public void makeParkedCarsReport(Customer c){
        loadFromFileS();
        ParkedCars = (ArrayList<Slot>) Fm.read("ParkedCarsFile.bin");
        for (int i = 0; i < Slots.size(); i++) {
            if (Slots.get(i).getCustData() == c) {
                ParkedCars.add(Slots.get(i));
                Fm.write("ParkedCarsFile.bin", ParkedCars);
            }
        }
    }
    
    public void makeShiftReport(Customer c){
        loadFromFileS();
        ShiftReport = (ArrayList<Slot>) Fm.read("ShiftReportFile.bin");
        for (int i = 0; i < Slots.size(); i++) {
            if (Slots.get(i).getCustData() == c) {
                ShiftReport.add(Slots.get(i));
                Fm.write("ShiftReportFile.bin", ShiftReport);
            }
        }
    }
}

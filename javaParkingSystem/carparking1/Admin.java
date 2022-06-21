package CarParking1;


import static CarParking1.Customer.*;
import static CarParking1.Customer.ShiftReport;
import static CarParking1.Slot.*;
import static CarParking1.User.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends User implements Serializable{
    private int FeePerHour = 10;
    
    public int getFeePerHour() {
        return FeePerHour;
    }

    public void setFeePerHour(int FeePerHour) {
        this.FeePerHour = FeePerHour;
    }
    
    public void addslots(int Sltnum){
        loadFromFileS();
        for(int i = Slot.Slots.size()+1;Sltnum != 0;i++){
            Slot s1 = new Slot(i);
            Slot.Slots.add(s1);
            commitToFileS();
            Sltnum--;
        }
    }
    public ArrayList<Slot> ViewSlots(){
        loadFromFileS();
        return Slots ;

    }


    public void AddUser(User u){
        loadFromFileU();
        Users.add(u);
        commitToFileU();
    }
    public boolean UpdateUser(String NewRole,User u1){
        loadFromFileU();
        for (int i = 0; i < Users.size(); i++) {
            if (Users.get(i).getUserRole().equals(u1.getUserRole()) && Users.get(i).getUserName().equals(u1.getUserName()) && Users.get(i).getUserPass().equals(u1.getUserPass())){
                Users.get(i).setUserRole(NewRole);
                commitToFileU();
                return true;
            }
        }
        return false;
    }
    public boolean deleteUser(User u)
    {
        loadFromFileU();
        for(int i = 0;i<User.Users.size();i++){
            if((User.Users.get(i).getUserName().equals(u.getUserName() ))&&(Users.get(i).getUserRole().equals(u.getUserRole() ))&&(Users.get(i).getUserPass().equals(u.getUserPass() )))
            {
                User.Users.remove(i);
                commitToFileU();
                return true ;

            }
        }
        return false;
    }

    public ArrayList<Slot> viewShiftReports(){
        ShiftReport = (ArrayList<Slot>) Fm.read("ShiftReportFile.bin");
        return ShiftReport;
    }

    public ArrayList<Slot> viewParkedCarsReports(){
        ParkedCars = (ArrayList<Slot>) Fm.read("ParkedCarsFile.bin");
        return ParkedCars;
    }
}
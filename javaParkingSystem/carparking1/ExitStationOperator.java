package CarParking1;


import static CarParking1.Slot.Slots;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExitStationOperator extends User implements Serializable{
    private String StrLeavingDate;

    ExitStationOperator(Slot s){
        Date LeavingDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.StrLeavingDate = dateFormat.format(LeavingDate); 
        s.setStrLeavingDate(StrLeavingDate);
    }

    public int ConvertToHoursInt(String StrDate){
        String[] words = null;
        String[] s = null;
        int hour=0;
        words = StrDate.split(" ");
        s = words[1].split(":");
        System.out.println(StrDate);
        hour = Integer.parseInt(s[0]);
        if (Integer.parseInt(s[1]) > 0) {
            hour++;
        }
        return hour;
    }

    public String ReturnTransDate(int Id) {
        for (int i = 0; i < Slots.size(); i++) {
            if (Slots.get(i).getCustData().getTicket().GetId() == Id) {
                return Slots.get(i).getCustData().getTicket().GetTransDate();
            }
        }
        return null;
    }

    public int CalculateTotalHours(int Id) throws IOException{
        int hours = this.ConvertToHoursInt(StrLeavingDate) - this.ConvertToHoursInt(this.ReturnTransDate(Id));
        return hours;
    }

}
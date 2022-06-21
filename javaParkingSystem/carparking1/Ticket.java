package CarParking1;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Omar
 */
public class Ticket implements Serializable {
    private int PlateNumber;
    private static int Id = 12345;
    private String StrTransDate;

    Ticket(){}//to avoid the constructor's error

    Ticket(int PlateNumber){
        setId(++Id);
        Date TransDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        StrTransDate = dateFormat.format(TransDate);
        this.PlateNumber = PlateNumber;
    }

    public void setId(int id){
        this.Id = id;
    }
    
    public int GetId(){
        return this.Id;
    }

    public int GetPlateNumber(){
        return this.PlateNumber;
    }

    public String GetTransDate(){
        return this.StrTransDate;
    }
}
import java.io.Serializable;
import java.util.Date;

public class Timer implements Serializable {
    public Date date;

    Timer(){
        date = new Date();
        System.out.println("Timer constructed");
    }

    public void getTimer(){
        System.out.println(date);
    }
}

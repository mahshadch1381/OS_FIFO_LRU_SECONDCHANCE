import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class costumer {
    public int id;
    public int refrence;
    public  int counter;
    public costumer(int id) {
        this.id = id;
        refrence=0;
        counter=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        costumer costumer = (costumer) o;
        return id == costumer.id ;
    }


}

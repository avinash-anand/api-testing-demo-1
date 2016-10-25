package models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

//basically this is data-structure, just to hold data, so no point in making fields private and making getters and setters; That's why all fields are public
@JsonAutoDetect
public class Registration {
    public int registrationId;
    public String status;
    public LocalDate registrationDate;
    public DateTime processingDate;
    public boolean isActive;
    public double cost;
}

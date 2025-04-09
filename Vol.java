import java.io.Serializable;
import java.util.Date;

public class Vol implements Serializable {
    private String Numeroserie;
    private String type;
    private String description;
    private String ownerContact;
    private Date dateReported;

    public Vol(String Numeroserie, String type, String description, String ownerContact, Date dateReported) {
        this.Numeroserie = Numeroserie;
        this.type = type;
        this.description = description;
        this.ownerContact = ownerContact;
        this.dateReported = dateReported;
    }

    // Getters
    public String getNumeroserie() { return Numeroserie; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getOwnerContact() { return ownerContact; }
    public Date getDateReported() { return dateReported; }
}
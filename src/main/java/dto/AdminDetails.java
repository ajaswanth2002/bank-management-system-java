package dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_details")
public class AdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "admin_gender")
    private String adminGender;

    @Column(name = "admin_email")
    private String adminEmail;

    @Column(name = "admin_pin")
    private int adminPin;

    // Default constructor required by JPA
    public AdminDetails() { }

    // Custom constructor for insertion
    public AdminDetails(String adminName, String adminGender, String adminEmail, int adminPin) {
        this.adminName = adminName;
        this.adminGender = adminGender;
        this.adminEmail = adminEmail;
        this.adminPin = adminPin;
    }

    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }

    public String getAdminEmail() { return adminEmail; }
    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }

    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }

    public int getAdminPin() { return adminPin; }
    public void setAdminPin(int adminPin) { this.adminPin = adminPin; }

    public String getAdminGender() { return adminGender; }
    public void setAdminGender(String adminGender) { this.adminGender = adminGender; }

    @Override
    public String toString() {
        return "AdminDetails [adminId=" + adminId + ", adminName=" + adminName +
                ", adminGender=" + adminGender + ", adminEmail=" + adminEmail +
                ", adminPin=" + adminPin + "]";
    }
}
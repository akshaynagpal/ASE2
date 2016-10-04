package models;
import javax.persistence.*;

@Entity
public class ExistingPerson{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public String email;
    public String password;
}

package lk.riyapola.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String mobileNo;
    String email;
    String password;

    public User(String name, String mobileNo, String email, String password) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
    }


}

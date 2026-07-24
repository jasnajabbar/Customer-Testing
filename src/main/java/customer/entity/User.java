package customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    private int userId;
    @Column(name = "user_name")
    private String username;
    private String password;
    private String role;
}

package customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CustomerDetails")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVerificationEntity {
    @Id
    Integer customerVerificationId;
    String customerUserName;
    String customerPassword;
    String customerRole;
}

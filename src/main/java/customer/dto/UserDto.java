package customer.dto;

import customer.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class UserDto implements UserDetails {

        private User user;

        public UserDto(User user) {
                this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority(user.getRole()));
        }

        @Override
        public String getPassword() {
                return user.getPassword();
        }

        @Override
        public String getUsername() {
                return user.getUsername();
        }
}

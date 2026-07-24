package customer.controller;

import customer.dto.UserDto;
import customer.dto.UserRegisterDto;
import customer.entity.User;
import customer.repository.UserCredentialRepository;
import customer.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public String login(@RequestBody UserRegisterDto userRegisterDto){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRegisterDto.getUsername(),userRegisterDto.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userRegisterDto.getUsername());
        }
        return "Login Failed";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto){
        User existingUser=userCredentialRepository.findByUsername(userRegisterDto.getUsername());
        if (existingUser!=null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUserId(1);
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRole(userRegisterDto.getRole());

        userCredentialRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}

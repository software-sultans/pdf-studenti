package org.example.pdfstudentiv2.controllers;



import org.example.pdfstudentiv2.Role;
import org.example.pdfstudentiv2.entities.User;
import org.example.pdfstudentiv2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Ensure you have a "login.html" or "login.jsp" in your templates folder
    }

    // Display registration page
    @GetMapping("/register")
    public String registerPage() {
        return "register";  // Ensure you have a "register.html" or "register.jsp" in your templates folder
    }

    // Handle user registration
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/login";  // Redirect to login page after successful registration
    }
}

package com.yodishtr.smart_city_guide.Controllers;

import com.yodishtr.smart_city_guide.Entities.User;
import com.yodishtr.smart_city_guide.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/register")
public class AuthController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    public AuthController(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email,  Model model) {
        if (username == null || username.isEmpty()) {
            model.addAttribute("error", "Username is required");
            return "register";
        }
        if (password == null || password.isEmpty()) {
            model.addAttribute("error", "Password is required");
            return "register";
        }
        Optional<User> currentUser = userRepository.findByUsername(username);
        if (currentUser.isPresent()) {
            model.addAttribute("error", "Username is already in use");
            return "register";
        } else {
            String hashedPassword = bCryptPasswordEncoder.encode(password);
            User newlyRegisteredUser = new User(username, password, email, User.ROLE.getRole("user"));
            try {
                userRepository.save(newlyRegisteredUser);
                return "redirect:/login";
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                return "register";
            }
        }
    }
}

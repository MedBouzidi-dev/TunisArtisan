package com.example.tunisartisan.Controllers;

import com.example.tunisartisan.Entities.User;
import com.example.tunisartisan.Services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/user";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }

    @PostMapping
    public String create(@ModelAttribute User user) {
        prepareUserForSave(user, null);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "user/show";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute User formUser) {
        User existing = userService.getUserById(id);
        if (existing == null) {
            return "redirect:/users";
        }
        prepareUserForSave(formUser, existing);
        userService.updateUser(id, formUser);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    private void prepareUserForSave(User incoming, User existing) {
        String trimmedUserName = incoming.getUserName() != null ? incoming.getUserName().trim() : "";
        if (trimmedUserName.isEmpty() && incoming.getEmail() != null) {
            incoming.setUserName(incoming.getEmail().trim());
        }
        if (existing != null) {
            if (incoming.getPassword() == null || incoming.getPassword().isBlank()) {
                incoming.setPassword(existing.getPassword());
            } else {
                incoming.setPassword(passwordEncoder.encode(incoming.getPassword()));
            }
        } else if (incoming.getPassword() != null && !incoming.getPassword().isBlank()) {
            incoming.setPassword(passwordEncoder.encode(incoming.getPassword()));
        }
    }
}

package com.example.tunisartisan.Controllers;

import com.example.tunisartisan.Entities.AppRole;
import com.example.tunisartisan.Entities.Artisan;
import com.example.tunisartisan.Entities.StatutArtisan;
import com.example.tunisartisan.Entities.User;
import com.example.tunisartisan.Repositories.AppRoleRepository;
import com.example.tunisartisan.Repositories.UserRepository;
import com.example.tunisartisan.Services.ArtisanService;
import com.example.tunisartisan.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AppRoleRepository appRoleRepository;
    private final ArtisanService artisanService;
    private final PasswordEncoder passwordEncoder;

    public SecurityController(
            UserService userService,
            UserRepository userRepository,
            AppRoleRepository appRoleRepository,
            ArtisanService artisanService,
            PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.appRoleRepository = appRoleRepository;
        this.artisanService = artisanService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/pasautorise")
    public String pasautorisé(){
        return "pasautorise";
    }
    @GetMapping("/login")
    public String login(){
        return "login";}



    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String handleRegister(@ModelAttribute RegisterForm form, Model model) {
        if (form.getUserName() == null || form.getUserName().isBlank()) {
            model.addAttribute("error", "Le nom d'utilisateur est obligatoire.");
            return "register";
        }
        if (form.getPassword() == null || form.getPassword().isBlank()) {
            model.addAttribute("error", "Le mot de passe est obligatoire.");
            return "register";
        }
        if (userRepository.findByUserName(form.getUserName()) != null) {
            model.addAttribute("error", "Ce nom d'utilisateur est déjà utilisé.");
            return "register";
        }
        if (form.getEmail() != null && userRepository.findByEmail(form.getEmail()) != null) {
            model.addAttribute("error", "Cet email est déjà utilisé.");
            return "register";
        }

        User user = new User();
        user.setNom(form.getNom());
        user.setPrenom(form.getPrenom());
        user.setEmail(form.getEmail());
        user.setUserName(form.getUserName());
        user.setAdresse(form.getAdresse());
        user.setTel(form.getTel());
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        String accountType = form.getAccountType();
        String roleName = "artisan".equalsIgnoreCase(accountType) ? "ARTISAN" : "USER";
        AppRole role = appRoleRepository.findByRole(roleName);
        if (role == null) {
            role = new AppRole();
            role.setRole(roleName);
            appRoleRepository.save(role);
        }
        user.getRoles().add(role);
        userService.saveUser(user);

        if ("artisan".equalsIgnoreCase(accountType)) {
            Artisan artisan = new Artisan();
            artisan.setUser(user);
            artisan.setStatut(StatutArtisan.En_ATTENTE);
            artisanService.addArtisan(artisan);
        }

        return "redirect:/login?registered";
    }

    public static class RegisterForm {
        private String nom;
        private String prenom;
        private String email;
        private String userName;
        private String password;
        private String adresse;
        private String tel;
        private String accountType;

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }
    }
}

package com.notionIQ.controller;

import com.notionIQ.entity.Notes;
import com.notionIQ.entity.User;
import com.notionIQ.services.NoteService;
import com.notionIQ.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private userService userServices;


    @GetMapping("/home")
    public String showHomePage(Model model) {
             return "NotionIQ"; // This refers to the home.html template in the templates folder
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Returns login.html
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        // Authenticate user and redirect to notes page
        return "redirect:/api/notes"; // After login, show the notes page
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Returns register.html
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        // Register user and redirect to notes page
        userServices.registerUser(new User(username, email, password));
        return "redirect:/api/notes/home"; // After registration, show the notes page
    }

    @GetMapping("/notes")
    public String showNotesPage(Model model) {
        // Fetch the user's notes from the database
        List<Notes> notes = userServices.getNotes();
        model.addAttribute("notes", notes);
        return "notes"; // Returns notes.html
    }

    @PostMapping("/notes")
    public String saveNote(@RequestParam String noteContent) {
        // Save the note
        userServices.saveNote(noteContent);
        return "redirect:/api/notes"; // Redirect to the notes page to see the updated list
    }
}

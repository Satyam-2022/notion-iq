package com.notionIQ.services.Impl;

import com.notionIQ.entity.Notes;
import com.notionIQ.entity.User;
import com.notionIQ.repository.NoteRepository;
import com.notionIQ.repository.userRepository;
import com.notionIQ.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements userService,UserDetailsService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private NoteRepository notesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core
                .userdetails.User(user.getUsername(),
                user.getPassword(), new ArrayList<>());
    }

    @Override
    public List<Notes> getNotes() {
        return notesRepository.findAll();
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void saveNote(String noteContent) {
        Notes note = new Notes();
        note.setContent(noteContent);
        User currentUser = getCurrentUser();
        // Assuming we have a logged-in user context
        note.setUser(currentUser);  // You'll need a way to get the logged-in user
        notesRepository.save(note);
    }
}

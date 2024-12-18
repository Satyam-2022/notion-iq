package com.notionIQ.services;

import com.notionIQ.entity.Notes;
import com.notionIQ.entity.User;
import com.notionIQ.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface userService {

    User registerUser(User user) ;
    UserDetails loadUserByUsername(String username);
    List<Notes> getNotes();
    void saveNote(String noteContent);


}

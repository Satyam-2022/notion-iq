package com.notionIQ.repository;

import com.notionIQ.entity.Notes;
import com.notionIQ.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Notes,Long> {
    List<Notes> findByUser(User user);
}

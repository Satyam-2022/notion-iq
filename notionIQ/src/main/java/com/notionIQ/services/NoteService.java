package com.notionIQ.services;

import com.notionIQ.entity.Notes;
import org.aspectj.weaver.ast.Not;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    Notes create(Notes notes);
    List<Notes>getAllNotes();
    Optional<Notes>getNotesById(Long id);
    Notes updateNotes(Long id,Notes updateNotes);
    void deleteNotes(Long id);
}

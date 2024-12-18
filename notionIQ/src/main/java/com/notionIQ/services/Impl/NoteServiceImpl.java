package com.notionIQ.services.Impl;

import com.notionIQ.entity.Notes;
import com.notionIQ.repository.NoteRepository;
import com.notionIQ.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Notes create(Notes notes) {
        return noteRepository.save(notes);
    }


    public List<Notes> getAllNotes() {
        return noteRepository.findAll();
    }


    public Optional<Notes> getNotesById(Long id) {
        return noteRepository.findById(id);
    }


    public Notes updateNotes(Long id,Notes updateNotes) {
        Notes notes = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notes Not Found"));
        notes.setTitle(updateNotes.getTitle());
        notes.setContent(updateNotes.getContent());
        return noteRepository.save(notes);
    }


    public void deleteNotes(Long id) {
         noteRepository.deleteById(id);
    }
}

package com.notionIQ.controller;

import com.notionIQ.entity.Notes;
import com.notionIQ.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/notes")
public class NotesController {
    @Autowired
    private NoteService noteService;


    @GetMapping("/home")
    public String showNotesPage(Model model) {
        List<Notes> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return "notes"; // Make sure notes.html exists in the templates directory
    }

    @GetMapping("/create")
    public String showCreateNotePage() {
        return "create-note"; // Template for creating notes
    }

    @PostMapping("/create")
    public String createNote(@RequestParam String title, @RequestParam String content) {
        Notes newNote = new Notes();
        newNote.setTitle(title);
        newNote.setContent(content);
        noteService.create(newNote);
        return "redirect:/api/notes/home"; // Redirect to the notes list page
    }

    @GetMapping("/view/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        Notes note = noteService.getNotesById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        model.addAttribute("note", note);
        return "view-note"; // Template to display note details
    }

    @GetMapping("/edit/{id}")
    public String showEditNotePage(@PathVariable Long id, Model model) {
        Notes note = noteService.getNotesById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        model.addAttribute("note", note);
        return "edit-note"; // Template to edit the note
    }

    @PostMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, @RequestParam String title, @RequestParam String content) {
        Notes note = noteService.getNotesById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        note.setTitle(title);
        note.setContent(content);
        noteService.updateNotes(id, note);
        return "redirect:/api/notes/home";
    }


    @PostMapping
    public ResponseEntity<Notes> create(@RequestBody Notes notes){
        Notes createNotes = noteService.create(notes);
        return ResponseEntity.status(HttpStatus.CREATED).body(createNotes);
    }

    @GetMapping
    public List<Notes>getAll(){
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notes> getNoteById(@PathVariable Long id) {
        Optional<Notes> note = noteService.getNotesById(id);
        return note.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notes> updateNote(@PathVariable Long id, @RequestBody Notes updatedNote) {
        Notes note = noteService.updateNotes(id, updatedNote);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNotes(id);
        return ResponseEntity.noContent().build();
    }
}

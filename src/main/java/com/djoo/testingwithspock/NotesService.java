package com.djoo.testingwithspock;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<Note> getAllNotes() {
        return notesRepository.findAll();
    }
}

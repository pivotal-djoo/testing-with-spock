package com.djoo.testingwithspock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NotesController {

    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService= notesService;
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNotes() {
        return new ResponseEntity<>(notesService.getAllNotes(), HttpStatus.OK);
    }
}

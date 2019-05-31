package com.djoo.testingwithspock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@Controller
public class NotesController {

    private NotesService notesService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Note> getNotes() {
        log.debug("GET /notes called");
        return notesService.getAllNotes();
    }

    @PostMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Note createNote(@RequestBody Note note) throws JsonProcessingException {
        log.debug("POST /notes called with " + objectMapper.writeValueAsString(note));
        return notesService.createNote(note);
    }
}

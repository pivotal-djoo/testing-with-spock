package com.djoo.testingwithspock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class NotesControllerTest {

    @MockBean
    NotesService notesService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getNotes_returnsNotes() throws Exception {
        List<Note> notes = asList(Note.builder().text("note 1").build(),
                Note.builder().text("note 2").build());
        when(notesService.getAllNotes()).thenReturn(notes);

        mvc.perform(get("/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].text", is(notes.get(0).getText())))
                .andExpect(jsonPath("$[1].text", is(notes.get(1).getText())));
    }
}

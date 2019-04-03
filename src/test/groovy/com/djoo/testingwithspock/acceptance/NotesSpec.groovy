package com.djoo.testingwithspock.acceptance

import com.djoo.testingwithspock.Note
import com.djoo.testingwithspock.NotesRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static java.util.Arrays.asList
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class NotesSpec extends Specification {

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MockMvc mvc

    @Autowired
    NotesRepository notesRepository

    def "notes can be retrieved from GET /notes"() {
        given: "there are two notes added previously"
        List<Note> notes = asList(
                Note.builder().text("note 1").build(),
                Note.builder().text("note 2").build()
        )
        notesRepository.saveAll(notes)


        when: "we attempt to retrieve all notes"
        def response = mvc.perform(get("/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        List<Note> actualNotes = objectMapper.readValue(response, new TypeReference<List<Note>>(){})


        then: "all two notes should return"
        actualNotes.size() == 2 &&
                actualNotes.get(0).text == "note 1"
                actualNotes.get(1).text == "note 2"
    }
}

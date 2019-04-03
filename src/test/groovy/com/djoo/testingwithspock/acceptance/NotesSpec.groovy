package com.djoo.testingwithspock.acceptance

import com.djoo.testingwithspock.Note
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat

@SpringBootTest
@AutoConfigureMockMvc
class NotesSpec extends Specification {

    @Value('${server.baseUrl}')
    private String serverBaseUrl

    private RestTemplate restTemplate = new RestTemplate()

    def "new notes can be added via POST /notes"() {

        given: "there is a note to be added"

        Note newNote = Note.builder().text("note 1").build()


        when: "POST /notes is invoked with a new note"

        Note savedNote = restTemplate.postForObject(serverBaseUrl + "/notes", newNote, Note.class)


        then: "new note should be saved"

        assertThat(savedNote).isNotNull()
        assertThat(savedNote.getId()).isNotNull()
        assertThat(savedNote.getText()).isEqualTo(newNote.text)

        List<Note> notes = restTemplate.exchange(serverBaseUrl + "/notes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Note>>() {})
                .getBody()
        assertThat(notes.get(notes.size() - 1)).isEqualTo(savedNote)
    }

    def "notes can be retrieved from GET /notes"() {

        given: "there are two notes added previously"

        Note note1 = Note.builder().text("note 1").build()
        Note note2 = Note.builder().text("note 2").build()
        note1 = restTemplate.postForObject(serverBaseUrl + "/notes", note1, Note.class)
        note2 = restTemplate.postForObject(serverBaseUrl + "/notes", note2, Note.class)


        when: "we attempt to retrieve all notes"

        List<Note> notes = restTemplate.exchange(serverBaseUrl + "/notes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Note>>() {})
                .getBody()


        then: "all two notes should return"

        notes.size() >= 2 &&
                notes.get(notes.size() - 2).text == note1.text &&
                notes.get(notes.size() - 1).text == note2.text
    }
}

package com.djoo.testingwithspock.acceptance

import com.djoo.testingwithspock.NoteDto
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
    private String serverBaseUrl = "http://localhost:9292"

    private RestTemplate restTemplate = new RestTemplate()

    def "new notes can be added via POST /notes"() {

        System.out.println("NotesSpec: serverBaseUrl = " + serverBaseUrl)
        System.out.println("NotesSpec: restTemplate = " + restTemplate)

        given: "there is a note to be added"

        NoteDto newNote = NoteDto.builder().text("note 1").build()


        when: "POST /notes is invoked with a new note"

        NoteDto savedNote = restTemplate.postForObject(serverBaseUrl + "/notes", newNote, NoteDto.class)


        then: "new note should be saved"

        assertThat(savedNote).isNotNull()
        assertThat(savedNote.getId()).isNotNull()
        assertThat(savedNote.getText()).isEqualTo(newNote.text)

        List<NoteDto> notes = restTemplate.exchange(serverBaseUrl + "/notes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NoteDto>>() {})
                .getBody()
        assertThat(notes.get(notes.size() - 1)).isEqualTo(savedNote)
    }

    def "notes can be retrieved from GET /notes"() {

        given: "there are two notes added previously"

        NoteDto note1 = NoteDto.builder().text("note 1").build()
        NoteDto note2 = NoteDto.builder().text("note 2").build()
        note1 = restTemplate.postForObject(serverBaseUrl + "/notes", note1, NoteDto.class)
        note2 = restTemplate.postForObject(serverBaseUrl + "/notes", note2, NoteDto.class)


        when: "we attempt to retrieve all notes"

        List<NoteDto> notes = restTemplate.exchange(serverBaseUrl + "/notes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NoteDto>>() {})
                .getBody()


        then: "all two notes should return"

        notes.size() >= 2 &&
                notes.get(notes.size() - 2).text == note1.text &&
                notes.get(notes.size() - 1).text == note2.text
    }
}

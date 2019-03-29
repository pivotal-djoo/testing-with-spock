package com.djoo.testingwithspock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NotesServiceTest {

    @Mock
    private NotesRepository notesRepository;

    @InjectMocks
    private NotesService subject;

    @Test
    public void getAllNotes_returnsAllNotes() {
        List<Note> notes = asList(
                Note.builder().text("note 1").build(),
                Note.builder().text("note 2").build()
        );
        when(notesRepository.findAll()).thenReturn(notes);

        List<Note> actualNotes = subject.getAllNotes();
        assertThat(actualNotes.size()).isEqualTo(2);
        assertThat(actualNotes.get(0).getText()).isEqualTo(notes.get(0).getText());
        assertThat(actualNotes.get(1).getText()).isEqualTo(notes.get(1).getText());
    }
}
package com.djoo.testingwithspock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NotesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NotesRepository subject;

    @Test
    public void findAll_returnsAllNotes() {
        Note note1 = Note.builder().text("note 1").build();
        Note note2 = Note.builder().text("note 2").build();
        entityManager.persistAndFlush(note1);
        entityManager.persistAndFlush(note2);

        List<Note> actualNotes = subject.findAll();
        assertThat(actualNotes.size()).isEqualTo(2);
        assertThat(actualNotes.get(0).getText()).isEqualTo(note1.getText());
        assertThat(actualNotes.get(1).getText()).isEqualTo(note2.getText());
    }
}
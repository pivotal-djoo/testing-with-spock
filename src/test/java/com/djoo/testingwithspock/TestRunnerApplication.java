package com.djoo.testingwithspock;

import com.djoo.testingwithspock.acceptance.NotesSpec;
import org.junit.internal.TextListener;
import spock.util.EmbeddedSpecRunner;

import java.util.Collections;

public class TestRunnerApplication {
	public static void main(String[] args) {
		System.out.println("Running tests!");

		EmbeddedSpecRunner embeddedSpecRunner = new EmbeddedSpecRunner();
		embeddedSpecRunner.setListeners(Collections.singletonList(new TextListener(System.out)));
		embeddedSpecRunner.runClass(NotesSpec.class);
	}
}

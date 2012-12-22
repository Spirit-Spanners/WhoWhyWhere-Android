package am.tir.abstractaction.data.beans;

import java.io.Serializable;
import java.util.List;

public class Story implements Serializable {

	private List<String> answers;

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}

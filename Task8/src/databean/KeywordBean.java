package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("word")
public class KeywordBean implements Comparable<KeywordBean> {
	private String word;
	private int frequency;
	
	public KeywordBean() {
	}

	public KeywordBean(String keyword) {
		this.word = keyword;
		this.frequency = 1;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String keyword) {
		this.word = keyword;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public int compareTo(KeywordBean key) {
		return key.frequency - frequency;
	}
}

	

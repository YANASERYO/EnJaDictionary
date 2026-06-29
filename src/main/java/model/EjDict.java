package model;
import java.io.Serializable;

public class EjDict implements Serializable {
	private int id;
	private String word;
	private String explanation;
	
	public EjDict() {}
	public EjDict(int id,String word,String explanation) {
		this.id = id;
		this.word = word;
		this.explanation = explanation;
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getWord() {return word;}
	public void setWord(String word) {this.word = word;}
	
	public String getExplanation() {return explanation;}
	public void setExplanation(String explanation) {this.explanation = explanation;}

}

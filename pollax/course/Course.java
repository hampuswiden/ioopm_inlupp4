package pollax.course;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import pollax.items.Book;
/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Course {
	private String courseName;
	private Book book;
	private int hp;
	private String question; // NEW EDIT
	private List<String> choices = new ArrayList<String>(); // NEW EDIT
	private String correctAnswer; // NEW EDIT

	public Course() {

	}

	public <T> Course(HashMap<String, T> dbBooks, String... args) {
		this.courseName = args[0];
		T book = dbBooks.get(args[1].toLowerCase());
		this.book = (Book) book;
		this.hp = Integer.parseInt(args[2]);
	}

	public void addQuiz(String... args){
		this.question = args[0];
		this.choices.add(args[1]);
		this.choices.add(args[2]);
		this.choices.add(args[3]);
		this.correctAnswer = args[4];
	}

	public String getQuestion(){
		return this.question;
	}

	public String getCorrectAnswer() {
		return this.correctAnswer;
	}

	public List<String> getChoices(){
		return this.choices;
	}

	public void removeChoice(String choice){
		if (this.choices.contains(choice)) {
			this.choices.remove(choice);
		}
	}

	public String toString() {
		return this.courseName;
	}

	public Book getCourseBook(){
		return this.book;
	}

	public int getHp() {
		return this.hp;
	}

	@Override
	public boolean equals(Object other) {
	    if (other instanceof Course) {
	        return this.equals((Course) other);
	    } else {
	        return false;
	    }
	}

	public boolean equals(Course other) {
		return this.courseName.equals(other.courseName);
	}
}

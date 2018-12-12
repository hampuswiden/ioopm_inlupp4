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
	/**
	* Name of the course.
	*/
	private String courseName;

	/**
	* Book related to the course.
	*/
	private Book book;

	/**
	*	number of HP the course gives.
	*/
	private int hp;

	/**
	* The question asked when taking exam for course.
	*/
	private String question;

	/**
	* List of choices for the exam.
	*/
	private List<String> choices = new ArrayList<String>();

	/**
	* the correct answer to the exam.
	*/
	private String correctAnswer;

	/**
	* empty constructor for Course.
	*/
	public Course() {

	}

	/**
	* Constructor for course.
	* @param dbBooks HashMap mapping String to <T> (Book).
	* @param args Arguments to be used when constructing class.
	*/
	public <T> Course(HashMap<String, T> dbBooks, String... args) {
		this.courseName = args[0];
		T book = dbBooks.get(args[1].toLowerCase());
		this.book = (Book) book;
		this.hp = Integer.parseInt(args[2]);
	}

	/**
	* adds a quiz to Course.
	* @param args args to be used in the quiz.
	*/
	public void addQuiz(String... args){
		this.question = args[0];
		this.choices.add(args[1]);
		this.choices.add(args[2]);
		this.choices.add(args[3]);
		this.correctAnswer = args[4];
	}

	/**
	* gets question, instance variable.
	* @return question.
	*/
	public String getQuestion(){
		return this.question;
	}

	/**
	* gets correctAnswer, instance variable.
	* @return correctAnswer.
	*/
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}

	/**
	* gets choices, instance variable.
	* @return choices.
	*/
	public List<String> getChoices(){
		return this.choices;
	}

	/**
	* removes a choice from choices.
	* @param choice the choice to remove.
	*/
	public void removeChoice(String choice){
		if (this.choices.contains(choice)) {
			this.choices.remove(choice);
		}
	}

	/**
	* To string function.
	* @return course name.
	*/
	public String toString() {
		return this.courseName;
	}

	/**
	* gets courseBook, instance variable.
	* @return book.
	*/
	public Book getCourseBook(){
		return this.book;
	}

	/**
	* gets hp, instance variable.
	* @return hp.
	*/
	public int getHp() {
		return this.hp;
	}

	/**
	* compares equality between this and other object.
	* @param other Object to compare this to.
	* @return true if other and this are equal.
	*/
	@Override
	public boolean equals(Object other) {
	    if (other instanceof Course) {
	        return this.equals((Course) other);
	    } else {
	        return false;
	    }
	}

	/**
	* compares equality between this and other Course.
	* @param other Course to compare this to.
	* @return true if other and this are equal.
	*/
	public boolean equals(Course other) {
		return this.courseName.equals(other.courseName);
	}
}

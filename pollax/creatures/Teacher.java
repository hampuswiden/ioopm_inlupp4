package pollax.creatures;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import pollax.course.Course;
import pollax.items.Book;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Teacher extends Creature {
	private String name;
	private Course course;
	private String question;
	private List<String> choices = new ArrayList<String>();
	private String correctAnswer; 

	public Teacher() {

	}

	public <T> Teacher(HashMap<String, T> dbCourses, String... args) {
		this.name = args[0];
		T course = dbCourses.get(args[1]);
		this.course = (Course) course;
		this.question = args[2];
		this.choices.add(args[3]);
		this.choices.add(args[4]);
		this.choices.add(args[5]);
		this.correctAnswer = args[6];
	}

	public String toString() {
		return this.name;
	}

	public boolean isTeacher() {
		return true;
	}
}

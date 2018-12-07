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
		T course = dbCourses.get(args[1].toLowerCase());
		this.course = (Course) course;
		this.question = args[2];
		this.choices.add(args[3]);
		this.choices.add(args[4]);
		this.choices.add(args[5]);
		this.correctAnswer = args[6];
	}

	public String toString() {
		return "(T) " + this.name;
	}

	public String getName() {
		return this.name;
	}

	public boolean isTeacher() {
		return true;
	}

	public Course getCourse() {
		return this.course;
	}

	public void talk() {
		System.out.println(this.name + ": Hi! If you wan't to sign up, type 'enroll " + this.course + "'.");
	}

	public boolean quiz() {
		System.out.println("\n" + this.name + ": " + question);
		String choicesStr = "";
		for (int i = 0; i < this.choices.size(); i++) {
			choicesStr += i+1 + ": " + this.choices.get(i);
			if (i != this.choices.size()-1) {
				choicesStr += ", ";
			}
		}
		System.out.println(choicesStr + ".");
		System.out.print("Choice: ");
		String choice = System.console().readLine();
		if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
			String answer = this.choices.get(Integer.parseInt(choice)-1);
			return answer.equals(this.correctAnswer);
		} else {
			// throw new Bad Input 
		}
		return false; // Can be removed when throw new Error is implemented?
	}

	@Override
	public boolean equals(Object other) {
	    if (other instanceof Teacher) {
	        return this.equals((Teacher) other);
	    } else {
	        return false;
	    }
	}

	public boolean equals(Teacher other) {
		return this.name.equals(other.name);
	}
}

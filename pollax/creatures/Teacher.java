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
	/**
	* name of the teacher.
	*/
	private String name;

	/**
	* course that Teacher teaches.
	*/
	private Course course;
	//private String question;
	//private List<String> choices = new ArrayList<String>();
	//private String correctAnswer;

	/**
	* empty constructor for Teacher.
	*/
	public Teacher() {

	}

	/**
	* constructor for Teacher.
	* @param dbCourses HashMap mapping course names to <T> (Courses).
	* @param args Arguments to be used in constructor.
	*/
	public <T> Teacher(HashMap<String, T> dbCourses, String... args) {
		this.name = args[0];
		T course = dbCourses.get(args[1].toLowerCase());
		this.course = (Course) course;
		this.course.addQuiz(args[2], args[3], args[4], args[5], args[6]);
	}

	/**
	* toString function for Teacher.
	* @return (T) + teacher name.
	*/
	public String toString() {
		return "(T) " + this.name;
	}

	/**
	* gets name, instance variable.
	* @return name.
	*/
	public String getName() {
		return this.name;
	}

	/**
	* checks if creature is of type Teacher.
	* @return true;
	*/
	public boolean isTeacher() {
		return true;
	}

	/**
	* gets course, instance variable.
	* @return course.
	*/
	public Course getCourse() {
		return this.course;
	}

	/**
	* prints name and course of Teacher.
	*/
	public void talk() {
		System.out.println(this.name + ": Hi! If you wan't to sign up, type 'enroll " + this.course + "'.");
	}

	/**
	* Makes a quiz that user has to answer. If answered correct, finish course and get hp, otherwise unifish course and lose hp or nothing.
	* @return true if quiz answered correctly.
	*/
	public boolean quiz() {
		System.out.println("\n" + this.name + ": " + this.course.getQuestion());
		String choicesStr = "";
		List<String> quizChoices = this.course.getChoices();
		for (int i = 0; i < quizChoices.size(); i++) {
			choicesStr += i+1 + ": " + quizChoices.get(i);
			if (i != quizChoices.size()-1) {
				choicesStr += ", ";
			}
		}
		System.out.println(choicesStr + ".");
		System.out.print("Choice: ");
		String choice = System.console().readLine();
		if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
			String answer = quizChoices.get(Integer.parseInt(choice)-1);
			return answer.equals(this.course.getCorrectAnswer());
		} else {
			// throw new Bad Input
		}
		return false; // Can be removed when throw new Error is implemented?
	}

	/**
	* checks for equality between this and Object other.
	* @param other Object to compare equality with.
	* @return true if equal.
	*/
	@Override
	public boolean equals(Object other) {
	    if (other instanceof Teacher) {
	        return this.equals((Teacher) other);
	    } else {
	        return false;
	    }
	}

	/**
	* checks for equality between this and Teacher other.
	* @param other Teacher to compare equality with.
	* @return true if name is equal.
	*/
	public boolean equals(Teacher other) {
		return this.name.equals(other.name);
	}
}

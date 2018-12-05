package pollax.creatures;

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

	public Teacher(String name) {
		this.name = name;
	}
}

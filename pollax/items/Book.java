package pollax.items;

import pollax.course.Course;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Book extends Item {
	private String title;
	private String author;
	private String year;
	private Course course;

	public Book() {

	}

	public Book(String... args) {
		this.title = args[0];
		this.author = args[1];
		this.year = args[2];
		super.setCapacity(Integer.parseInt(args[3]));
	}

	@Override
	public String toString() {
		return "Title: " + this.title; /*+
		", Author: " + this.author +
		", Year: " + this.year +
		", Capacity: " + super.getCapacity();*/
	}

	public Course getCourse(){
		return this.course;
	}

	public void assignCourse(Course course){
		this.course = course;
	}
}

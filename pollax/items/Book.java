package pollax.items;

import pollax.course.Course;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Book extends Item {
	/**
	* title of Book.
	*/
	private String title;

	/**
	* author of Book.
	*/
	private String author;

	/**
	* year Book was published.
	*/
	private String year;

	/**
	* Course that Book belongs to.
	*/
	private Course course;

	/**
	* text that Book contains.
	*/
	private String bookText;

	/**
	* empty constructor for Book.
	*/
	public Book() {

	}

	/**
	* constructor for Book.
	* @param args Arguments to be applied to Book.
	*/
	public Book(String... args) {
		this.title = args[0];
		this.author = args[1];
		this.year = args[2];
		super.setCapacity(Integer.parseInt(args[3]));
		this.bookText = args[4];
	}

	/**
	* toString function for Book.
	* @return returns title of Book.
	*/
	@Override
	public String toString() {
		return /*"Title: " + */this.title; /*+
		", Author: " + this.author +
		", Year: " + this.year +
		", Capacity: " + super.getCapacity();*/
	}

	/**
	* gets course which book belongs to.
	* @return course.
	*/
	public Course getCourse(){
		return this.course;
	}

	/**
	* assigns a course to Book.
	* @param course assigned to Book.
	*/
	public void assignCourse(Course course){
		this.course = course;
	}

	/**
	* gets text that book contains.
	* @return bookText.
	*/
	public String getBookText(){
		return this.bookText;
	}
}

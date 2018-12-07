package pollax.course;

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

	public Course() {

	}
	
	public <T> Course(HashMap<String, T> dbBooks, String... args) {
		this.courseName = args[0];
		T book = dbBooks.get(args[1]);
		this.book = (Book) book;
		this.hp = Integer.parseInt(args[2]);
	}
}

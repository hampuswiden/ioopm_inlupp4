package pollax.course;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Course {
	private String courseName;
	private String book;
	private int hp;

	public Course(String courseName, String book, String hp) {
		this.courseName = courseName;
		this.book = book;
		this.hp = Integer.parseInt(hp);
	}
}

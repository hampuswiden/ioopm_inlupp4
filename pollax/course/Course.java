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

	public Course() {

	}
	
	public Course(String... args) {
		this.courseName = args[0];
		this.book = args[1];
		this.hp = Integer.parseInt(args[2]);
	}
}

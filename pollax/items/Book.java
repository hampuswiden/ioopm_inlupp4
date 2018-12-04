package pollax.items;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Book extends Item {
	private String title;
	private String author;
	private String year;
	private int capacity;

	public Book() {

	}

	public Book(String... args) {
		this.title = args[0];
		this.author = args[1];
		this.year = args[2];
		this.capacity = Integer.parseInt(args[3]);
	}
}
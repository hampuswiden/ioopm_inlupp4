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
		return "Title: " + this.title +
		", Author: " + this.author + 
		", Year: " + this.year +
		", Capacity: " + super.getCapacity();
	}
}
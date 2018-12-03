package pollax.items;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Key extends Item {
	private boolean key;

	public Key(String name) {
		this.key = true;
	}

	public boolean keyActive() {
		return this.key;
	}
}

package pollax.interactive;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Door extends InteractiveObject {
	private boolean open;

	public Door(String s) {
		if (s.equals("True")) {
			this.open = true;
		} else {
			this.open = false;
		}
	}

	public boolean isOpen() {
		return this.open;
	}

	public void open() {
		this.open = true;
	}
}

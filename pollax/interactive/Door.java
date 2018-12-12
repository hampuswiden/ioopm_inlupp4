package pollax.interactive;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Door extends InteractiveObject {
	/**
	* boolean if door is open. true if open.
	*/
	private boolean open;

	/**
	* sets opem to true (open) or false (locked).
	* @param s String that holds "True" or "False".
	*/
	public Door(String s) {
		if (s.equals("True")) {
			this.open = false;
		} else {
			this.open = true;
		}
	}

	/**
	* checks if door is open, instance variable.
	* @return true if open.
	*/
	public boolean isOpen() {
		return this.open;
	}

	/**
	* opens door (sets open to true).
	*/
	public void open() {
		this.open = true;
	}
}

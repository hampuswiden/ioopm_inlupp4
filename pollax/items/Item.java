package pollax.items;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Item {
	/**
	* capacity that Item occupies when carried.
	*/
	private int capacity;

	/**
	* gets capacity that Item occupies.
	* @return capacity.
	*/
	public int getCapacity() {
		return this.capacity;
	}

	/**
	* sets capacity Item occupies.
	* @param capacity that Item occupies.
	*/
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}

package pollax.items;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Key extends Item {

	/**
	* constructor for Key.
	*/
	public Key() {
		super.setCapacity(1);
	}

	/**
	* toString function for Key.
	* @return "Key".
	*/
	@Override
	public String toString() {
		return "Key";
	}

	/**
	* checks for equality between this and other Object.
	* @param other Object to compare equality with.
	* @return true if this and other are equal.
	*/
	@Override
	public boolean equals(Object other) {
	    if (other instanceof Key) {
	        return true;
	    } else {
	        return false;
	    }
	}

	/**
	* Gets HashCode for Key.
	* @return HashCode for "key".
	*/
	@Override
	public int hashCode() {
		return "key".hashCode();
	}
}

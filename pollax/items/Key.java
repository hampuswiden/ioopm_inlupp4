package pollax.items;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Key extends Item {

	public Key() {
		super.setCapacity(1);
	}

	
	@Override
	public String toString() {
		return "Key";
	}


	@Override
	public boolean equals(Object other) {
	    if (other instanceof Key) {
	        return true;
	    } else {
	        return false;
	    }
	}

	@Override
	public int hashCode() {
		return "key".hashCode();
	} 
}

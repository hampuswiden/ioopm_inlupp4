package pollax.creatures;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public abstract class Creature {

	//public Room getRoom()

	public boolean isAvatar() {
		return false;
	}

	public boolean isTeacher() {
		return false;
	}

	public boolean isStudent() {
		return false;
	}

	public abstract void talk();
}

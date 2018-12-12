package pollax.creatures;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public abstract class Creature {

	/**
	* checks if creature is of type Avatar.
	* @return false.
	*/
	public boolean isAvatar() {
		return false;
	}

	/**
	* checks if creature is of type Teacher.
	* @return false.
	*/
	public boolean isTeacher() {
		return false;
	}

	/**
	* checks if creature is of type Student.
	* @return false.
	*/
	public boolean isStudent() {
		return false;
	}

	/**
	* checks if creature is of type Sfinx.
	* @return false.
	*/
	public boolean isSfinx() {
		return false;
	}

	public abstract void talk();
}

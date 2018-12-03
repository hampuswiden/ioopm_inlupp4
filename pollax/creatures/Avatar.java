package pollax.creatures;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Avatar extends Creature {
	private String name;

	public Avatar(String name) {
		this.name = name;
	}

	@Override
	public boolean isAvatar() {
		return true;
	}
}

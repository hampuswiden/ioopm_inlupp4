package pollax.creatures;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Sfinx extends Creature {
	/**
	* name for the Sfinx.
	*/
	private String name = "Sfinxen";

	/**
	* List of wisdoms that the sfinx holds.
	*/
	private static List<String> wisdoms = new ArrayList<String>();

	/**
	* random().
	*/
	private Random rand = new Random();

	static {
		Sfinx.wisdoms.add("One must master the art of reading code, before mastering the art of writing code.");
		Sfinx.wisdoms.add("Be happy when a program crashes, at least it didn't trash.");
		Sfinx.wisdoms.add("Off by one errors are the hardest to remember, but the easiest to fix.");
		Sfinx.wisdoms.add("Hash tables are your best friends, use them.");
	}

	/**
	* empty constructor for Sfinx.
	*/
	public Sfinx() {

	}

	/**
	* to string funciton for Sfinx.
	* @return name of Sfinx.
	*/
	@Override
	public String toString() {
		return this.name;
	}

	/**
	* checks if creature is of type Sfinx.
	* @return true.
	*/
	@Override
	public boolean isSfinx() {
		return true;
	}

	/**
	* Makes the sfinx talk.
	*/
	@Override
	public void talk() {
		int n = this.rand.nextInt(this.wisdoms.size());
		String randomWisdom = this.wisdoms.get(n);
		System.out.println(this.name + ": " + randomWisdom);
	}

	/**
	* Attempts to graduate the avatar, if enough hp has been gathered.
	* @param avatar avatar that tries to graduate.
	* @return true if student meets requirements of graduating.
	*/
	public boolean graduate(Avatar avatar) {
		if (avatar.getHp() >= 180 && avatar.getUnfinishedCourses().size() == 0) {
			System.out.println(this.name + ": " + "Congratulationz! You have now graduated from UU with a 3 year degree!");
			return true;
		}
		System.out.println(this.name + ": " + "Hmm. You don't seem to meet the requirements. Make sure you've earned 180hp and that you have no unfinished course(s) left!");
		return false;
	}
}

package pollax.creatures;

import pollax.world.*;
import pollax.items.*;
import pollax.course.*;
import pollax.utils.InvalidInputException;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Avatar extends Student {
	/**
	* avatar current hp.
	*/
	private int hp = 60;

	/**
	* avatar max capacity.
	*/
	private int maxCapacity = 10;

	/**
	* Constructor for Avatar.
	* @param name name for Avatar.
	* @param startRoom the room which Avatar starts in.
	*/
	public Avatar(String name, Room startRoom) {
		super(name, startRoom);
	}

	/**
	* checks if Object is Avatar.
	* @return true.
	*/
	@Override
	public boolean isAvatar() {
		return true;
	}

	/**
	* gets current room, instance variable.
	* @return room.
	*/
	public Room getRoom() {
		return super.getRoom();
	}

	/**
	* gets student total hp, instance variable.
	* @return hp.
	*/
	public int getHp() {
		return this.hp;
	}

	/**
	* moves avatar to different room if possible.
	* @param direction the direction which avatar tries to move.
	* @param world the current world.
	*/
	public void go(String direction, World world) {
		Room room = super.getRoom();
		super.go(direction, world);
		Room newRoom = super.getRoom();

		if (newRoom.equals(room)) { // We didn't move anywhere, no quiz!
			return;
		}

		List<Course> unfinishedCourses = super.getUnfinishedCourses();
		List<Course> finishedCourses = super.getFinishedCourses();
		if (newRoom.hasTeacher()) {
			Teacher teacher = newRoom.getTeacher();
			Course course = teacher.getCourse();

			int percentage = 0;
			if (unfinishedCourses.contains(course)) {
				percentage = 75;
			} else if (finishedCourses.contains(course)) {
				percentage = 50;
			} else {
				// Man kanske vill printa att det finns ny kurs här att enrolla till här?
			}
			boolean askedQuiz = world.random(percentage);
			if (askedQuiz) {
				boolean correct = teacher.quiz();
				if (correct) {
					this.finishCourse(course);
				} else {
					this.unfinishCourse(course);
				}
			}
		}
	}

	/**
	* Tries to open a locked door.
	* @param direction the direction which avatar tries to unlock a door.
	*/
	public void openDoor(String direction) {
		Room room = super.getRoom();
		List<Item> items = super.getItems();

		Key key = new Key();
		if (room.checkDirectionRoom(direction)) {
			if (!room.checkDirectionDoor(direction)) {
				if (items.contains(key)) {
					room.openDoor(direction);
					super.removeItem(key);
					System.out.println("Succesfully opened the " + direction + " door.");
				} else {
					System.out.println("No key(s) in inventory!");
				}
			} else {
				System.out.println("The " + direction + " door is already open.");
			}
		} else {
			System.out.println("No room to the " + direction + ".");
		}
	}

	/**
	* picks up item from current room and places it in inventory.
	* @param itemName name of item that avatar tries to pick up.
	* @param world the current world.
	*/
	public void pickUp(String itemName, World world) {
		Item item = world.dbItems().get(itemName);
		Room room = super.getRoom();
		List<Item> roomItems = room.getItems();

		if (roomItems.contains(item)) {
			if (checkCapacity(item)) {
				super.addItem(item);
				room.removeItem(item);
				System.out.println("Picked up " + item);
			} else {
				System.out.println("Not enough capacity.");
			}
		} else {
			System.out.println("No item '" + itemName + "' in the room.");
		}
	}

	/**
	* tries to drop item from inventory to current room.
	* @param itemName name of item that avatar tries to drop.
	* @param world the current world.
	*/
	public void drop(String itemName, World world) {
		Item item = world.dbItems().get(itemName);

		List<Item> items = super.getItems();
		Room room = super.getRoom();
		List<Item> roomItems = room.getItems();

		if (items.contains(item)) {
			room.addItem(item);
			super.removeItem(item);
			System.out.println("Dropped " + item + " on the floor.");
		} else {
			System.out.println("No " + item + " in inventory.");
		}
	}

	/**
	* Calculates current carrying capacity.
	* @return the current capacity avatar is carrying.
	*/
	public int currentCapacity() {
		List<Item> items = super.getItems();

		Item item;
		int sumCapacity = 0;
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			sumCapacity += item.getCapacity();
		}
		return sumCapacity;
	}

	/**
	* checks capacity for item and if it fits avatar inventory.
	* @param item the item checked.
	* @return true if it fits in inventory.
	*/
	public boolean checkCapacity(Item item) {
		return this.currentCapacity() + item.getCapacity() <= this.maxCapacity;
	}

	/**
	* prints current inventory to user.
	*/
	public void inventory() {
		List<Item> items = super.getItems();

		String retStr = "Items: ";
		Item item;
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			retStr += item.toString();
			if (i != items.size()-1)
        	{
				retStr += ", ";
        	}
		}
		retStr += "\nCapacity: " + this.currentCapacity() + "/" + this.maxCapacity;
		System.out.println(retStr);
	}

	/**
	* prints info about current courses to user.
	*/
	public void courseInfo() {
		List<Course> unfinishedCourses = super.getUnfinishedCourses();
		List<Course> finishedCourses = super.getFinishedCourses();

		String retStr = "Unfinished Courses: ";
		for (int i = 0; i < unfinishedCourses.size(); i++) {
        	retStr += unfinishedCourses.get(i);
        	if (i != unfinishedCourses.size()-1) {
				retStr += ", ";
        	}
		}
		retStr += "\nFinished Courses: ";
		for (int i = 0; i < finishedCourses.size(); i++) {
        	retStr += finishedCourses.get(i);
        	if (i != finishedCourses.size()-1) {
				retStr += ", ";
        	}
		}
		retStr += "\nHP: " + this.hp;
		System.out.println(retStr);
	}

	/**
	* Tries to talk to another creature.
	* @param creatureName name of the creature avatar tries to talk to.
	* @param world the current world.
	*/
	public void talk(String creatureName, World world) {
		Creature creature = world.dbCreatures().get(creatureName);
		Room room = super.getRoom();
		List<Creature> roomCreatures = room.getCreatures();
		if (roomCreatures.contains(creature)) {
			if (creature.isStudent()){
				Student student = (Student) creature;
				student.talk(this);
			} else {
				creature.talk();
			}

		} else {
			System.out.println("No person called '" + creatureName + "' in the room.");
		}
	}

	/**
	* Tries to trade an item to another Creature. Might get items back.
	* @param creatureName name of the creature avatar tries to talk to.
	* @param world the current world.
	*/
	public void trade(String creatureName, World world) {
		Creature creature = world.dbCreatures().get(creatureName);
		Room room = super.getRoom();
		List<Creature> roomCreatures = room.getCreatures();
		if (roomCreatures.contains(creature)) {
			if (creature.isStudent()){
				Student student = (Student) creature;
				student.trade(this);
			} else if (creature.isTeacher()) {
				System.out.println("I don't think teachers takes kindly to bribes...");
			}

		} else {
			System.out.println("No person called '" + creatureName + "' in the room.");
		}
	}

	/**
	* Tries to read a book in inventory and print its contents.
	* @param bookName name of the book avatar tries to read.
	* @param world the current world.
	*/
	public void read(String bookName, World world) {
		Item item = world.dbItems().get(bookName);
		List<Item> items = super.getItems();

		if (items.contains(item) && item instanceof Book) {
			Book book = (Book) item;
			System.out.println(book.getBookText());
		} else {
			System.out.println("No book called " + bookName + " in inventory.");
		}
	}

	/**
	* Tries to enroll a course
	* @param courseName the name of the course avatar tries to enroll.
	* @param world the current world.
	*/
	public void enroll(String courseName, World world) {
		Course course = world.dbCourses().get(courseName);
		if (course == null) {
			System.out.println("No course named: " + courseName);
			return;
		}

		List<Course> unfinishedCourses = super.getUnfinishedCourses();
		List<Course> finishedCourses = super.getFinishedCourses();
		if (unfinishedCourses.contains(course)) {
			System.out.println("You are already enrolled in " + course + ".");
			return;
		}
		if (finishedCourses.contains(course)) {
			System.out.println("You have already completed " + course + "!");
			return;
		}

		Room room = super.getRoom();
		List<Creature> roomCreatures = room.getCreatures();
		Creature creature;
		for (int i = 0; i < roomCreatures.size(); i++) {
        	creature = roomCreatures.get(i);
        	if (creature.isTeacher()) {
        		Teacher teacher = (Teacher) creature;
        		if (teacher.getCourse().equals(course)) { // Check that teacher for course is in room
        			unfinishedCourses.add(course);
        			System.out.println("You have enrolled in '" + course + "'.");
        			return;
        		}
        	}
		}
		System.out.println("No teacher who teaches '" + course + "' in the room.");
	}

	/**
	* finishes a course and gain corresponding hp from avatar.
	* @param course course to finish.
	*/
	public void finishCourse(Course course) {
		List<Course> unfinishedCourses = super.getUnfinishedCourses();
		List<Course> finishedCourses = super.getFinishedCourses();

		Room room = super.getRoom();
		if (!room.hasTeacher()) {
			// throw new BLABLA SHOULD NEVER HAPPEN!
		}
		Teacher teacher = room.getTeacher();
		String teacherName = teacher.getName();
		if (unfinishedCourses.contains(course)) {
			unfinishedCourses.remove(course);
			finishedCourses.add(course);
			this.hp += course.getHp();
			System.out.println(teacherName + ": Excellent! You earned " + course.getHp() + "hp and are now done with " + course + ".");
		} else if (finishedCourses.contains(course)) {
			System.out.println(teacherName + ": Good job! You continue to have completed " + course + ".");
		}
	}

	/**
	* unfinishes a course and remove corresponding hp from avatar.
	* @param course course to unfinish.
	*/
	public void unfinishCourse(Course course) {
		List<Course> unfinishedCourses = super.getUnfinishedCourses();
		List<Course> finishedCourses = super.getFinishedCourses();

		Room room = super.getRoom();
		if (!room.hasTeacher()) {
			// throw new BLABLA SHOULD NEVER HAPPEN!
		}
		Teacher teacher = room.getTeacher();
		String teacherName = teacher.getName();
		if (unfinishedCourses.contains(course)) {
			System.out.println(teacherName + ": Unfortunate! You'll have to try again next exam!");
		} else if (finishedCourses.contains(course)) {
			finishedCourses.remove(course);
			unfinishedCourses.add(course);
			this.hp -= course.getHp();
			System.out.println(teacherName + ": Sorry... Your earned hp for the course (" + course.getHp() + ") has been withdrawn.\nYou'll have to retake the course.");
		}
	}

	/**
	* graduates from universtiy if enough hp has been gathered.
	* @param world the current world.
	* @return true if conditions are met for graduating.
	*/
	public boolean graduate(World world) {
		Room room = super.getRoom();
		if (!room.hasSfinx()) {
			System.out.println("You need to talk to 'Sfinxen' in order to graduate.");
			return false;
		}

		Sfinx sfinxen = room.getSfinx();
		boolean graduated = sfinxen.graduate(this);
		System.out.println("Graduated: " + graduated);


		return graduated;
	}
}

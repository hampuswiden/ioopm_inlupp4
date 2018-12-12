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
	private int hp = 60;
	private int maxCapacity = 10;

	public Avatar(String name, Room startRoom) {
		super(name, startRoom);
	}

	@Override
	public boolean isAvatar() {
		return true;
	}

	public Room getRoom() {
		return super.getRoom();
	}

	public int getHp() {
		return this.hp;
	}
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

	public boolean checkCapacity(Item item) {
		return this.currentCapacity() + item.getCapacity() <= this.maxCapacity;
	}

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

	public void read(String bookName, World world) {
		Item item = world.dbItems().get(bookName);
		List<Item> items = super.getItems();

		if (items.contains(item) && item instanceof Book) {
			Book book = (Book) item;
			String correctChoice = book.getCourse().getCorrectAnswer();
			System.out.println(
			"After some actual studying i figured out that the answer to the Quiz is \'" +
			correctChoice +
			"\'. Hmm... reading wasn't as bad as i expected."
			);
		} else {
			System.out.println("No book called " + bookName + " in inventory.");
		}
	}

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

package pollax.creatures;

import pollax.world.*;
import pollax.items.*;
import pollax.course.Course;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Student extends Creature {
	/**
	* name of student.
	*/
	private String name;

	/**
	* current room.
	*/
	private Room room;

	/**
	* List of items held.
	*/
	private List<Item> items = new ArrayList<Item>();

	/**
	* List of unfinished courses.
	*/
	private List<Course> unfinishedCourses = new ArrayList<Course>();

	/**
	* List of finished courses.
	*/
	private List<Course> finishedCourses = new ArrayList<Course>();

	/**
	* empty constructor for Student.
	*/
	public Student(){}

	/**
	* constructor for student.
	* @param name student name.
	* @param startRoom starting room.
	*/
	public Student(String name, Room startRoom) {
		this.name = name;
		this.room = startRoom;
	}

	/**
	* constructor for student.
	* @param dbCourses HashMap mapping Course name to <T> (Course).
	* @param args Arguments to be applied to Student.
	*/
	public <T> Student(HashMap<String, T> dbCourses, String... args) {
		this.name = args[0];
		T finishedCourse = dbCourses.get(args[1].toLowerCase());
		T unfinishedCourse = dbCourses.get(args[2].toLowerCase());
		this.finishedCourses.add((Course) finishedCourse);
		this.unfinishedCourses.add((Course) unfinishedCourse);
	}

	/**
	* toString function for Student.
	* @return (S) + name of student.
	*/
	public String toString() {
		return "(S) " + this.name;
	}

	/**
	* checks if Creature is of type student.
	* @return true.
	*/
	public boolean isStudent() {
		return true;
	}

	/**
	* assigns a room to student.
	* @param room the room which to be assigned.
	*/
	public void assignRoom(Room room) {
		this.room = room;
	}

	/**
	* Makes a conversation with avatar.
	* @param avatar the avatar.
	*/
	public void talk(Avatar avatar) {
		Book wantedBook = this.unfinishedCourses.get(0).getCourseBook();
		List<Item> avatarItems = avatar.getItems();

		if(this.items.contains(wantedBook)){
			System.out.println("Hi, thanks again for the book. I don't know what I'd have done without it!");
			return;
		}

		System.out.println(
		this.toString() +
		": Hi! I'm currently studying \'" +
		this.unfinishedCourses.get(0) +
		"\'. However, I lost my course book, \'" + wantedBook + "\'.");
		if (avatar.getItems().contains(wantedBook)){
			System.out.print(".. Wait, is that a copy of \'" +
			wantedBook +
			"\' sticking out of your bag? If you trade it to me, I'd be willing to give you ");
			if(this.items.size()==1){
				System.out.print("\'" + this.items.get(0) + "\', the course book for \'" + this.finishedCourses.get(0) + "\'.");
			} else {
				System.out.print("some tips for the quiz in \'" + this.finishedCourses.get(0) + "\'.");
			}
		} else {
			System.out.print(" I'd owe you a big favor if you could find me one.");
		}
	}

	/**
	* attempts to trade with avatar. Will gain a book and will give a book if student holds one of his finished course.
	* @param avatar avatar to trade with.
	*/
	public void trade(Avatar avatar) {
		Course finishedCourse = this.finishedCourses.get(0);
		Course unfinishedCourse = this.unfinishedCourses.get(0);
		Book studentBook = finishedCourse.getCourseBook();
		Book wantedBook = unfinishedCourse.getCourseBook();
		List<Item> avatarItems = avatar.getItems();

		if(avatarItems.contains(wantedBook)){
			avatar.removeItem(wantedBook);
			this.addItem(wantedBook);

			if(this.items.size()==2){
				avatar.addItem(studentBook);
				this.removeItem(studentBook);
				System.out.println("Wow! Thanks, here you have \'" + studentBook + "\', that should help you with \'" + finishedCourse + "\'.");
				return;

			} else {
				System.out.println("Wow! Thanks, so about those notes. ");
				List<String> choices = finishedCourse.getChoices();
				for(String choice : choices){
					if(!choice.equals(finishedCourse.getCorrectAnswer())){
							finishedCourse.removeChoice(choice);
							System.out.print( "The right answer to the quiz in \'" + finishedCourse + "\' is... Not \'" + choice + "\'!!");
							return;
					}
				}
			}
		}
	}

	/**
	* gets current room, instance variable.
	* @return room.
	*/
	public Room getRoom() {
		return this.room;
	}

	/**
	* gets list of held items.
	* @return List of Item.
	*/
	public List<Item> getItems() {
		return this.items;
	}

	/**
	* adds item to held items.
	* @param item item to be placed in inventory.
	*/
	public void addItem(Item item) {
		this.items.add(item);
	}

	/**
	* removes item from held items.
	* @param item to be removed from inventory.
	*/
	public void removeItem(Item item) {
		if (this.items.contains(item)) {
			this.items.remove(item);
		}
	}

	/**
	* gets list of unfinished courses.
	* @return List of unfinished Courses
	*/
	public List<Course> getUnfinishedCourses() {
		return this.unfinishedCourses;
	}

	/**
	* gets list of finished courses.
	* @return List of finished Courses.
	*/
	public List<Course> getFinishedCourses() {
		return this.finishedCourses;
	}

	/**
	* attempts to move student to new room in a direction.
	* @param direction direction which student tries to move.
	* @param world the current world.
	*/
	public void go(String direction, World world) {
		if (this.room.checkDirectionRoom(direction)) {
			if (this.room.checkDirectionDoor(direction)) {
				String newRoomStr = this.room.getRoom(direction);
				Room newRoom = world.dbRooms().get(newRoomStr);
				this.room = newRoom;
				if (this.isAvatar()) {
					System.out.println(newRoom);
				}
			} else if (this.isAvatar()) {
				System.out.println("The " + direction + " door is locked.");
			}
		} else if (this.isAvatar()) {
			System.out.println("No room to the " + direction + ".");
		}
	}

	/**
	* Makes student state his name.
	*/
	public void talk() {
		System.out.println("Hey I'm " + this.name + ".");
	}
}

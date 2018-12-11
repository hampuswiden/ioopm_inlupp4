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
	private String name;
	private Room room;
	private List<Item> items = new ArrayList<Item>();
	private List<Course> unfinishedCourses = new ArrayList<Course>();
	private List<Course> finishedCourses = new ArrayList<Course>();

	public Student(){}

	public Student(String name, Room startRoom) {
		this.name = name;
		this.room = startRoom;
	}

	public <T> Student(HashMap<String, T> dbCourses, String... args) {
		this.name = args[0];
		T finishedCourse = dbCourses.get(args[1].toLowerCase());
		T unfinishedCourse = dbCourses.get(args[2].toLowerCase());
		this.finishedCourses.add((Course) finishedCourse);
		this.unfinishedCourses.add((Course) unfinishedCourse);
	}

	public String toString() {
		return "(S) " + this.name;
	}

	public boolean isStudent() {
		return true;
	}

	public void assignRoom(Room room) {
		this.room = room;
	}

//Hämta namnet på boken?
	public void talk(Avatar avatar) {
		Book wantedBook = this.unfinishedCourses.get(0).getCouseBook();
		List<Item> avatarItems = avatar.getItems();

		if(this.items.contains(wantedBook)){
			System.out.println("Hi, thanks again for the book. I don't know what I'd have done without it!");
			return;
		}

		System.out.println(
		this.name +
		": Hi! I'm currently studying " +
		this.unfinishedCourses.get(0) +
		"... However, I lost my book.");
		if (avatar.getItems().contains(wantedBook)){
			System.out.print(".. Wait, is that " +
			wantedBook +
			" sticking out of your bag? If you trade it to me, I'd be willing to give you ");
			if(this.items.size()==1){
				System.out.print(this.items.get(0));
			} else {
				System.out.print("some tips for the quiz in " + this.finishedCourses.get(0));
			}
		} else {
			System.out.print(" I'd owe you a big favor if you could find me one.");
		}
	}

	public void trade(Avatar avatar) {
		Book wantedBook = this.unfinishedCourses.get(0).getCouseBook();
		Book studentBook = this.finishedCourses.get(0).getCourseBook();
		List<Item> avatarItems = avatar.getItems();

		if(avatarItems.contains(wantedBook)){
			avatar.removeItem(wantedBook);
			this.addItem(wantedBook);

			if(this.items.size()==2){
				avatar.addItem(studentBook);
				this.removeItem(studentBook);

				System.out.printl("Wow! Thanks, here you have " + studentBook);
			} else {
				System.out.println("Wow! Thanks, here are some notes I took from the exam!");
				// FIX COURSE
			}
		}


	}

	public Room getRoom() {
		return this.room;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		if (this.items.contains(item)) {
			this.items.remove(item);
		}
	}

	public List<Course> getUnfinishedCourses() {
		return this.unfinishedCourses;
	}

	public List<Course> getFinishedCourses() {
		return this.finishedCourses;
	}

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

	public void talk() {
		System.out.println("Hey I'm " + this.name + ".");
	}
}

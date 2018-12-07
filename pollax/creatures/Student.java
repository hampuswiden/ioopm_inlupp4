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

	public Student(String name, Room startRoom) {
		this.name = name;
		this.room = startRoom;
	}

	public boolean isStudent() {
		return true;
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
}

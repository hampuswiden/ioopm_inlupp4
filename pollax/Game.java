package pollax;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import pollax.course.*;
import pollax.creatures.*;
import pollax.interactive.*;
import pollax.items.*;
import pollax.world.*;

public class Game {

	public static void main(String[] args) {
		HashMap<String, Room> dbRooms = new HashMap<String, Room>();
		HashMap<String, Course> dbCourses = new HashMap<String, Course>();
		HashMap<String, Book> dbBooks = new HashMap<String, Book>();

		World world = new World(dbRooms, dbCourses, dbBooks);

		Room startRoom = world.randomRoom();
		Avatar player = new Avatar("Ninja", startRoom);
		System.out.println(player.currentRoom());


		boolean loop = true;

        System.out.println("Welcome to the PollaxMUD!");
		while(loop) {
	        System.out.print("Input: ");
	        String input = System.console().readLine();
	        try {
	        	String[] split = input.split(" ");
	        	split[]
	        	if (input.equals("go"))
	            SymbolicExpression result = p.statement();

	        } catch(IOException e) {
	        	expressions += 1;
	            System.err.println("IO Exception!");
	        } 
		}
		System.out.println("Program Terminated");
	}
}

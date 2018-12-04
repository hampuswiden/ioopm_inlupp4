package pollax;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import pollax.course.*;
import pollax.creatures.*;
import pollax.interactive.*;
import pollax.items.*;
import pollax.utils.*;
import pollax.world.*;

public class Game {

	public static void main(String[] args) {
		HashMap<String, Room> dbRooms = new HashMap<String, Room>();
		HashMap<String, Course> dbCourses = new HashMap<String, Course>();
		HashMap<String, Book> dbBooks = new HashMap<String, Book>();

		World world = new World(dbRooms, dbCourses, dbBooks);

		Room startRoom = world.randomRoom();
		Avatar avatar = new Avatar("Ninja", startRoom);


		boolean loop = true;
		StringIdentifier si = new StringIdentifier();

        System.out.println("Welcome to the PollaxMUD!");
		while(loop) {
			try{
						System.out.println(avatar.currentRoom());
		        System.out.print("Input: ");
		        String input = System.console().readLine();
		        String[] result = si.findCommand(input);
		        String command = result[0];
		        String argument = result[1];
		        //System.out.println("command: " + command);
		        //System.out.println("argument: " + argument);

		        if (command.equals("go")) {
		        	avatar.go(argument, dbRooms);
		        }

				} catch(InvalidInputException e) {
					System.out.print("Invalid Input Error: ");
					System.out.println(e.getMessage());
				} /*catch(IOException e) {
					System.err.println("IOException");
				}*/




		}
		System.out.println("Program Terminated");
	}
}

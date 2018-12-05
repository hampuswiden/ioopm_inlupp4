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
		HashMap<String, Item> dbItems = new HashMap<String, Item>();

		World world = new World(dbRooms, dbCourses, dbItems);

		Room startRoom = world.randomRoom();
		Avatar avatar = new Avatar("Ninja", startRoom);

		StringIdentifier si = new StringIdentifier();

        System.out.println("Welcome to the PollaxMUD!");
		System.out.println(startRoom);
		boolean loop = true;

		while(loop) {
			try{
				System.out.println();
		        System.out.print("Input: ");
		        String input = System.console().readLine().toLowerCase();
		        String[] result = si.findCommand(input);
		        String command = result[0];
		        String argument = result[1];
		        //System.out.println("command: " + command);
		        //System.out.println("argument: " + argument);

		        if (command.equals("go")) {
		        	avatar.go(argument, dbRooms);
		        } else if (command.equals("use key with")) {
		        	avatar.openDoor(argument);
		        } else if (command.equals("pick up")) {
		        	avatar.pickUp(argument, dbItems);
		        } else if (command.equals("inventory")) {
		        	avatar.inventory();
		        } else if (command.equals("drop")) {
		        	avatar.drop(argument, dbItems);
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

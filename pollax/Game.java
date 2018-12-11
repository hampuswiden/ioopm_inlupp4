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
		World world = new World();

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


		        if (command.equals("go")) {
							world.moveStudents();
		        	avatar.go(argument, world);
		        } else if (command.equals("use key with")) {
		        	avatar.openDoor(argument);
		        } else if (command.equals("pick up")) {
		        	avatar.pickUp(argument, world);
		        } else if (command.equals("inventory")) {
		        	avatar.inventory();
		        } else if (command.equals("drop")) {
		        	avatar.drop(argument, world);
		        } else if (command.equals("talk")) {
		        	avatar.talk(argument, world);
		        } else if (command.equals("enroll")) {
		        	avatar.enroll(argument, world);
		        } else if (command.equals("courseinfo")) {
		        	avatar.courseInfo();
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

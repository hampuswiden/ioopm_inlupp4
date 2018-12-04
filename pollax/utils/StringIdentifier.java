package pollax.utils;

import java.util.Arrays;

public class StringIdentifier {
	private String string;
	private String[] commands = new String[]{
		"go",
		"talk",
		"drop",
		"inventory",
		"enroll",
		"trade",
		"graduate",
		"pick up",
		"use key with"};





	public StringIdentifier() {
	}
	/////// FIX ME, CHECK COMPLETE WORDS
	public String[] findCommand(String string) {
		String command;
		for (int i = 1; i <= string.length(); i++) {
			command = string.substring(0, i);

			if (Arrays.stream(this.commands).anyMatch(command::equals)) {
				if (i+1 > string.length()) {
					throw new InvalidInputException("\'" + string + "\'" + " needs an argument");
				}
				String argument = string.substring(i+1, string.length());
				return new String[]{command, argument};
			}
		}
		throw new InvalidInputException("\'" + string + "\'" + " is not a valid command");
	}
}

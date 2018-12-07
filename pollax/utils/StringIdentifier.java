package pollax.utils;

import java.util.Arrays;

public class StringIdentifier {
	private String string;
	private String[] commands = new String[]{
		"go",
		"talk",
		"drop",
		"enroll",
		"trade",
		"pick up",
		"use key with"};


	private String[] noArgument = new String[]{
		"inventory", 
		"graduate",
		"courseinfo"
	};



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
				} else {
					if (!string.substring(i, i+1).equals(" ")) {
						throw new InvalidInputException("\'" + string + "\'" + " is not a valid command");
					}
					String argument = string.substring(i+1, string.length());
					return new String[]{command, argument};
				}
			} else {
				if (Arrays.stream(this.noArgument).anyMatch(command::equals)) {
					if (command.length() == string.length()) {
						return new String[]{command, ""};
					}
				}
				
			}
		}
		throw new InvalidInputException("\'" + string + "\'" + " is not a valid command");
	}
}

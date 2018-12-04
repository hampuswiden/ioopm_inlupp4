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

	public String[] findCommand(String string) {
		String command;
		for (int i = 1; i <= string.length(); i++) {
			command = string.substring(0, i);

			if (Arrays.stream(this.commands).anyMatch(command::equals)) {
				// throw if i+1 > string.length().
				String argument = string.substring(i+1, string.length());
				return new String[]{command, argument};
			} 
		}
		return new String[]{"Error", "Couldn't find a Command"};
	}	
}
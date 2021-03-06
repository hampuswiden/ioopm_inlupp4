package pollax.utils;

import java.io.StringReader;
import java.util.Scanner;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import pollax.world.Room;
import pollax.course.Course;
import pollax.items.Book;
import pollax.creatures.*;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Parser {
	private String path;

	/**
	* empty constructor for Parser.
	*/
	public Parser() {
	}

	/**
	* assigns input path to instance variable.
	* @param path path for file.
	*/
	public void parse(String path) {
		this.path = path;
	}

	/**
	* generates a database of Strings mapped to given Type <T>.
	* @param instance an instance of type T.
	* @param db the new empty db to be generated.
	* @param dbHelp a databases which is used to generate the new db.
	*/
	@SuppressWarnings("unchecked")
	public <T,S> void generateDB(T instance, HashMap<String, T> db, HashMap<String, S> dbHelp) {
		try {
			FileReader fr = new FileReader(this.path);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				String[] split = line.split("; ");
				String name = split[0].toLowerCase();

	    		if (instance instanceof Room) {
	    			Room room = new Room(split);
	    			db.put(split[0], (T) room);
	    		} else if (instance instanceof Book) {
	    			Book book = new Book(split);
	    			db.put(name, (T) book);
	    		} else if (instance instanceof Course) {
	    			Course course = new Course(dbHelp, split);
	    			db.put(name, (T) course);
	    		} else if (instance instanceof Teacher) {
	    			Teacher teacher = new Teacher(dbHelp, split);
	    			db.put(name, (T) teacher);
					} else if (instance instanceof Student) {
						Student student = new Student(dbHelp, split);
						db.put(name, (T) student);
				} else {
					// Error handling
				}
			}
		} catch(IOException e) {
			System.out.println("error: " + e);
		}
	}
}

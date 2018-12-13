import junit.framework.*;
import org.junit.*;

import pollax.course.*;
import pollax.creatures.*;
import pollax.interactive.*;
import pollax.items.*;
import pollax.utils.*;
import pollax.world.*;


public class Test {

    public Test(){
        World world = new World("./pollax/test_text_files/");
		Room startRoom = world.randomRoom();
		Avatar avatar = new Avatar("Recce", startRoom);
    }


}

import org.junit.*;
import static org.junit.Assert.*;


import pollax.course.*;
import pollax.creatures.*;
import pollax.interactive.*;
import pollax.items.*;
import pollax.utils.*;
import pollax.world.*;


public class GameTest {
    private World world;
    private World bigWorld;
    private Room room;
    private Room bigRoom;
    private Teacher teacher;
    private Student student;
    private Book book;
    private Avatar avatar;


    @Before
    public void before() {
    	World world = new World("./pollax/test_text_files/");
        World bigWorld = new World("./pollax/test_text_files/big/");
        Room startRoom = world.dbRooms().get("Start Room");
        Room bigStartRoom = bigWorld.dbRooms().get("Room Start");
        Creature cTeacher = world.dbCreatures().get("professor oak");
        Teacher teacher = (Teacher) cTeacher;
        Creature cStudent = world.dbCreatures().get("ash ketchup");
        Student student = (Student) cStudent;
        Item iBook = world.dbItems().get("bara att bita i");
        Book book = (Book) iBook;
        Avatar avatar = new Avatar("Recce", startRoom);

        this.world = world;
        this.bigWorld = bigWorld;
        this.room = startRoom;
        this.bigRoom = bigStartRoom;
        this.teacher = teacher;
        this.student = student;
        this.book = book;
        this.avatar = avatar;
    }

    @Test
    public void pickUp() {
        boolean result = this.avatar.getItems().contains(this.book);
        assertTrue(!result);
        this.avatar.pickUp("bara att bita i", this.world);
        result = this.avatar.getItems().contains(this.book);
        assertTrue(result);
    }

    @Test
    public void drop() {
        this.avatar.addItem(this.book);
        boolean result = this.avatar.getItems().contains(this.book);
        assertTrue(result);

        this.avatar.drop("bara att bita i", this.world);
        result = this.avatar.getItems().contains(this.book);
        assertTrue(!result);
    }

    @Test
    public void trade() {
        this.avatar.addItem(this.book);

        Item iStudentBook = world.dbItems().get("how to \"catch 'em all\"");
        Book studentBook = (Book) iStudentBook;
        this.student.addItem(studentBook);

        boolean hasBook = this.avatar.getItems().contains(studentBook);
        assertTrue(!hasBook);

        avatar.trade("ash ketchup", this.world);

        hasBook = this.avatar.getItems().contains(studentBook);
        boolean studentGotBook = this.student.getItems().contains(this.book);
        assertTrue(hasBook && studentGotBook);
    }

    @Test
    public void goOpen() {
        Avatar avatar = new Avatar("Recce", this.bigRoom);

        avatar.go("north", this.bigWorld);
        Room room = avatar.getRoom();
        Room roomOpen = this.bigWorld.dbRooms().get("Room Open");
        boolean equals = room.equals(roomOpen);
        assertTrue(equals);
    }

    @Test
    public void goLocked() {
        Avatar avatar = new Avatar("Recce", this.bigRoom);

        avatar.go("south", this.bigWorld);
        Room room = avatar.getRoom();
        Room roomLocked = this.bigWorld.dbRooms().get("Room Locked");
        boolean equals = room.equals(roomLocked);
        assertTrue(!equals);
    }

    @Test
    public void goWall() {
        Avatar avatar = new Avatar("Recce", this.bigRoom);

        avatar.go("east", this.bigWorld);
        Room room = avatar.getRoom();
        Room noRoom = this.bigWorld.dbRooms().get("Room Start");
        boolean equals = room.equals(noRoom);
        assertTrue(equals);
    }

    @Test
    public void unlockDoor() {
        Avatar avatar = new Avatar("Recce", this.bigRoom);

        avatar.go("south", this.bigWorld);
        Room room = avatar.getRoom();
        Room roomLocked = this.bigWorld.dbRooms().get("Room Locked");
        boolean equals = room.equals(roomLocked);
        assertTrue(!equals);

        Item iKey = this.bigWorld.dbItems().get("key");
        Key key = (Key) iKey;
        avatar.addItem(key);

        avatar.openDoor("south");

        avatar.go("south", this.bigWorld);
        room = avatar.getRoom();
        equals = room.equals(roomLocked);
        assertTrue(equals);
    }

    @Test
    public void finishCourse() {
        this.avatar.enroll("pokémonjakt 1", this.world);
        Course course = this.world.dbCourses().get("pokémonjakt 1");

        int hp = this.avatar.getHp();
        assertEquals(hp, 60);
        this.avatar.finishCourse(course);
        hp = this.avatar.getHp();
        assertEquals(hp, 80);
    }

    @Test
    public void graduate() {
        this.avatar.setHp(180);
        boolean graduated = this.avatar.graduate(this.world);
        assertTrue(graduated);
    }


}

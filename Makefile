TEST_LIBS = lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar

build: build_course build_creature build_interactive build_item build_utils build_world
	javac -d classes pollax/Game.java
run:
	java -classpath classes pollax.Game
build_course:
	javac -d classes pollax/course/*.java
build_creature:
	javac -d classes pollax/creatures/*.java
build_interactive:
	javac -d classes pollax/interactive/*.java
build_item:
	javac -d classes pollax/items/*.java
build_utils:
	javac -d classes pollax/utils/*.java
build_world:
	javac -d classes pollax/world/*.java
clean:
	rm -rf classes
build_test: build_course build_creature build_interactive build_item build_utils build_world
	javac -d classes pollax/GameTest.java
test: build build_test
	java -classpath classes:$(TEST_LIBS) org.junit.runner.JUnitCore GameTest

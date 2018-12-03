build: build_creature build_interactive build_item build_world

run: build

build_creature:
	javac -d classes pollax/creatures/*.java
run_creature: build_creature
	java -classpath classes pollax.creatures.Creature
build_interactive:
	javac -d classes pollax/interactive/*.java
run_interactive: build_interactive
	java -classpath classes pollax.interactive.InteractiveObjects
build_item:
	javac -d classes pollax/items/*.java
run_item: build_item
	java -classpath classes pollax.items.Item
build_world:
	javac -d classes pollax/world/*.java
run_world: build_world
	java -classpath classes pollax.world.World
clean:
	rm -rf classes

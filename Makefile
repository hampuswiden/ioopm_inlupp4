build_creature:
	javac -d classes pollax/creatures/*.java
run_creature: build_creature
	java -classpath classes pollax.creatures.Creature
clean:
	rm -rf classes
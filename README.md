***Program information***

This program is developed in Eclipse IDE with Java 8 by Jiayuan Guo. Junit 4 library is required to run to the unit test class. The project follows the below structure:

* src/main/java
    * solution/drawing
        * Canvas.java
        * CommandLineProcessor.java
        * Driver.java 

* src/test/java
    * solution/drawing
        * CanvasTest.java

__How to run the program__

Import the project into your Eclipse IDE. Run the Driver.java file located in the src/main/java/ directory


__Some assumptions made__

1. The options in the command line is separated by whitespace characters only.
2. The minimum width and height of a canvas is 5 and 3 respectively.
3. The width and height of a rectangle must be at least 3.
4. Diagonal lines are not supported.
5. All lines and rectangle are drawn/painted with a 'x' character.
6. Bucket fill with "color" 'x' is not allowed.


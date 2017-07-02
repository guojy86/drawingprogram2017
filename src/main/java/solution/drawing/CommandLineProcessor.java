package solution.drawing;

import java.util.Scanner;

public class CommandLineProcessor {
	
	
	public static void run(String [] args) {
		String prompt = "enter command: ";
		Scanner sc = new Scanner(System.in);
		String[] options = null;
		
		Canvas canvas = null;
		
		do {
			System.out.print(prompt);
			String input = "";
			if (sc.hasNextLine()) {
				input = sc.nextLine().trim();
			}
			options = input.split("\\s+");
			
			String command = options[0].toUpperCase();
			
			if ("C".equals(command)) {
				if (options.length >= 3 && isNumeric(options, 1, 2)) {
					int w = Integer.valueOf(options[1]);
					int h = Integer.valueOf(options[2]);
					if (w < 5 || h < 3) {
						System.out.println("Minimum width and height of the canvas is 5 and 3 respectively.");
					} else {
						canvas = new Canvas(w, h);
						canvas.drawCanvas();
					}
				} else {
					System.out.println(String.format("Unrecognised command: %s.", input));
				}
			} else if ("L".equals(command)) {
				if (options.length >= 5 && isNumeric(options, 1, 4)) {
					if (canvas != null) {
						canvas.drawLine(Integer.valueOf(options[1]), Integer.valueOf(options[2]),
								Integer.valueOf(options[3]), Integer.valueOf(options[4]));
					} else {
						System.out.println("Please draw a Canvas first.");
					}
				} else {
					System.out.println(String.format("Unrecognised command: %s.", input));
				}
			} else if ("R".equals(command)) {
				if (options.length >= 5 && isNumeric(options, 1, 4)) {
					if (canvas != null) {
						canvas.drawRectangle(Integer.valueOf(options[1]), Integer.valueOf(options[2]),
								Integer.valueOf(options[3]), Integer.valueOf(options[4]));
					} else {
						System.out.println("Please draw a Canvas first.");
					}
				} else {
					System.out.println(String.format("Unrecognised command: %s.", input));
				}
			} else if ("B".equals(command)) {
				if (options.length >= 4 && isNumeric(options, 1, 2)) {
					if (options[3].charAt(0) == Canvas.LINE_CHAR) {
						System.out.println("'x' is not a valid fill colour.");
					} else {
						if (canvas != null) {
							canvas.bucketFill(Integer.valueOf(options[1]), Integer.valueOf(options[2]),
									options[3].charAt(0));							
						} else {
							System.out.println("Please draw a Canvas first.");
						}
					}
					
				} else {
					System.out.println(String.format("Unrecognised command: %s.", input));
				}
			} else if ("Q".equals(command)) {
				break;
			} else {
				System.out.println(String.format("Unrecognised command: %s.", input));
			}
			
			System.out.println();
			
		} while (true);
		
		sc.close();
	}
	
	private static boolean isNumeric(String [] args, int start, int end) {
		boolean isValid = true;
		
		for(int i = start; i <= end; i++) {
			isValid = isValid && isInteger(args[i]);
		}
		
		return isValid;
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

}

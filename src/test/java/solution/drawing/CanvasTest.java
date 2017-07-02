package solution.drawing;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class CanvasTest {
	
	@Test
	public void drawHorizontalLineWithY1EqualsY2() {
		String expected = new StringBuffer()
				.append("       ").append("\\n")
				.append("       ").append("\\n")
				.append(" xxxxx ").append("\\n")
				.append("       ").append("\\n")
			.toString();
		
		Canvas canvas = new Canvas(7, 4);
		canvas.drawLine(2, 3, 6, 3);
		
		assertEquals(expected, canvas.toString());
		
		// Testing boundary cases here..
		expected = new StringBuffer()
				.append("       ").append("\\n")
				.append("       ").append("\\n")
				.append("       ").append("\\n")
				.append("xxxxxxx").append("\\n")
			.toString();
		
		canvas = new Canvas(7, 4);
		canvas.drawLine(-1, 5, 10, 5);
		
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void drawVerticalLineWithX1EqualsX2() {
		String expected = new StringBuffer()
				.append("       ").append("\\n")
				.append(" x     ").append("\\n")
				.append(" x     ").append("\\n")
				.append(" x     ").append("\\n")
			.toString();
		
		Canvas canvas = new Canvas(7, 4);
		canvas.drawLine(2, 2, 2, 4);
		
		assertEquals(expected, canvas.toString());
		
		// Testing boundary cases here..
		expected = new StringBuffer()
				.append("      x").append("\\n")
				.append("      x").append("\\n")
				.append("      x").append("\\n")
				.append("      x").append("\\n")
			.toString();
		
		canvas = new Canvas(7, 4);
		canvas.drawLine(8, -2, 8, 4);
		
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void drawRectangleWithWidthNHeightGreaterThan3() {
		String expected = new StringBuffer()
				.append("  xxxxxxx ").append("\\n")
				.append("  x     x ").append("\\n")
				.append("  xxxxxxx ").append("\\n")
				.append("          ").append("\\n")
			.toString();
		
		Canvas canvas = new Canvas(10, 4);
		canvas.drawRectangle(3, 1, 9, 3);
		
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void drawRectangleWithWidthNHeightLessThan3() {
		String expected = new StringBuffer()
				.append("          ").append("\\n")
				.append("          ").append("\\n")
				.append("          ").append("\\n")
				.append("          ").append("\\n")
			.toString();
		
		Canvas canvas = new Canvas(10, 4);
		canvas.drawRectangle(-3, 1, 0, 2);
		
		assertEquals(expected, canvas.toString());
		
	}
	
	@Test
	public void bucketFillWithColorX() {
		//'x' is reserved color and any bucket fill with 'x'
		// will be ignored..
		String expected = new StringBuffer()
				.append("          ").append("\\n")
				.append("          ").append("\\n")
				.append("          ").append("\\n")
				.append("          ").append("\\n")
			.toString();
		
		Canvas canvas = new Canvas(10, 4);
		canvas.bucketFill(5, 2, 'x');
		
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void bucketFillWithColorO() {
		String expected = new StringBuffer()
				.append("oooooooooo").append("\\n")
				.append("oooooooooo").append("\\n")
				.append("oooooooooo").append("\\n")
				.append("oooooooooo").append("\\n")
			.toString();
		
		Canvas canvas = new Canvas(10, 4);
		canvas.bucketFill(5, 1000, 'o');
		
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void bucketFillWithObjectsInCanvas() {
		String expected = new StringBuffer()
				.append("     xxxx ").append("\\n")
				.append("    xxoox ").append("\\n")
				.append("  xxxooox ").append("\\n")
				.append("  xooxxxx ").append("\\n")
				.append("  xxxx    ").append("\\n")
			.toString();
		
		Canvas canvas = new Canvas(10, 5);
		canvas.drawLine(6, 1, 9, 1);
		canvas.drawLine(9, 2, 9, 3);
		canvas.drawLine(3, 3, 3, 4);
		canvas.drawLine(5, 2, 6, 2);
		canvas.drawLine(4, 3, 5, 3);
		canvas.drawLine(6, 4, 9, 4);
		canvas.drawLine(3, 5, 6, 5);
		canvas.bucketFill(4, 4, 'o');
		
		assertEquals(expected, canvas.toString());
		
		expected = new StringBuffer()
				.append("aaxxxxxxxa").append("\\n")
				.append("aax     xa").append("\\n")
				.append("aaxxxxxxxa").append("\\n")
				.append("aaaaaaaaaa").append("\\n")
			.toString();
		
		canvas = new Canvas(10, 4);
		canvas.drawRectangle(3, 1, 9, 3);
		canvas.bucketFill(4, 4, 'a');

		assertEquals(expected, canvas.toString());
	}
}

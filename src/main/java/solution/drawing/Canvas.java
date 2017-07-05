package solution.drawing;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A class that represents a Canvas object
 * @author Jiayuan
 *
 */
public class Canvas {
	
	public static final char LINE_CHAR = 'x';
	public static final char EMPTY_CHAR = ' ';
	
	private int width;
	private int height;
	
	private char cells[][];
	
	public Canvas (int w, int h) {
		this.width = w;
		this.height = h;
		initCells();
	}
	
	
	public void initCells() {
		this.cells = new char [height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = EMPTY_CHAR;
			}
		}
	}
	
	
	public void setCells(char cells[][]) {
		this.cells = cells;
	}
	
	public char[][] getCells() {
		return cells;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	/**
	 * Draw a line based on the given 2 coordinates. Horizontal lines are 
	 * always drawn from left to right, and vertical lines are drawn from
	 * top to bottom. Diagonal lines are not supported.
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		x1 = checkXCoord(x1);
		x2 = checkXCoord(x2);
		y1 = checkYCoord(y1);
		y2 = checkYCoord(y2);
		
		// Neither horizontal nor vertical line.. skip
		if (x1 != x2 && y1 != y2) {
			return;
		}
		
		if (x1 == x2) {
			if (y1 > y2) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			// draw vertical line
			for (int i = y1 - 1; i < y2; i++) {
				cells[i][x1-1] = LINE_CHAR;
			}
		} else {
			if (x1 > x2) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			// draw horizontal line
			for (int i = x1 - 1; i < x2; i++) {
				cells[y1-1][i] = LINE_CHAR;
			}
		}
		
		drawCanvas();
	}
	
	private int checkXCoord(int x) {
		// index here starts at 1..
		return x <= 0 ? 1 : (x > width ? width : x);
	}
	
	private int checkYCoord(int y) {
		// index here starts at 1..
		return y <= 0 ? 1 : (y > height ? height : y);
	}
	
	/**
	 * Draw a rectangle based on the given 2 coordinates. A rectangle is drawn
	 * from left to right, top to bottom. The minimum width/height of a rectangle
	 * is assumed to be 3.
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawRectangle(int x1, int y1, int x2, int y2) {
		x1 = checkXCoord(x1);
		x2 = checkXCoord(x2);
		y1 = checkYCoord(y1);
		y2 = checkYCoord(y2);
		
		if (x1 > x2) {
			int tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		
		if (y1 > y2) {
			int tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		
		if (x2 - x1 < 2 || y2 - y1 < 2) {
			return;
		}
		
		for (int i = x1 - 1; i < x2; i++) {
			cells[y1 -1][i] = LINE_CHAR;
		}
		
		for (int i = y1, j = y2 - 1; i < j; i++) {
			cells[i][x1-1] = LINE_CHAR;
			cells[i][x2-1] = LINE_CHAR;
		}
		
		for (int i = x1 - 1; i < x2; i++) {
			cells[y2 -1][i] = LINE_CHAR;
		}
		
		drawCanvas();
	}
	
	/**
	 * Applies the flood-fill algorithm (8-ways) to fill the entire   
	 * area connected to (x,y) with color c. The order complexity of 
	 * the algorithm is O(n.m). The algorithm is described here: 
	 * https://en.wikipedia.org/wiki/Flood_fill 
	 * @param x
	 * @param y
	 * @param c
	 */
	public void bucketFill(int x, int y, char c) {
		x = checkXCoord(x) - 1;
		y = checkYCoord(y) - 1;
		
		// returns if the target cell is already filled with color c
		// or if the cell is already filled with 'x'.
		if (c == LINE_CHAR || cells[y][x] == c || cells[y][x] == LINE_CHAR) {
			return;
		}
		
		Queue<Cell> queue = new LinkedList<Cell>();
		queue.add(new Cell(x, y));
		cells[y][x] = c;
		
		while (queue.size() > 0) {
			Cell cell = queue.poll();
			int x1 = cell.getX();
			int y1 = cell.getY();
			// 1. check west of the cell
			fill(queue, x1 - 1, y1, c);
			// 2. check east of the cell
			fill(queue, x1 + 1, y1, c);
			// 3. check north of the cell
			fill(queue, x1, y1 - 1, c);
			// 4. check south of the cell
			fill(queue, x1, y1 + 1, c);
			// 5. check north-west of the cell
			fill(queue, x1 - 1, y1 - 1, c);
			// 6. check north-east of the cell
			fill(queue, x1 + 1, y1 - 1, c);
			// 7. check south-west of the cell
			fill(queue, x1 - 1, y1 + 1, c);
			// 8. check south-east of the cell
			fill(queue, x1 + 1, y1 + 1, c);
		}
		
		drawCanvas();
	}
	
	private void fill(Queue<Cell> queue, int x, int y, char c) {
		if (x < 0 || 
			x > width - 1 || 
			y < 0 || 
			y > height - 1 || 
			cells[y][x] == c || 
			cells[y][x] == LINE_CHAR) {
			return;
		}
		
		cells[y][x] = c;
		queue.add(new Cell(x, y));
	}
	
	/**
	 * This method simply prints the canvas and its contents based on
	 * the layout given in the problem statement
	 */
	public void drawCanvas() {
		drawCanvasTop();
		
		for (int i = 0; i < height; i++) {
			System.out.print("|");
			for (int j = 0; j < width; j++) {
				System.out.print(cells[i][j]);
			}
			System.out.println("|");
		}
		
		drawCanvasTop();
	}
	
	private void drawCanvasTop() {
		for (int i = 0, drawWidth = width + 2; i < drawWidth; i++) {
			System.out.print('-');
		}
		System.out.println();
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				buf.append(cells[i][j]);
			}
			buf.append("\\n");
		}
		return buf.toString();
	}
	
	/**
	 * Inner class to represent a cell/pixel object in the canvas
	 * @author Jiayuan
	 *
	 */
	class Cell {
		private int x;
		private int y;
		
		public Cell(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private Canvas getOuterType() {
			return Canvas.this;
		}
		
		
	}
}

package finalProject_Maze;

/**
 * 
 * @author Danny
 *
 */
class Point {
	int x; // stores x value
	int y; // stores y value
	int distance; // distance from start
	Point predecessor;
	static final int INFINITE = Integer.MAX_VALUE;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.distance = INFINITE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point getPredecessor() {
		return predecessor;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ") | Predecessor: " + predecessor;
	}
}

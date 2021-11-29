package finalProject_Maze;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 * Compilation: javac MazeModified.java Execution: java MazeModified.java n
 * Dependencies: StdDraw.java
 *
 * Generates a perfect n-by-n maze using depth-first search with a stack.
 *
 * % java MazeModified 62
 *
 * % java MazeModified 61
 *
 * Note: this program generalizes nicely to finding a random tree in a graph.
 *
 ******************************************************************************/

public class MazeModified {
	private int n; // dimension of maze
	private boolean[][] north; // is there a wall to north of cell i, j
	private boolean[][] east;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] visited;
	private boolean done = false;
	static final int INFINITE = Integer.MAX_VALUE;

	/**
	 * Private class to create a point
	 * 
	 * @author admin
	 *
	 */
	private class Point {
		int x; // stores x value
		int y; // stores y value
		int distance; // distance from start
		Point predecessor;

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

	public MazeModified(int n) {
		this.n = n;
		StdDraw.setXscale(0, n + 2);
		StdDraw.setYscale(0, n + 2);
		init();
		generate();
	}

	private void init() {
		// initialize border cells as already visited
		visited = new boolean[n + 2][n + 2];
		for (int x = 0; x < n + 2; x++) {
			visited[x][0] = true;
			visited[x][n + 1] = true;
		}
		for (int y = 0; y < n + 2; y++) {
			visited[0][y] = true;
			visited[n + 1][y] = true;
		}

		// initialze all walls as present
		north = new boolean[n + 2][n + 2];
		east = new boolean[n + 2][n + 2];
		south = new boolean[n + 2][n + 2];
		west = new boolean[n + 2][n + 2];
		for (int x = 0; x < n + 2; x++) {
			for (int y = 0; y < n + 2; y++) {
				north[x][y] = true;
				east[x][y] = true;
				south[x][y] = true;
				west[x][y] = true;
			}
		}
	}

	// generate the maze
	private void generate(int x, int y) {
		visited[x][y] = true;

		// while there is an unvisited neighbor
		while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x - 1][y]) {

			// pick random neighbor (could use Knuth's trick instead)
			while (true) {
				double r = StdRandom.uniform(4);
				if (r == 0 && !visited[x][y + 1]) {
					north[x][y] = false;
					south[x][y + 1] = false;
					generate(x, y + 1);
					break;
				} else if (r == 1 && !visited[x + 1][y]) {
					east[x][y] = false;
					west[x + 1][y] = false;
					generate(x + 1, y);
					break;
				} else if (r == 2 && !visited[x][y - 1]) {
					south[x][y] = false;
					north[x][y - 1] = false;
					generate(x, y - 1);
					break;
				} else if (r == 3 && !visited[x - 1][y]) {
					west[x][y] = false;
					east[x - 1][y] = false;
					generate(x - 1, y);
					break;
				}
			}
		}
	}

	// generate the maze starting from lower left
	private void generate() {
		generate(1, 1);

		// delete some random walls
		for (int i = 0; i < n; i++) {
			int x = 1 + StdRandom.uniform(n - 1);
			int y = 1 + StdRandom.uniform(n - 1);
			north[x][y] = south[x][y + 1] = false;
		}

		// add some random walls
		for (int i = 0; i < 10; i++) {
			int x = n / 2 + StdRandom.uniform(n / 2);
			int y = n / 2 + StdRandom.uniform(n / 2);
			east[x][y] = west[x + 1][y] = true;
		}

	}

	// solve the maze using depth-first search
	private void solveDFS(int x, int y) {
		if (x == 0 || y == 0 || x == n + 1 || y == n + 1)
			return;
		if (done || visited[x][y])
			return;
		visited[x][y] = true;

		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
		StdDraw.show();
		StdDraw.pause(30);

		// reached middle
		if (x == n / 2 && y == n / 2)
			done = true;

		if (!north[x][y])
			solveDFS(x, y + 1);
		if (!east[x][y])
			solveDFS(x + 1, y);
		if (!south[x][y])
			solveDFS(x, y - 1);
		if (!west[x][y])
			solveDFS(x - 1, y);

		if (done)
			return;

		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.circle(x + 0.5, y + 0.5, 0.25);
		StdDraw.show();
		StdDraw.pause(150);
	}

	private void solveBFS(int x, int y) {
		// Queue to store points for BFS
		Queue<Point> q = new Queue<>();
		Stack<Point> endPoint = new Stack<>();

		for(int i = 0; i < n+2; i++) {
			for(int j = 0; j < n+2; j++) {
				visited[i][j] = false;
			}
		}
		
		visited[x][y] = true;
		Point start = new Point(x, y);
		start.distance = 0;
		q.enqueue(start);

		// Loop through the maze and visit each node
		while (!q.isEmpty()) {
			Point point = q.dequeue();

			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledCircle(point.getX() + 0.5, point.getY() + 0.5, 0.25);
			StdDraw.show();
			StdDraw.pause(5);

			// calculate the new distances
			int newDistance = point.distance + 1;

			// NORTH
			if (!north[point.getX()][point.getY()] && !visited[point.getX()][point.getY() + 1]) {
				Point c = new Point(point.getX(), point.getY() + 1);
				visited[point.getX()][point.getY() + 1] = true;
				c.distance = newDistance;
				c.predecessor = point;
				if (c.getX() == n / 2 && c.getY() == n / 2)
					endPoint.push(c);
				q.enqueue(c);
				//allPoints.push(c);
			}
			// EAST
			if (!east[point.getX()][point.getY()] && !visited[point.getX() + 1][point.getY()]) {
				Point c = new Point(point.getX() + 1, point.getY());
				visited[point.getX() + 1][point.getY()] = true;
				c.distance = newDistance;
				c.predecessor = point;
				if (c.getX() == n / 2 && c.getY() == n / 2)
					endPoint.push(c);
				q.enqueue(c);
				//allPoints.push(c);
			}
			// SOUTH
			if (!south[point.getX()][point.getY()] && !visited[point.getX()][point.getY() - 1]) {
				Point c = new Point(point.getX(), point.getY() - 1);
				visited[point.getX()][point.getY() - 1] = true;
				c.distance = newDistance;
				c.predecessor = point;
				if (c.getX() == n / 2 && c.getY() == n / 2)
					endPoint.push(c);
				q.enqueue(c);
				//allPoints.push(c);
			}
			// WEST
			if (!west[point.getX()][point.getY()] && !visited[point.getX() - 1][point.getY()]) {
				Point c = new Point(point.getX() - 1, point.getY());
				visited[point.getX() - 1][point.getY()] = true;
				c.distance = newDistance;
				c.predecessor = point;
				if (c.getX() == n / 2 && c.getY() == n / 2)
					endPoint.push(c);
				q.enqueue(c);
				//allPoints.push(c);
			}
		}
		
		// TODO: Reconstruct path and color the shortest path red
		Stack<Point> path = new Stack<>();
		Point shortestPath = new Point(-1, -1);
		shortestPath.distance = -1;
		for(Point p : endPoint) {
			if(p.distance > shortestPath.distance)
				shortestPath = p;
		}
		//for(Point p : endPoint) {
			path.push(shortestPath);
			while(shortestPath.getPredecessor() != null) {
				path.push(shortestPath);
				shortestPath = shortestPath.predecessor;
			}
		//}
		for(Point p : path) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledCircle(p.getX() + 0.5, p.getY() + 0.5, 0.25);
			StdDraw.show();
			StdDraw.pause(15);
		}
		System.out.println("done");
	}
	// solve the maze using DFS starting from the start state
	public void solveDFS() {
		for (int x = 1; x <= n; x++)
			for (int y = 1; y <= n; y++)
				visited[x][y] = false;
		done = false;
		solveDFS(1, 1);
	}

	// solve the maze using BFS starting from the start state
	public void solveBFS() {
		for (int x = 1; x <= n; x++)
			for (int y = 1; y <= n; y++)
				visited[x][y] = false;
		done = false;
		solveBFS(1, 1);
	}

	// draw the maze
	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(n / 2.0 + 0.5, n / 2.0 + 0.5, 0.375);
		StdDraw.filledCircle(1.5, 1.5, 0.375);

		StdDraw.setPenColor(StdDraw.BLACK);
		for (int x = 1; x <= n; x++) {
			for (int y = 1; y <= n; y++) {
				if (south[x][y])
					StdDraw.line(x, y, x + 1, y);
				if (north[x][y])
					StdDraw.line(x, y + 1, x + 1, y + 1);
				if (west[x][y])
					StdDraw.line(x, y, x, y + 1);
				if (east[x][y])
					StdDraw.line(x + 1, y, x + 1, y + 1);
			}
		}
		StdDraw.show();
		StdDraw.pause(1000);
	}
	public void start() {
		StdDraw.enableDoubleBuffering();
		draw();

	}

	// a test client
	public static void main(String[] args) {
		int n = 25;
		MazeModified maze = new MazeModified(n);
		StdDraw.enableDoubleBuffering();
		maze.draw();
//		maze.solveDFS();
		maze.solveBFS();
	}

}
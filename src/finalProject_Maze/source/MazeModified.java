package finalProject_Maze.source;

import edu.princeton.cs.algs4.Queue;


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

public class MazeModified  {
	/**
	 * 
	 */

	private int n; // dimension of maze
	private boolean[][] north; // is there a wall to north of cell i, j
	private boolean[][] east;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] visited;
	private boolean done = false;
	static final int INFINITE = Integer.MAX_VALUE;
	static final int UNDEFINED = -1;

	/**
	 * Private class to create a point
	 * 
	 * @author admin
	 *
	 */
	private class Point {
		int x; // stores x value
		int y; // stores y value

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}

	public MazeModified(int n) {
		this.n = n;
		StdDraw.setXscale(0, n + 2);
		StdDraw.setYscale(0, n + 2);
		init();
		generate();
		start();
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

		// Arrays to store approximate distances
		int[][] distances = new int[n+2][n+2];
		int[][] predecessors = new int[n+2][n+2];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				distances[i][j] = INFINITE;
				predecessors[i][j] = UNDEFINED;
			}
		}

		// Store the first node
		visited[x][y] = true;
		q.enqueue(new Point(x, y));
		distances[x][y] = 0;
		predecessors[x][y] = -1;

		while (!q.isEmpty()) {
			Point b = q.dequeue();

			System.out.println(b.getX() + " " + b.getY());
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledCircle(b.getX() + 0.5, b.getY() + 0.5, 0.25);
			StdDraw.show();
			StdDraw.pause(30);

			// calculate the new distances
			int newDistance = distances[b.getX()][b.getY()] + 1;
			int newPredecessor = distances[b.getX()][b.getY()];

			if (!north[b.getX()][b.getY()] && !visited[b.getX()][b.getY() + 1]) {
				Point c = new Point(b.getX(), b.getY() + 1);
				visited[b.getX()][b.getY() + 1] = true;
				distances[b.getX()][b.getY() + 1] = newDistance;
				predecessors[b.getX()][b.getY() + 1] = newPredecessor;
				q.enqueue(c);
			}
			if (!east[b.getX()][b.getY()] && !visited[b.getX() + 1][b.getY()]) {
				Point c = new Point(b.getX() + 1, b.getY());
				visited[b.getX() + 1][b.getY()] = true;
				distances[b.getX() + 1][b.getY()] = newDistance;
				predecessors[b.getX() + 1][b.getY()] = newPredecessor;
				q.enqueue(c);
			}
			if (!south[b.getX()][b.getY()] && !visited[b.getX()][b.getY() - 1]) {
				Point c = new Point(b.getX(), b.getY() - 1);
				visited[b.getX()][b.getY() - 1] = true;
				distances[b.getX()][b.getY() - 1] = newDistance;
				predecessors[b.getX()][b.getY() - 1] = newPredecessor;
				q.enqueue(c);
			}
			if (!west[b.getX()][b.getY()] && !visited[b.getX() - 1][b.getY()]) {
				Point c = new Point(b.getX() - 1, b.getY());
				visited[b.getX() - 1][b.getY()] = true;
				distances[b.getX() - 1][b.getY()] = newDistance;
				predecessors[b.getX() - 1][b.getY()] = newPredecessor;
				q.enqueue(c);
			}

			// TODO: Color the shortest path red
			
		}
	}

	// solve the maze starting from the start state
	public void solveDFS() {
		for (int x = 1; x <= n; x++)
			for (int y = 1; y <= n; y++)
				visited[x][y] = false;
		done = false;
		solveDFS(1, 1);
	}

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
		solveDFS();
		solveBFS();
	}
	// a test client
	public static void main(String[] args) {
//      int n = Integer.parseInt(args[0]);
		int n = 6;
		MazeModified maze = new MazeModified(n);
		StdDraw.enableDoubleBuffering();
		maze.draw();
		maze.solveDFS();
		maze.solveBFS();
	}

}
package finalProject_Maze;


import edu.princeton.cs.algs4.StdRandom;

/**
 * Generates and solves a perfect n-by-n maze using various search algorithms.
 * 
 * @author Danny Dwyer
 * @author Qi Cao
 *
 */
public class MazeModified {
	protected int n; // dimension of maze
	protected boolean[][] north; // is there a wall to north of cell i, j
	protected boolean[][] east;
	protected boolean[][] south;
	protected boolean[][] west;
	protected boolean[][] visited;
	protected boolean done = false;
	private int BFSnumberOfSteps;
	private int DFSnumberOfSteps;
	static final int INFINITE = Integer.MAX_VALUE;

	/**
	 * Constructor to generate a <code>N</code>x<code>N</code> maze.
	 * 
	 * @param n
	 */
	public MazeModified(int n) {
		this.n = n;
		this.DFSnumberOfSteps = -1;
		this.BFSnumberOfSteps = -1;
		StdDraw.setXscale(0, n + 2);
		StdDraw.setYscale(0, n + 2);
		init();
		generate();
	}

	/**
	 * @return the BFSnumberOfSteps
	 */
	public int getBFSnumberOfSteps() {
		return BFSnumberOfSteps;
	}

	/**
	 * @return the DFSnumberOfSteps
	 */
	public int getDFSnumberOfSteps() {
		return DFSnumberOfSteps;
	}

	/**
	 * @return If this maze is complete or not.
	 */
	public boolean isDone() {
		return this.done;
	}

	/**
	 * Initializes the maze.
	 */
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

	/**
	 * Generates the maze
	 * 
	 * @param x X value of point being initialized
	 * @param y Y value of point being initialized
	 */
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

	/**
	 * generate the maze starting from lower left
	 */
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

	/**
	 * Resets this board to a new random n-n maze
	 */
	public void resetBoard() {
		this.DFSnumberOfSteps = -1;
		this.BFSnumberOfSteps = -1;
		this.done = false;
		StdDraw.setXscale(0, n + 2);
		StdDraw.setYscale(0, n + 2);
		init();
		generate();
		StdDraw.clear();
	}

	/**
	 * Draws this maze.
	 */
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

	/**
	 * Redraws the board and sets all visited to false.
	 */
	public void reset() {
		StdDraw.clear();
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				visited[i][j] = false;
			}
		}
		this.done = false;
		draw();
		StdDraw.show();
	}

	/**
	 * Solves this maze using BFS
	 */
	public void solveBFS() {
		this.BFSnumberOfSteps = BFS.solveBFS(this).size();
	}

	/**
	 * Solves this maze using DFS
	 */
	public void solveDFS() {
		this.DFSnumberOfSteps = DFS.solveDFS(this).size();
	}

	/**
	 * Method used to draw maze in a thread.
	 */
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
		maze.solveDFS();
		maze.resetBoard();
		maze.draw();
		maze.solveBFS();
	}

}
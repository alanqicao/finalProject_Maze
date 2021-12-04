package finalProject_Maze;

import edu.princeton.cs.algs4.Stack;


/**
 * Class that gives us utility functions for Depth First Search with our
 * modified maze.
 * 
 * @author Danny
 *
 */
class DFS {

	/**
	 * Method that solves the maze using DFS and returns a stack with the points on
	 * the solved route.
	 * 
	 * If the returned stack's size is 0, the maze is not solvable and a new maze
	 * should be initialized.
	 * 
	 * @return a Queue with the points on the solved route.
	 */
	protected static Stack<Point> solveDFS(MazeModified maze) {
		return solveDFS(1, 1, maze);
	}

	/*
	 * Solves the maze using depth-first search from a certain point
	 * 
	 * @param x is the x point to start at
	 * 
	 * @param y is the y point to start at.
	 */
	protected static Stack<Point> solveDFS(int x, int y, MazeModified maze) {
		Stack<Point> s = new Stack<>();
		for (int x1 = 1; x1 <= maze.n; x1++)
			for (int y1 = 1; y1 <= maze.n; y1++)
				maze.visited[x1][y1] = false;
		solveDFS(x, y, s, maze);
		return s;
	}

	/**
	 * Solves the maze using DFS.
	 * 
	 * @param x
	 * @param y
	 * @param s
	 */
	protected static void solveDFS(int x, int y, Stack<Point> s, MazeModified maze) {
		if (x == 0 || y == 0 || x == maze.n + 1 || y == maze.n + 1)
			return;
		if (maze.done || maze.visited[x][y])
			return;
		maze.visited[x][y] = true;

		s.push(new Point(x, y));
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
		StdDraw.show();
		StdDraw.pause(30);
		
		// reached middle
		if (x == maze.n / 2 && y == maze.n / 2)
			maze.done = true;

		if (!maze.north[x][y]) {
			solveDFS(x, y + 1, s, maze);
		}
		if (!maze.east[x][y]) {
			solveDFS(x + 1, y, s, maze);
		}
		if (!maze.south[x][y]) {
			solveDFS(x, y - 1, s, maze);
		}
		if (!maze.west[x][y]) {
			solveDFS(x - 1, y, s, maze);
		}

		if (maze.done)
			return;

		s.pop();
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.circle(x + 0.5, y + 0.5, 0.25);
		StdDraw.show();
		StdDraw.pause(30);
	}
}

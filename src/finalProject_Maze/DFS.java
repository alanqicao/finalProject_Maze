package finalProject_Maze;

import edu.princeton.cs.algs4.Stack;


/**
 * Class that gives us utility functions for Depth First Search with our
 * modified maze.
 * 
 * @author Danny
 *
 */
public class DFS {

	/**
	 * Method that draws the DFS path on the maze
	 */
	public static void drawDFS(MazeModified maze) {
		Stack<Point> s = solveDFS(maze);
		Stack<Point> reversed = new Stack<>();
		for (Point p : s) {
			reversed.push(p);
		}
		for (Point p : reversed) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledCircle(p.getX() + 0.5, p.getY() + 0.5, 0.25);
			StdDraw.show();
			StdDraw.pause(15);
		}
	}

	/**
	 * Method that solves the maze using DFS and returns a queue with the points on
	 * the solved route.
	 * 
	 * If the returned stack's size is 0, the maze is not solvable and a new maze
	 * should be initialized.
	 * 
	 * @return a Queue with the points on the solved route.
	 */
	public static Stack<Point> solveDFS(MazeModified maze) {
		return solveDFS(1, 1, maze);
	}

	/**
	 * Returns the number of points needed to solve the maze in DFS.
	 * 
	 * @return The number of points
	 */
	public static int numberOfSteps(MazeModified maze) {
		return solveDFS(maze).size();
	}

	/*
	 * Solves the maze using depth-first search.
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
		System.out.println(s.size());
		return s;
	}

	/**
	 * Solves the maze using DFS.
	 * 
	 * @param x
	 * @param y
	 * @param s
	 */
	private static void solveDFS(int x, int y, Stack<Point> s, MazeModified maze) {
		if (x == 0 || y == 0 || x == maze.n + 1 || y == maze.n + 1)
			return;
		if (maze.done || maze.visited[x][y])
			return;
		maze.visited[x][y] = true;

		s.push(new Point(x, y));
		
		// reached middle
		if (x == maze.n / 2 && y == maze.n / 2)
			maze.done = true;

		if (!maze.north[x][y]) {
			//s.push(new Point(x, y + 1));
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
	}
}

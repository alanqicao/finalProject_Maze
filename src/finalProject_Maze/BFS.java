package finalProject_Maze;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;


/**
 * Class designed to allow Breadth First Search traversals of the modified maze.
 * 
 * @author Danny
 *
 */
public class BFS {
	
	/**
	 * Method that draws out the path for a BFS traversal on the maze.
	 */
	public static void drawBFS(MazeModified maze) {
		Stack<Point> path = solveBFS(1, 1, maze);
		for (Point p : path) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledCircle(p.getX() + 0.5, p.getY() + 0.5, 0.25);
			StdDraw.show();
			StdDraw.pause(15);
		}
	}
	
	/**
	 * Method that solves the maze using BFS
	 * @return A stack of all the points in the BFS
	 */
	public static Stack<Point> solveBFS(MazeModified maze){
		return solveBFS(1, 1, maze);
	}
	
	/**
	 * Method that returns the number of steps required to
	 * solve the maze using BFS.
	 * 
	 * @return
	 */
	public static int numberOfSteps(MazeModified maze) {
		return solveBFS(maze).size();
	}

	/**
	 * Solves the maze using breadth first search.
	 * 
	 * @param x is the x point to start at
	 * @param y is the y point to start at.
	 * 
	 * @return Stack with the solution path
	 */
	protected static Stack<Point> solveBFS(int x, int y, MazeModified maze) {

		// Queue to store points for BFS
		Queue<Point> q = new Queue<>();
		Stack<Point> endPoint = new Stack<>();

		for (int i = 1; i <= maze.n; i++)
			for (int j = 1; j <= maze.n; j++)
				maze.visited[i][j] = false;
		maze.done = false;

		maze.visited[x][y] = true;
		Point start = new Point(x, y);
		start.distance = 0;
		q.enqueue(start);

		// Loop through the maze and visit each node
		while (!q.isEmpty()) {
			Point point = q.dequeue();

			// calculate the new distances
			int newDistance = point.distance + 1;

			// NORTH
			if (!maze.north[point.getX()][point.getY()] && !maze.visited[point.getX()][point.getY() + 1]) {
				Point c = new Point(point.getX(), point.getY() + 1);
				maze.visited[point.getX()][point.getY() + 1] = true;
				c.distance = newDistance;
				c.predecessor = point;
				if (c.getX() == maze.n / 2 && c.getY() == maze.n / 2)
					endPoint.push(c);
				q.enqueue(c);
			}
			// EAST
			if (!maze.east[point.getX()][point.getY()] && !maze.visited[point.getX() + 1][point.getY()]) {
				Point c = new Point(point.getX() + 1, point.getY());
				maze.visited[point.getX() + 1][point.getY()] = true;
				c.distance = newDistance;
				c.predecessor = point;
				if (c.getX() == maze.n / 2 && c.getY() == maze.n / 2)
					endPoint.push(c);
				q.enqueue(c);
			}
			// SOUTH
			if (!maze.south[point.getX()][point.getY()] && !maze.visited[point.getX()][point.getY() - 1]) {
				Point c = new Point(point.getX(), point.getY() - 1);
				maze.visited[point.getX()][point.getY() - 1] = true;
				c.distance = newDistance;
				c.predecessor = point;
				if (c.getX() == maze.n / 2 && c.getY() == maze.n / 2)
					endPoint.push(c);
				q.enqueue(c);
			}
			// WEST
			if (!maze.west[point.getX()][point.getY()] && !maze.visited[point.getX() - 1][point.getY()]) {
				Point c = new Point(point.getX() - 1, point.getY());
				maze.visited[point.getX() - 1][point.getY()] = true;
				c.distance = newDistance;
				c.predecessor = point;
				if (c.getX() == maze.n / 2 && c.getY() == maze.n / 2)
					endPoint.push(c);
				q.enqueue(c);
			}
		}

		Stack<Point> path = new Stack<>();
		Point shortestPath = new Point(-1, -1);
		shortestPath.distance = -1;
		for (Point p : endPoint) {
			if (p.distance > shortestPath.distance)
				shortestPath = p;
		}
		path.push(shortestPath);
		while (shortestPath.getPredecessor() != null) {
			path.push(shortestPath);
			shortestPath = shortestPath.predecessor;
		}
		for (Point p : path) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledCircle(p.getX() + 0.5, p.getY() + 0.5, 0.25);
			StdDraw.show();
			StdDraw.pause(15);
		}
		return path;
	}
}

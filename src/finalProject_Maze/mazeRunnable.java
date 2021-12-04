package finalProject_Maze;

public class mazeRunnable implements Runnable {
	private int size;
	private boolean radionButton;
	private boolean done;
	private MazeModified newMaze;
	@Override
	public void run() {
		newMaze=new MazeModified(size);
		newMaze.start();
		if(radionButton) {
			BFS.drawBFS(newMaze);
			
		}else {
			DFS.drawDFS(newMaze);
			
		}
		checkIsDone(newMaze);
	}

	private void checkIsDone(MazeModified newMaze) {
		if(newMaze.isDone()) {	
			((Timers) MazeUI.getTimeText()).stop();
		}
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void reSet() {
		newMaze.reset();
	}
	
//	public void closeFram() {
//		newMaze.closeFrame();
//	}

	public mazeRunnable(int size, boolean radionButton) {
		super();
		this.size = size;
		this.radionButton = radionButton;
	}

}

package finalProject_Maze;

public class mazeRunnable implements Runnable {
	private int size;
	private boolean radionButton;
	private boolean done;
	@Override
	public void run() {
		MazeModified newMaze=new MazeModified(size);
		newMaze.start();
		if(radionButton) {
			newMaze.solveBFS();
			
		}else {
			newMaze.solveDFS();
			
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
	
	

	public mazeRunnable(int size, boolean radionButton) {
		super();
		this.size = size;
		this.radionButton = radionButton;
	}

}

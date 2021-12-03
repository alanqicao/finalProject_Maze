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
	
	public void closeFram() {
		newMaze.closeFrame();
	}

	public mazeRunnable(int size, boolean radionButton) {
		super();
		this.size = size;
		this.radionButton = radionButton;
	}

}

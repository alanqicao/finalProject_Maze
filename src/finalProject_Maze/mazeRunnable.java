package finalProject_Maze;

public class mazeRunnable implements Runnable {
	private int size;
	private boolean radionButton;
	private boolean done;
	private MazeModified newMaze;
	private int setpNumber;
	private boolean reSet;
	@Override
	public void run() {

		
		if(!reSet) {
			newMaze=new MazeModified(size);
			newMaze.start();
			if(radionButton) {
			newMaze.solveBFS();	
		}	else {
			newMaze.solveDFS();	
		}
			checkIsDone(newMaze);
			updatesetNumber(newMaze);

		}else {
			newMaze.reset();
			if(radionButton) {
				newMaze.solveBFS();
		
			}else {
				newMaze.solveDFS();	
			}
			checkIsDone(newMaze);
			updatesetNumber(newMaze);
		}
		
		
		
	}

	private void checkIsDone(MazeModified newMaze) {
		if(newMaze.isDone()) {	
			((Timers) MazeUI.getTimeText()).stop();
		}
	}
	
	private void updatesetNumber(MazeModified newMaze) {
		if(newMaze.isDone()) {
			if(radionButton) {
				((StepNumber) MazeUI.getstepNumber()).setBFSStepNumber(newMaze);
			}else {
				((StepNumber) MazeUI.getstepNumber()).setDFSStepNumber(newMaze);
			}
		}
	}
	
	public boolean isDone() {
		return done;
	}
	

	public void setReSet(boolean reSet) {
		this.reSet = reSet;
	}

	public void setRadionButton(boolean radionButton) {
		this.radionButton = radionButton;
	}

	public int getSetpNumber() {
		return setpNumber;
	}

	public mazeRunnable(int size, boolean radionButton) {
		super();
		this.size = size;
		this.radionButton = radionButton;
	}

}

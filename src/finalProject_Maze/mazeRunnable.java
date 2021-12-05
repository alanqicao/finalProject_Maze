package finalProject_Maze;

public class mazeRunnable implements Runnable {
	private int size;
	private boolean radionButton;
	private boolean done;
	private MazeModified newMaze;
	private int setpNumber;
	private boolean reSet;
	private int menu;
	private StepNumber stepRun;
	private Thread stepThread;
	@Override
	public void run() {

		switch(menu) {
		case 1:
			newMaze=new MazeModified(size);
			stepRun = new StepNumber(newMaze,radionButton);
			stepThread = new Thread(stepRun);
			stepThread.start();
			newMaze.start();
			if(radionButton) {
			newMaze.solveBFS();	
		}	else {
			newMaze.solveDFS();	
		}
			checkIsDone(newMaze);
			//updatesetNumber(newMaze);
			
			
			// First time run
			break;
		case 2:
			newMaze.reset();
			((StepNumber) MazeUI.getstepNumber()).setText("");;
			if(radionButton) {
				newMaze.solveBFS();
		
			}else {
				newMaze.solveDFS();	
			}
			checkIsDone(newMaze);
			updatesetNumber(newMaze);
			// reStart
			break;
		case 3:
			newMaze.resetBoard();
			newMaze.draw();
			((StepNumber) MazeUI.getstepNumber()).setText("");;
			if(radionButton) {
				newMaze.solveBFS();	
			}else {
				newMaze.solveDFS();	
			}
			checkIsDone(newMaze);
			updatesetNumber(newMaze);
			//reSet
			break;
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
	

	public MazeModified getNewMaze() {
		return newMaze;
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
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getMenu() {
		return menu;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}

	public mazeRunnable(int size, boolean radionButton) {
		super();
		this.size = size;
		this.radionButton = radionButton;
	}

}

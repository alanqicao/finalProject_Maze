package finalProject_Maze;
/**
 * A Runnable class 
 * @author Qi
 *
 */
public class mazeRunnable implements Runnable {
	private int size;
	private boolean radionButton;
	private boolean done;
	private MazeModified newMaze;
	private int setpNumber;

	private int menu;
	
	@Override
	public void run() {

		switch(menu) {
		case 1:
			newMaze=new MazeModified(size);
			newMaze.start();
			if(radionButton) {
			newMaze.solveBFS();	
		}	else {
			newMaze.solveDFS();	
		}
			checkIsDone(newMaze);
			updatesetNumber(newMaze);
			// First time run
			break;
		case 2:
			newMaze.n = size;
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
			newMaze.n = size;
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

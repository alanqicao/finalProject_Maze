/**
 * 
 */
package finalProject_Maze;

import javax.swing.JTextField;

/**
 * @author Qi Cao
 *
 */
public class StepNumber extends JTextField implements Runnable {
	private MazeModified newMaze;
	private boolean radionButton;
	
	public StepNumber(MazeModified newMaze,boolean radionButton) {
		setEditable(false);
		setText("");
	
		this.radionButton = radionButton;
		this.newMaze = newMaze;
	}
	
	public void setDFSStepNumber(MazeModified newMaze) {
		setText(Integer.toString(newMaze.getRealTimeDFS()));
		//setText(Integer.toString(newMaze.getDFSnumberOfSteps()));
	}
	
	public void setBFSStepNumber(MazeModified newMaze) {
		setText(Integer.toString(newMaze.getRealTimeBFS()));
		//setText(Integer.toString(newMaze.getBFSnumberOfSteps()));
	}

	@Override
	public void run() {
		
		while(!newMaze.done) {
			if(radionButton) {
				setBFSStepNumber(newMaze);
				
			}else {
				setDFSStepNumber(newMaze);
				
			}
		}
		
	}

//	public void setNewMaze(MazeModified newMaze) {
//		this.newMaze = newMaze;
//	}




	
	

}

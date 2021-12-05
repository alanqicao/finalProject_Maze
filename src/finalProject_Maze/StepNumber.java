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
		setText("BEgain");
	
		this.radionButton = radionButton;
		this.newMaze = newMaze;
	}
	
	public void setDFSStepNumber(MazeModified newMaze) {
		setText(Integer.toString(newMaze.getRealTimeStesDFS()));
		//setText(Integer.toString(newMaze.getDFSnumberOfSteps()));
	}
	
	public void setBFSStepNumber(MazeModified newMaze) {
		setText(Integer.toString(newMaze.getRealTimeStepsBFS()));
		//setText(Integer.toString(newMaze.getBFSnumberOfSteps()));
	}

	@Override
	public void run() {
		this.setText("BFS");
		while(!newMaze.done) {
			if(radionButton) {
				//setBFSStepNumber(newMaze);
				//System.out.println(newMaze.getRealTimeStepsBFS());
				//setText(Integer.toString(newMaze.getRealTimeStepsBFS()));
				this.setText("BFS");
				
			}else {
				//setDFSStepNumber(newMaze);
				//setText(Integer.toString(newMaze.getRealTimeStesDFS()));
				//System.out.println(newMaze.getRealTimeStesDFS());
				this.setText("DFS");
			}
		}
		
	}

//	public void setNewMaze(MazeModified newMaze) {
//		this.newMaze = newMaze;
//	}




	
	

}

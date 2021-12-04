/**
 * 
 */
package finalProject_Maze;

import javax.swing.JTextField;

/**
 * @author Qi Cao
 *
 */
public class StepNumber extends JTextField  {

	/**
	 * 
	 */

	
	public StepNumber() {
		setEditable(false);
		setText("");
	}
	
	public void setDFSStepNumber(MazeModified newMaze) {
		
		setText(Integer.toString(newMaze.getDFSnumberOfSteps()));
	}
	
	public void setBFSStepNumber(MazeModified newMaze) {
		
		setText(Integer.toString(newMaze.getBFSnumberOfSteps()));
	}

}

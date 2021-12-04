package finalProject_Maze;

public class mazeThread extends Thread{
	private int size;
	private boolean radionButton;
	
	public mazeThread(int size, boolean radionButton) {
		this.size = size;
		this.radionButton = radionButton;
	}
	
		@Override
		public void run() {
			MazeModified newMaze=new MazeModified(size);
			newMaze.start();
			if(radionButton) {
				//newMaze.solveBFS();
			}else {
				//newMaze.solveDFS();
			}
			
		}
	
}

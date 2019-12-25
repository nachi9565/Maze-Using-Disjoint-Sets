import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

public class Maze extends JFrame {
	
	private static MazeElement[][] mazeElementList;
	
	public static MazeElement[][] getCells() {
		return mazeElementList;
	}

	public static void main(String args[]) {
		System.out.println("Enter the number of rows: ");	
		Scanner rowsIn = new Scanner(System.in);
		int rows = rowsIn.nextInt();
		System.out.println("Enter the number of columns: ");
		Scanner columnsIn = new Scanner(System.in);
		int columns = columnsIn.nextInt();
		if(rows<=0 || columns <=0)
			System.out.println("Please enter values greater than 0");
		else {
			int total = rows*columns;
			mazeElementList = new MazeElement[rows][columns];
			int count = 0;
			for(int i=0;i<rows;i++)
				for(int j=0;j<columns;j++)
					mazeElementList[i][j] = new MazeElement(count++ , true, true);
			DisjSets mazeDisjointSets = new DisjSets(total);
			Random rand = new Random();
			while(true) {
				int randomElementValue = rand.nextInt(total);
				int currentRow = randomElementValue / columns;
				int currentColumn = randomElementValue - currentRow*columns;
				MazeElement mazeElement = mazeElementList[currentRow][currentColumn];
				List<MazeElement> setOfConnectedElements = new ArrayList<MazeElement>();
				List<String> direction = new ArrayList<String>();
				if(currentRow-1>=0) {
					setOfConnectedElements.add(mazeElementList[currentRow-1][currentColumn]);
					direction.add("top");
				}
				if(currentRow+1<rows) {
					setOfConnectedElements.add(mazeElementList[currentRow+1][currentColumn]);
					direction.add("bottom");
				}
				if(currentColumn-1>=0) {
					setOfConnectedElements.add(mazeElementList[currentRow][currentColumn-1]);
					direction.add("left");
				}
				if(currentColumn+1<columns) {
					setOfConnectedElements.add(mazeElementList[currentRow][currentColumn+1]);
					direction.add("right");
				}
				int randomNeighborNumber = rand.nextInt(setOfConnectedElements.size());
				MazeElement nextElement = setOfConnectedElements.get(randomNeighborNumber);
				String nextElementDirection = direction.get(randomNeighborNumber);		
				if(!(mazeDisjointSets.find(mazeElement.getValue()) == mazeDisjointSets.find(nextElement.getValue()))) {
					mazeDisjointSets.union(mazeDisjointSets.find(mazeElement.getValue()), mazeDisjointSets.find(nextElement.getValue()));
					if(nextElementDirection.contains("top")) {
						nextElement.setBottomWall(false);
					}else if(nextElementDirection.contains("bottom")) {
						mazeElement.setBottomWall(false);
					}else if(nextElementDirection.contains("left")) {
						nextElement.setRightWall(false);
					}else if(nextElementDirection.contains("right")) {
						mazeElement.setRightWall(false);
					}
				}
				if(mazeDisjointSets.find(0) == mazeDisjointSets.find(total-1)) {
					break;
				}			
			}
			GUI gui = new GUI(rows, columns, mazeElementList);
			gui.setSize(new Dimension(rows*100,columns*100));
			gui.setVisible(true);
			System.out.println("The result is displayed in the GUI window");
		}
	}
}

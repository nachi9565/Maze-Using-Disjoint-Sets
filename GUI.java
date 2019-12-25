import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class GridCanvas extends JPanel {
  int width, height;

  int rows;

  int cols;
  
  MazeElement[][] cells;

  GridCanvas(int w, int h, int r, int c, MazeElement[][] cells) {
    setSize(width = w, height = h);
    rows = r;
    cols = c;
    this.cells = cells;
  }

  public void paint(Graphics g) {
    width = getSize().width-100;
    height = getSize().height-100;
    int rowHt = height / (rows);
    int rowWid = width / (cols);
    
    for(int i=1;i<cols;i++) {
    	g.drawLine(10+i*(rowWid), 10, 10+(i+1)*(rowWid), 10);
    }
    for(int i=1;i<rows;i++) {
    	g.drawLine(10, 10+i*(rowHt), 10, 10+(i+1)*(rowHt));
    }
    for (int i = 1; i < rows+1; i++) {
    	for(int j=0;j<cols;j++) {
    		if(cells[i-1][j].isFloor() && !(i==rows && j==cols-1))
    			g.drawLine(10+j*(rowWid),10+i*(rowHt),10+(j+1)*(rowWid),10+(rowHt)*i);
    	}
    }
    for (int i = 1; i < cols+1; i++) {
    	for(int j=0;j<rows;j++) {
    		if(cells[j][i-1].isRightWall() && !(j==rows-1 && i==cols))
    			g.drawLine(10+i*(rowWid),10+j*(rowHt),10+i*(rowWid),10+(rowHt)*(j+1));
    	}
    }
  }
}

public class GUI extends JFrame {
	
  public GUI(int row, int col, MazeElement[][] cells) {
    GridCanvas xyz = new GridCanvas(row*20, col*20, row, col, cells);
    add(xyz);
    pack();
  }
  
}
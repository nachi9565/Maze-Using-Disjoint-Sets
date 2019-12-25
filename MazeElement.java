public class MazeElement {
	private int value;
	private boolean rightWall;
	private boolean bottomWall;
	
	public MazeElement(int value, boolean rightWall, boolean bottomWall) {
		this.value = value;
		this.rightWall = rightWall;
		this.bottomWall = bottomWall;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isRightWall() {
		return rightWall;
	}
	public void setRightWall(boolean rightWall) {
		this.rightWall = rightWall;
	}
	public boolean isFloor() {
		return bottomWall;
	}
	public void setBottomWall(boolean bottomWall) {
		this.bottomWall = bottomWall;
	}
	
}

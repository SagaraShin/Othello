
public class Piece {
	private String color;
	
	public void setColor(String color) {
		this.color=color;
	}
	public String getColor() {
		return color;
	}
	
	public void changeColor() {
		if(this.color.equals("白")) {
			this.color="黒";
		}else{
			this.color="白";
		}
	}
	
}


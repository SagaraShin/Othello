import java.util.Scanner;

public class Player {
	private String name;
	private String color;//コマの色
	
	Scanner scan=new Scanner(System.in);
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
	public void setColor(String color) {
		this.color=color;
	}
	public String getColor() {
		return color;
	}
}

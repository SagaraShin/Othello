
public class Board {

	private Piece [][]board;

	/*上、下、右、左、右上、左上、右下、左下に
		ひっくり返すコマがあり、その場所にコマがおけるのかを判断する
	 */
	private boolean canPutUp;
	private boolean canPutDown;
	private boolean canPutRight;
	private boolean canPutLeft;
	private boolean canPutUpRight;
	private boolean canPutUpLeft;
	private boolean canPutDownRight;
	private boolean canPutDownLeft;

	public Board() {
		board=new Piece[8][8];
	}

	public void setPiece(int line,int row,Piece piece) {
		board[line][row]=piece;
	}

	public void openPiece() {
		System.out.print("　");
		for(int i=0; i<8; i++) {
			System.out.print(i+"　");
		}
		System.out.println();
		for(int column=0; column<8; column++) {
			System.out.print(column+" ");
			for(int row=0; row<8; row++) {
				System.out.print(board[column][row].getColor()+" ");
			}
			System.out.println();
		}
	}

	//盤面でコマを置ける場所があるのか判断する(true:おける false:おけない)
	public boolean checkPass(String color) {
		boolean judge=false;
		for(int line=0; line<=7; line++) {
			for(int row=0; row<=7; row++) {
				if(checkPutPiece(line,row,color)) {
					judge=true;
					break;
				}
			}
		}
		return judge;
	}

	/*指定された場所にコマを置けるのか判断する
	 * 全方向のどこかにひっくり返すコマがあれば置けると判断する
	 */
	public boolean checkPutPiece(int line,int row,String color){

		checkUp(line,row,color);
		checkDown(line,row,color);
		checkRight(line,row,color);
		checkLeft(line,row,color);
		checkUpRight(line,row,color);
		checkUpLeft(line,row,color);
		checkDownRight(line,row,color);
		checkDownLeft(line,row,color);

		if(canPutUp||canPutDown||canPutRight||canPutLeft||canPutUpRight||canPutUpLeft
				||canPutDownRight||canPutDownLeft) {
			return true;
		}
		return false;
	}

	/*
	 * 指定された場所に、すでにＮでないコマがあれば、その場所には置けない
	 * 指定された場所のひとつ上の行のコマが指定された色と同じ色、またはＮの場合も置けない
	 * line>=1、つまり上の行から二番目以内の場合もひっくり返すコマがないので置けない
	 *
	 * 以上に当てはまらない場合は、上のコマが違う色なので、
	 * for文を用いて２行上のコマから調べていき自分の色があれば、ひっくり返せる。
	 * この場合間にＮが入っているとひっくり返せないことも考慮する。
	 *
	 * これと同様の行為を上、下、右、左、右上、左上、右下、左下で行う
	 */
	public void checkUp(int line,int row,String color) {
		canPutUp=false;
		if(line<=1||board[line][row].getColor().equals("白")
				||board[line][row].getColor().equals("黒")
				||board[line-1][row].getColor().equals(color)
				||board[line-1][row].getColor().equals("Ｎ")) {
			canPutUp=false;
		}else {
			for(int startLine=line-2; startLine>=0; startLine--) {
				if(board[startLine][row].getColor().equals("Ｎ")) {
					canPutUp=false;
					break;
				}else if(board[startLine][row].getColor().equals(color)) {
					canPutUp=true;
					break;
				}
			}
		}

	}
	public void checkDown(int line,int row,String color) {
		canPutDown=false;
		if(line>=6||board[line][row].getColor().equals("白")
				||board[line][row].getColor().equals("黒")
				||board[line+1][row].getColor().equals(color)
				||board[line+1][row].getColor().equals("Ｎ")) {
			canPutDown=false;
		}else {
			for(int startLine=line+2; startLine<8; startLine++) {
				if(board[startLine][row].getColor().equals("Ｎ")) {
					canPutDown=false;
					break;
				}else if(board[startLine][row].getColor().equals(color)) {
					canPutDown=true;
					break;
				}
			}
		}

	}
	public void checkRight(int line,int row,String color) {
		canPutRight=false;
		if(row>=6||board[line][row].getColor().equals("白")
				||board[line][row].getColor().equals("黒")
				||board[line][row+1].getColor().equals(color)
				||board[line][row+1].getColor().equals("Ｎ")) {
			canPutRight=false;
		}else {
			for(int startRow=row+2; startRow<8; startRow++) {
				if(board[line][startRow].getColor().equals("Ｎ")) {
					canPutRight=false;
					break;
				}else if(board[line][startRow].getColor().equals(color)) {
					canPutRight=true;
					break;
				}
			}
		}
	}
	public void checkLeft(int line,int row,String color) {
		canPutLeft=false;
		if(row<=1||board[line][row].getColor().equals("白")
				||board[line][row].getColor().equals("黒")
				||board[line][row-1].getColor().equals(color)
				||board[line][row-1].getColor().equals("Ｎ")) {
			canPutLeft=false;
		}else {
			for(int startRow=row-2; startRow>=0; startRow--) {
				if(board[line][startRow].getColor().equals("Ｎ")) {
					canPutLeft=false;
					break;
				}else if(board[line][startRow].getColor().equals(color)) {
					canPutLeft=true;
					break;
				}
			}
		}
	}
	public void checkUpRight(int line,int row,String color) {
		canPutUpRight=false;
		if(line<=1||row>=6
				||board[line][row].getColor().equals("白")
				||board[line][row].getColor().equals("黒")
				||board[line-1][row+1].getColor().equals(color)
				||board[line-1][row+1].getColor().equals("Ｎ")) {
			canPutUpRight=false;
		}else {
			for(int i=2; i<8; i++) {
				if(board[line-i][row+i].getColor().equals("Ｎ")) {
					canPutUpRight=false;
					break;
				}else if(board[line-i][row+i].getColor().equals(color)) {
					canPutUpRight=true;
					break;
				}
				if((line-i)==0||(row+i)==7) {
					break;
				}
			}
		}
	}
	public void checkUpLeft(int line,int row,String color) {
		canPutUpLeft=false;
		if(line<=1||row<=1
				||board[line][row].getColor().equals("白")
				||board[line][row].getColor().equals("黒")
				||board[line-1][row-1].getColor().equals(color)
				||board[line-1][row-1].getColor().equals("Ｎ")) {
			canPutUpLeft=false;
		}else {
			for(int i=2; i<=8; i++) {
				if(board[line-i][row-i].getColor().equals("Ｎ")) {
					canPutUpLeft=false;
					break;
				}else if(board[line-i][row-i].getColor().equals(color)) {
					canPutUpLeft=true;
					break;
				}
				if((line-i)==0||(row-i)==0) {
					break;
				}
			}
		}
	}
	public void checkDownRight(int line,int row,String color) {
		canPutDownRight=false;
		if(line>=6||row>=6
				||board[line][row].getColor().equals("白")
				||board[line][row].getColor().equals("黒")
				||board[line+1][row+1].getColor().equals(color)
				||board[line+1][row+1].getColor().equals("Ｎ")) {
			canPutDownRight=false;
		}else {
			for(int i=2; i<8; i++) {
				if(board[line+i][row+i].getColor().equals("Ｎ")) {
					canPutDownRight=false;
					break;
				}else if(board[line+i][row+i].getColor().equals(color)) {
					canPutDownRight=true;
					break;
				}
				if((line+i)==7||(row+i)==7) {
					break;
				}
			}
		}
	}
	public void checkDownLeft(int line,int row,String color) {
		canPutDownLeft=false;
		if(line>=6||row<=1
				||board[line][row].getColor().equals("白")
				||board[line][row].getColor().equals("黒")
				||board[line+1][row-1].getColor().equals(color)
				||board[line+1][row-1].getColor().equals("Ｎ")) {
			canPutDownLeft=false;
		}else {
			for(int i=2; i<8; i++) {
				if(board[line+i][row-i].getColor().equals("Ｎ")) {
					canPutDownLeft=false;
					break;
				}else if(board[line+i][row-i].getColor().equals(color)) {
					canPutDownLeft=true;
					break;
				}
				if((line+i)==7||(row-i)==0) {
					break;
				}
			}
		}
	}
	/*checkPutPiece()の際に、ひっくり返す駒がある方向がわかったので
	 * それを用いてコマをひっくり返していく。
	 */
	public void turnPieces(int line,int row,String color) {

		if(canPutUp) {
			turnUpPiece(line,row,color);
		}

		if(canPutDown) {
			turnDownPiece(line,row,color);
		}

		if(canPutRight) {
			turnRightPiece(line,row,color);
		}

		if(canPutLeft) {
			turnLeftPiece(line,row,color);
		}

		if(canPutUpRight) {
			turnUpRightPiece(line,row,color);
		}

		if(canPutUpLeft) {
			turnUpLeftPiece(line,row,color);
		}

		if(canPutDownRight) {
			turnDownRightPiece(line,row,color);
		}

		if(canPutDownLeft) {
			turnDownLeftPiece(line,row,color);
		}

	}
	//指定されて場所のコマの色を変える
	public void changeColor(int line,int row,String color) {
		board[line][row].setColor(color);
	}
	/*
	 * 自分の色のコマまで、色を変えていくことで
	 * コマをヒックリ返す動作を行っている
	 */
	public void turnUpPiece(int line,int row,String color) {
		for(int startLine=line-1; startLine>=0; startLine--) {
			if(board[startLine][row].getColor().equals(color)) {
				break;
			}
			changeColor(startLine,row,color);
		}
	}
	public void turnDownPiece(int line,int row,String color) {
		for(int startLine=line+1; startLine<=7; startLine++) {
			if(board[startLine][row].getColor().equals(color)) {
				break;
			}
			changeColor(startLine,row,color);
		}

	}
	public void turnRightPiece(int line,int row,String color) {
		for(int startRow=row+1; startRow<=7; startRow++) {
			if(board[line][startRow].getColor().equals(color)) {
				break;
			}
			changeColor(line,startRow,color);
		}
	}
	public void turnLeftPiece(int line,int row,String color) {
		for(int startRow=row-1; startRow>=0; startRow--) {
			if(board[line][startRow].getColor().equals(color)) {
				break;
			}
			changeColor(line,startRow,color);
		}
	}
	public void turnUpRightPiece(int line,int row,String color) {

		for(int i=1; i<=7; i++) {
			if(board[line-i][row+i].getColor().equals(color)) {
				break;
			}
			changeColor(line-i,row+i,color);
		}
	}
	public void turnUpLeftPiece(int line,int row,String color) {

		for(int i=1; i<=7; i++) {
			if(board[line-i][row-i].getColor().equals(color)) {
				break;
			}
			changeColor(line-i,row-i,color);
		}
	}
	public void turnDownRightPiece(int line,int row,String color) {
		for(int i=1; i<=7; i++) {
			if(board[line+i][row+i].getColor().equals(color)) {
				break;
			}
			changeColor(line+i,row+i,color);
		}
	}
	public void turnDownLeftPiece(int line,int row,String color) {
		for(int i=1; i<=7; i++) {
			if(board[line+i][row-i].getColor().equals(color)) {
				break;
			}
			changeColor(line+i,row-i,color);
		}
	}



	public void judgeWinner(Player player1,Player player2) {
		int sumWhite=0;
		int sumBlack=0;
		for(int line=0; line<8; line++) {
			for(int row=0; row<8; row++) {
				switch(board[line][row].getColor()) {
					case "白":
						sumWhite++;
						break;
					case "黒":
						sumBlack++;
						break;
				}
			}
		}
		System.out.println("白："+sumWhite+"黒:"+sumBlack);
		if(sumWhite>sumBlack) {
			if(player1.getColor().equals("白")) {
				System.out.println(player1.getName()+"さんの勝ちです");
			}else {
				System.out.println(player2.getName()+"さんの勝ちです");
			}
		}else if(sumBlack>sumWhite) {
			if(player1.getColor().equals("黒")) {
				System.out.println(player1.getName()+"さんの勝ちです");
			}else {
				System.out.println(player2.getName()+"さんの勝ちです");
			}
		}else {
			System.out.println("引き分けです");
		}
	}

}


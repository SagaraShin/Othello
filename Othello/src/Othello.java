import java.util.ArrayList;
import java.util.Scanner;


public class Othello {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Player player1=new Player();
		Player player2=new Player();
		Board board=new Board();
		ArrayList<Piece> allPiece=new ArrayList<Piece>();//ゲームで使うコマ
		for(int i=0; i<64; i++) {
			Piece piece=new Piece();
			piece.setColor("Ｎ");
			allPiece.add(piece);
		}
		/*はじめから場面にコマを埋め込んでおくことで、NullPointerException
		 出ないようにした*/
		for(int line=0; line<8; line++) {
			for(int row=0; row<8; row++) {
				board.setPiece(line, row,allPiece.get(0));
				allPiece.remove(0);
			}
		}
		//はじめのコマだけ色を変えておく
		board.changeColor(3,3,"白");
		board.changeColor(3,4,"黒");
		board.changeColor(4,3,"黒");
		board.changeColor(4,4,"白");


		System.out.println("オセロを始めます");
		System.out.println("一人目の方は名前を入力してください");
		Scanner scan=new Scanner(System.in);
		String name=scan.next();
		player1.setName(name);
		player1.setColor("白");
		System.out.println(player1.getName()+"さんのコマの色は白です");
		System.out.println();

		System.out.println("二人目の方は名前を入力してください");
		name=scan.next();
		player2.setName(name);
		player2.setColor("黒");
		System.out.println(player2.getName()+"さんのコマの色は黒です");
		System.out.println();
		board.openPiece();


		int gameCount=0;//ゲーム進行かつプレイヤーの順番を判断する変数
		int remainPiece=60;//ゲーム終了までに置くコマの数
		Piece piece;
		int line;
		int row;
		while(gameCount<remainPiece){
			gameCount++;
			System.out.println();

			if(gameCount%2==1) {//プレイヤー1の番
				System.out.println(player1.getName()+"さんの番です");
				//パスになるのかどうかの判断を行う
				if(!board.checkPass(player1.getColor())) {
					System.out.println(player1.getName()+"さんがコマを置く場所はありません");
					remainPiece++;
					continue;
				}

				while(true) {
					System.out.println("コマを置く場所を入力してください");
					System.out.println("何行ですか(0～7)？");
					line=scan.nextInt();
					System.out.println("何列ですか(0～7)？");
					row=scan.nextInt();
					//指定された場所にコマを置けるのかどうか判断する
					if(board.checkPutPiece(line, row, player1.getColor())){
						break;
					}else {
						System.out.println("その場所にはコマを置けません");
						System.out.println();
					}
				}
				//コマを置いてから、他のコマをひっくり返す
				board.changeColor(line, row, player1.getColor());
				board.turnPieces(line, row, player1.getColor());


			}else {//プレイヤー2の番
				System.out.println(player2.getName()+"さんの番です");

				if(!board.checkPass(player2.getColor())) {
					System.out.println(player2.getName()+"さんがコマを置く場所はありません");
					remainPiece++;
					continue;
				}

				while(true) {
					System.out.println("コマを置く場所を入力してください");
					System.out.println("何行ですか(0～7)？");
					line=scan.nextInt();
					System.out.println("何列ですか(0～7)？");
					row=scan.nextInt();
					if(board.checkPutPiece(line, row, player2.getColor())){
						break;
					}else {
						System.out.println("その場所にはコマを置けません");
					}
				}
				board.changeColor(line, row, player2.getColor());
				board.turnPieces(line,row,player2.getColor());

			}
			board.openPiece();
			System.out.println();

		}
		System.out.println("終了しました");
		System.out.println("結果判定を行います");
		board.judgeWinner(player1, player2);
	}

}

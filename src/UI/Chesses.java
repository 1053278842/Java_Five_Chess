package UI;

import java.awt.image.BufferedImage;

public class Chesses {
	int cw,ch;
	int x,y;
	BufferedImage img;
	boolean isClick=false;
	public Chesses() {
		// TODO Auto-generated constructor stub
	}
	public Chesses(String s) {
		cw=ChessPanle.grid_span/2;
		ch=cw;
		if(s=="black") {
			img=Tool.getImage("/img/Chess_b.png");
		}else {
			img=Tool.getImage("/img/Chess_w.png");
		}
	}
	
	private boolean isClicked(Chesses ch) {
		return ch.isClick;
	}
}

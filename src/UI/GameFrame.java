package UI;

import java.awt.Color;

import javax.swing.*;

public class GameFrame extends JFrame{
	int btn_span=200;
	int w=ChessPanle.row*ChessPanle.grid_span+2*ChessPanle.margin+btn_span;
	int h=ChessPanle.cow*ChessPanle.grid_span+2*ChessPanle.margin;
	public GameFrame(String s) {
		super(s);
		setSize(w, h);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		setResizable(false);
		setBackground(Color.WHITE);
	}
	         
	public static void main(String[] args) {
		GameFrame f=new GameFrame("Îå×ÓÆå");
		ChessPanle p=new ChessPanle();
		f.add(p);
		f.setVisible(true);
	}

}

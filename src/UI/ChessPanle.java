package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;


public class ChessPanle extends JPanel{
	static int cow=15;//列
	static int row=15;//行
	static int margin=30;//边界
	static int grid_span=35;
	int w=16*grid_span+2*margin,h=16*grid_span+2*margin+200;
	//图片
	BufferedImage img_b,img_w,BackGround,Tool_Bg;
	//坐标
	int m_x,m_y;
	int cxindex,cyindex;
	int gcx,gcy;
	int move_x,move_y;
	//棋子
	List<Chesses> chs=new ArrayList<Chesses>();
	int[][] chessbackground=new int[row][cow];
	int chesses[][]=new int[cow][row];
	static int bw;
	//胜利条件
	int bcount=0,wcount=0;
	
	public ChessPanle()  {
		BackGround=Tool.getImage("/img/BackGround2.jpg");
		Tool_Bg=Tool.getImage("/img/Tool_bg.jpg");
//		//初始化棋子
		bw=1;	//黑先行
		repaint();
		setSize(w,h);
		MouseAdapter M_adapter=new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(move_x<=margin+14*grid_span&&move_x>=margin&&move_y>=margin&&move_y<=margin+14*grid_span) {
					m_x=e.getX();
					m_y=e.getY();
//					System.out.println("x="+m_x+"y="+m_y);
					if(bw%2==1) {
						alignCoordinate("black");
					}else {
						alignCoordinate("white");
					}
					bw++;
					repaint();
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				move_x=e.getX();
				move_y=e.getY();
				if(move_x<=margin+14*grid_span&&move_x>=margin&&move_y>=margin&&move_y<=margin+14*grid_span) {
					alignBackGroundChess();
					repaint();
				}
			}
		};
		addMouseListener(M_adapter);
		addMouseMotionListener(M_adapter);
	}
	protected void alignBackGroundChess() {
		// TODO Auto-generated method stub
		cxindex=(move_x-margin)/grid_span;
		cyindex=(move_y-margin)/grid_span;
		cxindex+=(move_x-margin)%grid_span>=grid_span/2? 1:0;
		cyindex+=(move_y-margin)%grid_span>=grid_span/2? 1:0;
		chessbackground[cxindex][cyindex]=1;
	}
	protected void alignCoordinate(String s) {
		cxindex=(m_x-margin)/grid_span;
		cyindex=(m_y-margin)/grid_span;
		cxindex+=(m_x-margin)%grid_span>=grid_span/2? 1:0;
		cyindex+=(m_y-margin)%grid_span>=grid_span/2? 1:0;
		gcx=margin+cxindex*grid_span;
		gcy=margin+cyindex*grid_span;
		if(chesses[cxindex][cyindex]==0) {
			Chesses chess=new Chesses(s);
			chess.x=gcx-7;
			chess.y=gcy-7;
			chess.isClick=true;
			chs.add(chess);
			if(s=="black") {
				chesses[cxindex][cyindex]=1;//黑色为1
//				五子检测
				FiveLine(cxindex,cyindex,1);
			}else {
				chesses[cxindex][cyindex]=2;//白色为2
				FiveLine(cxindex,cyindex,2);
			}

		}else {
			bw--;
		}
	}
	private void FiveLine(int x,int y,int index) {
			//横向
			for(int i=0;i<row;i++) {
				if(chesses[i][y]==index){
					bcount++;
//					System.out.println("横向最大："+bcount);
					if(bcount>=5)
						winShow(index);
				}
				else{
					bcount=0;
					
				}
			}
			//纵向
			for(int i=0;i<cow;i++) {
				if(chesses[x][i]==index){
					bcount++;
//					System.out.println("纵向最大："+bcount);
					if(bcount>=5)
						winShow(index);
				}
				else{
					bcount=0;
				}
			}
			//斜下
			/*
			 * 判断x与y大小
			 */
			if(x==y) {
				for(int i=0;i<row;i++) {
					if(chesses[i][i]==index){
						bcount++;
						if(bcount>=5)
							winShow(index);
					}
					else{
						bcount=0;
					}
				}
			}else if(x<y) {
				for(int i=y-x;i<cow;i++) {
					if(chesses[i-(y-x)][i]==index){
						bcount++;
//						System.out.println("斜方向有："+bcount);
						if(bcount>=5)
							winShow(index);
					}
					else{
						bcount=0;
					}
				}
			}else if(x>y) {
				for(int i=x-y;i<row;i++) {
					if(chesses[i][i-(x-y)]==index){
						bcount++;
//						System.out.println("斜方向有："+bcount);
						if(bcount>=5)
							winShow(index);
					}
					else{
						bcount=0;
					}
				}
			}
			//斜上
			if(x+y==14) {
				for(int i=0;i<row;i++) {
					if(chesses[i][row-1-i]==index){
						bcount++;
//						System.out.println("斜方向有："+bcount);
						if(bcount>=5)
							winShow(index);
					}
					else{
						bcount=0;
					}
				}
			}
			if(x+y<14) {
				for(int i=0;i<=x+y;i++) {
					if(chesses[i][x+y-i]==index){
						bcount++;
//						System.out.println("斜方向有："+bcount);
						if(bcount>=5)
							winShow(index);
					}
					else{
						bcount=0;
					}
				}
			}
			if(x+y>14) {
				for(int i=x+y-14;i<=x+y-(x+y-14);i++) {
					if(chesses[i][x+y-i]==index){
						bcount++;
//						System.out.println("斜方向有："+bcount);
						if(bcount>=5)
							winShow(index);
					}
					else{
						bcount=0;
					}
				}
			}
	}
	private void winShow(int index) {
		if(index==1) {
			System.out.println("黑色胜利！");
		}else {
			System.out.println("白色胜利！");
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(BackGround, 0, 0, margin+grid_span*row,margin+grid_span*row,null);
		g.drawImage(Tool_Bg, margin+grid_span*row, 0,250,ChessPanle.cow*ChessPanle.grid_span+2*ChessPanle.margin, null);
		g.setFont(new Font("黑体", Font.BOLD, 25));
		if(bw%2==1)
			g.drawString(String.valueOf("黑行"), 600, 100);
		else
			g.drawString(String.valueOf("白行"), 600, 100);
		
		g.setFont(new Font("黑体", 0, 15));
		for(int i=0;i<row;i++) {//行
			g.drawLine(margin, margin+i*grid_span, margin+grid_span*(row-1), margin+i*grid_span);
			g.drawString(String.valueOf(i+1), margin-20,margin+i*grid_span+5 );
		}
		for(int i=0;i<cow;i++) {//列
			g.drawLine(margin+i*grid_span, margin,margin+i*grid_span, margin+(cow-1)*grid_span);
			g.drawString(String.valueOf((char)(i+65)), margin+i*grid_span,margin-15);
		}
		for(int i=0;i<chs.size();i++) {
			Chesses ch=chs.get(i);
			g.drawImage(ch.img,ch.x,ch.y, ch.cw,ch.ch,null);
		}
		for(int i=0;i<row;i++) {
			for(int j=0;j<cow;j++) {
				if(chessbackground[i][j]==1) {
					g.setColor(Color.yellow);
					g.fillOval(margin+i*grid_span-7,margin+j*grid_span-7, 14, 14);
					chessbackground[i][j]=0;
				}
			}
		}
	}
}

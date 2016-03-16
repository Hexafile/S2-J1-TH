package org.treasurehunt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.treasurehunt.plateau.Board;

//@author Thomas Plouchart
public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Frame f = new Frame();
	private JPanel pane;
	private Board bd = new Board();;
	private Component jeu;
	private boolean start = true;

	public Image sea;
	public Image sand;
	public Image chest;
	public Image key;
	public Image rock;
	public Image base;
	public Image lineV;
	public Image lineH;

	public Gui(Board b) {
		if (start) {
			this.bd = b;

			start = false;

			try {
				Image sea = ImageIO.read(new File("img/sea.jpg"));
				Image sand = ImageIO.read(new File("img/sand.jpg"));
				Image chest = ImageIO.read(new File("img/chest.png"));
				Image rock = ImageIO.read(new File("img/rock.png"));
				Image key = ImageIO.read(new File("img/key.png"));
				Image base = ImageIO.read(new File("img/base.png"));
				Image lineV = ImageIO.read(new File("img/line.png"));
				Image lineH = ImageIO.read(new File("img/lineH.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		jeu = new Component() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				g.drawImage(sea, 0, 0, null);
				for (int i = 0; i < bd.getSizeHeight() + 2; i++) {
					for (int j = 0; j < bd.getSizeWidth() + 2; j++) {
						if (bd.getCell(i, j).getObstacle()==4 ||bd.getCell(i, j).isBase()){
							g.drawImage(sea, i * 40, j * 40, null);
						}else{
							g.drawImage(sand, i * 40, j * 40, null);
						}
						if (!(bd.getCell(i, j).getObstacle()==0 || bd.getCell(i, j).getObstacle()==4)
								&& !bd.getCell(i, j).getRevealed()){
							g.drawImage(rock, i * 40, j * 40, null);
						}
						if (bd.getCell(i, j).isBase()){
							g.drawImage(base, i * 40, j * 40, null);
						}
						
						if (i != 0){
							g.drawImage(lineV, i * 40, j * 40, null);
						}
						if (j != 0){
							g.drawImage(lineH, i * 40, j * 40, null);
						}
					}
				}
			}
		};

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		pane = new JPanel();
		pane.setLayout(new BorderLayout(1, 1));
		pane.add(jeu);
		f.setBackground(Color.BLACK);
		f.add(pane);
		f.setSize(bd.getSizeHeight() * 40 + 80, bd.getSizeWidth() * 40 + 80);
		f.setResizable(false);
		f.setTitle("Treasure Hunt");
		f.setLocation(200, 200);
		f.setVisible(true);

	}

}

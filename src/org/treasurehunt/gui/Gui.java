package org.treasurehunt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.treasurehunt.plateau.Board;

//@author Thomas Plouchart
public class Gui extends JFrame {
	public JFrame f = new JFrame();
	private JPanel pane;
	private JComponent jeu;
	private JComponent menu;
	private Board bd = new Board();;
	private boolean start = true;

	public Image sea;
	public Image sand;
	public Image chest;
	public Image key;
	public Image rock;
	public Image base;
	public Image soldat;
	public Image lineV;
	public Image lineH;

	public Gui(Board b) {
		if (start) {
			this.bd = b;

			start = false;

			sea = Toolkit.getDefaultToolkit().getImage("img/sea.jpg");
			sand = Toolkit.getDefaultToolkit().getImage("img/sand.jpg");
			chest = Toolkit.getDefaultToolkit().getImage("img/chest.png");
			rock = Toolkit.getDefaultToolkit().getImage("img/rock.png");
			key = Toolkit.getDefaultToolkit().getImage("img/key.png");
			base = Toolkit.getDefaultToolkit().getImage("img/base.png");
			lineV = Toolkit.getDefaultToolkit().getImage("img/line.png");
			lineH = Toolkit.getDefaultToolkit().getImage("img/lineH.png");
			soldat = Toolkit.getDefaultToolkit().getImage("img/soldat.png");
		}

		jeu = new JComponent() {

			public void paint(Graphics g1) {

				Graphics2D g = (Graphics2D) g1;
				super.paint(g);
				g.drawImage(sea, 10, 10, this);
				g.setColor(Color.WHITE);
				g.drawString("lololol", 100, 100);

				for (int i = 0; i < bd.getSizeWidth() + 2; i++) {
					for (int j = 0; j < bd.getSizeHeight() + 2; j++) {

						// Detect the Cell if is water else display sand
						if (bd.getCell(j, i).getObstacle() == 4
								|| bd.getCell(j, i).isBase()) {
							g.drawImage(sea, i * 40, j * 40, this);
						} else {
							g.drawImage(sand, i * 40, j * 40, this);
						}

						// display rock
						if (!(bd.getCell(j, i).getObstacle() == 0 || bd
								.getCell(j, i).getObstacle() == 4)) {
							if (bd.isRevealed()) {
								if (bd.getCell(j, i).getObstacle() == 2)
									g.drawImage(key, i * 40, j * 40, this);
								if (bd.getCell(j, i).getObstacle() == 3)
									g.drawImage(chest, i * 40, j * 40, this);
							} else {
								g.drawImage(rock, i * 40, j * 40, this);
							}
						}
						// Display base
						if (bd.getCell(j, i).isBase()) {
							g.drawImage(base, i * 40, j * 40, this);
						}

						// Display grid
						if (i != 0) {
							g.drawImage(lineV, i * 40, j * 40, this);
						}
						if (j != 0) {
							g.drawImage(lineH, i * 40, j * 40, this);
						}
						if (bd.getCell(j,i).getCharacter() != null){g.drawImage(soldat, i * 40, j * 40, this);System.out.println("woooaaaa");}
					}
				}
			}
		};

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		//f.setLayout(new BorderLayout(1,1));
		jeu.setSize(bd.getSizeHeight() * 40 + 285, bd.getSizeWidth() * 40 + 110);
		jeu.setBackground(new Color(116, 208, 241));
		f.add(jeu);
		f.setSize(jeu.getWidth(), jeu.getHeight());
		f.setResizable(false);
		f.setTitle("Treasure Hunt");
		f.setLocation(200, 200);
		f.setVisible(true);

	}

}

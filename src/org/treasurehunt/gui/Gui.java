package org.treasurehunt.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private int x_pos = -2, y_pos = -2, cptTour, act;
	private boolean[][] moves = null;

	public Image sea, sand, chest, key, rock, base, soldat, lineV, lineH,
			fleche_haut, fleche_bas, fleche_gauche, fleche_droite, croix,
			reduire;

	public Gui(Board b) {
		if (start) {
			this.bd = b;

			start = false;

			croix = Toolkit.getDefaultToolkit().getImage("img/croix.png");
			reduire = Toolkit.getDefaultToolkit().getImage("img/reduire.png");
			sea = Toolkit.getDefaultToolkit().getImage("img/sea.jpg");
			sand = Toolkit.getDefaultToolkit().getImage("img/sand.jpg");
			chest = Toolkit.getDefaultToolkit().getImage("img/chest.png");
			rock = Toolkit.getDefaultToolkit().getImage("img/rock.png");
			key = Toolkit.getDefaultToolkit().getImage("img/key.png");
			base = Toolkit.getDefaultToolkit().getImage("img/base.png");
			lineV = Toolkit.getDefaultToolkit().getImage("img/line.png");
			lineH = Toolkit.getDefaultToolkit().getImage("img/lineH.png");
			soldat = Toolkit.getDefaultToolkit().getImage("img/soldat.png");
			fleche_bas = Toolkit.getDefaultToolkit().getImage(
					"img/fleche_bas.png");
			fleche_haut = Toolkit.getDefaultToolkit().getImage(
					"img/fleche_haut.png");
			fleche_gauche = Toolkit.getDefaultToolkit().getImage(
					"img/fleche_gauche.png");
			fleche_droite = Toolkit.getDefaultToolkit().getImage(
					"img/fleche_droite.png");

		}

		jeu = new JComponent() {

			public void paint(Graphics g1) {

				Graphics2D g = (Graphics2D) g1;
				super.paint(g);
				g.setColor(Color.WHITE);
				g.drawImage(croix, bd.getSizeHeight() * 40 + 256, 2, this);
				g.drawImage(reduire, bd.getSizeHeight() * 40 + 225, 2, this);

				for (int i = 0; i < bd.getSizeWidth() + 2; i++) {
					for (int j = 0; j < bd.getSizeHeight() + 2; j++) {

						// Detect the Cell if is water else display sand
						if (bd.getCell(i, j).getObstacle() == 4
								|| bd.getCell(i, j).isBase()) {
							g.drawImage(sea, i * 40, j * 40, this);
						} else {
							g.drawImage(sand, i * 40, j * 40, this);
						}

						// display rock
						if (!(bd.getCell(i, j).getObstacle() == 0 || bd
								.getCell(i, j).getObstacle() == 4)) {
							if (bd.isRevealed()) {
								if (bd.getCell(i, j).getObstacle() == 2)
									g.drawImage(key, i * 40, j * 40, this);
								if (bd.getCell(i, j).getObstacle() == 3)
									g.drawImage(chest, i * 40, j * 40, this);
							} else {
								g.drawImage(rock, i * 40, j * 40, this);
							}
						}

						// Display base
						if (bd.getCell(i, j).isBase()) {
							g.drawImage(base, i * 40, j * 40, this);
						}

						if (bd.getCell(i, j).isCharacter()) {
							g.drawImage(soldat, i * 40, j * 40, this);
						}

						// Display grid
						if (i != 0) {
							g.drawImage(lineV, i * 40, j * 40, this);
						}
						if (j != 0) {
							g.drawImage(lineH, i * 40, j * 40, this);
						}

						if (i - 1 == getX_pos() && j - 1 == getY_pos()
								&& bd.getCell(i - 1, j - 1).isCharacter()) {
							moves = bd.getMoves(bd.getCell(i - 1, j - 1)
									.getCharacter());
							if (moves[0][0])
								;
							if (moves[0][1])
								g.drawImage(fleche_haut, (i - 1) * 40,
										(j - 1) * 40 - 40, this);
							if (moves[0][2])
								;
							if (moves[1][0])
								g.drawImage(fleche_gauche, (i - 1) * 40 - 40,
										(j - 1) * 40, this);
							if (moves[1][2])
								g.drawImage(fleche_droite, (i - 1) * 40 + 40,
										(j - 1) * 40, this);
							if (moves[2][0])
								;
							if (moves[2][1])
								g.drawImage(fleche_bas, (i - 1) * 40,
										(j - 1) * 40 + 40, this);
							if (moves[2][2])
								;
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

		f.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Detecte fermeture de fenetre
				if (e.getX() >= bd.getSizeHeight() * 40 + 256 && e.getY() < 29) {
					System.exit(DISPOSE_ON_CLOSE);
					// Detecte reduction de la fenetre
				} else if (e.getX() >= bd.getSizeHeight() * 40 + 225
						&& e.getY() < 29) {
					f.setExtendedState(HIDE_ON_CLOSE);
				}

				// Detecte sur Board
				if (e.getX() / 40 < bd.getSizeHeight()
						&& e.getY() / 40 < bd.getSizeWidth()) {
					// Si Personnage
					if (bd.getCell(e.getX() / 40, e.getY() / 40).isCharacter()
							&& bd.getCell(e.getX() / 40, e.getY() / 40)
									.getCharacter().getTeam() == getTourTeam()) {
						setX_pos(e.getX() / 40);
						setY_pos(e.getY() / 40);
						// Vers droite
					} else if (e.getX() / 40 == getX_pos() - 1
							&& e.getY() / 40 == getY_pos() && moves[1][0]) {
						bd.move(getX_pos(), getY_pos(), getX_pos() - 1,
								getY_pos());
						removeXY();
						increaseAct();
						// Vers gauche
					} else if (e.getX() / 40 == getX_pos() + 1
							&& e.getY() / 40 == getY_pos() && moves[1][2]) {
						bd.move(getX_pos(), getY_pos(), getX_pos() + 1,
								getY_pos());
						removeXY();
						increaseAct();
						// Vers haut
					} else if (e.getX() / 40 == getX_pos()
							&& e.getY() / 40 == getY_pos() - 1 && moves[0][1]) {
						bd.move(getX_pos(), getY_pos(), getX_pos(),
								getY_pos() - 1);
						removeXY();
						increaseAct();
						// Vers bas
					} else if (e.getX() / 40 == getX_pos()
							&& e.getY() / 40 == getY_pos() + 1 && moves[2][1]) {
						bd.move(getX_pos(), getY_pos(), getX_pos(),
								getY_pos() + 1);
						removeXY();
						increaseAct();
						// Sinon remove les coord du Personnage en cache
					} else {
						removeXY();
					}
					jeu.repaint();
				}
			}
		});

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		jeu.setSize(bd.getSizeHeight() * 40 + 285, bd.getSizeWidth() * 40 + 80);
		jeu.setBackground(new Color(116, 208, 241));
		f.setUndecorated(true);
		f.add(jeu);
		f.setSize(jeu.getWidth(), jeu.getHeight());
		f.setResizable(false);
		f.setLocation((int) ((width - jeu.getWidth()) / 2),
				(int) (height - jeu.getHeight()) / 2);
		f.setVisible(true);

	}

	public int getX_pos() {
		return x_pos;
	}

	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}

	public int getY_pos() {
		return y_pos;
	}

	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}

	public void removeXY() {
		setX_pos(-2);
		setY_pos(-2);
	}

	public int getTourTeam() {
		return cptTour % 2 + 1;
	}

	public void increaseAct() {
		if (act > 2) {
			cptTour++;
			act = 0;
		}
		act++;
	}
}

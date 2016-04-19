package org.treasurehunt.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu extends JFrame implements ActionListener {

	private int ex1;
	private int ex2;
	private int sc1;
	private int sc2;
	private int th1;
	private int th2;
	private int wa1;
	private int wa2;
	private JTextField j1;
	private JTextField j2;
	private JTextField j3;
	private JTextField j4;
	private JTextField j5;
	private JTextField j6;
	private JTextField j7;
	private JTextField j8;
	private JButton enter;
	private boolean enterPressed = false;

	public Menu() {

		setSize(50, 200);
		setLayout(new GridLayout(10, 2));

		add(new JLabel("Equipe 1 :"));
		add(new JLabel("Equipe 2 :"));
		add(new JLabel("Explorer :"));
		add(new JLabel("Explorer :"));
		j1 = new JTextField("0", 1);
		j1.addActionListener(this);
		j2 = new JTextField("0", 1);
		j2.addActionListener(this);
		add(j1);
		add(j2);
		add(new JLabel("Scavenger :"));
		add(new JLabel("Scavenger :"));
		j3 = new JTextField("0", 1);
		j3.addActionListener(this);
		j4 = new JTextField("0", 1);
		j4.addActionListener(this);
		add(j3);
		add(j4);
		add(new JLabel("Thief: "));
		add(new JLabel("Thief: "));
		j5 = new JTextField("0", 1);
		j5.addActionListener(this);
		j6 = new JTextField("0", 1);
		j6.addActionListener(this);
		add(j5);
		add(j6);
		add(new JLabel("Warrior :"));
		add(new JLabel("Warrior :"));
		j7 = new JTextField("0", 1);
		j7.addActionListener(this);
		j8 = new JTextField("0", 1);
		j8.addActionListener(this);
		add(j7);
		add(j8);
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				enterPressed = true;
			}
		});
		add(enter);

		setLocation(200, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);

	}

	public void actionPerformed(ActionEvent evt) {

		String text1 = j1.getText();
		if (text1.matches("\\d"))
			ex1 = Integer.parseInt(text1);
		String text2 = j2.getText();
		if (text2.matches("\\d"))
			ex2 = Integer.parseInt(text2);
		String text3 = j3.getText();
		if (text3.matches("\\d"))
			sc1 = Integer.parseInt(text3);
		String text4 = j4.getText();
		if (text4.matches("\\d"))
			sc2 = Integer.parseInt(text4);
		String text5 = j5.getText();
		if (text5.matches("\\d"))
			th1 = Integer.parseInt(text5);
		String text6 = j6.getText();
		if (text6.matches("\\d"))
			th2 = Integer.parseInt(text6);
		String text7 = j7.getText();
		if (text7.matches("\\d"))
			wa1 = Integer.parseInt(text7);
		String text8 = j8.getText();
		if (text8.matches("\\d"))
			wa2 = Integer.parseInt(text8);

	}

	public boolean getPressedEnter() {
		return enterPressed;
	}

	public List<Integer> getCharacter() {
		List<Integer> l = new ArrayList<Integer>(8);
		l.add(ex1);
		l.add(ex2);
		l.add(sc1);
		l.add(sc2);
		l.add(th1);
		l.add(th2);
		l.add(wa1);
		l.add(wa2);
		return l;
	}

}

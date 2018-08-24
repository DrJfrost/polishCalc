package com.polishCalc.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.polishCalc.logic.Functions;

public class Calculator implements ActionListener, MouseListener {

	private JFrame frame;
	private JLabel title, numPad, display, stack, pile;
	private JButton button[][] = new JButton[5][5];
	private JButton close, minimize;
	private Font customFont;
	private String[][] text = { { "1", "2", "3", "CE", "Sort" }, { "4", "5", "6", "×", "/" },
			{ "7", "8", "9", "-", "+" } };
	private Functions func = new Functions();

	public Calculator() {
		frame = new JFrame();
		numPad = new JLabel();
		frame.setBounds(500, 100, 430, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(17, 21, 33));
		frame.setUndecorated(true);
		numPad.setLayout(null);

		close = new JButton("X");
		close.setBounds(405, 0, 25, 25);
		close.addActionListener(this);
		close.addMouseListener(this);
		close.setFont(new Font("ARIAL", Font.PLAIN, 20));
		close.setBackground(new Color(17, 21, 33));
		close.setBorderPainted(false);
		close.setFocusPainted(false);
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setForeground(new Color(215, 220, 229));
		frame.add(close);

		minimize = new JButton("-");
		minimize.setBounds(380, 0, 25, 25);
		minimize.addActionListener(this);
		minimize.addMouseListener(this);
		minimize.setFont(new Font("ARIAL", Font.PLAIN, 20));
		minimize.setBackground(new Color(17, 21, 33));
		minimize.setBorderPainted(false);
		minimize.setFocusPainted(false);
		minimize.setMargin(new Insets(0, 0, 0, 0));
		minimize.setForeground(new Color(215, 220, 229));
		frame.add(minimize);

		title = new JLabel("Polish Calculator");
		// display.setBorder(new LineBorder(Color.BLACK, 2,true));
		title.setBounds(30, 30, 370, 50);
		title.setBackground(new Color(17, 21, 33));
		title.setOpaque(true);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Niagara Solid", Font.PLAIN, 30));
		title.setForeground(new Color(215, 220, 229));
		frame.add(title);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				button[i][j] = new JButton(text[i][j]);
				button[i][j].setBounds(j * 75, i * 65, 70, 60);
				button[i][j].addActionListener(this);
				button[i][j].addMouseListener(this);
				button[i][j].setFont(new Font("Niagara Solid", Font.BOLD, 39));
				button[i][j].setBackground(new Color(51, 56, 66));
				button[i][j].setBorderPainted(false);
				button[i][j].setFocusPainted(false);
				button[i][j].setForeground(new Color(215, 220, 229));
				numPad.add(button[i][j]);
			}
		}

		button[0][3].setBackground(new Color(124, 51, 51));
		button[1][3].setBackground(new Color(26, 29, 35));
		button[1][4].setBackground(new Color(26, 29, 35));
		button[2][3].setBackground(new Color(26, 29, 35));
		button[2][4].setBackground(new Color(26, 29, 35));

		button[0][4].setFont(new Font("Niagara Solid", Font.PLAIN, 35));
		button[0][4].setBackground(new Color(26, 29, 35));

		button[3][0] = new JButton("0");
		button[3][0].setBounds(0, 195, 220, 60);
		button[3][0].addActionListener(this);
		button[3][0].addMouseListener(this);
		button[3][0].setFont(new Font("Niagara Solid", Font.BOLD, 39));
		button[3][0].setBackground(new Color(51, 56, 66));
		button[3][0].setBorderPainted(false);
		button[3][0].setFocusPainted(false);
		button[3][0].setForeground(new Color(215, 220, 229));
		numPad.add(button[3][0]);

		button[3][1] = new JButton("Enter");
		button[3][1].setBounds(225, 195, 145, 60);
		button[3][1].addActionListener(this);
		button[3][1].addMouseListener(this);
		button[3][1].setFont(new Font("Niagara Solid", Font.PLAIN, 39));
		button[3][1].setHorizontalTextPosition(SwingConstants.CENTER);
		button[3][1].setBackground(new Color(26, 29, 35));
		button[3][1].setBorderPainted(false);
		button[3][1].setFocusPainted(false);
		button[3][1].setForeground(new Color(215, 220, 229));
		numPad.add(button[3][1]);

		button[4][0] = new JButton("Search");
		button[4][0].setBounds(0, 260, 370, 60);
		button[4][0].addActionListener(this);
		button[4][0].addMouseListener(this);
		button[4][0].setFont(new Font("Niagara Solid", Font.PLAIN, 39));
		button[4][0].setHorizontalTextPosition(SwingConstants.CENTER);
		button[4][0].setBackground(new Color(26, 29, 35));
		button[4][0].setBorderPainted(false);
		button[4][0].setFocusPainted(false);
		button[4][0].setForeground(new Color(215, 220, 229));
		numPad.add(button[4][0]);

		numPad.setBounds(30, 200, 400, 500);

		frame.add(numPad);

		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Calculator.ttf")).deriveFont(Font.PLAIN,
					20);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		display = new JLabel("");
		// display.setBorder(new LineBorder(Color.BLACK, 2,true));
		display.setBounds(30, 100, 370, 70);
		display.setBackground(new Color(51, 56, 66));
		display.setOpaque(true);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setFont(customFont.deriveFont(Font.PLAIN, 60));
		display.setForeground(new Color(215, 220, 229));
		frame.add(display);

		pile = new JLabel("Pila");
		pile.setBounds(50, 540, 320, 30);
		pile.setHorizontalAlignment(SwingConstants.CENTER);
		pile.setVerticalAlignment(SwingConstants.CENTER);
		pile.setFont(new Font("Niagara Solid", Font.PLAIN, 30));
		pile.setForeground(new Color(215, 220, 229));
		pile.setVisible(false);
		frame.add(pile);

		stack = new JLabel();
		stack.setBounds(30, 570, 370, 50);
		stack.setBackground(Color.white);
		// stack.setBorder(new LineBorder(Color.BLACK,1));
		stack.setOpaque(false);
		stack.setHorizontalAlignment(SwingConstants.RIGHT);
		stack.setFont(customFont.deriveFont(Font.PLAIN, 30));
		stack.setForeground(new Color(215, 220, 229));
		stack.setVisible(false);
		frame.add(stack);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (action.getSource().equals(button[i][j]) && !action.getSource().equals(button[0][3])
						&& !action.getSource().equals(button[0][4]) && !action.getSource().equals(button[3][1])
						&& !action.getSource().equals(button[4][0])) {
					display.setText(display.getText() + button[i][j].getText());
				} else if (action.getSource().equals(button[0][3])) {
					func = new Functions();
					stack.setText("");
					display.setText("");
					pile.setVisible(false);
					stack.setVisible(false);
				} else if (action.getSource().equals(button[0][4])) {
					String[] list = { "Ascending Sort", "Descending Sort" };
					JComboBox<String> jcb = new JComboBox<String>(list);
					JOptionPane.showMessageDialog(null, jcb, "select or type a value", JOptionPane.QUESTION_MESSAGE);
					if (jcb.getSelectedItem().equals("Ascending Sort")) {
						stack.setText(func.ascendingSort().toString());
						display.setText("");
					} else if (jcb.getSelectedItem().equals("Descending Sort")) {
						stack.setText(func.descendingSort().toString());
						display.setText("");
					}
					i = 4;
					j = 5;
				} else if (action.getSource().equals(button[3][1])) {
					func.fill(display.getText());
					stack.setText(func.printStack());
					display.setText("");
					pile.setVisible(true);
					stack.setVisible(true);
					i = 4;
					j = 5;
				} else if (action.getSource().equals(button[4][0])) {
					JOptionPane.showMessageDialog(null, func.find(display.getText()));
					i = 4;
					j = 5;
				} else if (i == 4 && j == 0) {
					i = 4;
					j = 5;
				}
			}
		}
		if (action.getSource().equals(close)) {
			System.exit(0);
		}else if (action.getSource().equals(minimize)) {
			frame.setState(Frame.ICONIFIED);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent action) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (action.getSource().equals(button[i][j]) && !action.getSource().equals(button[0][3])
						&& !action.getSource().equals(button[0][4]) && !action.getSource().equals(button[1][3])
						&& !action.getSource().equals(button[1][4]) && !action.getSource().equals(button[2][3])
						&& !action.getSource().equals(button[2][4]) && !action.getSource().equals(button[3][1])
						&& !action.getSource().equals(button[4][0])) {
					button[i][j].setBackground(new Color(103, 107, 117));
				} else if (action.getSource().equals(button[i][j]) && !action.getSource().equals(button[0][0])
						&& !action.getSource().equals(button[0][1]) && !action.getSource().equals(button[0][2])
						&& !action.getSource().equals(button[1][0]) && !action.getSource().equals(button[1][1])
						&& !action.getSource().equals(button[1][2]) && !action.getSource().equals(button[2][0])
						&& !action.getSource().equals(button[2][1]) && !action.getSource().equals(button[2][2])
						&& !action.getSource().equals(button[3][0]) && !action.getSource().equals(button[0][3])) {
					button[i][j].setBackground(new Color(14, 15, 17));
				} else if (action.getSource().equals(button[0][3])) {
					button[0][3].setBackground(new Color(198, 89, 89));
				}
			}
		}
		if (action.getSource().equals(close)) {
			close.setBackground(new Color(124, 51, 51));
		}else if (action.getSource().equals(minimize)) {
			minimize.setBackground(new Color(51, 56, 66));
		}
	}

	@Override
	public void mouseExited(MouseEvent action) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (action.getSource().equals(button[i][j]) && !action.getSource().equals(button[0][3])
						&& !action.getSource().equals(button[0][4]) && !action.getSource().equals(button[1][3])
						&& !action.getSource().equals(button[1][4]) && !action.getSource().equals(button[2][3])
						&& !action.getSource().equals(button[2][4]) && !action.getSource().equals(button[3][1])
						&& !action.getSource().equals(button[4][0])) {
					button[i][j].setBackground(new Color(51, 56, 66));
					;
				} else if (action.getSource().equals(button[i][j]) && !action.getSource().equals(button[0][0])
						&& !action.getSource().equals(button[0][1]) && !action.getSource().equals(button[0][2])
						&& !action.getSource().equals(button[1][0]) && !action.getSource().equals(button[1][1])
						&& !action.getSource().equals(button[1][2]) && !action.getSource().equals(button[2][0])
						&& !action.getSource().equals(button[2][1]) && !action.getSource().equals(button[2][2])
						&& !action.getSource().equals(button[3][0]) && !action.getSource().equals(button[0][3])) {
					button[i][j].setBackground(new Color(26, 29, 35));
				} else if (action.getSource().equals(button[0][3])) {
					button[0][3].setBackground(new Color(124, 51, 51));
				}
			}
		}
		if (action.getSource().equals(close)) {
			close.setBackground(new Color(17, 21, 33));
		}else if (action.getSource().equals(minimize)) {
			minimize.setBackground(new Color(17, 21, 33));
		}

	}

	@Override
	public void mousePressed(MouseEvent action) {

	}

	@Override
	public void mouseReleased(MouseEvent action) {

	}
}

package com.polishCalc.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.polishCalc.logic.Functions;

public class Calculator implements ActionListener{

	private JFrame frame;
	private JLabel numPad, display, stack, pile;
	private JButton button[][] = new JButton[5][5];
	private Font customFont;
	private String[][] text = {{"1", "2", "3", "CE", "Sort"},
								{"4", "5", "6", "*", "/"},
								{"7", "8", "9", "-", "+"}};
	private Functions func = new Functions();

	public Calculator() {
		frame = new JFrame();
		numPad = new JLabel();
		frame.setBounds(100, 100, 390, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		numPad.setLayout(null);
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<5; j++) {
				button[i][j] = new JButton(text[i][j]);
				button[i][j].setBounds(j*65, i*65, 60, 60);
				button[i][j].addActionListener(this);
				numPad.add(button[i][j]);
			}
		}
		
		button[3][0] = new JButton("0");
		button[3][0].setBounds(1*65, 3*65, 125, 60);
		button[3][0].addActionListener(this);
		numPad.add(button[3][0]);
		
		button[3][1] = new JButton("=>");
		button[3][1].setBounds(3*65, 3*65, 60, 60);
		button[3][1].addActionListener(this);
		numPad.add(button[3][1]);
		numPad.setBounds(30, 90, 400, 400);
		
		frame.add(numPad);
		
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Calculator.ttf")).deriveFont(Font.PLAIN, 20);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
		} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		display = new JLabel("");
		display.setBorder(new LineBorder(Color.BLACK, 1,true));
		display.setBounds(30, 10, 320, 70);
		display.setBackground(Color.white);
		display.setOpaque(true);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setFont(customFont.deriveFont(Font.PLAIN, 60));
		frame.add(display);
		
		
		pile = new JLabel("Pila");
		pile.setBounds(30, 360, 320, 30);
		pile.setHorizontalAlignment(SwingConstants.CENTER);
		pile.setVerticalAlignment(SwingConstants.CENTER);
		pile.setVisible(false);
		frame.add(pile);
		
		stack = new JLabel();
		stack.setBorder(new LineBorder(Color.BLACK, 1,true));
		stack.setBounds(30, 400, 320, 50);
		stack.setBackground(Color.white);
		stack.setOpaque(false);
		stack.setHorizontalAlignment(SwingConstants.RIGHT);
		stack.setFont(customFont.deriveFont(Font.PLAIN, 30));
		stack.setVisible(false);
		frame.add(stack);
		
	
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		for (int i=0; i<4; i++) {
			for (int j=0; j<5; j++) {
				if (action.getSource().equals(button[i][j]) && !action.getSource().equals(button[0][3]) 
						&& !action.getSource().equals(button[0][4]) && !action.getSource().equals(button[3][1]) ) {
					display.setText(display.getText()+button[i][j].getText());
				}else if(action.getSource().equals(button[0][3])){
					func = new Functions();
					stack.setText("");
					display.setText("");
					pile.setVisible(false);
					stack.setVisible(false);
				}else if(action.getSource().equals(button[0][4])){
					String[] list = {"Ascending Sort", "Descending Sort"};
					JComboBox<String> jcb = new JComboBox<String>(list);
					JOptionPane.showMessageDialog( null, jcb, "select or type a value", JOptionPane.QUESTION_MESSAGE);
					if (jcb.getSelectedItem().equals("Ascending Sort")) {
						stack.setText(func.ascendingSort().toString());
						display.setText("");
					}else if (jcb.getSelectedItem().equals("Descending Sort")) {
						stack.setText(func.descendingSort().toString());
						display.setText("");
					}
					i=4;
					j=5;
				}else if(action.getSource().equals(button[3][1])) {
					func.fill(display.getText());
					stack.setText(func.printStack());
					display.setText("");
					pile.setVisible(true);
					stack.setVisible(true);
					i=4;
					j=5;
				}else if(i==3 && j==1){
					i=4;
					j=5;
				}
			}
		}
		
	}
}


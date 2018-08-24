package com.polishCalc.logic;

import java.util.Stack;

import javax.swing.JOptionPane;

public class Functions {

	public boolean active = true;
	public String fill;
	public Stack<String> stack = new Stack<String>();

	public void fill(String value) {
		try {
			Float.parseFloat(value);
			stack.push(value);
		} catch (Exception e) {
			float aux = 0;
			if (value.equals("+") && stack.size() != 1) {
				aux = Float.parseFloat(stack.pop()) + Float.parseFloat(stack.pop());
				stack.push(Float.toString(aux));
			} else if (value.equals("-") && stack.size() != 1) {
				aux = Float.parseFloat(stack.pop()) - Float.parseFloat(stack.pop());
				stack.push(Float.toString(aux));
			} else if (value.equals("×") && stack.size() != 1) {
				aux = Float.parseFloat(stack.pop()) * Float.parseFloat(stack.pop());
				stack.push(Float.toString(aux));
			} else if (value.equals("/") && stack.size() != 1) {
				aux = Float.parseFloat(stack.pop()) / Float.parseFloat(stack.pop());
				stack.push(Float.toString(aux));
			}else if (stack.size() == 1) {
				JOptionPane.showMessageDialog(null, "Only one number to operate");
			}else {
				JOptionPane.showMessageDialog(null, "Not an Operator or Number");
			}
		}
	}

	public String printStack() {
		return stack.toString();
	}

	public Stack<Float> ascendingSort() {
		Stack<Float> numb = new Stack<Float>();
		for (int i = 0; i < stack.size(); i++) {
			numb.add(Float.parseFloat(stack.get(i)));
		}
		for (int i = 1; i < numb.size(); i++) {
			Float temp = numb.get(i);
			int j = i - 1;
			while ((j >= 0) && (temp < numb.get(j))) {
				numb.set(j + 1, numb.get(j));
				j--;
			}
			numb.set(j + 1, temp);
		}
		for (int i = 0; i < numb.size(); i++) {
			stack.set(i, numb.get(i).toString());
		}
		return numb;
	}

	public String find(String number) {
		String ans = "No numbers were Found";
		for (int i = 0; i < stack.size(); i++) {
			if(stack.get(i).equals(number)) {
				ans = "number: " + stack.get(i).toString() + "             Position: " + i;
			}
		}
		return ans;
	}
	public Stack<Float> descendingSort() {
		Stack<Float> numb = new Stack<Float>();
		for (int i = 0; i < stack.size(); i++) {
			numb.add(Float.parseFloat(stack.get(i)));
		}
		for (int i = 1; i < numb.size(); i++) {
			Float temp = numb.get(i);
			int j = i - 1;
			while ((j >= 0) && (temp > numb.get(j))) {
				numb.set(j + 1, numb.get(j));
				j--;
			}
			numb.set(j + 1, temp);
		}
		for (int i = 0; i < numb.size(); i++) {
			stack.set(i, numb.get(i).toString());
		}
		return numb;
	}
}

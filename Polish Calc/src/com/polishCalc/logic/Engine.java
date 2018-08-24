package com.polishCalc.logic;

import com.polishCalc.GUI.Calculator;

public class Engine {

	private static Calculator cal;

	public static void main(String[] args) {
		setCal(new Calculator());
	}

	public static Calculator getCal() {
		return cal;
	}

	public static void setCal(Calculator cal) {
		Engine.cal = cal;
	}

}

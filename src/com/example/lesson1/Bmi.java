package com.example.lesson1;

public class Bmi {
	public double height;
	public double weight;
	public double value;

	public Bmi() {

	}

	public Bmi(double height, double weight) {
		this.height = height;
		this.weight = weight;
		this.value = 0.0;
	}

	public double caculaterBmi() {
		value = weight / (height * height);
		return value;
	}

}

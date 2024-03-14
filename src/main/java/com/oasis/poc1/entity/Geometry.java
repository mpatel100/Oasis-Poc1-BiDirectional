package com.oasis.poc1.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Geometry implements Serializable {
	
	private Double x;
	private Double y;
	
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Geometry [x=" + x + ", y=" + y + "]";
	}
	
	
	

}
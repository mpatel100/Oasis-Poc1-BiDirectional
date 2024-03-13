package com.oasis.poc1.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AllWells implements Serializable{
	private String layers;
	
	public String getLayers() {
		return layers;
	}

	public void setLayers(String layers) {
		this.layers = layers;
	}

	@Override
	public String toString() {
		return "AllWells [layers=" + layers + "]";
	}
}
	
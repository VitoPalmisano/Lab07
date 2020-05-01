package it.polito.tdp.poweroutages.model;

import java.util.Comparator;

public class ComparatoreDataCrescente implements Comparator<PowerOutage> {

	public int compare(PowerOutage po1, PowerOutage po2) {
		return po1.getDateEventBegan().compareTo(po2.getDateEventBegan());
	}
	
}

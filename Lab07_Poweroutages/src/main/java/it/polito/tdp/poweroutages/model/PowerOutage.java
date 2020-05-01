package it.polito.tdp.poweroutages.model;

import java.time.*;

public class PowerOutage {
	private int id;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	
	public PowerOutage(int id, int customersAffected, LocalDateTime dateEventBegan, LocalDateTime dataEventFinished) {
		this.id = id;
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dataEventFinished;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDateTime dataEventFinished) {
		this.dateEventFinished = dataEventFinished;
	}
	
	public Duration getDurata() {
		return Duration.between(dateEventBegan, dateEventFinished);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return dateEventBegan.getYear()+" "+dateEventBegan+" "+dateEventFinished+" "+Duration.between(dateEventBegan, dateEventFinished).toHours()+":"+Duration.between(dateEventBegan, dateEventFinished).toMinutesPart()+" "+customersAffected;
	}
	
	
}

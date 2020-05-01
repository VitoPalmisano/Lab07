package it.polito.tdp.poweroutages.model;

import java.time.*;
import java.util.*;

public class Ricerca {
	
	private List<PowerOutage> powerOutagesTotali;
	private Period anniMax;
	private Duration oreMax;
	
	private List<PowerOutage> powerOutages;
	private int utentiMax;
	private Duration ore;

	public List<PowerOutage> trovaBlackouts(List<PowerOutage> powerOutagesTotali, int anniMax, int oreMax){
		
		this.powerOutagesTotali = powerOutagesTotali;
		this.anniMax = Period.ofYears(anniMax);
		this.oreMax = Duration.ofHours(oreMax);
		this.utentiMax = 0;
		powerOutages = new ArrayList<PowerOutage>();
		this.ore = Duration.ofHours(0);
		cerca(new ArrayList<PowerOutage>(), 0, 0, null, null, -1, this.ore);
		return powerOutages;
	}

	private void cerca(List<PowerOutage> parziale, int utenti, int livello, LocalDate primoGiorno, LocalDate ultimoGiorno, int id, Duration ore) {
		
		if(utenti>utentiMax) {
			powerOutages = new ArrayList<PowerOutage>(parziale);
			utentiMax = utenti;
			this.ore = ore;
		}
		
		if(livello>powerOutagesTotali.size())
			return;
		
		for(PowerOutage po : powerOutagesTotali) {
			if(po.getId()>id) {
				id=po.getId();
				if(ore.plus(Duration.between(po.getDateEventBegan(), po.getDateEventFinished())).toSeconds()<=oreMax.toSeconds()) {
					if(livello == 0 || Period.between(primoGiorno, po.getDateEventBegan().toLocalDate()).isNegative()) {
						primoGiorno = po.getDateEventBegan().toLocalDate();
					}
					if(livello == 0 || Period.between(po.getDateEventFinished().toLocalDate(), ultimoGiorno).isNegative()) {
						ultimoGiorno = po.getDateEventFinished().toLocalDate();
					}
					Period anni = Period.between(primoGiorno, ultimoGiorno);
					if(anni.getYears()<anniMax.getYears() || ( anni.getYears()==anniMax.getYears() && anni.getDays()==0 && anni.getMonths()==0 ) ) {
						parziale.add(po);
						cerca(parziale, utenti+po.getCustomersAffected(), livello +1, primoGiorno, ultimoGiorno, id, ore.plus(Duration.between(po.getDateEventBegan(), po.getDateEventFinished())));
						parziale.remove(po);
					}
				}
			}
		}
	}

	public int getUtentiMax() {
		return utentiMax;
	}

	public Duration getOre() {
		return ore;
	}
	
	
}

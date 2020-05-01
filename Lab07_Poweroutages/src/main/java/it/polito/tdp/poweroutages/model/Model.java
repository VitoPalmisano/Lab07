package it.polito.tdp.poweroutages.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public String trovaBlackouts(Nerc nerc, int anni, int ore) {
		String s = "";
		Ricerca r = new Ricerca();
		nerc.setPowerOutages(podao.getPowerOutageList(nerc.getId()));
		List<PowerOutage> powerOutagesVoluti = r.trovaBlackouts(nerc.getPowerOutages(), anni, ore);
		if(powerOutagesVoluti.isEmpty()) {
			s += "Nessun dato trovato relativo ai parametri selezionati";
			return s;
		}
		s += r.getUtentiMax()+"\n";
		s += r.getOre().toHours()+":"+r.getOre().toMinutesPart()+"\n";
		Collections.sort(powerOutagesVoluti, new ComparatoreDataCrescente());
		for(PowerOutage po : powerOutagesVoluti) {
			s += po.toString()+"\n";
		}
		return s;
	}
}

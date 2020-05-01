package it.polito.tdp.poweroutages.model;

import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		PowerOutageDAO dao = new PowerOutageDAO() ;
		
	//	System.out.println(model.getNercList());
		List<Nerc> nercs = model.getNercList();
		System.out.println(nercs.get(3));
		System.out.println(model.trovaBlackouts(nercs.get(3), 4, 200));

	}

}

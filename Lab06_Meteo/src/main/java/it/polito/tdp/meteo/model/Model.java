package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	private MeteoDAO meteoDao;
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	public Model() {
		meteoDao = new MeteoDAO();
	}

	// of course you can change the String output with what you think works best
	public Map<String,String> getUmiditaMedia(int mese) {
		Map<String,String> medie = new TreeMap<String, String>();
		List<Rilevamento> list = meteoDao.getAllRilevamenti();
		List<Rilevamento> list1 = new ArrayList<>();
		for(Rilevamento r : list) {
			double media = 0.0;
			list1 = meteoDao.getAllRilevamentiLocalitaMese(mese, r.getLocalita());
			int totale = 0;
			for(Rilevamento r1 : list1) {
				totale += r1.getUmidita();
			}
			media = totale / list1.size();
			if(medie.get(r.getLocalita())==null) {
				medie.put(r.getLocalita(), String.valueOf(media));
			}
		}
		
		return medie;
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		return "TODO!";
	}
	

}

package it.polito.tdp.meteo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.polito.tdp.meteo.model.Rilevamento;

public class TestMeteoDAO {

	public static void main(String[] args){
		
		MeteoDAO dao = new MeteoDAO();

		List<Rilevamento> list = dao.getAllRilevamenti();

		// STAMPA: localita, giorno, mese, anno, umidita (%)
		/*for (Rilevamento r : list) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		*/
//		System.out.println(dao.getAllRilevamentiLocalitaMese(1, "Genova"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(1, "Genova"));
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(5, "Milano"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Milano"));
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(5, "Torino"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Torino"));
		YearMonth ym = YearMonth.of(2013, 1);
		YearMonth ym1 = YearMonth.of(2013, 3);
		// get the last day of month
		int lastDay = ym.lengthOfMonth();
		 LocalDate primo = ym.atDay(lastDay);
		 LocalDate ultimo = ym1.atDay(1);
		//LocalDate first = ym.atDay(1);
		// loop through the days
		for(Rilevamento r : list) {
			if(r.getLocalita().equals("Genova")) {
				LocalDate data = new java.sql.Date( r.getData().getTime()).toLocalDate();
				if(data.isAfter(primo)&&data.isBefore(ultimo)) {
					System.out.println(data);
				}
			}
		}
	}}

package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.time.*;
import java.util.List;

import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
		YearMonth ym;
		if(mese==1) {
			ym = YearMonth.of(2012, 12);
		}else {
			ym = YearMonth.of(2013, mese-1);
		}
		YearMonth ym1; 
		if(mese==12) {
			ym1=YearMonth.of(2014,1);
		}else {
			ym1 = YearMonth.of(2013, mese+1);
		}
		
		// get the last day of month
		int lastDay = ym.lengthOfMonth();
		 LocalDate primo = ym.atDay(lastDay);
		 LocalDate ultimo = ym1.atDay(1);
		//LocalDate first = ym.atDay(1);
		// loop through the days
		for(Rilevamento r : getAllRilevamenti()) {
			if(r.getLocalita().equals("Genova")) {
				LocalDate data = new java.sql.Date( r.getData().getTime()).toLocalDate();
				if(data.isAfter(primo)&&data.isBefore(ultimo)) {
					rilevamenti.add(r);
				}
			}
		}
	    return rilevamenti;
	}


}

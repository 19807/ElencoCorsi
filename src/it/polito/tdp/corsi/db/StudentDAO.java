package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class StudentDAO {

	public Studente getStudenteByMatricola(int matricola) {
		
		String sql = "SELECT nome, cognome, cds " + 
				"FROM studente WHERE matricola = ?" ;
		
		Studente result = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, matricola);
			
			ResultSet res = st.executeQuery() ;
			
			if (res.next()) {
				result = new Studente(matricola, res.getString("nome"), res.getString("cognome"), res.getString("cds"));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
		
		return result;
	}

}

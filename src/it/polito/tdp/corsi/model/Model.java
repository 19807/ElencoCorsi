package it.polito.tdp.corsi.model;

import java.util.List;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudentDAO;

public class Model {

	private List<Corso> corsi;
	private CorsoDAO corsoDAO;
	private StudentDAO studenteDAO;

	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudentDAO();
	}

	public List<Corso> listaCorsiSemestre(int pd) {

		// opzione 1: leggo tutto e filtro io
//		this.corsi = corsoDAO.listAll();
//
//		List<Corso> risultato = new ArrayList<>();
//		for (Corso c : this.corsi) {
//			if (c.getPd() == pd) {
//				risultato.add(c);
//			}
//		}
//		return risultato;

		// opzione 2: faccio lavorare il database
		List<Corso> risultato2 = corsoDAO.listByPD(pd);
		return risultato2;

	}

	public String getNomeCognomeByMatricola(int matricola) {
		
		Studente studente = studenteDAO.getStudenteByMatricola(matricola);
		if (studente == null) {
			return "Non ho trovato nessuno studente associato a quella matricola.";
		}
		return studente.getNome() + " " + studente.getCognome();
	}

	public String getStatisticheFromCorsi() {
		this.corsi = corsoDAO.listAll();
		
		StringBuilder sb = new StringBuilder();
		for (Corso c : this.corsi) {
			Statistiche stat = corsoDAO.getStatisticheByCodins(c.getCodIns());
			sb.append("codins: " + c.getCodIns() + "\n");
			for (String cds : stat.getMappaCDS().keySet()) {
				sb.append(" - " + cds + " " + stat.getMappaCDS().get(cds) + "\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}

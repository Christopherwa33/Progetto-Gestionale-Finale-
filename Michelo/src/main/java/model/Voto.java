package model;

import java.util.Map;

import utils.IMappable;

public class Voto implements IMappable {

	private int id;
	private String materia;
	private double punteggio;
	private int idStudente;

	public Voto() {
		super();
	}

	public Voto(String materia, double punteggio, int idStudente) {
		super();
		this.materia = materia;
		this.punteggio = punteggio;
		this.idStudente = idStudente;
	}

	public Voto(int id, String materia, double punteggio, int idStudente) {
		super();
		this.id = id;
		this.materia = materia;
		this.punteggio = punteggio;
		this.idStudente = idStudente;
	}

	public Voto(Map<String, String> map) {
		fromMap(map);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public double getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(double punteggio) {
		this.punteggio = punteggio;
	}

	public int getIdStudente() {
		return idStudente;
	}

	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", materia=" + materia + ", punteggio=" + punteggio + ", idStudente=" + idStudente
				+ "]";
	}

}

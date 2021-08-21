package model;

import java.util.List;
import java.util.Map;

import utils.IMappable;

public class Studente implements IMappable {
	private int id;
	private String nome;
	private String cognome;
	private String dataDiNascita;
	private int classe;
	private String sezione;
	private List<Voto> voti;

	public Studente() {
		super();
	}

	public Studente(String nome, String cognome, String dataDiNascita, int classe, String sezione, List<Voto> voti) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.classe = classe;
		this.sezione = sezione;
		this.voti = voti;
	}

	public Studente(String nome, String cognome, String dataDiNascita, int classe, String sezione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.classe = classe;
		this.sezione = sezione;
	}

	public Studente(int id, String nome, String cognome, String dataDiNascita, int classe, String sezione,
			List<Voto> voti) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.classe = classe;
		this.sezione = sezione;
		this.voti = voti;
	}

	public Studente(Map<String, String> map) {
		fromMap(map);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public List<Voto> getVoti() {
		return voti;
	}

	public void setVoti(List<Voto> voti) {
		this.voti = voti;
	}

	public double mediaVoti() {
		if (voti.isEmpty())
			return 0;

		double somma = 0;

		for (Voto v : voti) {
			somma += v.getPunteggio();
		}

		return somma / voti.size();
	}

	@Override
	public String toString() {
		return "Studente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita
				+ ", classe=" + classe + ", sezione=" + sezione + ", voti=" + voti + "]";
	}

}

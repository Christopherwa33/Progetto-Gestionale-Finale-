package dao;

import java.util.List;

import model.Studente;

public interface IDaoStudenti {
	List<Studente> lista();

	Studente dettaglio(int id);

	void add(Studente studente);

	void update(int id, Studente studente);

	void delete(int id);
}

package dao;

import java.util.List;

import model.Voto;

public interface IDaoVoti {
	List<Voto> listaVoti(int idStudente);

	Voto voto(int id);

	void add(Voto voto);

	void update(int id, Voto voto);

	void delete(int id);
}

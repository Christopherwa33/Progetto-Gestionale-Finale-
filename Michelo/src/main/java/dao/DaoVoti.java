package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Voto;

public class DaoVoti extends BasicDao implements IDaoVoti {

	public DaoVoti(String dbAddress) throws SQLException {
		super(dbAddress);
	}

	@Override
	public List<Voto> listaVoti(int idStudente) {
		List<Map<String, String>> queryResults = findAll("SELECT * from studente, voti where studente.id = ?;", idStudente);
		List<Voto> ris = new ArrayList<>();
		
		for (Map<String, String> map: queryResults) {
			ris.add(new Voto(map));
		}
		
		return ris;
	}

	@Override
	public Voto voto(int id) {
		Map<String, String> queryResult = findOne("SELECT * FROM voti WHERE id = ?;", id);
		if (queryResult == null) return null;
		
		return new Voto(queryResult);
	}

	@Override
	public void add(Voto voto) {
		executeUpdate(
				"INSERT INTO voti(materia, punteggio, idstudente) VALUES(?,?,?);",
				voto.getMateria(),
				voto.getPunteggio(),
				voto.getIdStudente()
		);
	}

	@Override
	public void update(int id, Voto voto) {
		executeUpdate(
			"UPDATE voti SET materia = ?, punteggio = ? WHERE id = ?;",
			voto.getMateria(),
			voto.getPunteggio(),
			id
		);
	}

	@Override
	public void delete(int id) {
		executeUpdate("DELETE FROM voti WHERE id = ?;", id);
	}

}

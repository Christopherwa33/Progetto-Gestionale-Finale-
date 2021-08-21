package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Studente;

public class DaoStudenti extends BasicDao implements IDaoStudenti {
	
	private IDaoVoti daoVoti;

	public DaoStudenti(String dbAddress, IDaoVoti daoVoti) throws SQLException {
		super(dbAddress);
		this.daoVoti = daoVoti;
	}

	@Override
	public List<Studente> lista() {
		List<Map<String, String>> queryResults = findAll("SELECT * FROM studente;");
		
		List<Studente> ris = new ArrayList<>();
		
		for (Map<String, String> map: queryResults) {
			ris.add(new Studente(map));
		}
		
		return ris;
	}

	@Override
	public Studente dettaglio(int id) {
		Map<String, String> queryResult = findOne("SELECT * FROM studente WHERE id = ?;", id);
		if (queryResult == null) return null;
		
		Studente ris = new Studente(queryResult);

		ris.setVoti(daoVoti.listaVoti(id));
		
		return ris;
	}

	@Override
	public void add(Studente studente) {
		executeUpdate(
				"INSERT INTO studente(nome, cognome, datadinascita, classe, sezione) VALUES(?,?,?,?,?);",
				studente.getNome(),
				studente.getCognome(),
				studente.getDataDiNascita(),
				studente.getClasse(),
				studente.getSezione()
		);
	}

	@Override
	public void update(int id, Studente studente) {
		executeUpdate(
			"UPDATE studente SET nome = ?, cognome = ?, datadinascita = ?, classe = ?, sezione = ? WHERE id = ?;",
			studente.getNome(),
			studente.getCognome(),
			studente.getDataDiNascita(),
			studente.getClasse(),
			studente.getSezione(),
			id
		);
	}

	@Override
	public void delete(int id) {
		executeUpdate("DELETE FROM studente WHERE id = ?;", id);
	}

}

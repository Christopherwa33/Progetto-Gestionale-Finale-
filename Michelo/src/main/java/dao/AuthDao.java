package dao;

import java.sql.SQLException;
import java.util.Map;

import model.Utente;

public class AuthDao extends BasicDao {

	public AuthDao(String dbAddress) throws SQLException {
		super(dbAddress);
	}
	
	/**
	 * Se il login va a buon fine resituitamo un oggetto
	 * che corrisponde all'utente che ha appena effettuato il login
	 * Altrimenti restituiamo null
	 * @param username
	 * @param password
	 * @return
	 */
	public Utente login(String username, String password) {
		Map<String, String> queryResult = 
				findOne("SELECT * FROM users WHERE username = ? AND password = ?;",
					username,
					password
				);
		if (queryResult == null) return null;
		
		return new Utente(queryResult);
	}

}

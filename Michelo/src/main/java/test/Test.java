package test;

import java.sql.SQLException;

import dao.DaoStudenti;
import dao.DaoVoti;
import dao.IDaoStudenti;
import dao.IDaoVoti;
import model.Studente;
import model.Voto;

public class Test {

	public static void main(String[] args) throws SQLException {
		String dbAddress = "jdbc:mysql://localhost:3306/scuolacom?user=root&password=root";
		IDaoVoti daoVoti = new DaoVoti(dbAddress);
		IDaoStudenti daoStudenti = new DaoStudenti(dbAddress, daoVoti);
		
		System.out.println(daoStudenti.dettaglio(1));
		
//		daoStudenti.add(new Studente("Ciccio", "Franco", "1999-05-08", 1, "A", null));
//		daoStudenti.update(5, new Studente("Cicciooooo", "Franceschino", "1999-05-28", 2, "B", null));
//		daoStudenti.delete(2);
//		daoVoti.add(new Voto("Italiano", 10, 3));
//		System.out.println(daoStudenti.dettaglio(3));
//		daoVoti.update(8, new Voto("Francese", 7, 0));
//		daoVoti.delete(6);
	}

}

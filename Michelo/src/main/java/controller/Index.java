package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthDao;
import dao.DaoStudenti;
import dao.DaoVoti;
import dao.IDaoStudenti;
import dao.IDaoVoti;
import model.Studente;
import model.Utente;
import model.Voto;
import view.ViewHTML;


@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static String DB_ADDRESS = "jdbc:mysql://localhost:3306/scuolacom?user=root&password=root";
    
	private IDaoStudenti daoStudenti;
	private IDaoVoti daoVoti;
	private ViewHTML view;
	private AuthDao authDao;
   
    public Index() {
        super();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			daoVoti = new DaoVoti(DB_ADDRESS);
			daoStudenti = new DaoStudenti(DB_ADDRESS, daoVoti);
			authDao = new AuthDao(DB_ADDRESS);
			view = new ViewHTML("C:\\Users\\Utente\\eclipse-workspace\\Michelo\\src\\main\\webapp\\WEB-INF\\templates\\");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    private boolean isLogged(HttpServletRequest request) {
    	Object loggedUser = request.getSession().getAttribute("loggedUser");
    	return loggedUser != null;
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if (cmd == null) cmd = "home";
		
		String ris;
		
		switch (cmd) {
		case "home":
			ris = view.renderHome(daoStudenti.lista(), isLogged(request));
			break;
		case "dettaglio":
			int id = Integer.parseInt(request.getParameter("id"));
			ris = view.renderStudentDetail(daoStudenti.dettaglio(id), isLogged(request));
			break;
		case "eliminastudente":
			id = Integer.parseInt(request.getParameter("id"));
			daoStudenti.delete(id);
			response.sendRedirect("Index");
			return;
		case "eliminavoto":
			id = Integer.parseInt(request.getParameter("id"));
			int idStudente = Integer.parseInt(request.getParameter("idstudente"));
			daoVoti.delete(id);
			response.sendRedirect("Index?cmd=dettaglio&id="+idStudente);
			return;
		case "addstudente":
			ris = view.renderFormStudente(isLogged(request));
			break;
		case "modificastudente":
			id = Integer.parseInt(request.getParameter("id"));
			ris = view.renderFormStudente(daoStudenti.dettaglio(id), isLogged(request));
			break;
		case "editvoto":
			idStudente = Integer.parseInt(request.getParameter("idstudente"));
			id = Integer.parseInt(request.getParameter("id"));
			Studente s = daoStudenti.dettaglio(idStudente);
			Voto daModificare = daoVoti.voto(id);
			ris = view.renderFormEditVoto(s, daModificare, isLogged(request));
			break;
		case "login":
			ris = view.renderLoginForm(isLogged(request));
			break;
		case "logout":
			request.getSession().invalidate();
			response.sendRedirect("Index");
			return;
		default:
			ris = view.renderBody("Page Not Found", isLogged(request));
		}
		
		response.getWriter().append(ris);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if (cmd == null) {
			doGet(request, response);
		}
		String ris = "";
		
		// Se sta cercando di effetuare operazioni, ad accezione del login,
		// MA non risulta essere loggato, lo blocco lato server
		if (!cmd.equals("login")) {
			boolean isLogged = isLogged(request);
			if (!isLogged) {
				response.getWriter().append(view.renderForbidden());
				return;
			}
		}
		
		switch(cmd) {
		case "addvoto":
			int idStudente = Integer.parseInt(request.getParameter("idstudente"));
			String materia = request.getParameter("materia");
			double punteggio = Double.parseDouble(request.getParameter("punteggio"));
			Voto votoStudente = new Voto(materia, punteggio, idStudente);
			daoVoti.add(votoStudente);
			response.sendRedirect("Index?cmd=dettaglio&id="+idStudente);
			return;
		case "addstudente":
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String dataDiNascita = request.getParameter("datadinascita");
			int classe = Integer.parseInt(request.getParameter("classe"));
			String sezione = request.getParameter("sezione");
			Studente nuovoStudente = new Studente(nome, cognome, dataDiNascita, classe, sezione);
			daoStudenti.add(nuovoStudente);
			response.sendRedirect("Index");
			return;
		case "editstudente":
			int id = Integer.parseInt(request.getParameter("id"));
			nome = request.getParameter("nome");
			cognome = request.getParameter("cognome");
			dataDiNascita = request.getParameter("datadinascita");
			classe = Integer.parseInt(request.getParameter("classe"));
			sezione = request.getParameter("sezione");
			Studente studenteModificato = new Studente(nome, cognome, dataDiNascita, classe, sezione);
			daoStudenti.update(id, studenteModificato);
			response.sendRedirect("Index");
			return;
		case "editvoto":
			idStudente = Integer.parseInt(request.getParameter("idstudente"));
			int idVoto = Integer.parseInt(request.getParameter("idvoto"));
			materia = request.getParameter("materia");
			punteggio = Double.parseDouble(request.getParameter("punteggio"));
			daoVoti.update(idVoto, new Voto(materia, punteggio, 0));
			response.sendRedirect("Index?cmd=dettaglio&id="+idStudente);
			return;
		case "login":
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Utente loggedUser = authDao.login(username, password);
			if (loggedUser == null) {
				ris = view.renderForbidden();
			} else {
				request.getSession().setAttribute("loggedUser", loggedUser);
				response.sendRedirect("Index");
				return;
			}
			break;
		}
		
		response.getWriter().append(ris);
	}

}

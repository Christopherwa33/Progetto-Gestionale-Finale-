package view;

import java.util.List;

import model.Studente;
import model.Voto;

public class ViewHTML extends TemplateView {

	public ViewHTML(String templatePath) {
		super(templatePath);
	}
	
	public String renderBody(String body, boolean isLogged) {
		return loadTemplate(isLogged ? "index.html" : "index_nl.html")
				.replace("<!-- body -->", body);
	}

	
	public String renderHome(List<Studente> lista, boolean isLogged) {
		String viewHome = loadTemplate("table_studenti.html");
		String viewTableContent = "";
		
		for (Studente s: lista) {
			viewTableContent += renderTableRow(s, isLogged) + "\n";
		}
		
		return renderBody( 
				viewHome.replace("<!-- lista -->", viewTableContent),
				isLogged
		);
	}

	private String renderTableRow(Studente s, boolean isLogged) {
		return loadTemplate(isLogged ? "row_studenti.html" : "row_studenti_nl.html")
				.replace("[NOME]", s.getNome())
				.replace("[COGNOME]", s.getCognome())
				.replace("[CLASSE]", s.getClasse() + "")
				.replace("[SEZIONE]", s.getSezione())
				.replace("[ID]", s.getId() + "");
	}

	public String renderStudentDetail(Studente s, boolean isLogged) {
		String viewDetail = loadTemplate(isLogged ? "detail_studente.html" : "detail_studente_nl.html")
				.replace("[NOME]", s.getNome())
				.replace("[COGNOME]", s.getCognome())
				.replace("[CLASSE]", s.getClasse() + "")
				.replace("[SEZIONE]", s.getSezione())
				.replace("[ID]", s.getId() + "")
				.replace("[DATADINASCITA]", s.getDataDiNascita()+ "")
				.replace("<!-- voti -->", renderVoti(s.getVoti(), isLogged));

		return renderBody(viewDetail, isLogged);
	}

	private String renderVoti(List<Voto> voti, boolean isLogged) {
		String templateVoto = loadTemplate(
				isLogged 
				? 
				"row_voti.html" 
				: 
				"row_voti_nl.html"
		);
		String ris = "";
		
		for (Voto v: voti) {
			ris += templateVoto
					.replace("[MATERIA]", v.getMateria())
					.replace("[PUNTEGGIO]", v.getPunteggio() + "")
					.replace("[ID]", v.getId() + "")
					.replace("[IDSTUDENTE]", v.getIdStudente() + "");
		}
		
		return ris;
	}

	public String renderFormStudente(boolean isLogged) {
		return renderBody(
				loadTemplate(isLogged ? "form_add_studente.html" : "forbidden.html"), 
				isLogged
		);
	}

	public String renderFormStudente(Studente s, boolean isLogged) {
		// TODO
		return renderBody(
				isLogged 
				?
				loadTemplate("form_edit_studente.html")
					.replace("[ID]", s.getId() + "")
					.replace("[NOME]", s.getNome())
					.replace("[COGNOME]", s.getCognome())
					.replace("[DATADINASCITA]", s.getDataDiNascita())
					.replace("[CLASSE]", s.getClasse() + "")
					.replace("[SEZIONE]", s.getSezione())
				: loadTemplate("forbidden.html"),
					isLogged
		);
	}

	public String renderFormEditVoto(Studente s, Voto v, boolean isLogged) {
		String viewDetail = 
				isLogged 
				? 
				loadTemplate("edit_voto.html")
					.replace("[NOME]", s.getNome())
					.replace("[COGNOME]", s.getCognome())
					.replace("[CLASSE]", s.getClasse() + "")
					.replace("[SEZIONE]", s.getSezione())
					.replace("[ID]", s.getId() + "")
					.replace("[DATADINASCITA]", s.getDataDiNascita())
					.replace("<!-- voti -->", renderVoti(s.getVoti(), isLogged))
					.replace("[IDVOTO]", v.getId() + "")
					.replace("[MATERIA]", v.getMateria())
					.replace("[PUNTEGGIO]", v.getPunteggio() + "")
				:
				loadTemplate("forbidden.html");
		
		return renderBody(viewDetail, isLogged);
	}

	public String renderLoginForm(boolean isLogged) {
		return renderBody(loadTemplate("login_form.html"), isLogged);
	}

	public String renderForbidden() {
		return renderBody(loadTemplate("forbidden.html"), false);
	}

}

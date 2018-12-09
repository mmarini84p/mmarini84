package it.polcity.test.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author marco.marini
 *	Model per Persona, contiene anche i riferimenti ai Documenti associati
 */
public class PersonaWithDetails extends Persona {

	private List<Documento> documenti = new ArrayList<Documento>();

	public PersonaWithDetails() {
		super();
	}

	public PersonaWithDetails(Integer idPersona, String nome, String cognome, Date dataNascita, Sesso sesso,
			String luogoNascita) {
		super(idPersona, nome, cognome, dataNascita, sesso, luogoNascita);
	}

	public PersonaWithDetails(Integer idPersona, String nome, String cognome, Date dataNascita, Sesso sesso,
			String luogoNascita, List<Documento> documenti) {
		this(idPersona, nome, cognome, dataNascita, sesso, luogoNascita);
		this.documenti = documenti;
	}

	public List<Documento> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
	}

}

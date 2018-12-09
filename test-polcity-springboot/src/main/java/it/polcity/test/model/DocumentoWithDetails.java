package it.polcity.test.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marco.marini
 *	Model per i Documenti che contiene anche i riferimenti alle Persone associate
 */
public class DocumentoWithDetails extends Documento {
	
	/**
	 * Lista di persone associate al Documento
	 */
	private List<Persona> persone = new ArrayList<Persona>();
	
	public DocumentoWithDetails() {
		super();
	}
	
	public DocumentoWithDetails(Integer idProtocollo, Integer annoProtocollo, String testo) {
		super(idProtocollo, annoProtocollo, testo);
	}
	
	public DocumentoWithDetails(Integer idProtocollo, Integer annoProtocollo, String testo, List<Persona> persone) {
		super(idProtocollo, annoProtocollo, testo);
		this.persone = persone;
	}

	public List<Persona> getPersone() {
		return persone;
	}

	public void setPersone(List<Persona> persone) {
		this.persone = persone;
	}

}

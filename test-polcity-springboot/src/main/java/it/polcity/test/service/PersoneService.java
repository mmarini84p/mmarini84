package it.polcity.test.service;

import java.util.List;

import it.polcity.test.model.Persona;
import it.polcity.test.model.PersonaWithDetails;

/**
 * @author marco.marini Service per le operazioni relative alle persone Ogni
 *         metodo è transazionale
 */
public interface PersoneService {

	/**
	 * Inserimento di una Persona
	 * 
	 * @param nome         nome
	 * @param cognome      cognome
	 * @param dataNascita  dataNascita
	 * @param sesso        sesso
	 * @param luogoNascita luogoNascita
	 * @return
	 * @throws Exception
	 */
	public Persona insertPersona(String nome, String cognome, String dataNascita, String sesso, String luogoNascita)
			throws Exception;

	/**
	 * Modifica di una persona
	 * @param idPersona
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param luogoNascita
	 * @return
	 * @throws Exception
	 */
	public Persona updatePersona(Integer idPersona, String nome, String cognome, String dataNascita, String sesso,
			String luogoNascita) throws Exception;

	/**
	 * Cancellazione di una persona
	 * @param idPersona
	 * @throws Exception
	 */
	public void deletePersona(Integer idPersona) throws Exception;

	/**
	 * Ritorna una Persona dato il suo id
	 * @param idPersona
	 * @return
	 * @throws Exception
	 */
	public Persona getPersona(Integer idPersona) throws Exception;

	/**
	 * Ricerca delle Persone attraverso dei parametri di ricerca
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param luogoNascita
	 * @return
	 * @throws Exception
	 */
	public List<Persona> listPersona(String nome, String cognome, String dataNascita, String sesso, String luogoNascita)
			throws Exception;

	/**
	 * Operazione per associare una Persona ad uno o più Documenti
	 * @param idPersona
	 * idPersona da associare
	 * @param lstProtocollo
	 * lista di protocolli da associare
	 * @return
	 * @throws Exception
	 */
	public PersonaWithDetails associaDocumenti(Integer idPersona, List<String> lstProtocollo) throws Exception;

	/**
	 * Operazione per disassociare una Persona ad uno o più Documenti
	 * @param idPersona
	 * idPersona da disassociare
	 * @param lstProtocollo
	 * lista di protocolli da disassociare
	 * @return
	 * @throws Exception
	 */
	public PersonaWithDetails disassociaDocumenti(Integer idPersona, List<String> lstProtocollo) throws Exception;

	/**
	 * Ritorna i dati della Persona con la lista dei documenti associati
	 * @param idPersona
	 * idPersona
	 * @return
	 * @throws Exception
	 */
	public PersonaWithDetails getDocumentiAssociati(Integer idPersona) throws Exception;

}

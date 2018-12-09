package it.polcity.test.dao;

import java.util.Date;
import java.util.List;

import it.polcity.test.model.Persona;
import it.polcity.test.model.Persona.Sesso;

/**
 * @author marco.marini
 *	DAO per la gestione della tabella Persone
 */
public interface PersoneDao {

	/**
	 * Inserimento di una nuova persona
	 * @param nome
	 * nome
	 * @param cognome
	 * cognome
	 * @param dataNascita
	 * data di nascita
	 * @param sesso
	 * sesso
	 * @param luogoNascita
	 * luogo di nascita
	 * @return
	 * la persona inserita con l'id generato
	 * @throws Exception
	 */
	public Persona insertPersona(String nome, String cognome, Date dataNascita, Sesso sesso, String luogoNascita)
			throws Exception;

	/**
	 * Modifica di una persona
	 * @param idPersona
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param luogoNascita
	 * @throws Exception
	 */
	public void updatePersona(Integer idPersona, String nome, String cognome, Date dataNascita, Sesso sesso,
			String luogoNascita) throws Exception;

	/**
	 * Cancellazione di una Persona
	 * @param idPersona
	 * @throws Exception
	 */
	public void deletePersona(Integer idPersona) throws Exception;

	/**
	 * Ritorna una Persona dato l'id
	 * @param idPersona
	 * 	idPersona
	 * @return
	 * @throws Exception
	 */
	public Persona getPersona(Integer idPersona) throws Exception;

	/**
	 * Ricerca le persone attraverso dei parametri di filtro
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param luogoNascita
	 * @return
	 * @throws Exception
	 */
	public List<Persona> listPersona(String nome, String cognome, Date dataNascita, Sesso sesso, String luogoNascita) throws Exception;

}

package it.polcity.test.dao;

import java.util.List;

import it.polcity.test.model.Documento;

/**
 * @author marco.marini
 *	DAO per la gestione della tabella Documenti
 */
public interface DocumentiDao {

	/**
	 * Inserimento di un nuovo documento
	 * @param testo
	 * 	testo del documento
	 * @return
	 * 	il nuovo documento generato
	 * @throws Exception
	 */
	public Documento insertDocumento(String testo) throws Exception;

	/**
	 * Modifica dei campi del documento
	 * @param idProtocollo
	 * 	idProtocollo da modificare
	 * @param testo
	 * 	testo da modificare
	 * @throws Exception
	 */
	public void updateDocumento(Integer idProtocollo, String testo) throws Exception;

	/**
	 * Cancellazione di un documento
	 * @param idProtocollo
	 * idProtocollo da cancellare
	 * @throws Exception
	 */
	public void deleteDocumento(Integer idProtocollo) throws Exception;

	/**
	 * Ritorna un documento attraverso l'id
	 * @param idProtocollo
	 * 	chiave del documento
	 * @return
	 * @throws Exception
	 */
	public Documento getDocumento(Integer idProtocollo) throws Exception;
	
	/**
	 * Ritorna un documento attraverso l'id e attraverso l'anno
	 * (in realtà l'anno è ridondante, serve solo per controllare che il dato in ingresso dell'utente sia corretto)
	 * @param idProtocollo
	 * @param annoProtocollo
	 * @return
	 * @throws Exception
	 */
	public Documento getDocumento(Integer idProtocollo, Integer annoProtocollo) throws Exception;

	/**
	 * Filtra i documenti per alcuni criteri di ricerca
	 * @param anno
	 * 	anno 
	 * @param testo
	 * 	testo
	 * @return
	 * @throws Exception
	 */
	public List<Documento> listDocumento(Integer anno, String testo) throws Exception;

}

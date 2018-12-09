package it.polcity.test.service;

import java.util.List;

import it.polcity.test.model.Documento;
import it.polcity.test.model.DocumentoWithDetails;

/**
 * @author marco.marini
 *	Service per le operazioni relative ai Documenti
 *	Ogni metodo è transazionale
 */
public interface DocumentiService {
	
	/**
	 * Inserimento di un nuovo Documento
	 * @param testo
	 * @return
	 * @throws Exception
	 */
	public Documento insertDocumento(String testo) throws Exception;

	/**
	 * Modifica di un documento
	 * @param protocollo
	 * @param testo
	 * @return
	 * @throws Exception
	 */
	public Documento updateDocumento(String protocollo, String testo) throws Exception;

	/**
	 * Cancellazione di un docuemnto
	 * @param protocollo
	 * @throws Exception
	 */
	public void deleteDocumento(String protocollo) throws Exception;

	/**
	 * Ritorna un documento dato il suo protocollo in formato xx/yyyy (es:5/2018)
	 * @param protocollo
	 * @return
	 * @throws Exception
	 */
	public Documento getDocumento(String protocollo) throws Exception;
	
	/**
	 * Ricerca dei documenti attraverso dei parametri di ricerca
	 * @param anno
	 * @param testo
	 * @return
	 * @throws Exception
	 */
	public List<Documento> listDocumento(Integer anno, String testo) throws Exception;
	
	/**
	 * Operazione per associare un Documento ad una lista di Persone
	 * @param protocollo
	 * 	protocollo del documento da associare
	 * @param lstIdPersona
	 * 	lista di idPersona da associare
	 * @return
	 * @throws Exception
	 */
	public DocumentoWithDetails associaPersone(String protocollo, List<Integer> lstIdPersona) throws Exception;

	/**
	 * Operazione per disassociare un Documento ad una lista di Persone
	 * @param protocollo
	 * 	protocollo del documento da disassociare
	 * @param lstIdPersona
	 * 	lista di idPersona da disassociare
	 * @return
	 * @throws Exception
	 */
	public DocumentoWithDetails disassociaPersone(String protocollo, List<Integer> lstIdPersona) throws Exception;

	/**
	 * Ritorna i dati del Documento con la lista delle persone associate
	 * @param protocollo
	 * 	protocollo del documento da ricercare
	 * @return
	 * @throws Exception
	 */
	public DocumentoWithDetails getPersoneAssociate(String protocollo) throws Exception;

}

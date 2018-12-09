package it.polcity.test.dao;

import java.util.List;

import it.polcity.test.model.DocumentoWithDetails;
import it.polcity.test.model.PersonaWithDetails;

/**
 * @author marco.marini DAO per la gestione della tabella DocumentiPersone
 */
public interface DocumentiPersoneDao {

	/**
	 * Operazione di associazione di una persona a uno o più documenti
	 * 
	 * @param idPersona       idPersona da associare
	 * @param lstIdProtocollo lista di idProtocolli da associare alla persona
	 * @throws Exception
	 */
	public void associaDocumenti(Integer idPersona, List<Integer> lstIdProtocollo) throws Exception;

	/**
	 * Operazione di disassociazione di una persona a uno o più documenti
	 * 
	 * @param idPersona       idPersona da disassociare
	 * @param lstIdProtocollo lista di idProtocolli da disassociare alla persona
	 * @throws Exception
	 */
	public void disassociaDocumenti(Integer idPersona, List<Integer> lstIdProtocollo) throws Exception;

	/**
	 * Estrae la lista dei Documenti associati alla Persona
	 * 
	 * @param idPersona idPersona
	 * @return PersonaWithDetails
	 * @throws Exception
	 */
	public PersonaWithDetails getDocumentiAssociati(Integer idPersona) throws Exception;

	/**
	 * Operazione di associazione di una documento a una o più persone
	 * 
	 * @param idProtocollo idProtocollo da associare
	 * @param lstIdPersona lista di idPersona da associare al protocollo
	 * @throws Exception
	 */
	public void associaPersone(Integer idProtocollo, List<Integer> lstIdPersona) throws Exception;

	/**
	 * Operazione di disassociazione di una documento a una o più persone
	 * 
	 * @param idProtocollo idProtocollo da disassociare
	 * @param lstIdPersona lista di idPersona da disassociare al protocollo
	 * @throws Exception
	 */
	public void disassociaPersone(Integer idProtocollo, List<Integer> lstIdPersona) throws Exception;

	/**
	 * Estrae la lista delle Persone associate al Documento
	 * 
	 * @param idProtocollo   idProtocollo
	 * @param annoProtocollo annoProtocollo
	 * @return DocumentoWithDetails
	 * @throws Exception
	 */
	public DocumentoWithDetails getPersoneAssociate(Integer idProtocollo, Integer annoProtocollo) throws Exception;

}

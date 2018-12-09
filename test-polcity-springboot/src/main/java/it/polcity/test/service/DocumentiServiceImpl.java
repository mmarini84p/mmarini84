package it.polcity.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import it.polcity.test.dao.DocumentiDao;
import it.polcity.test.dao.DocumentiPersoneDao;
import it.polcity.test.dao.PersoneDao;
import it.polcity.test.model.Documento;
import it.polcity.test.model.DocumentoWithDetails;
import it.polcity.test.model.Persona;
import it.polcity.test.utils.Utils;

/**
 * @author marco.marini
 *	Implementazione di {@link DocumentiService}
 */
@Component("documentiService")
@Transactional
public class DocumentiServiceImpl implements DocumentiService {

	@Autowired
	DocumentiDao documentiDao;

	@Autowired
	PersoneDao personeDao;

	@Autowired
	DocumentiPersoneDao documentiPersoneDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public Documento insertDocumento(String testo) throws Exception {

		Assert.hasText(testo, String.format("Il campo '%s' non è stato valorizzato ", "testo"));
		Documento documento = documentiDao.insertDocumento(testo);
		return documento;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public Documento updateDocumento(String protocollo, String testo) throws Exception {

		Assert.hasText(protocollo, String.format("Il campo '%s' non è stato valorizzato ", "protocollo"));
		Assert.hasText(testo, String.format("Il campo '%s' non è stato valorizzato ", "testo"));

		Integer idProtocollo = Utils.getIdFromProtocollo(protocollo);
		Integer annoProtocollo = Utils.getAnnoFromProtocollo(protocollo);

		Documento documentoToUpdate = documentiDao.getDocumento(idProtocollo, annoProtocollo);
		Assert.notNull(documentoToUpdate,
				String.format("Il protocollo inserito '%s' non esiste all'interno del sistema", protocollo));

		documentiDao.updateDocumento(idProtocollo, testo);

		// Ritorna il documento aggiornato
		Documento documento = documentiDao.getDocumento(idProtocollo, annoProtocollo);

		return documento;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public void deleteDocumento(String protocollo) throws Exception {

		Assert.hasText(protocollo, String.format("Il campo '%s' non è stato valorizzato ", "protocollo"));

		Integer idProtocollo = Utils.getIdFromProtocollo(protocollo);
		Integer annoProtocollo = Utils.getAnnoFromProtocollo(protocollo);

		Documento documentoToDelete = documentiDao.getDocumento(idProtocollo, annoProtocollo);
		Assert.notNull(documentoToDelete,
				String.format("Il protocollo da eliminare '%s' non esiste all'interno del sistema", protocollo));

		documentiDao.deleteDocumento(idProtocollo);
	}

	@Override
	public Documento getDocumento(String protocollo) throws Exception {

		Assert.hasText(protocollo, String.format("Il campo '%s' non è stato valorizzato ", "protocollo"));

		Integer idProtocollo = Utils.getIdFromProtocollo(protocollo);
		Integer annoProtocollo = Utils.getAnnoFromProtocollo(protocollo);

		Documento documento = documentiDao.getDocumento(idProtocollo, annoProtocollo);
		return documento;
	}

	@Override
	public List<Documento> listDocumento(Integer annoProtocollo, String testo) throws Exception {

		List<Documento> lstDocumento = documentiDao.listDocumento(annoProtocollo, testo);

		return lstDocumento;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public DocumentoWithDetails associaPersone(String protocollo, List<Integer> lstIdPersona) throws Exception {

		Assert.hasText(protocollo, String.format("Il campo '%s' non è stato valorizzato ", "protocollo"));
		Assert.isTrue(!CollectionUtils.isEmpty(lstIdPersona),
				"La lista con i riferimenti delle persone da assegnare non è stata valorizzata");

		Integer idProtocollo = Utils.getIdFromProtocollo(protocollo);
		Integer annoProtocollo = Utils.getAnnoFromProtocollo(protocollo);

		Documento documentoToAssign = documentiDao.getDocumento(idProtocollo, annoProtocollo);
		Assert.notNull(documentoToAssign,
				String.format("Il protocollo da associare '%s' non esiste all'interno del sistema", protocollo));

		for (Integer idPersona : lstIdPersona) {
			Persona persona = personeDao.getPersona(idPersona);
			Assert.notNull(persona, String.format("La persona da associare %s non esiste ", idPersona));
		}

		documentiPersoneDao.associaPersone(idProtocollo, lstIdPersona);
		
		// Ritorna il Documento aggiornato
		DocumentoWithDetails documentoWithDetails = documentiPersoneDao.getPersoneAssociate(idProtocollo,
				annoProtocollo);
		return documentoWithDetails;
	}

	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public DocumentoWithDetails disassociaPersone(String protocollo, List<Integer> lstIdPersona) throws Exception {

		Assert.hasText(protocollo, String.format("Il campo '%s' non è stato valorizzato ", "protocollo"));
		Assert.isTrue(!CollectionUtils.isEmpty(lstIdPersona),
				"La lista con i riferimenti delle persone da disassegnare non è stata valorizzata");

		Integer idProtocollo = Utils.getIdFromProtocollo(protocollo);
		Integer annoProtocollo = Utils.getAnnoFromProtocollo(protocollo);

		DocumentoWithDetails documentoWithDetails = documentiPersoneDao.getPersoneAssociate(idProtocollo,
				annoProtocollo);
		Assert.notNull(documentoWithDetails,
				String.format("Il protocollo da disassociare '%s' non esiste all'interno del sistema", protocollo));

		Assert.isTrue(!CollectionUtils.isEmpty(documentoWithDetails.getPersone()),
				String.format("Il protocollo da disassociare '%s' non ha nessuna persona associata", protocollo));

		for (Integer idPersonaToFind : lstIdPersona) {

			Persona personaResult = documentoWithDetails.getPersone().stream()
					.filter(persona -> idPersonaToFind.equals(persona.getIdPersona())).findAny().orElse(null);

			Assert.notNull(personaResult, String.format(
					"La persona da disassociare '%s' non è associata al protocollo", idPersonaToFind));
		}

		documentiPersoneDao.disassociaPersone(idProtocollo, lstIdPersona);

		// Ritorna il Documento aggiornato
		documentoWithDetails = documentiPersoneDao.getPersoneAssociate(idProtocollo, annoProtocollo);
		return documentoWithDetails;
	}

	@Override
	public DocumentoWithDetails getPersoneAssociate(String protocollo) throws Exception {
		Assert.hasText(protocollo, String.format("Il campo '%s' non è stato valorizzato ", "protocollo"));

		Integer idProtocollo = Utils.getIdFromProtocollo(protocollo);
		Integer annoProtocollo = Utils.getAnnoFromProtocollo(protocollo);

		DocumentoWithDetails documentoWithDetails = documentiPersoneDao.getPersoneAssociate(idProtocollo,
				annoProtocollo);

		return documentoWithDetails;
	}

}

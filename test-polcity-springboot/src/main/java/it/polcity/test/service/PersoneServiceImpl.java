package it.polcity.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import it.polcity.test.dao.DocumentiDao;
import it.polcity.test.dao.DocumentiPersoneDao;
import it.polcity.test.dao.PersoneDao;
import it.polcity.test.model.Documento;
import it.polcity.test.model.Persona;
import it.polcity.test.model.Persona.Sesso;
import it.polcity.test.model.PersonaWithDetails;
import it.polcity.test.utils.Utils;

/**
 * @author marco.marini
 *	Implementazione di {@link PersoneService}
 */	
@Component("personeService")
@Transactional
public class PersoneServiceImpl implements PersoneService {

	@Autowired
	DocumentiDao documentiDao;

	@Autowired
	PersoneDao personeDao;

	@Autowired
	DocumentiPersoneDao documentiPersoneDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public Persona insertPersona(String nome, String cognome, String dataNascita, String sesso, String luogoNascita)
			throws Exception {

		Assert.hasText(nome, String.format("Il campo '%s' non è stato valorizzato ", "nome"));
		Assert.hasText(cognome, String.format("Il campo '%s' non è stato valorizzato ", "cognome"));
		Assert.hasText(dataNascita, String.format("Il campo '%s' non è stato valorizzato ", "dataNascita"));
		Assert.hasText(sesso, String.format("Il campo '%s' non è stato valorizzato ", "sesso"));
		Assert.hasText(luogoNascita, String.format("Il campo '%s' non è stato valorizzato ", "luogoNascita"));

		Date dDataNascita = Utils.getDate(dataNascita);
		Sesso enumSesso = Utils.getSesso(sesso);

		Persona persona = personeDao.insertPersona(nome, cognome, dDataNascita, enumSesso, luogoNascita);
		return persona;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public Persona updatePersona(Integer idPersona, String nome, String cognome, String dataNascita, String sesso,
			String luogoNascita) throws Exception {

		Assert.notNull(idPersona, String.format("Il campo '%s' non è stato valorizzato ", "idPersona"));
		Assert.hasText(nome, String.format("Il campo '%s' non è stato valorizzato ", "nome"));
		Assert.hasText(cognome, String.format("Il campo '%s' non è stato valorizzato ", "cognome"));
		Assert.hasText(dataNascita, String.format("Il campo '%s' non è stato valorizzato ", "dataNascita"));
		Assert.hasText(sesso, String.format("Il campo '%s' non è stato valorizzato ", "sesso"));
		Assert.hasText(luogoNascita, String.format("Il campo '%s' non è stato valorizzato ", "luogoNascita"));

		Date dDataNascita = Utils.getDate(dataNascita);
		Sesso enumSesso = Utils.getSesso(sesso);

		Persona personaToUpdate = personeDao.getPersona(idPersona);
		Assert.notNull(personaToUpdate, String.format("La persona '%s' non esiste all'interno del sistema", idPersona));

		personeDao.updatePersona(idPersona, nome, cognome, dDataNascita, enumSesso, luogoNascita);

		// Ritorna il record aggiornato
		Persona persona = personeDao.getPersona(idPersona);

		return persona;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public void deletePersona(Integer idPersona) throws Exception {

		Assert.notNull(idPersona, String.format("Il campo '%s' non è stato valorizzato ", "idPersona"));

		Persona personaToDelete = personeDao.getPersona(idPersona);
		Assert.notNull(personaToDelete,
				String.format("La persona da eliminare '%s' non esiste all'interno del sistema", idPersona));

		personeDao.deletePersona(idPersona);

	}

	@Override
	public Persona getPersona(Integer idPersona) throws Exception {

		Assert.notNull(idPersona, String.format("Il campo '%s' non è stato valorizzato ", "idPersona"));

		Persona persona = personeDao.getPersona(idPersona);
		return persona;
	}

	@Override
	public List<Persona> listPersona(String nome, String cognome, String dataNascita, String sesso, String luogoNascita)
			throws Exception {

		Date dDataNascita = StringUtils.hasText(dataNascita) ? Utils.getDate(dataNascita) : null;
		Sesso enumSesso = StringUtils.hasText(sesso) ? Utils.getSesso(sesso) : null;

		List<Persona> lstPersona = personeDao.listPersona(nome, cognome, dDataNascita, enumSesso, luogoNascita);
		return lstPersona;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public PersonaWithDetails associaDocumenti(Integer idPersona, List<String> lstProtocollo) throws Exception {

		Assert.notNull(idPersona, String.format("Il campo '%s' non è stato valorizzato ", "idPersona"));
		Assert.isTrue(!CollectionUtils.isEmpty(lstProtocollo),
				"La lista con i riferimenti dei protocolli da assegnare non è stata valorizzata");

		Persona personaToAssociate = personeDao.getPersona(idPersona);
		Assert.notNull(personaToAssociate,
				String.format("La persona da associare '%s' non esiste all'interno del sistema", idPersona));

		List<Integer> lstIdProtocollo = new ArrayList<Integer>();

		for (String protocollo : lstProtocollo) {
			Integer idProtocollo = Utils.getIdFromProtocollo(protocollo);
			Integer annoProtocollo = Utils.getAnnoFromProtocollo(protocollo);

			Documento documento = documentiDao.getDocumento(idProtocollo, annoProtocollo);
			Assert.notNull(documento, String.format("Il documento da associare %s non esiste ", protocollo));
			lstIdProtocollo.add(idProtocollo);
		}

		documentiPersoneDao.associaDocumenti(idPersona, lstIdProtocollo);

		// Ritorna il record aggiornato
		PersonaWithDetails personaWithDetails = documentiPersoneDao.getDocumentiAssociati(idPersona);
		return personaWithDetails;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
	@Override
	public PersonaWithDetails disassociaDocumenti(Integer idPersona, List<String> lstProtocollo) throws Exception {

		Assert.notNull(idPersona, String.format("Il campo '%s' non è stato valorizzato ", "idPersona"));
		Assert.isTrue(!CollectionUtils.isEmpty(lstProtocollo),
				"La lista con i riferimenti dei protocolli da disassegnare non è stata valorizzata");

		PersonaWithDetails personaToDisassociate = documentiPersoneDao.getDocumentiAssociati(idPersona);
		Assert.notNull(personaToDisassociate,
				String.format("La persona da disassociare '%s' non esiste all'interno del sistema", idPersona));

		List<Integer> lstIdProtocollo = new ArrayList<Integer>();
		for (String protocollo : lstProtocollo) {

			Integer idProtocollo = Utils.getIdFromProtocollo(protocollo);
			Integer annoProtocollo = Utils.getAnnoFromProtocollo(protocollo);

			Documento documentoResult = personaToDisassociate.getDocumenti().stream()
					.filter(documento -> idProtocollo.equals(documento.getIdProtocollo())
							&& annoProtocollo.equals(documento.getAnnoProtocollo()))
					.findAny().orElse(null);

			Assert.notNull(documentoResult,
					String.format("Il documento da disassociare '%s' non è associato alla persona", protocollo));

			lstIdProtocollo.add(idProtocollo);
		}

		documentiPersoneDao.disassociaDocumenti(idPersona, lstIdProtocollo);

		// Ritorna il record aggiornato
		personaToDisassociate = documentiPersoneDao.getDocumentiAssociati(idPersona);
		return personaToDisassociate;
	}

	@Override
	public PersonaWithDetails getDocumentiAssociati(Integer idPersona) throws Exception {

		Assert.notNull(idPersona, String.format("Il campo '%s' non è stato valorizzato ", "idPersona"));

		PersonaWithDetails personaWithDetails = documentiPersoneDao.getDocumentiAssociati(idPersona);
		return personaWithDetails;
	}

}

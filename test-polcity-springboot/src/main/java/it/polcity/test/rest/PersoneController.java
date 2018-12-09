package it.polcity.test.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.polcity.test.exception.TestPolcityException;
import it.polcity.test.model.Persona;
import it.polcity.test.model.PersonaWithDetails;
import it.polcity.test.service.PersoneService;

/**
 * @author marco.marini
 *	Controller per i servizi associati alle Persone
 */
@RestController
@RequestMapping("persone")
public class PersoneController {

	@Autowired
	PersoneService personeService;

	@RequestMapping(value = "/insert", method = RequestMethod.PUT)
	public Persona insertPersona(@RequestParam(value = "nome", required = true) String nome,
			@RequestParam(value = "cognome", required = true) String cognome,
			@RequestParam(value = "dataNascita", required = true) String dataNascita,
			@RequestParam(value = "sesso", required = true) String sesso,
			@RequestParam(value = "luogoNascita", required = true) String luogoNascita) throws TestPolcityException {

		Persona persona = null;

		try {
			persona = personeService.insertPersona(nome, cognome, dataNascita, sesso, luogoNascita);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}
		return persona;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Persona updatePersona(@RequestParam(value = "id", required = true) Integer idPersona,
			@RequestParam(value = "nome", required = true) String nome,
			@RequestParam(value = "cognome", required = true) String cognome,
			@RequestParam(value = "dataNascita", required = true) String dataNascita,
			@RequestParam(value = "sesso", required = true) String sesso,
			@RequestParam(value = "luogoNascita", required = true) String luogoNascita) throws TestPolcityException {

		Persona persona = null;

		try {
			persona = personeService.updatePersona(idPersona, nome, cognome, dataNascita, sesso, luogoNascita);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return persona;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deletePersona(@RequestParam(value = "id", required = true) Integer idPersona)
			throws TestPolcityException {

		try {
			personeService.deletePersona(idPersona);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Persona getPersona(@RequestParam(value = "id", required = true) Integer idPersona)
			throws TestPolcityException {

		Persona persona = null;

		try {
			persona = personeService.getPersona(idPersona);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return persona;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Persona> listPersona(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cognome", required = false) String cognome,
			@RequestParam(value = "dataNascita", required = false) String dataNascita,
			@RequestParam(value = "sesso", required = false) String sesso,
			@RequestParam(value = "luogoNascita", required = false) String luogoNascita) throws TestPolcityException {

		List<Persona> lstResults = new ArrayList<Persona>();
		try {
			lstResults = personeService.listPersona(nome, cognome, dataNascita, sesso, luogoNascita);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return lstResults;

	}

	@RequestMapping(value = "/associa", method = RequestMethod.POST)
	public PersonaWithDetails associaDocumenti(@RequestParam(value = "id", required = true) Integer idPersona,
			@RequestParam(value = "protocolli", required = true) List<String> lstProtocolli)
			throws TestPolcityException {

		PersonaWithDetails personaWithDetails = null;

		try {
			personaWithDetails = personeService.associaDocumenti(idPersona, lstProtocolli);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return personaWithDetails;
	}

	@RequestMapping(value = "/disassocia", method = RequestMethod.POST)
	public PersonaWithDetails disassociaDocumenti(@RequestParam(value = "id", required = true) Integer idPersona,
			@RequestParam(value = "protocolli", required = true) List<String> lstProtocolli)
			throws TestPolcityException {

		PersonaWithDetails personaWithDetails = null;

		try {
			personaWithDetails = personeService.disassociaDocumenti(idPersona, lstProtocolli);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return personaWithDetails;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public PersonaWithDetails getDetails(@RequestParam(value = "id", required = true) Integer idPersona)
			throws TestPolcityException {

		PersonaWithDetails personDetails = null;
		try {
			personDetails = personeService.getDocumentiAssociati(idPersona);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return personDetails;

	}

}

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
import it.polcity.test.model.Documento;
import it.polcity.test.model.DocumentoWithDetails;
import it.polcity.test.service.DocumentiService;

/**
 * @author marco.marini
 *	Controller per i servizi associati ai Documenti
 */
@RestController
@RequestMapping("documenti")
public class DocumentiController {

	@Autowired
	DocumentiService documentiService;

	@RequestMapping(value = "/insert", method = RequestMethod.PUT)
	public Documento insertDocumento(@RequestParam(value = "testo", required = true) String testo)
			throws TestPolcityException {

		Documento documento = null;
		try {
			documento = documentiService.insertDocumento(testo);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}
		return documento;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Documento updateDocumento(@RequestParam(value = "protocollo", required = true) String protocollo,
			@RequestParam(value = "testo", required = true) String testo) throws TestPolcityException {

		Documento documento = null;

		try {
			documento = documentiService.updateDocumento(protocollo, testo);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return documento;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteDocumento(@RequestParam(value = "protocollo", required = true) String protocollo)
			throws TestPolcityException {

		try {
			documentiService.deleteDocumento(protocollo);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Documento getDocumento(@RequestParam(value = "protocollo", required = true) String protocollo)
			throws TestPolcityException {

		Documento documento = null;

		try {
			documento = documentiService.getDocumento(protocollo);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return documento;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Documento> listDocumento(
			@RequestParam(value = "annoProtocollo", required = false) Integer annoProtocollo,
			@RequestParam(value = "testo", required = false) String testo) throws TestPolcityException {

		List<Documento> lstResults = new ArrayList<Documento>();
		try {
			lstResults = documentiService.listDocumento(annoProtocollo, testo);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return lstResults;

	}

	@RequestMapping(value = "/associa", method = RequestMethod.POST)
	public DocumentoWithDetails associaPersone(@RequestParam(value = "protocollo", required = true) String protocollo,
			@RequestParam(value = "persone", required = true) List<Integer> lstIdPersona) throws TestPolcityException {

		DocumentoWithDetails documentoWithDetails = null;

		try {
			documentoWithDetails = documentiService.associaPersone(protocollo, lstIdPersona);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return documentoWithDetails;
	}

	@RequestMapping(value = "/disassocia", method = RequestMethod.POST)
	public DocumentoWithDetails disassociaPersone(
			@RequestParam(value = "protocollo", required = true) String protocollo,
			@RequestParam(value = "persone", required = true) List<Integer> lstIdPersona) throws TestPolcityException {

		DocumentoWithDetails documentoWithDetails = null;

		try {
			documentoWithDetails = documentiService.disassociaPersone(protocollo, lstIdPersona);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return documentoWithDetails;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public DocumentoWithDetails getDetails(@RequestParam(value = "protocollo", required = true) String protocollo)
			throws TestPolcityException {

		DocumentoWithDetails documentoWithDetails = null;

		try {
			documentoWithDetails = documentiService.getPersoneAssociate(protocollo);
		} catch (Exception e) {
			throw new TestPolcityException(e.getMessage(), e);
		}

		return documentoWithDetails;

	}

}

package it.polcity.test.model;

import it.polcity.test.utils.Constants;

/**
 * @author marco.marini
 *	Model per Documento
 */
public class Documento {

	private Integer idProtocollo;
	private Integer annoProtocollo;
	private String testo;

	public Documento() {
		super();
	}
	
	public Documento(Integer idProtocollo, Integer annoProtocollo, String testo) {
		super();
		this.idProtocollo = idProtocollo;
		this.annoProtocollo = annoProtocollo;
		this.testo = testo;
	}

	public Integer getIdProtocollo() {
		return idProtocollo;
	}

	public void setIdProtocollo(Integer idProtocollo) {
		this.idProtocollo = idProtocollo;
	}

	public Integer getAnnoProtocollo() {
		return annoProtocollo;
	}

	public void setAnnoProtocollo(Integer annoProtocollo) {
		this.annoProtocollo = annoProtocollo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getProtocollo() {
		if (idProtocollo == null) {
			return "";
		}

		return "" + idProtocollo + Constants.PROTOCOLLO_SEPARATOR + annoProtocollo;
	}

}

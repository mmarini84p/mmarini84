package it.polcity.test.model;

import java.util.Date;

import it.polcity.test.utils.Utils;

/**
 * @author marco.marini
 *	Model per Persona
 */
public class Persona {

	private Integer idPersona;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private Sesso sesso;
	private String luogoNascita;

	public Persona() {
		super();
	}

	public Persona(Integer idPersona, String nome, String cognome, Date dataNascita, Sesso sesso, String luogoNascita) {
		super();
		this.idPersona = idPersona;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.luogoNascita = luogoNascita;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}
	
	public String getDataNascitaString() {
		return Utils.formatDate(dataNascita);
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public static enum Sesso {
		M, F;

		public static Sesso getSesso(String sesso) {

			if ("M".equalsIgnoreCase(sesso)) {
				return M;
			} else if ("F".equalsIgnoreCase(sesso)) {
				return F;
			}

			return null;

		}
	}

}

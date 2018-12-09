package it.polcity.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import it.polcity.test.model.Documento;
import it.polcity.test.model.DocumentoWithDetails;
import it.polcity.test.model.Persona;
import it.polcity.test.model.PersonaWithDetails;

/**
 * @author marco.marini
 *	Mapper per la tabella DocumentiPersone
 */
@Mapper
public interface DocumentiPersoneMapper {

	@Insert("<script>" + "<foreach collection=\"lstProtocollo\" item=\"idProtocollo\" index=\"index\" >"
			+ "INSERT INTO documenti_persone(id_protocollo,id_persona) " + " VALUES (#{idProtocollo},#{idPersona});"
			+ "</foreach>" + "</script>")
	public void associaDocumenti(@Param("idPersona") Integer idPersona,
			@Param("lstProtocollo") List<Integer> lstProtocollo);

	@Delete("<script>" + "<foreach collection=\"lstProtocollo\" item=\"idProtocollo\" index=\"index\" >"
			+ "DELETE FROM documenti_persone WHERE id_persona =#{idPersona} AND id_protocollo =#{idProtocollo};"
			+ "</foreach>" + "</script>")
	public void disassociaDocumenti(@Param("idPersona") Integer idPersona,
			@Param("lstProtocollo") List<Integer> lstProtocollo);

	@Insert("<script>" + "<foreach collection=\"lstPersona\" item=\"idPersona\" index=\"index\" >"
			+ "INSERT INTO documenti_persone(id_protocollo,id_persona) " + " VALUES (#{idProtocollo},#{idPersona});"
			+ "</foreach>" + "</script>")
	public void associaPersone(@Param("idProtocollo") Integer idProtocollo,
			@Param("lstPersona") List<Integer> lstPersona);

	@Delete("<script>" + "<foreach collection=\"lstPersona\" item=\"idPersona\" index=\"index\" >"
			+ "DELETE FROM documenti_persone WHERE id_persona =#{idPersona} AND id_protocollo =#{idProtocollo};"
			+ "</foreach>" + "</script>")
	public void disassociaPersone(@Param("idProtocollo") Integer idProtocollo,
			@Param("lstPersona") List<Integer> lstPersona);

	@Results(id = "PersonaDetailsResult", value = {
			@Result(property = "idPersona", column = "id_persona", id = true),
			@Result(property = "nome", column = "nome"), @Result(property = "cognome", column = "cognome"),
			@Result(property = "dataNascita", column = "data_nascita"),
			@Result(property = "sesso", column = "sesso"), @Result(property = "luogoNascita", column = "luogo_nascita"),
			@Result(property = "documenti", javaType = List.class, column = "id_persona", many = @Many(select = "getDocumentiByPersona")) })
	@Select("select distinct p.id_persona, p.nome, p.cognome, p.data_nascita, p.sesso, p.luogo_nascita"
			+ " from persone as p left join documenti_persone dp" + "	on (p.id_persona = dp.id_persona) "
			+ " where p.id_persona = #{idPersona}")
	public PersonaWithDetails getDocumentiAssociati(@Param("idPersona") Integer idPersona);

	@Results(id = "DocumentoResult", value = { @Result(property = "idProtocollo", column = "id_protocollo", id = true),
			@Result(property = "annoProtocollo", column = "anno_protocollo"),
			@Result(property = "testo", column = "testo") })
	@Select("SELECT distinct d.id_protocollo, d.anno_protocollo, d.testo "
			+ " from documenti_persone as dp left join documenti as d on (dp.id_protocollo = d.id_protocollo) "
			+ " where dp.id_persona = #{idPersona}")
	public List<Documento> getDocumentiByPersona(@Param("idPersona") Integer idPersona);

	@Results(id = "DocumentoDetailsResult", value = {
			@Result(property = "idProtocollo", column = "id_protocollo", id = true),
			@Result(property = "annoProtocollo", column = "anno_Protocollo"),
			@Result(property = "testo", column = "testo"),
			@Result(property = "persone", javaType = List.class, column = "id_protocollo", many = @Many(select = "getPersoneByDocumento")) })
	@Select("select distinct d.id_protocollo, d.anno_protocollo, d.testo"
			+ " from documenti as d left join documenti_persone dp" + "	on (d.id_protocollo = dp.id_protocollo) "
			+ " where d.id_protocollo = #{idProtocollo} AND d.anno_protocollo = #{annoProtocollo}")
	public DocumentoWithDetails getPersoneAssociate(@Param("idProtocollo") Integer idProtocollo,
			@Param("annoProtocollo") Integer annoProtocollo);

	@Results(id = "PersonaResult", value = { 
			@Result(property = "idPersona", column = "id_persona", id = true),
			@Result(property = "nome", column = "nome"), @Result(property = "cognome", column = "cognome"),
			@Result(property = "dataNascita", column = "data_nascita"),
			@Result(property = "sesso", column = "sesso"), @Result(property = "luogoNascita", column = "luogo_nascita")})
	@Select("SELECT distinct p.id_persona, p.nome, p.cognome, p.data_nascita, p.sesso, p.luogo_nascita "
			+ " from documenti_persone as dp left join persone as p on (dp.id_persona = p.id_persona) "
			+ " where dp.id_protocollo = #{idProtocollo}")
	public List<Persona> getPersoneByDocumento(@Param("idProtocollo") Integer idProtocollo);

}

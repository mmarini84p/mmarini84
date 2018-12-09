package it.polcity.test.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import it.polcity.test.model.Persona;

/**
 * @author marco.marini
 *	Mapper per la tabella Persone
 */
@Mapper
public interface PersoneMapper {

	@Insert("INSERT INTO persone(nome, cognome, data_nascita, sesso, luogo_nascita) VALUES"
			+ "(#{nome}, #{cognome}, #{dataNascita}, #{sesso}, #{luogoNascita})")
	@Options(useGeneratedKeys = true, keyProperty = "idPersona", keyColumn = "id_persona")
	public int insertPersona(Persona persona);
	
	@Update("<script>" + "UPDATE persone <set>" + "<if test=\"nome!=null \"> nome = #{nome},</if>"
			+ "<if test=\"cognome!=null \"> cognome = #{cognome}, </if>"
			+ "<if test=\"dataNascita!=null \"> data_nascita = #{dataNascita}, </if>"
			+ "<if test=\"sesso!=null \"> sesso = #{sesso}, </if>"
			+ "<if test=\"luogoNascita!=null \"> luogo_nascita = #{luogoNascita}, </if>"
			+ "</set> WHERE id_persona =#{idPersona}" + "</script>")
	@Options(useGeneratedKeys = true, keyProperty = "idPersona", keyColumn = "id_persona")
	public int updatePersona(@Param("idPersona") Integer idPersona, @Param("nome") String nome,
			@Param("cognome") String cognome, @Param("dataNascita") Date dataNascita, @Param("sesso") String sesso,
			@Param("luogoNascita") String luogoNascita);

	@Delete("DELETE FROM persone WHERE id_persona =#{idPersona}")
	public void deletePersona(Integer idPersona);

	@ResultMap("PersonaResult")
	@Select("SELECT id_persona, nome, cognome, data_nascita, sesso, luogo_nascita from persone WHERE id_persona = #{idPersona}")
	public Persona getPersonaById(@Param("idPersona") Integer idPersona);

	@Results(id = "PersonaResult", value = { @Result(property = "idPersona", column = "id_persona", id = true),
			@Result(property = "nome", column = "nome"), @Result(property = "cognome", column = "cognome"),
			@Result(property = "dataNascita", column = "data_nascita"), @Result(property = "sesso", column = "sesso"),
			@Result(property = "luogoNascita", column = "luogo_nascita") })
	@Select("<script>" + "SELECT id_persona, nome, cognome, data_nascita, sesso, luogo_nascita from persone" + "<where>"
			+ "true " + "<if test=\"nome!=null and nome!=''\"> AND UPPER(nome) like '%' || UPPER(#{nome}) || '%' </if>"
			+ "<if test=\"cognome!=null and cognome!=''\"> AND UPPER(cognome) like '%' || UPPER(#{cognome}) || '%' </if>"
			+ "<if test=\"dataNascita!=null \"> AND data_nascita =  #{dataNascita} </if>"
			+ "<if test=\"sesso!=null and sesso!=''\"> AND UPPER(sesso) = UPPER(#{sesso}) </if>"
			+ "<if test=\"luogoNascita!=null and luogoNascita!=''\"> AND UPPER(luogo_nascita) like '%' || UPPER(#{luogoNascita}) || '%' </if>"
			+ "</where>" + "order by id_persona desc" + "</script>")
	public List<Persona> getPersona(@Param("nome") String nome, @Param("cognome") String cognome,
			@Param("dataNascita") Date dataNascita, @Param("sesso") String sesso,
			@Param("luogoNascita") String luogoNascita);

}

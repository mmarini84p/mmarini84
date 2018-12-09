package it.polcity.test.mapper;

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

import it.polcity.test.model.Documento;

/**
 * @author marco.marini
 *	Mapper per la tabella Documenti
 */
@Mapper
public interface DocumentiMapper {

	@Insert("INSERT INTO documenti(anno_protocollo, testo) VALUES" + "(#{annoProtocollo}, #{testo})")
	@Options(useGeneratedKeys = true, keyProperty = "idProtocollo", keyColumn = "id_protocollo")
	public int insertDocumento(Documento documento);

	@Update("UPDATE documenti SET testo=#{testo} WHERE id_protocollo =#{idProtocollo}")
	@Options(useGeneratedKeys = true, keyProperty = "idProtocollo", keyColumn = "id_protocollo")
	public int updateDocumento(@Param("idProtocollo") Integer idProtocollo, @Param("testo") String testo);

	@Delete("DELETE FROM documenti WHERE id_protocollo =#{idProtocollo}")
	public void deleteDocumento(Integer idProtocollo);

	@ResultMap("DocumentoResult")
	@Select("SELECT id_protocollo, anno_protocollo, testo from documenti WHERE id_protocollo = #{idProtocollo}")
	public Documento getDocumentoByIdProtocollo(@Param("idProtocollo") Integer idProtocollo);

	@ResultMap("DocumentoResult")
	@Select("SELECT id_protocollo, anno_protocollo, testo from documenti " + " WHERE id_protocollo = #{idProtocollo}"
			+ " AND anno_protocollo = #{annoProtocollo}")
	public Documento getDocumentoByProtocollo(@Param("idProtocollo") Integer idProtocollo,
			@Param("annoProtocollo") Integer annoProtocollo);
	
	@Results(id = "DocumentoResult", value = { @Result(property = "idProtocollo", column = "id_protocollo", id = true),
			@Result(property = "annoProtocollo", column = "anno_protocollo"),
			@Result(property = "testo", column = "testo") })
	@Select("<script>" + "SELECT id_protocollo, anno_protocollo, testo from documenti " + "<where>" + "true "
			+ "<if test=\"annoProtocollo!=null and annoProtocollo!=''\"> AND anno_protocollo = #{annoProtocollo} </if>"
			+ "<if test=\"testo!=null and testo!=''\"> AND UPPER(testo) like '%' || UPPER(#{testo}) || '%' </if>" + "</where>"
			+ "order by id_protocollo desc" + "</script>")
	public List<Documento> getDocumento(@Param("annoProtocollo") Integer annoProtocollo, @Param("testo") String testo);
	
	

}

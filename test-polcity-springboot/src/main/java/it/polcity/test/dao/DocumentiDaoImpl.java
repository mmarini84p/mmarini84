package it.polcity.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.polcity.test.exception.TestPolcityException;
import it.polcity.test.mapper.DocumentiMapper;
import it.polcity.test.model.Documento;

/**
 * @author marco.marini
 *	Implementazione di {@link DocumentiDao}
 */
@Component("documentiDao")
public class DocumentiDaoImpl implements DocumentiDao {

	@Autowired
	SqlSessionManager sqlSessionManager;

	@Override
	public Documento insertDocumento(String testo) throws Exception {

		Documento documento = null;
		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			DocumentiMapper mapper = sqlSessionManager.getMapper(DocumentiMapper.class);
			documento = new Documento(null, Calendar.getInstance().get(Calendar.YEAR), testo);
			mapper.insertDocumento(documento);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t.getMessage(), t);
		} finally {
			sqlSessionManager.close();
		}

		return documento;
	}

	@Override
	public void updateDocumento(Integer idProtocollo, String testo) throws TestPolcityException {

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			DocumentiMapper mapper = sqlSessionManager.getMapper(DocumentiMapper.class);
			mapper.updateDocumento(idProtocollo, testo);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t.getMessage(), t);
		} finally {
			sqlSessionManager.close();
		}

	}

	@Override
	public void deleteDocumento(Integer idProtocollo) throws TestPolcityException {
		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			DocumentiMapper mapper = sqlSessionManager.getMapper(DocumentiMapper.class);
			mapper.deleteDocumento(idProtocollo);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t);
		} finally {
			sqlSessionManager.close();
		}

	}

	@Override
	public Documento getDocumento(Integer idDocumento) {
		Documento documento = null;
		try (SqlSession sqlSession = sqlSessionManager.openSession(TransactionIsolationLevel.READ_COMMITTED)) {

			DocumentiMapper mapper = sqlSession.getMapper(DocumentiMapper.class);
			documento = mapper.getDocumentoByIdProtocollo(idDocumento);
		} catch (Exception e) {
			throw e;
		}

		return documento;
	}

	@Override
	public Documento getDocumento(Integer idProtocollo, Integer annoProtocollo) throws Exception {

		Documento documento = null;
		try (SqlSession sqlSession = sqlSessionManager.openSession(TransactionIsolationLevel.READ_COMMITTED)) {

			DocumentiMapper mapper = sqlSession.getMapper(DocumentiMapper.class);
			documento = mapper.getDocumentoByProtocollo(idProtocollo, annoProtocollo);
		} catch (Exception e) {
			throw e;
		}

		return documento;
	}

	@Override
	public List<Documento> listDocumento(Integer anno, String testo) {
		List<Documento> lstResults = new ArrayList<Documento>();
		try (SqlSession sqlSession = sqlSessionManager.openSession(TransactionIsolationLevel.READ_COMMITTED)) {

			DocumentiMapper mapper = sqlSession.getMapper(DocumentiMapper.class);
			List<Documento> lstMapperResults = mapper.getDocumento(anno, testo);
			lstResults.addAll(lstMapperResults);
		} catch (Exception e) {
			throw e;
		}

		return lstResults;
	}

}

package it.polcity.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.polcity.test.exception.TestPolcityException;
import it.polcity.test.mapper.DocumentiPersoneMapper;
import it.polcity.test.model.DocumentoWithDetails;
import it.polcity.test.model.PersonaWithDetails;

/**
 * @author marco.marini
 *	Implementazione di {@link DocumentiPersoneDao}
 */
@Component("documentiPersoneDao")
public class DocumentiPersoneDaoImpl implements DocumentiPersoneDao {

	@Autowired
	SqlSessionManager sqlSessionManager;

	@Override
	public void associaDocumenti(Integer idPersona, List<Integer> lstIdProtocollo) throws TestPolcityException {

//		sqlSessionManager.startManagedSession(ExecutorType.BATCH, TransactionIsolationLevel.READ_COMMITTED);
		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			DocumentiPersoneMapper mapper = sqlSessionManager.getMapper(DocumentiPersoneMapper.class);
			mapper.associaDocumenti(idPersona, lstIdProtocollo);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t);
		} finally {
			sqlSessionManager.close();
		}

	}

	@Override
	public void disassociaDocumenti(Integer idPersona, List<Integer> lstIdProtocollo) throws TestPolcityException {

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			DocumentiPersoneMapper mapper = sqlSessionManager.getMapper(DocumentiPersoneMapper.class);
			mapper.disassociaDocumenti(idPersona, lstIdProtocollo);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t);
		} finally {
			sqlSessionManager.close();
		}

	}

	@Override
	public PersonaWithDetails getDocumentiAssociati(Integer idPersona) throws TestPolcityException {
		PersonaWithDetails personaDetails = null;

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			DocumentiPersoneMapper mapper = sqlSessionManager.getMapper(DocumentiPersoneMapper.class);
			personaDetails = mapper.getDocumentiAssociati(idPersona);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t);
		} finally {
			sqlSessionManager.close();
		}

		return personaDetails;

	}

	@Override
	public void associaPersone(Integer idProtocollo, List<Integer> lstIdPersona) throws TestPolcityException {

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);
		try {

			DocumentiPersoneMapper mapper = sqlSessionManager.getMapper(DocumentiPersoneMapper.class);
			mapper.associaPersone(idProtocollo, lstIdPersona);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t);
		} finally {
		   sqlSessionManager.close();
		}

	}

	@Override
	public void disassociaPersone(Integer idProtocollo, List<Integer> lstIdPersona) throws TestPolcityException {

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);
		try {

			DocumentiPersoneMapper mapper = sqlSessionManager.getMapper(DocumentiPersoneMapper.class);
			mapper.disassociaPersone(idProtocollo, lstIdPersona);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t);
		} finally {
			sqlSessionManager.close();
		}

	}

	@Override
	public DocumentoWithDetails getPersoneAssociate(Integer idProtocollo, Integer annoProtocollo)
			throws TestPolcityException {

		DocumentoWithDetails documentoDetails = null;

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			DocumentiPersoneMapper mapper = sqlSessionManager.getMapper(DocumentiPersoneMapper.class);
			documentoDetails = mapper.getPersoneAssociate(idProtocollo, annoProtocollo);

		} catch (Throwable t) {
			throw new TestPolcityException(t);
		} finally {
			sqlSessionManager.close();
		}

		return documentoDetails;
	}

}

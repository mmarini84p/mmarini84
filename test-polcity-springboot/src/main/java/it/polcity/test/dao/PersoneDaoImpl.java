package it.polcity.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.polcity.test.exception.TestPolcityException;
import it.polcity.test.mapper.PersoneMapper;
import it.polcity.test.model.Persona;
import it.polcity.test.model.Persona.Sesso;

/**
 * @author marco.marini
 *	Implementazione di {@link PersoneDao}
 */
@Component("personeDao")
public class PersoneDaoImpl implements PersoneDao {

	@Autowired
	SqlSessionManager sqlSessionManager;

	public Persona insertPersona(String nome, String cognome, Date dataNascita, Sesso sesso, String luogoNascita)
			throws Exception {

		Persona persona = null;

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			PersoneMapper mapper = sqlSessionManager.getMapper(PersoneMapper.class);
			persona = new Persona(null, nome, cognome, dataNascita, sesso, luogoNascita);

			mapper.insertPersona(persona);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t.getMessage(), t);
		} finally {
			sqlSessionManager.close();
		}

		return persona;
	}

	@Override
	public void updatePersona(Integer idPersona, String nome, String cognome, Date dataNascita, Sesso sesso,
			String luogoNascita) throws Exception {

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			PersoneMapper mapper = sqlSessionManager.getMapper(PersoneMapper.class);
			mapper.updatePersona(idPersona, nome, cognome, dataNascita, sesso != null ? sesso.toString() : null,
					luogoNascita);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t.getMessage(), t);
		} finally {
			sqlSessionManager.close();
		}
	}

	@Override
	public void deletePersona(Integer idPersona) throws Exception {

		sqlSessionManager.startManagedSession(TransactionIsolationLevel.READ_COMMITTED);

		try {

			PersoneMapper mapper = sqlSessionManager.getMapper(PersoneMapper.class);
			mapper.deletePersona(idPersona);

			sqlSessionManager.commit();
		} catch (Throwable t) {
			sqlSessionManager.rollback();
			throw new TestPolcityException(t.getMessage(), t);
		} finally {
			sqlSessionManager.close();
		}

	}

	@Override
	public Persona getPersona(Integer idPersona) throws Exception {

		Persona persona = null;
		try (SqlSession sqlSession = sqlSessionManager.openSession(TransactionIsolationLevel.READ_COMMITTED)) {

			PersoneMapper mapper = sqlSessionManager.getMapper(PersoneMapper.class);
			persona = mapper.getPersonaById(idPersona);
		} catch (Throwable t) {
			throw new TestPolcityException(t.getMessage(), t);
		}

		return persona;

	}

	@Override
	public List<Persona> listPersona(String nome, String cognome, Date dataNascita, Sesso sesso, String luogoNascita)
			throws Exception {

		List<Persona> lstResults = new ArrayList<Persona>();
		try (SqlSession sqlSession = sqlSessionManager.openSession(TransactionIsolationLevel.READ_COMMITTED)) {

			PersoneMapper mapper = sqlSessionManager.getMapper(PersoneMapper.class);
			List<Persona> lstMapperResults = mapper.getPersona(nome, cognome, dataNascita,
					sesso != null ? sesso.toString() : null, luogoNascita);
			lstResults.addAll(lstMapperResults);
		} catch (Throwable t) {
			throw new TestPolcityException(t.getMessage(), t);
		}
		return lstResults;
	}

}

package org.security.JWTSpring.exp.dao;

import java.util.List;

import org.security.JWTSpring.exp.enums.NativeSqlQueryName;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * @author Abhideep Bakshi
 *
 * @param <BO>
 */
public abstract class GenericDao<BO> {

	abstract protected List<BO> search(NativeSqlQueryName queryNames, SqlParameterSource sqlparameter,
			RowMapper<BO> rowmapper);

}

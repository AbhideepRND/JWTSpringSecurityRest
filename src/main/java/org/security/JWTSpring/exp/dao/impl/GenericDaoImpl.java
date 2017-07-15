package org.security.JWTSpring.exp.dao.impl;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.security.JWTSpring.exp.bo.BoTemplate;
import org.security.JWTSpring.exp.dao.GenericDao;
import org.security.JWTSpring.exp.enums.NativeSqlQueryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * @author Abhideep Bakshi
 *
 *	GenericDaoImpl - This class connect the database for credential validation
 * @param <BO>
 */
public abstract class GenericDaoImpl<BO extends BoTemplate> extends GenericDao<BO> {

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier(value = "sqlProperties")
	private Properties properties;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	protected List<BO> search(NativeSqlQueryName queryNames, SqlParameterSource sqlparameter, RowMapper<BO> rowmapper) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		final String serachQuery = (String) properties.get(queryNames.getPropertyName());
		final List<BO> query = this.namedParameterJdbcTemplate.query(serachQuery, sqlparameter, rowmapper);
		return query;
	}

	public abstract List<BO> search(BO data);

}

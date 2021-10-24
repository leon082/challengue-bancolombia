package com.app.repository.impl;

import com.app.model.Client;
import com.app.model.rowMapper.ClientRowMapper;
import com.app.repository.IRepository;
import com.app.utils.Criteria;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository implements IRepository<Client> {

  private final String QUERY = "select client.id ,client.code ,client.male,client.type,client.location,client.company,client.encrypt, SUM(account.balance) as totalBalance from client inner join account on client.id = account.client_id ";
  private final String GROUP_BY = " group by client.code, client.id,client.male,client.type,client.location,client.company,client.encrypt";
  private final String ORDER_BY = " order by totalBalance desc , client.code desc ";

  private DataSource dataSource;
  private NamedParameterJdbcTemplate jdbcTemplate;

  public ClientRepository(DataSource jdbcTemplate) {
    this.dataSource = jdbcTemplate;
    this.jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
  }

  public List<Client> findByCriteria(Map<String, Object> criteria) {
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    final String finalQuery = QUERY.concat(buildConditionsByCriteria(criteria, parameterSource)).concat(GROUP_BY).concat(ORDER_BY);
    return jdbcTemplate.query(finalQuery, parameterSource, new ClientRowMapper());
  }

  private String buildConditionsByCriteria(Map<String, Object> criteria, MapSqlParameterSource parameterSource) {
    StringBuilder whereConditions = new StringBuilder("");

    if (criteria.containsKey(Criteria.TC)) {
      whereConditions.append(" WHERE ").append(Criteria.TC.getCampo().toUpperCase().concat(" = :").concat(Criteria.TC.getCampo()));
      parameterSource.addValue(Criteria.TC.getCampo(), criteria.get(Criteria.TC));

    }
    if (criteria.containsKey(Criteria.UG)) {
      whereConditions.append(whereConditions.length() == 0 ? " WHERE " : " AND ").append(Criteria.UG.getCampo().toUpperCase().concat(" = :").concat(Criteria.UG.getCampo()));
      parameterSource.addValue(Criteria.UG.getCampo(), criteria.get(Criteria.UG));
    }

    return whereConditions.toString();
  }


}

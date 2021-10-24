package com.app.model.rowMapper;

import com.app.model.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ClientRowMapper implements RowMapper<Client> {

  public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
    Client client = new Client();

    client.setId(rs.getLong("id"));
    client.setCode(rs.getString("code"));
    client.setCompany(rs.getString("company"));
    client.setLocation(rs.getString("location"));
    client.setType(rs.getInt("type"));
    client.setEncrypt(rs.getBoolean("encrypt"));
    client.setMale(rs.getBoolean("male"));
    client.setTotalBalance(rs.getBigDecimal("totalBalance"));
    return client;
  }
}

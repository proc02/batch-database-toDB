package com.tfa.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tfa.entity.RepriseEntity;

public class RepriseMapper implements RowMapper<RepriseEntity>{

	@Override
	public RepriseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return RepriseEntity.builder()
				.id(rs.getLong("id"))
				.numeroPersonne(rs.getString("USERNUM"))
				.numeroCarte(rs.getString("CARDNUM"))
				.numeroCompte(rs.getString("COMPTENUM"))
				.codeStatus(rs.getString("CODESTATUS"))
				.solde(rs.getDouble("SOLDE"))
				.nbrReprise(rs.getInt("nbr_reprise"))
				.build();
	}

}

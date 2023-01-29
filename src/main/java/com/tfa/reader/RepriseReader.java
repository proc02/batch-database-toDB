package com.tfa.reader;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.stereotype.Component;

import com.tfa.entity.RepriseEntity;
import com.tfa.mapper.RepriseMapper;

@Component
public class RepriseReader extends JdbcPagingItemReader<RepriseEntity> {

	public RepriseReader(DataSource dataSource) {
		setDataSource(dataSource);
		setRowMapper(new RepriseMapper());
		setQueryProvider(createQuery());
		setPageSize(10000);
		setFetchSize(10000);
	}

	private MySqlPagingQueryProvider createQuery() {
		final Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("id", Order.ASCENDING);
		final MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("*");
		queryProvider.setFromClause("REPRISE");
		queryProvider.setSortKeys(sortKeys);
		return queryProvider;
	}

}

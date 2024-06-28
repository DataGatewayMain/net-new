package vdb.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExclusionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getPidsFromTable(String tableName) {
        String sql = String.format("SELECT pid FROM %s", tableName);
        return jdbcTemplate.queryForList(sql, String.class);
    }
}

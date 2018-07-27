package service;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class DatabaseService {

    public static int getNextSequence(JdbcTemplate jdbcTemplate, String sequenceName) {
        return jdbcTemplate.queryForObject("select * from get_next_sequence(?)", Integer.class, sequenceName);
    }
}

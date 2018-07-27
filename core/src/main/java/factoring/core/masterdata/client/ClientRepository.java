package factoring.core.masterdata.client;

import service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    private static final String CLIENT_SEQUENCE = "clientid";
    private static DatabaseService databaseService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Client> findAll() {
        return jdbcTemplate.query("select id, \"companyName\" from client", new Client.ClientRowMapper());
    }

    public Client save(Client client) {
        int id = databaseService.getNextSequence(jdbcTemplate, CLIENT_SEQUENCE);
        jdbcTemplate.update("insert into client values(?, ?)", id, client.getCompanyName());
        client.setId(id);
        return client;
    }

    public Client findById(int id) {
        return jdbcTemplate.queryForObject("select id, \"companyName\" from client where id = ?", new Client.ClientRowMapper(), id);
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from client where id = ?", id);
    }

    public int deleteAll() {
        return jdbcTemplate.update("delete from client");
    }
}

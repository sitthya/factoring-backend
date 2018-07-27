package factoring.core.masterdata.client;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {

    // Properties
    private long id;
    private String companyName;

    // Constructor
    public Client() { }
    public Client(long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // DB mapper
    public static class ClientRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Client client = new Client();
            client.setId(rs.getLong(1));
            client.setCompanyName(rs.getString(2));
            return client;
        }
    }
}

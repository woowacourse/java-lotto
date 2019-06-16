package lotto.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBConnectionTest {


    @Test
    public void connection(){
        Connection con=DBConnection.getConnection();
        assertNotNull(con);
    }
}

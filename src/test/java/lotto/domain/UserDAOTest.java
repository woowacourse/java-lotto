package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDAOTest {
    private UserDAO userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDAO();
    }

    @Test
    void connection() {
        Connection con = userDao.getConnection();
        assertNotNull(con);
    }

    @Test
    void addUser() throws Exception {
        User user = new User("testUserId", "testUser");
        userDao.addUser(user);
    }
    @Test
    void updateUser() throws Exception{
        userDao.updateByUserId("a","a","test");
    }


}
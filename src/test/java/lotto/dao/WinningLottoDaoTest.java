package lotto.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoDaoTest {
    private WinningLottoDao winningLottoDao;

    @BeforeEach
    void setUp() {
        winningLottoDao = new WinningLottoDao();
    }

    @Test
    public void connection() {
        Connection con = winningLottoDao.getConnection();
        assertNotNull(con);
    }

}
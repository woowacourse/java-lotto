package lotto.domain;

import lotto.domain.DAO.WinningLottoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WinningLottoDAOTest {
    private lotto.domain.DAO.WinningLottoDAO winningLottoDAO;

    @BeforeEach
    void setUp() {
        winningLottoDAO = new lotto.domain.DAO.WinningLottoDAO();
    }

    @Test
    void connection() {
        Connection con = winningLottoDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void addRow() throws Exception{
        winningLottoDAO.addWinningLottoInfo(2,"1,2,3,4,5,6",10);
    }

    @Test
    void deleteRow() throws Exception{
        winningLottoDAO.deleteWinningLottoInfoByLottoRound(2);
    }

}
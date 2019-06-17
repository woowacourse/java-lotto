package lotto.domain;

import lotto.domain.DAO.DBUtil;
import lotto.domain.DAO.WinningLottoDAO;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WinningLottoDAOTest {

    @Test
    void connection() {
        Connection con = DBUtil.getConnection();
        assertNotNull(con);
    }

    @Test
    void addRow() throws Exception {
        WinningLottoDAO.addWinningLottoInfo(1, "11,12,13,14,15,16", 8);
    }

    @Test
    void getCurrentLoottoRoundTest() throws Exception {
        assertThat(WinningLottoDAO.getCurrentLottoRound()).isEqualTo(0);
    }

    @Test
    void deleteRow() throws Exception {
        WinningLottoDAO.deleteWinningLottoInfoByLottoRound(1);
    }

}
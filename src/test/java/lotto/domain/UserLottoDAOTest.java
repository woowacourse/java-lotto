package lotto.domain;

import lotto.domain.util.DBUtil;
import lotto.domain.DAO.UserLottoDAO;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserLottoDAOTest {
    private UserLottoDAO userLottoDAO;

    @Test
    void connection() {
        Connection con = DBUtil.getConnection();
        assertNotNull(con);
    }

    @Test
    void addRow() throws Exception {
        UserLottoDAO.addUserLottoNumbers("1,2,3,4,5,6", 1);
    }

    @Test
    void deleteRow() throws Exception {
        UserLottoDAO.deleteUserLottoNumbersByLottoRound(1);
    }

    @Test
    void getCurrentLoottoRoundTest() throws Exception {
        assertThat(UserLottoDAO.getCurrentLottoRound()).isEqualTo(0);
    }
}

package lotto.domain;

import lotto.domain.DAO.DBUtil;
import lotto.domain.DAO.ResultDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResultDAOTest {

    @Test
    void connection() {
        Connection con = DBUtil.getConnection();
        assertNotNull(con);
    }

    @Test
    void addRow() throws Exception{
      ResultDAO.addResult(1000000,"1,0,0,0,0,0",100,2);
    }

    @Test
    void deleteRow() throws Exception{
        ResultDAO.deleteResultByLottoRound(2);
    }

    @Test
    void getCurrentLoottoRoundTest() throws Exception{
        assertThat(ResultDAO.getCurrentLottoRound()).isEqualTo(5);
    }
}

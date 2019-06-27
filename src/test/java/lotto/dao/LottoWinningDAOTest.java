package lotto.dao;

import lotto.TestLotto;
import lotto.utils.DBUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoWinningDAOTest {
    private Connection connection = DBUtils.getConnection();
    private LottoRoundDAO lottoRoundDAO = new LottoRoundDAO(connection);
    private LottoWinningDAO lottoWInningDAO = new LottoWinningDAO(connection);

    @Test
    void saveWinningLotto() throws Exception {
        lottoWInningDAO.saveWinning("1,2,3,4,5,6", 7, lottoRoundDAO.totalRound());
    }

    @Test
    void inquireWinningLotto() throws Exception {
        saveWinningLotto();
        assertThat(lottoWInningDAO.inquireWinning(lottoRoundDAO.totalRound())).isEqualTo(TestLotto.winning);
    }
}

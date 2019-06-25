package lotto.dao;

import lotto.TestLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Numbers;
import lotto.utils.DBUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoPurchaseDAOTest {
    private Connection connection = DBUtils.getConnection();
    private LottoRoundDAO lottoRoundDAO = new LottoRoundDAO(connection);
    private LottoPurchaseDAO lottoPurchaseDAO = new LottoPurchaseDAO(connection);

    @Test
    void savePurchaseLotto() throws Exception {
        lottoRoundDAO.playRound();
        lottoPurchaseDAO.savePurchaseLotto(
                Arrays.asList(Lotto.of(new Numbers("1,2,3,4,5,6")), Lotto.of(new Numbers("2,3,4,5,6,7"))),
                lottoRoundDAO.totalRound()
        );
    }

    @Test
    void inquirePurchaseLotto() throws Exception {
        savePurchaseLotto();
        assertThat(lottoPurchaseDAO.inquireLotto(lottoRoundDAO.totalRound())).isEqualTo(TestLotto.lottos);
    }
}

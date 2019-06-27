package lotto.dao;

import lotto.TestLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Numbers;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Winning;
import lotto.utils.DBUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultDAOTest {
    private Connection connection = DBUtils.getConnection();
    private LottoRoundDAO lottoRoundDAO = new LottoRoundDAO(connection);
    private LottoResultDAO lottoResultDAO = new LottoResultDAO(connection);

    @Test
    void save_result() throws Exception {
        PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(2000), 2);
        Winning winning = Winning.of(Lotto.of(new Numbers("1,2,3,4,5,6")), 7);
        Lottos lottos = Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,2,3,4,5,6"), new Numbers("2,3,4,5,6,7")));

        lottoRoundDAO.playRound();
        lottoResultDAO.saveLottoResult(LottoResult.of(winning, lottos), lottoRoundDAO.totalRound());
    }

    @Test
    void inquireResult() throws Exception {
        save_result();
        assertThat(lottoResultDAO.inquireLottoResult(lottoRoundDAO.totalRound())).isEqualTo(TestLotto.lottoResult.getMap());
    }
}

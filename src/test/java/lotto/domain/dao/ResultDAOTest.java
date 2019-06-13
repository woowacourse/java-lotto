package lotto.domain.dao;

import lotto.domain.Money;
import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.rank.RankResult;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

public class ResultDAOTest {
    @Test
    void 결과_등록하기() throws SQLException {
        Lotto manualLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(Arrays.asList(manualLotto), 3);
        RankResult rank = lottos.matchLottoRank(new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7));
        Money money = new Money(3000);
        ResultDAO.addResult(rank,money);
    }
}

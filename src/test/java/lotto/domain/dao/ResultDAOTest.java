package lotto.domain.dao;

import lotto.domain.Money;
import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.rank.RankResult;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultDAOTest {
    @Test
    void 결과_등록하기() throws SQLException {
        Lotto manualLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(Arrays.asList(manualLotto), 3);
        RankResult rank = lottos.matchLottoRank(new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7));
        Money money = new Money(3000);
        ResultDAO.addResult(rank, money);
    }

    @Test
    void 당첨자_몇명인지_조회하기() throws SQLException {
        assertThat(ResultDAO.searchResultNumbers(1)).isEqualTo(Arrays.asList(0, 0, 1, 0, 0));
    }

    @Test
    void 당첨_금액_조회하기() throws SQLException {
        assertThat(ResultDAO.searchTotalMoney(1)).isEqualTo(1500000);
    }

    @Test
    void 수익률_조회하기() throws SQLException {
        assertThat(ResultDAO.searchRateOfReturn(1)).isEqualTo(121654);
    }
}

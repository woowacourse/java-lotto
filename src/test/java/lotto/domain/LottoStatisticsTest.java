package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.dto.WinningInform;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {

    @Test
    @DisplayName("수익률 계산 테스트")
    void testConvertToMapProfit() {
        //given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 7, 8, 9));
        Wallet wallet = new Wallet(List.of(lotto1, lotto2));

        WinningInform winningInform = new WinningInform(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                7
        );

        //when
        LottoStatistics statistics = LottoStatistics.from(wallet, winningInform);

        //assert
        assertThat(statistics.getCountOf(MatchRank.MATCH_SIX)).isEqualTo(1);    // lotto1
        assertThat(statistics.getCountOf(MatchRank.MATCH_THREE)).isEqualTo(1); // lotto2
        assertThat(statistics.getCountOf(MatchRank.MATCH_FOUR)).isEqualTo(0);
        assertThat(statistics.getCountOf(MatchRank.MATCH_FIVE)).isEqualTo(0);
        assertThat(statistics.getCountOf(MatchRank.MATCH_BONUS)).isEqualTo(0);
    }

    @Test
    @DisplayName("3개 일치로 5000원 당첨되고 구입금액이 10000원일 때 수익률은 0.5, isProfit=false")
    void calculateProfit_half() {
        // given
        Wallet wallet = new Wallet(List.of(
                new Lotto(List.of(1, 2, 3, 7, 8, 9))
        ));

        WinningInform winningInform = new WinningInform(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                7
        );

        Money spentMoney = new Money(10000);

        // when
        LottoStatistics statistics = LottoStatistics.from(wallet, winningInform);
        Profit profit = statistics.calculateProfit(spentMoney);

        // then
        assertThat(profit.rate()).isEqualTo(0.5);
        assertThat(profit.isProfit()).isFalse();
    }

}
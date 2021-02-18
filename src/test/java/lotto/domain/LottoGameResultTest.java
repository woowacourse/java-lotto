package lotto.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameResultTest {

    static LottoGameResult lottoGameResult = new LottoGameResult();


    @BeforeAll
    static void beforeAll() {
        lottoGameResult.add(Rank.rankOf(4, false)); // 4
        lottoGameResult.add(Rank.rankOf(3, false)); // 5
        lottoGameResult.add(Rank.rankOf(2, false));
        lottoGameResult.add(Rank.rankOf(1, false));
        lottoGameResult.add(Rank.rankOf(0, false));
    }

    @Test
    @DisplayName("랭크 별 개수 테스트")
    void testAdd() {
        assertThat(lottoGameResult.countByRank(Rank.FOURTH)).isEqualTo(1);
        assertThat(lottoGameResult.countByRank(Rank.NOTHING)).isEqualTo(3);
    }

    @Test
    @DisplayName("로또 당첨에 따른 총 상금 테스트")
    void testTotalReward() {
        assertThat(lottoGameResult.totalReward()).isEqualTo(55000);
    }

    @Test
    @DisplayName("수익률 테스트")
    void testProfit() {
        assertThat(lottoGameResult.calculateProfit(5000)).isEqualTo(11);
    }
}

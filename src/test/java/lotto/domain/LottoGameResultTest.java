package lotto.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameResultTest {

    static LottoGameResult lottoGameResult = new LottoGameResult();


    @BeforeAll
    static void beforeAll() {
        lottoGameResult.add(Rank.of(4, false));
        lottoGameResult.add(Rank.of(3, false));
        lottoGameResult.add(Rank.of(2, false));
        lottoGameResult.add(Rank.of(1, false));
        lottoGameResult.add(Rank.of(0, false));
    }

    @Test
    @DisplayName("랭크 별 당첨 개수 테스트")
    void testAdd() {
        assertThat(lottoGameResult.countByRank(Rank.FOURTH)).isEqualTo(1);
        assertThat(lottoGameResult.countByRank(Rank.FIFTH)).isEqualTo(1);
        assertThat(lottoGameResult.countByRank(Rank.NOTHING)).isEqualTo(3);
    }

    @Test
    @DisplayName("수익률 테스트")
    void testProfit() {
        assertThat(lottoGameResult.calculateProfit()).isEqualTo(11);
    }
}

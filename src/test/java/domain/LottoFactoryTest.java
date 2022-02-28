package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {

    LottoFactory lottoFactory;

    @BeforeEach
    void setUp() {
        final Money money = new Money("100000000");

        lottoFactory = new LottoFactory(money);
    }

    @DisplayName("구입 금액만큼 발급 받은 로또의 갯수를 확인한다.")
    @Test
    void issueLotto_count_correct() {
        lottoFactory.issueLotto();
        final List<Lotto> lottoTickets = lottoFactory.getLotto();

        assertThat(lottoTickets.size()).isEqualTo(100000);
    }

    @DisplayName("각각 로또들의 등수에 따라 받을 수 있는 총 상금의 합을 확인한다.")
    @Test
    void total_prize_correct() {
        SortedMap<RankPrize, Integer> rankCounts = new TreeMap<>();
        rankCounts.put(RankPrize.FOURTH, 1);
        rankCounts.put(RankPrize.SECOND, 1);
        rankCounts.put(RankPrize.THIRD, 1);

        int result = lottoFactory.calculatePrize(rankCounts);

        assertThat(result).isEqualTo(31550000);
    }
}

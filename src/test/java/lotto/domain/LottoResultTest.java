package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    private static final int LOTTO_PRICE = 1000;
    private static final int PERCENTAGE = 100;
    private static final int RATIO = LOTTO_PRICE / PERCENTAGE;

    Winning winning;
    LottoResult result;

    @BeforeEach
    void setUp() {
        winning = Winning.of(Arrays.asList(1,2,3,4,5,6), 7);
    }

    @Test
    void 일등_확인() {
        result = LottoResult.of(winning, Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(1,2,3,4,5,6)))));
        assertThat(result.yield()).isEqualTo(Rank.FIRST.getMoney() / RATIO);
    }

    @Test
    void 이등_확인() {
        result = LottoResult.of(winning, Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(1,2,3,4,5,7)))));
        assertThat(result.yield()).isEqualTo(Rank.SECOND.getMoney() / RATIO);
    }

    @Test
    void 삼등_확인() {
        result = LottoResult.of(winning, Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(1,2,3,4,5,8)))));
        assertThat(result.yield()).isEqualTo(Rank.THIRD.getMoney() / RATIO);
    }

    @Test
    void 사등_확인() {
        result = LottoResult.of(winning, Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(1,2,3,4,7,8)))));
        assertThat(result.yield()).isEqualTo(Rank.FOURTH.getMoney() / RATIO);
    }

    @Test
    void 오등_확인() {
        result = LottoResult.of(winning, Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(1,2,3,9,7,8)))));
        assertThat(result.yield()).isEqualTo(Rank.FIFTH.getMoney() / RATIO);
    }

    @Test
    void 꽝_확인() {
        result = LottoResult.of(winning, Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(1,2,10,9,7,8)))));
        assertThat(result.yield()).isEqualTo(Rank.MISS.getMoney() / RATIO);

        result = LottoResult.of(winning, Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(1,11,10,9,7,8)))));
        assertThat(result.yield()).isEqualTo(Rank.MISS.getMoney() / RATIO);

        result = LottoResult.of(winning, Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(12,11,10,9,7,8)))));
        assertThat(result.yield()).isEqualTo(Rank.MISS.getMoney() / RATIO);
    }
}
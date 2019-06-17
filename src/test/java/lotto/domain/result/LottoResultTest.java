package lotto.domain.result;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Numbers;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    private static final int LOTTO_PRICE = 1000;
    private static final int PERCENTAGE = 100;
    private static final int RATIO = LOTTO_PRICE / PERCENTAGE;

    PurchaseCount purchaseCount;
    Winning winning;
    LottoResult result;

    @BeforeEach
    void setUp() {
        winning = Winning.of(Lotto.of(new Numbers("1,2,3,4,5,6")), 7);
        purchaseCount = PurchaseCount.of(PurchaseAmount.of(1000), 1);
    }

    @Test
    void 일등_확인() {
        result = LottoResult.of(winning, Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,2,3,4,5,6"))));
        assertThat(result.yield()).isEqualByComparingTo(new BigDecimal(LottoRank.FIRST.getMoney() / RATIO));
    }

    @Test
    void 이등_확인() {
        result = LottoResult.of(winning, Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,2,3,4,5,7"))));
        assertThat(result.yield()).isEqualByComparingTo(new BigDecimal(LottoRank.SECOND.getMoney() / RATIO));
    }

    @Test
    void 삼등_확인() {
        result = LottoResult.of(winning, Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,2,3,4,5,8"))));
        assertThat(result.yield()).isEqualByComparingTo(new BigDecimal(LottoRank.THIRD.getMoney() / RATIO));
    }

    @Test
    void 사등_확인() {
        result = LottoResult.of(winning, Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,2,3,4,7,8"))));
        assertThat(result.yield()).isEqualByComparingTo(new BigDecimal(LottoRank.FOURTH.getMoney() / RATIO));
    }

    @Test
    void 오등_확인() {
        result = LottoResult.of(winning, Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,2,3,9,7,8"))));
        assertThat(result.yield()).isEqualByComparingTo(new BigDecimal(LottoRank.FIFTH.getMoney() / RATIO));
    }

    @Test
    void 꽝_확인() {
        result = LottoResult.of(winning, Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,2,10,9,7,8"))));
        assertThat(result.yield()).isEqualByComparingTo(new BigDecimal(LottoRank.MISS.getMoney() / RATIO));

        result = LottoResult.of(winning, Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,11,10,9,7,8"))));
        assertThat(result.yield()).isEqualByComparingTo(new BigDecimal(LottoRank.MISS.getMoney() / RATIO));

        result = LottoResult.of(winning, Lottos.of(purchaseCount, Arrays.asList(new Numbers("12,2,10,9,7,8"))));
        assertThat(result.yield()).isEqualByComparingTo(new BigDecimal(LottoRank.MISS.getMoney() / RATIO));
    }
}
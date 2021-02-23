package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.withPrecision;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lotto.domain.number.ManualCount;
import lotto.domain.number.Payout;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoExchangeTest {

    @Test
    @DisplayName("금액보다 수동 입력이 많을 경우 예외 처리")
    void valueOfWrongOrder() {
        assertThatIllegalArgumentException()
            .isThrownBy(() ->
                LottoExchange.getInstance()
                    .buyLottoTicket(Payout.valueOf("10000"), ManualCount.valueOf("11")))
            .withMessage("금액보다 수동 입력의 개수가 너무 많습니다.");
    }

    @Test
    @DisplayName("수익률 계산")
    void calculateYield() {
        Map<Rank, Long> statistics = new HashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> statistics.put(rank, 1L));
        Ranks ranks = new Ranks(statistics);
        double expected = Arrays.stream(Rank.values())
            .mapToDouble(rank -> rank.getWinnings()).sum() / Rank.values().length / 1000;

        assertThat(LottoExchange.getInstance().calculateYield(ranks))
            .isEqualTo(expected, withPrecision(0.01d));
    }
}

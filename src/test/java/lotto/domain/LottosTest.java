package lotto.domain;

import static lotto.constant.ErrorMessage.INVALID_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottosTest {
    @Test
    void 당첨_결과를_구한다() {
        Lottos lottos = new Lottos(List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(7, 8, 9, 10, 11, 12))
        ));
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        assertThat(lottos.getRankCount(winningNumbers).get(Rank.FIRST)).isEqualTo(1);
    }

    @ParameterizedTest(name = "[{index}] {0}원이 들어오면 {1}장을 반환한다")
    @CsvSource({
        "1000,1",
        "10000,10"
    })
    void 주어진_가격으로_정확한_매수를_구한다(int payment, int count) {
        Lottos lottos = new Lottos(payment);
        assertThat(lottos.getTicketCount()).isEqualTo(count);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 3003})
    void 구입_금액이_1000원으로_나누어떨어지지_않을_경우_예외를_반환한다(int payment) {
        assertThatThrownBy(() -> new Lottos(payment))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(INVALID_PRICE.getMessage());
    }
}

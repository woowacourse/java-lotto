package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class AmountToBuyLottoTest {
    @ParameterizedTest
    @ValueSource(strings = {"0", "1100", "-1"})
    @DisplayName("올바르지 않은 금액")
    void invalidAmount(int invalidAmount) {
        assertThatThrownBy(() -> new AmountToBuyLotto(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매한 로또 개수 구하기")
    void calculateLottoCount() {
        AmountToBuyLotto amount = new AmountToBuyLotto(1000);
        assertThat(amount.calculateLottoCount()).isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("수익률 구하기")
    @CsvSource(value = {"0:0", "1000:1", "10000:10"}, delimiter = ':')
    void calculateProfit(int prizeSum, int expectedProfit) {
        AmountToBuyLotto amount = new AmountToBuyLotto(1000);
        assertThat(amount.calculateProfit(prizeSum)).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("자동로또 티켓 개수 구하기")
    void calculateAutoMaticLottoCount() {
        AmountToBuyLotto amount = new AmountToBuyLotto(1000);
        ManualLottoCount manualLottoCount = ManualLottoCount.of(1, amount.calculateLottoCount());

        assertThat(amount.calculateAutomaticLottoCount(manualLottoCount)).isEqualTo(0);
    }
}

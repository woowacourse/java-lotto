package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AmountTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "0", "1100", "-1", "aaa"})
    @DisplayName("올바르지 않은 금액")
    void failed(String input) {
        assertThatThrownBy(() -> new Amount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매한 로또 개수 구하기")
    void calculateLottoCount() {
        Amount amount = new Amount("1000");
        assertThat(amount.calculateLottoCount()).isEqualTo(1);
    }
}

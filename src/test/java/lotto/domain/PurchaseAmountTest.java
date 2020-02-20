package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseAmountTest {

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void calculate_테스트() {
        Assertions.assertThat(PurchaseAmount.calculate(2000))
                .isInstanceOf(PurchaseAmount.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {999, 0})
    void 최소_구매_금액보다_작은_입력이_들어온_경우_calculate_테스트(int value) {
        Assertions.assertThatThrownBy(() -> PurchaseAmount.calculate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input money out of range (minimum - 1000).");
    }
}

package lotto.domain;

import lotto.domain.exception.OutOfRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseAmountTest {

    @Test
    @DisplayName("정상 입력 테스트")
    void calculate() {
        Assertions.assertThat(new PurchaseAmount(2, 0))
                .isInstanceOf(PurchaseAmount.class);
    }

    @ParameterizedTest
    @DisplayName("최소 구매 금액보다 작은 입력이 들어온_경우 calculate 테스트")
    @ValueSource(ints = {0, -1})
    void calcuate_less(int value) {
        Assertions.assertThatThrownBy(() -> new PurchaseAmount(value, 0))
                .isInstanceOf(OutOfRangeException.class);
    }
}

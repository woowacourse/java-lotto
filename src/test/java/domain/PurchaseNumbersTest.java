package domain;

import lotto.domain.PurchaseNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseNumbersTest {

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void calculate_테스트() {
        Assertions.assertThat(PurchaseNumber.calculate(2000))
                .isInstanceOf(PurchaseNumber.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {999, 0})
    void 최소_구매_금액보다_작은_입력이_들어온_경우_calculate_테스트(int value) {
        Assertions.assertThatThrownBy(() -> PurchaseNumber.calculate(value))
                .isInstanceOf(IllegalArgumentException.class);
    }


}

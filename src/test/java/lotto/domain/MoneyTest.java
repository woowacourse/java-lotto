package lotto.domain;

import lotto.domain.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    void validatePricePerLotto_로또_가격_단위로_입력하지_않았을_때() {
        int invalidInputMoney = 12100;
        assertThatThrownBy(() -> Money.validatePricePerLotto(invalidInputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가격은 1000원 단위로 입력해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {900, -1, 0})
    void validateUnderLottoPrice_로또_가격_미만으로_입력했을_때(int invalidInputMoney) {
        assertThatThrownBy(() -> Money.validateUnderLottoPrice(invalidInputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000원 이상으로 입력해야 합니다.");
    }
}

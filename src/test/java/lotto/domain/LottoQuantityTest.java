package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exceptions.InvalidLottoQuantityException;

class LottoQuantityTest {
    @ParameterizedTest
    @ValueSource(strings = {"abc", "-1", "100"})
    void create(String maybe) {
        assertThatThrownBy(() -> LottoQuantity.create(maybe, Money.create("99000")))
            .isInstanceOf(InvalidLottoQuantityException.class);
    }
}

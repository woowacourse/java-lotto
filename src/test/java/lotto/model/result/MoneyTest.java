package lotto.model.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.model.message.LottoCountExceptionMessage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "  ", "\t", "\n"})
    @DisplayName("투입 금액 공백 검증")
    void validateLottoNumber(String number) {
        assertThatThrownBy(() -> new Money(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoCountExceptionMessage.BLANK_ERROR.getMassage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"azpi", "++", "greeanlawn", "1dksl", "-1"})
    @DisplayName("투입 금액이 숫자가 아닌 경우 검증")
    void validateInputMoneyIsNumber(String number) {
        assertThatThrownBy(() -> new Money(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoCountExceptionMessage.NUMBER_ERROR.getMassage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "1001", "100001"})
    @DisplayName("투입 금액이 천원 단위가 아닌 경우")
    void validateNotThousandUnitInputMoney(String number) {
        assertThatThrownBy(() -> new Money(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoCountExceptionMessage.UNIT_ERROR.getMassage());
    }
}
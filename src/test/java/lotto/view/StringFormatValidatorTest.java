package lotto.view;

import static lotto.view.StringFormatValidatorFactory.INVALID_BONUS_NUMBER_FORMAT_MESSAGE;
import static lotto.view.StringFormatValidatorFactory.INVALID_LOTTO_NUMBER_FORMAT_MESSAGE;
import static lotto.view.StringFormatValidatorFactory.INVALID_MONEY_FORMAT_MESSAGE;
import static lotto.view.StringFormatValidatorFactory.lottoValidator;
import static lotto.view.StringFormatValidatorFactory.moneyValidator;
import static lotto.view.StringFormatValidatorFactory.numberValidator;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import verus.exception.InvalidFormatException;

public class StringFormatValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "01, 0, 1, 2, 3, 4", "-1, 1, 2, 3, 4, 5", "1,2,3,4,5,6,7",
        "a,b,c,d,e,f"})
    @DisplayName("잘못된 로또 번호 포맷 검증")
    void validateInvalidLottoNumberFormat(String text) {
        assertThatThrownBy(() -> lottoValidator().validate(text))
            .isInstanceOf(InvalidFormatException.class)
            .hasMessage(INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "1, 6, 1, 2, 3, 4", "1, 1, 2, 3, 4, 5", "1,2,3,5,6,7"})
    @DisplayName("정상적인 로또 번호 포맷 검증")
    void validateValidLottoNumberFormat(String text) {
        assertThatCode(() -> lottoValidator().validate(text)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("비정상적인 숫자 입력 처리")
    @ValueSource(strings = {"00", "05", "0", "-3", "클레이"})
    void invalidNumberFormat(String text) {
        assertThatThrownBy(() -> numberValidator().validate(text))
            .isInstanceOf(InvalidFormatException.class)
            .hasMessage(INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("정상적인 숫자 입력 처리")
    @ValueSource(strings = {"10", "5", "100", "3"})
    void validNumberFormat(String text) {
        assertThatCode(() -> numberValidator().validate(text)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"111", "-1", "numbers", "1111004"})
    @DisplayName("비정상적인 금액 형식 입력")
    void invalidMoneyFormat(String text) {
        assertThatThrownBy(() -> moneyValidator().validate(text))
            .isInstanceOf(InvalidFormatException.class)
            .hasMessage(INVALID_MONEY_FORMAT_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"111000", "10000", "1000", "12342314123000"})
    @DisplayName("정상적인 금액 형식 입력")
    void validMoneyFormat(String text) {
        assertThatCode(() -> moneyValidator().validate(text)).doesNotThrowAnyException();
    }

}

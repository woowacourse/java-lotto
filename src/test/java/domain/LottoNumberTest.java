package domain;

import static constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static constant.LottoConstants.MAXIMUM_LOTTO_NUMBER;
import static constant.LottoConstants.MINIMUM_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    public static final String PARAMETERIZED_TEST_DISPLAY_FORMAT =
            DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]";

    @Test
    void of_returnsIntOnValidInteger() {
        LottoNumber lottoNum = LottoNumber.of(1);
        assertThat(lottoNum.getNumber()).isEqualTo(1);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(ints = {MINIMUM_LOTTO_NUMBER - 1, MAXIMUM_LOTTO_NUMBER + 1})
    void of_failsOnInvalidRangeInteger(int value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoNumber.of(value))
                .withMessageMatching(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
    }

    @Test
    void of_returnsCacheForAlreadyCreatedNumber() {
        LottoNumber newLottoNum = LottoNumber.of(10);
        LottoNumber cachedLottoNum = LottoNumber.of(10);

        assertThat(newLottoNum).isEqualTo(cachedLottoNum);
    }
}
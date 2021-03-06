package lotto.lottoticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.lottoticket.LottoNumber.ERROR_MESSAGE_INVALID_RANGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("자동 로또 생성 숫자 1부터 45 사이 확인")
    void checkNumbersInRange(int value) {
        assertThat(LottoNumber.of(value)).isInstanceOf(LottoNumber.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("자동 로또 생성 숫자 1부터 45 사이 확인")
    void checkNumbersNotInRange(int value) {
        assertThatThrownBy(() -> LottoNumber.of(value)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_RANGE);
    }
}
package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exceptions.InvalidLottoNumberException;

class LottoNumberTest {

    @Test
    @DisplayName("당첨 번호 입력이 유효한 경우 잘 찾아내는지")
    void find() {
        assertThat(LottoNumber.find("1")).isEqualTo(LottoNumber.ONE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "abc"})
    @DisplayName("당첨 번호 입력이 유효하지 않은 경우 예외를 발생시키는지")
    void invalidFindWithNumber(String number) {
        assertThatThrownBy(() -> LottoNumber.find(number)
        ).isInstanceOf(InvalidLottoNumberException.class);
    }
}

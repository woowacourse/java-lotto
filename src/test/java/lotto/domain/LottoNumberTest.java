package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.constant.ErrorMessage;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    void 숫자_범위_검증() {
        //given
        int number = 46;

        //when & then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.RANGE_ERROR.getMessage());
    }

    @Test
    void 숫자_검증() {
        //given
        String number = "1번";

        //when & then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_FORMAT_ERROR.getMessage());
    }

}
package lotto.domain;

import lotto.exception.LottoNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest(name = "로또 번호를 불러 올 때 자연수가 아닌 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"-1", "0", "A"})
    void checkNaturalNumber(String input) {
        Assertions.assertThatThrownBy(() -> LottoNumber.getByString(input))
                .isInstanceOf(LottoNumberException.class)
                .hasMessage("로또 번호는 자연수여야 합니다.");
    }

    @ParameterizedTest(name = "로또 번호를 불러 올 때 범위에 벗어나는 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"100", "46"})
    void checkRange(String input) {
        Assertions.assertThatThrownBy(() -> LottoNumber.getByString(input))
                .isInstanceOf(LottoNumberException.class)
                .hasMessage("로또 번호는 1 ~ 45 사이여야 합니다.");
    }
}

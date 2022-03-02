package lotto.domain;

import lotto.exception.LottoNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest(name = "로또 번호를 불러 올 때 범위에 벗어나는 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"100", "46", "0", "-3"})
    void findByNumber(int input) {
        Assertions.assertThatThrownBy(() -> LottoNumber.findByNumber(input))
                .isInstanceOf(LottoNumberException.class)
                .hasMessage("로또 번호는 1 ~ 45 사이여야 합니다.");
    }
}

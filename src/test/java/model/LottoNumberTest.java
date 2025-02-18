package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.numbers.LottoNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    @ParameterizedTest
    @ValueSource(ints = {MIN_NUMBER - 1, MAX_NUMBER + 1})
    void 로또_번호의_범위가_유효하지_않은_경우_예외를_던진다(int value) {
        assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이여야 합니다.");
    }
}

package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @DisplayName("올바른 범위의 숫자로 로또 번호를 생성하면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 44, 45})
    void 올바른_범위의_숫자로_로또_번호_생성_테스트(int number) {
        Assertions.assertThatCode(() -> new LottoNumber(number))
                .doesNotThrowAnyException();
    }

    @DisplayName("올바르지 않은 범위의 숫자로 로또 번호를 생성하는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 100_000})
    void 올바르지_않은_범위의_숫자로_로또_번호_생성_테스트(int number) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1 이상 45 이하여야 합니다.");
    }
}
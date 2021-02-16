package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

class LottoNumberTest {

    @DisplayName("LottoNumber 정상 생성된다.")
    @Test
    void LottoNumber_생성_테스트() {
        assertThatCode(() -> new LottoNumber(3))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장되지 않은 LottoNumber 에러 발생한다. ")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    void LottoNumber_에러_테스트(int value) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
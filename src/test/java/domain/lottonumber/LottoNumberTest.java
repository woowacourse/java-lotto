package domain.lottonumber;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    LottoNumber lottoNumber;

    @ParameterizedTest
    @ValueSource(ints = {10, 1, 45})
    void 생성_테스트(int value) {
        lottoNumber = LottoNumberFactory.getInstance(value);
        Assertions.assertThat(lottoNumber).hasFieldOrPropertyWithValue("number", value);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46})
    void 예외처리_테스트(int value) {
        Assertions.assertThatThrownBy(() -> lottoNumber = LottoNumberFactory.getInstance(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1부터 45 사이의 숫자만 입력 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    void 동일성_테스트(int value) {
        Assertions.assertThat(LottoNumberFactory.getInstance(value)).isEqualTo(LottoNumberFactory.getInstance(value));
    }
}

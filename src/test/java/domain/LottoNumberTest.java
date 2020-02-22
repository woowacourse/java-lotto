package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @DisplayName("LottoNumber 생성자 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "-1", "46", "0"})
    public void lottoNumberConstructorTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new LottoNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

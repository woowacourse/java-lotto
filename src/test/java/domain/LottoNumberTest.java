package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @DisplayName("LottoNumber 생성자 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void lottoNumberConstructorTest(int input) {
        Assertions.assertThatThrownBy(() -> {
            new LottoNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoNumber equals, hashCode 테스트")
    @Test
    void equalsTest() {
        LottoNumber input = new LottoNumber(1);
        LottoNumber expected = new LottoNumber(1);
        Assertions.assertThat(input.equals(expected)).isTrue();
    }
}

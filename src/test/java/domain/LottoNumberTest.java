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

    @DisplayName("입력한 int 값이 LottoNumber 와 같은 숫자인지 확인하는 테스트")
    @Test
    void isSameNumberTest() {
        LottoNumber lottoNumber = new LottoNumber(10);
        boolean same = lottoNumber.isSameLottoNumber(10);
        boolean different = lottoNumber.isSameLottoNumber(9) == false;

        Assertions.assertThat(same).isTrue();
        Assertions.assertThat(different).isTrue();
    }
}

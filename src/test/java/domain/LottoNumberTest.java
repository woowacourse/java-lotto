package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("로또 숫자 테스트")
public class LottoNumberTest {
    @Test
    @DisplayName("로또 숫자 생성 예외처리")
    void lottoNumberConstructorTest() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~45 사이의 숫자만 가능합니다.");
    }

    @Test
    @DisplayName("로또 숫자 비교")
    void lottoNumberEqualTest() {
        LottoNumber number = new LottoNumber(5);
        assertThat(number.equals(new LottoNumber(5))).isTrue();
        assertThat(number.equals(new LottoNumber(4))).isFalse();
    }
}

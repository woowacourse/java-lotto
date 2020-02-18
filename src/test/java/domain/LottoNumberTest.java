package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로또 숫자 테스트")
public class LottoNumberTest {
    @Test
    @DisplayName("로또 숫자 비교 테스트")
    void lottoNumberEqualTest() {
        LottoNumber number = new LottoNumber(5);
        assertThat(number.equals(new LottoNumber(5))).isTrue();
        assertThat(number.equals(new LottoNumber(4))).isFalse();
    }
}

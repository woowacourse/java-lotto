package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberTest {
    @DisplayName("같은 숫자인 경우 정상 처리된다")
    @Test
    public void isEqualsTest() {
        LottoNumber first = new LottoNumber(1);
        LottoNumber second = new LottoNumber(1);

        assertThat(first).isEqualTo(second);
    }

    @DisplayName("다른 숫자인 경우 예외 처리된다")
    @Test
    public void isNotEqualsTest() {
        LottoNumber first = new LottoNumber(1);
        LottoNumber second = new LottoNumber(2);

        assertThat(first).isNotEqualTo(second);
    }
}

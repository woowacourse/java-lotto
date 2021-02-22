package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @DisplayName("같은 숫자인 경우 정상 처리된다")
    @Test
    public void isEqualsTest() {
        LottoNumber first = new LottoNumber("1");
        LottoNumber second = new LottoNumber("1");

        assertThat(first).isEqualTo(second);
    }

    @DisplayName("다른 숫자인 경우 예외 처리된다")
    @Test
    public void isNotEqualsTest() {
        LottoNumber first = new LottoNumber("1");
        LottoNumber second = new LottoNumber("2");

        assertThat(first).isNotEqualTo(second);
    }

    @DisplayName("45를 초과한 수가 입력될 경우 예외를 발생시킨다.")
    @Test
    public void validateNumberRangeTest() {
        assertThatThrownBy( () -> new LottoNumber("46")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음수가 입력된 경우 예외를 발생시킨다.")
    @Test
    public void validateNegativeNumberRangeTest() {
        assertThatThrownBy( () -> new LottoNumber("-1")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자가 아닌 경우 예외를 발생시킨다.")
    @Test
    public void validateNumberTest() {
        assertThatThrownBy( () -> new LottoNumber("a")).isInstanceOf(IllegalArgumentException.class);
    }
}

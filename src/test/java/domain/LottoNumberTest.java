package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @DisplayName("로또 번호가 같을 경우 정상 처리된다.")
    @Test
    public void isSameLottoNumber() {
        LottoNumber first = new LottoNumber(1);
        LottoNumber second = new LottoNumber(1);

        assertThat(first).isEqualTo(second);
    }

    @DisplayName("로또 번호가 다를 경우 정상 처리된다.")
    @Test
    public void isDifferentLottoNumber() {
        LottoNumber first = new LottoNumber(1);
        LottoNumber second = new LottoNumber(2);

        assertThat(first).isNotEqualTo(second);
    }

    @DisplayName("45를 초과한 수가 입력될 경우 예외를 발생시킨다.")
    @Test
    public void validateNumberRangeTest() {
        assertThatThrownBy(() -> new LottoNumber(46)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음수가 입력된 경우 예외를 발생시킨다.")
    @Test
    public void validateNegativeNumberRangeTest() {
        assertThatThrownBy(() -> new LottoNumber(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}

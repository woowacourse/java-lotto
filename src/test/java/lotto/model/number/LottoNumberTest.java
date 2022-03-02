package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
    @DisplayName("로또 번호가 1 이상 45 이하가 아니면 예외가 발생한다")
    @Test
    void bound_exception() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
    }

    @DisplayName("해당 로또 번호가 비교할 로또 번호보다 크면 1을 반환한다")
    @Test
    void compare_bigger() {
        LottoNumber BiggerNumber = new LottoNumber(2);
        LottoNumber SmallerNumber = new LottoNumber(1);
        assertThat(BiggerNumber.compareTo(SmallerNumber)).isEqualTo(1);
    }

    @DisplayName("해당 로또 번호가 비교할 로또 번호보다 작으면 -1을 반환한다")
    @Test
    void compare_smaller() {
        LottoNumber SmallerNumber = new LottoNumber(1);
        LottoNumber BiggerNumber = new LottoNumber(2);
        assertThat(SmallerNumber.compareTo(BiggerNumber)).isEqualTo(-1);
    }

    @DisplayName("숫자가 같은 로또 번호는 같은 객체로 취급한다")
    @Test
    void same_number_equals() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(1);
        assertThat(lottoNumber1.equals(lottoNumber2)).isTrue();
    }

}

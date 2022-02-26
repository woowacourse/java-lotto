package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("중복된 숫자로 생성시 예외 발생")
    void incorrect() {
        assertThatThrownBy(() -> new LottoNumbers("2,2,3,4,5,6")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 개수는 중복이 불가능합니다.");
    }

    @Test
    @DisplayName("숫자가 6개 미만")
    void incorrect4() {
        assertThatThrownBy(() -> new LottoNumbers("2,5,6")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 개수는 6개로 제한됩니다.");
    }

    @Test
    @DisplayName("중복된 숫자가 없는 경우")
    void correct() {
        assertThatNoException().isThrownBy(() -> new LottoNumbers("1,2,3,4,5,6"));
    }

    @Test
    @DisplayName("문자열이 포함된 경우")
    void incorrect2() {
        assertThatThrownBy(() -> new LottoNumbers("1,aa,3,4,5,6")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공백이 포함된 경우")
    void correct2() {
        assertThatNoException().isThrownBy(() -> new LottoNumbers("1,  8  ,3,4 ,5 ,6"));
    }

    @Test
    @DisplayName("공백 + 중복인 경우")
    void incorrect3() {
        assertThatThrownBy(() -> new LottoNumbers("1,  3,3  ,4,5,6")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("equals 확인")
    void test() {
        LottoNumbers lottoNumbers1 = new LottoNumbers("1,2,3,4,5,6");
        LottoNumbers lottoNumbers2 = new LottoNumbers("5,2,1,3,4,6");

        assertThat(lottoNumbers1).isEqualTo(lottoNumbers2);
    }
}

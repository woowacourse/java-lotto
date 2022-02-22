package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("중복된 숫자로 생성시 예외 발생")
    void incorrect() {
        assertThatThrownBy(() -> new LottoNumbers("2,2,3,4,5,6")).isInstanceOf(IllegalArgumentException.class);
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
}

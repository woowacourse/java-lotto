package model;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {
    @Test
    @DisplayName("숫자 범위 판별 테스트")
    public void validateBoundTest() {
        // 정상 범위
        assertThatNoException().isThrownBy(() -> new LottoNumber(1));
        assertThatNoException().isThrownBy(() -> new LottoNumber(45));
        // 예외 범위
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~45 사이의 숫자를 입력해주세요.");
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~45 사이의 숫자를 입력해주세요.");
    }
}

package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    @DisplayName("유효 범위 내의 로또 숫자 생성 테스트")
    void lottoNumber() {
        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();
        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유효 범위 밖의 로또 숫자 예외처리 테스트")
    void validateLottoNumber() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

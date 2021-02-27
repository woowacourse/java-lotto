package lottogame.domain.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("LottoNumber를 1~45 사이 값으로 복사할 때 테스트")
    void testValidLottoNumberCreate() {
        assertThat(LottoNumber.valueOf(1)).isSameAs(LottoNumber.valueOf(1));
        assertThat(LottoNumber.valueOf(45)).isEqualTo(LottoNumber.valueOf(45));
    }

    @Test
    @DisplayName("LottoNumber를 1~45 이외 값으로 생성하려 했을 때 예외처리")
    void testLottoNumberInvalideException() {
        assertThatThrownBy(() -> LottoNumber.valueOf(0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoNumber.valueOf(46)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("캐싱된 LottoNumber가 새로 생성한 LottoNumber와 동일하게 인식되는지 테스트")
    void testCacheLottoNumber() {
        assertThat(LottoNumber.numbers().contains(LottoNumber.valueOf(1))).isTrue();
        assertThat(LottoNumber.numbers().contains(LottoNumber.valueOf(45))).isTrue();
    }
}

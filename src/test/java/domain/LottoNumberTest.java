package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("생성된 로또 번호가 범위를 벗어날 시 에러를 발생한다.")
    void checkLottoNumberRange() {
        assertThatThrownBy(
                () -> LottoNumber.valueOf(46)
        ).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 번호는 1~45 사이로 입력해주세요.");
    }
}

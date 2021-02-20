package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {

    @DisplayName("로또 번호가 1에서 45 사이인지 검증 하는지")
    @Test
    void LottoNumber_numberOutOfBounds_throwError() {
        assertThatThrownBy(() -> new LottoNumber(49)).isInstanceOf(IllegalArgumentException.class);
    }
}

package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BonusNumberTest {

    @Test
    void 보너스번호_범위_외_테스트() {
        Assertions.assertThatThrownBy(() -> new BonusNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 1~45 사이의 수를 입력해야 합니다.");
    }

    @Test
    void 보너스번호_최소값_테스트() {
        Assertions.assertThatCode(() -> new BonusNumber(1))
                .doesNotThrowAnyException();
    }

    @Test
    void 보너스번호_최대값_테스트() {
        Assertions.assertThatCode(() -> new BonusNumber(45))
                .doesNotThrowAnyException();
    }

}

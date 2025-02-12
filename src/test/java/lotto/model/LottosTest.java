package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @DisplayName("천원 단위가 아니면 예외가 발생한다.")
    @Test
    void 천원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> Lottos.issue(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("천원 단위로 입력해 주세요.");
    }

}

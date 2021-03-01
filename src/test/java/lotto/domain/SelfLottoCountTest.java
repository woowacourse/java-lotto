package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SelfLottoCountTest {

    @DisplayName("수동 로또 개수에 따른 에러 테스트")
    @Test
    void exception() {
        assertThatThrownBy(() -> new SelfLottoCount(10, 11))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SelfLottoCount(10, -1))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
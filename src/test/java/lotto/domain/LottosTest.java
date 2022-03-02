package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottosTest {

    @Test
    @DisplayName("null 을 입력할 경우")
    void lottos_null() {
        assertThatThrownBy(() -> {
            Lottos lottos = new Lottos(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

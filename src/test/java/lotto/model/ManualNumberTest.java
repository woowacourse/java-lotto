package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.NotNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManualNumberTest {

    @Test
    @DisplayName("입력이 숫자인지 판단")
    void ManualNumber_isNumber() {
        assertThatThrownBy(() -> {
            new ManualNumber("q");
        }).isInstanceOf(NotNumberException.class)
            .hasMessage("숫자를 입력하세요.");
    }
}

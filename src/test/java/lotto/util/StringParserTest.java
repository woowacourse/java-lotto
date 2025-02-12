package lotto.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringParserTest {

    @Test
    void 소수이면_예외가_발생한다() {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> StringParser.parseInt("100.3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 21억 이하의 양수여야 합니다.");
    }


    @Test
    void 문자열이면_예외가_발생한다() {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> StringParser.parseInt("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 21억 이하의 양수여야 합니다.");
    }

    @Test
    void Integer_범위가_아니면_예외가_발생한다() {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> StringParser.parseInt("2200000000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 21억 이하의 양수여야 합니다.");
    }
}

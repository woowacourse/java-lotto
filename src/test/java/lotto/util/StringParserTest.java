package lotto.util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringParserTest {

    @Test
    @DisplayName("입력값을 숫자로 파싱한다.")
    void 입력값을_숫자로_파싱한다() {
        // Given
        final String input = "11";

        // When
        int number = StringParser.parseInt(input);

        // Then
        Assertions.assertThat(number).isEqualTo(11);
    }

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

    @Test
    void 문자열_토큰_여러_개를_Integer_리스트로_파싱한다() {
        // Given
        final String[] tokens = {" 5", " 10"};

        // When
        final List<Integer> parsedTokens = StringParser.parseTokens(tokens);

        // Then
        Assertions.assertThat(parsedTokens).isEqualTo(List.of(5, 10));
    }
}

package lotto.util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringToIntParserTest {

    @Test
    void 소수이면_예외가_발생한다() {
        // Given
        String decimal = "100.3";

        // When & Then
        Assertions.assertThatThrownBy(() -> StringToIntParser.parseInt(decimal))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 21억 이하의 양수여야 합니다.");
    }


    @Test
    void 문자열이면_예외가_발생한다() {
        // Given
        String input = "abc";

        // When & Then
        Assertions.assertThatThrownBy(() -> StringToIntParser.parseInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 21억 이하의 양수여야 합니다.");
    }

    @Test
    void Integer_범위가_아니면_예외가_발생한다() {
        // Given
        String overValue = "2200000000";

        // When & Then
        Assertions.assertThatThrownBy(() -> StringToIntParser.parseInt(overValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 21억 이하의 양수여야 합니다.");
    }

    @Test
    void 문자열_토큰_여러_개를_Integer_리스트로_파싱한다() {
        // Given
        String[] tokens = {" 5", " 10"};

        // When
        List<Integer> parsedTokens = StringToIntParser.parseTokens(tokens);

        // Then
        Assertions.assertThat(parsedTokens).isEqualTo(List.of(5, 10));
    }
}

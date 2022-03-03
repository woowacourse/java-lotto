package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.utils.WinTicketGenerator;

public class WinTicketGeneratorTest {
    @Test
    @DisplayName("올바른 문자열이면 예외를 반환하지 않아야 합니다.")
    void winTicketGeneratorValidTest() {
        assertThatCode(() -> WinTicketGenerator.generate("1, 2, 3, 4, 5, 6"))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("로또 번호 입력 실패 테스트")
    void winTicketGeneratorInvalidTest(String input, String testName) {
        assertThatThrownBy(() -> WinTicketGenerator.generate(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
            Arguments.of("1, 2, 3, 4, 5", "5개입력"),
            Arguments.of("1, 2, 3, 4, 5, 6, 7", "7개입력"),
            Arguments.of("1, 2, 3, 4, 5, a", "문자 개입력"),
            Arguments.of("1, 2, 3, 4, 5, 5", "중복 번호 입력"),
            Arguments.of("1, 2, 3, 4, 5, 6, ", "마지막에 구분자 추가"),
            Arguments.of(null, "널 입력"));
    }
}

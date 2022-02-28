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
    @DisplayName("문자열로 로또 정상 생성")
    void 입력받은_문자열로_로또번호_정상생성() {
        assertThatCode(() -> WinTicketGenerator.generate("1, 2, 3, 4, 5, 6"))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("로또 번호 입력 테스트")
    void 로또번호입력(String input, String testName) {
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

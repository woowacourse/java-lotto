package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

    @DisplayName("하나의 문자를 정수로 변환한다.")
    @Test
    void 하나의_문자를_정수로_변환한다() {

        String input = "3";
        int parsedInput = Parser.parseToInteger(input);

        assertThat(parsedInput).isEqualTo(3);
    }

    @DisplayName("잘못된 숫자 입력시 예외를 발생한다.")
    @Test
    void 잘못된_숫자_입력시_에외를_발생한다() {

        String input = "aaaa";
        assertThatThrownBy(() -> Parser.parseToInteger(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바르지 않은 입력값입니다.");
    }

    @DisplayName("문자들을 정수들로 변환한다.")
    @Test
    void 문자들을_정수들로_반환한다() {

        List<String> input = List.of("1", "2", "3", "4");
        List<Integer> integers = Parser.parseToIntegers(input);

        assertThat(integers).hasSize(4);
        assertThat(integers).containsExactly(1, 2, 3, 4);
    }

    @DisplayName("잘못된 숫자열을 입력시 예외를 발생한다.")
    @Test
    void 잘못된_숫자열을_입력시_예외를_발생한다() {
        List<String> input = List.of("1", "a", "3", "4");

        assertThatThrownBy(() -> Parser.parseToIntegers(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바르지 않은 입력값입니다.");
    }

}

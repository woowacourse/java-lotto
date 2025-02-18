package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SplitterTest {

    @DisplayName("콤마를 기준으로 문자열을 나눈다")
    @Test
    void 콤마를_기준으로_문자열을_나눈다() {
        String input = "1,2,3,4,5,6";
        List<String> strings = Splitter.splitByComma(input);

        assertThat(strings).hasSize(6);
        assertThat(strings).containsExactly("1", "2", "3", "4", "5", "6");
    }

    @DisplayName("잘못된 구분자 입력시 예외를 발생한다.")
    @Test
    void 잘못된_구분자_입력시_예외를_발생한다() {
        String input = "1:2:3:4:5:6";

        assertThatThrownBy(() -> Splitter.splitByComma(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바르지 않은 입력값입니다.");
    }

    @DisplayName("숫자가 아닌 값 입력시 예외를 발생한다.")
    @Test
    void 숫자가_아닌_값_입력시_예외를_발생한다() {
        String input = "1,2,3,4,5,kk";

        assertThatThrownBy(() -> Splitter.splitByComma(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바르지 않은 입력값입니다.");
    }
}

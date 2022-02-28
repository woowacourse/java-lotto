package lottoTest.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.view.InputView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@SuppressWarnings("NonAsciiCharacters")
class InputViewTest {

    @ParameterizedTest(name = "[{index}] List 인덱스: {0}, 기대값: {1}")
    @CsvSource(value = {"0:1", "1:2", "2:3", "3:4", "4:5", "5:6"}, delimiter = ':')
    void 당첨번호_입력_구분자_테스트(int input, Integer expected) {
        List<Integer> result = InputView.splitNumbers("1, 2, 3, 4, 5, 6");
        Integer actualValue = result.get(input);
        assertThat(expected).isEqualTo(actualValue);
    }

    @Test
    void 입력이_숫자가_아닌_경우_테스트() {
        assertThatThrownBy(() -> InputView.toInteger("돈"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("숫자 형태로 입력해야 합니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 입력이_null_혹은_빈_문자열인_경우_테스트(String input) {
        assertThatThrownBy(() -> InputView.validateIsNullOrEmpty(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("입력 null 혹은 빈 문자열 일수 없습니다.");
    }
}

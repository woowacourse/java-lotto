package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.InputView;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class InputViewTest {

    @ParameterizedTest(name = "[{index}] List 인덱스: {0}, 기대값: {1}")
    @CsvSource(value = {"0:1", "1:2", "2:3", "3:4", "4:5", "5:6"}, delimiter = ':')
    void 당첨번호_입력_구분자_테스트(int input, Integer expected) {
        List<Integer> result = InputView.splitNumbers("1, 2, 3, 4, 5, 6");
        Integer actualValue = result.get(input);
        assertThat(expected).isEqualTo(actualValue);
    }
}

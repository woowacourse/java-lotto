package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static lotto.constant.Limit.LOTTO_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {
    @DisplayName("당첨 번호가 고유한 6개의 번호가 아니라면 예외를 던진다")
    @MethodSource("generateData")
    @ParameterizedTest(name = "{index} : {1}")
    void 당첨_번호가_고유한_6개의_번호가_아니라면_예외를_던진다(List<Integer> inputWinningNumbers, String testName) {
        assertThatThrownBy(() -> new WinningNumbers(inputWinningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_SIZE.getValue() + "개의 고유한 번호를 입력해야 합니다.");
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 5), "당첨 번호가 고유한 번호가 아니라면 예외를 던진다"),
                Arguments.of(List.of(1, 2, 3, 4, 5), "당첨 번호가 6개의 번호가 아니라면 예외를 던진다")
        );
    }

    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외를 던진다")
    @Test
    void 당첨_번호와_보너스_번호가_중복되면_예외를_던진다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> winningNumbers.validateBonusNumberDuplicated(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
    }

    @DisplayName("로또 번호와 당첨 번호를 비교하여 일치하는 개수를 반환한다")
    @Test
    void 로또_번호와_당첨_번호를_비교하여_일치하는_개수를_반환한다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThat(winningNumbers.calculateMatchCount(List.of(1, 2, 3, 4, 5, 7))).isEqualTo(5);
    }
}

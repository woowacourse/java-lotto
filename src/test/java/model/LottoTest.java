package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    static Stream<List<Integer>> outOfRangeNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5, 46),
                List.of(-1, 2, 3, 4, 5, 6),
                List.of(-1, -2, -3, -4, -5, -6)
        );
    }

    @ParameterizedTest
    @MethodSource("outOfRangeNumbers")
    @DisplayName("번호가 범위를 넘어가면 예외가 발생한다")
    void 번호가_범위를_넘어가면_예외가_발생한다(List<Integer> outOfRangeNumbers) {
        assertThatThrownBy(() -> new Lotto(new TestLottoNumberGenerator(outOfRangeNumbers)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 중복되면 예외가 발생한다.")
    void 당첨_번호가_중복되면_예외가_발생한다() {
        List<Integer> inputNumbers = List.of(1, 1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(inputNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호의 수가 6개가 아니면 예외가 발생한다.")
    void 당첨_번호의_수가_6개가_아니면_예외가_발생한다() {
        List<Integer> inputNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> new Lotto(inputNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("번호는 6개의 숫자여야 합니다.");
    }

}

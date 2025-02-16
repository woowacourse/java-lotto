package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Test
    @DisplayName("당첨 번호가 중복되면 예외가 발생한다.")
    void 당첨_번호가_중복되면_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(new HashSet<>(winningNumbers)))
                .isInstanceOf(IllegalArgumentException.class)
                .withFailMessage("번호는 중복없이 6개의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다.")
    void 당첨_번호가_6개가_아니면_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4);
        assertThatThrownBy(() -> new Lotto(new HashSet<>(winningNumbers)))
                .isInstanceOf(IllegalArgumentException.class)
                .withFailMessage("번호는 중복없이 6개의 숫자여야 합니다.");
    }

    static Stream<Set<Integer>> outOfRangeNumbers() {
        return Stream.of(
                Set.of(1, 2, 3, 4, 5, 46),
                Set.of(-1, 2, 3, 4, 5, 6),
                Set.of(-1, -2, -3, -4, -5, -6)
        );
    }

    @ParameterizedTest
    @MethodSource("outOfRangeNumbers")
    @DisplayName("당첨 번호가 범위를 넘어가면 예외가 발생한다")
    void 당첨_번호가_범위를_넘어가면_예외가_발생한다(Set<Integer> outOfRangeNumbers) {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 55, 66);
        assertThatThrownBy(() -> new Lotto(outOfRangeNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1~45 사이여야 합니다.");
    }


}
